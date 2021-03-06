package by.ruslan.web.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPool} class represents pool of database connections
 *
 * @author Ruslan Nedvedskiy
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 8;
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock(true);
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> takenConnections;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    /**
     * Get instance of this class
     *
     * @return {@link ConnectionPool} instance
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()){
            lock.lock();
            if (instance == null){
                instance = new ConnectionPool();
                isCreated.set(true);
            }
            lock.unlock();
        }
        return instance;
    }

    /**
     * Initialize connection pool
     *
     * @throws RuntimeException if no connection created
     */
    private ConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        takenConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                Connection connection = ConnectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.error("Failed to create connection",e);
            }
        }
        if (freeConnections.size() == 0){
            logger.fatal("No one connection is created");
            throw new RuntimeException("No one connection is created");
        }
    }

    /**
     * Get a connection from the connection pool
     *
     * @return {@link Connection} connection to the database
     */
    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            takenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Returns the connection to the connection pool
     * @param connection {@link Connection} connection to the database
     */
    public void releaseConnection(Connection connection){
        if (connection.getClass() == ProxyConnection.class){
            if (takenConnections.remove(connection)){
                try {
                    freeConnections.put((ProxyConnection) connection);
                } catch (InterruptedException e) {
                    logger.error("Failed to return connection " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Destroy connection pool
     */
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
        deregisterDrivers();
    }

    /**
     * Deregister drivers
     */
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Can not deregister driver: " + e.getMessage());
            }
        });
    }
}
