package by.ruslan.web.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String PROPERTIES_PATH = "database.properties";
    private static final String DATABASE_URL;
    private static final String DATABASE_USERNAME;
    private static final String DATABASE_PASSWORD;
    static {
        try {
            ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTIES_PATH);
            properties.load(inputStream);
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            logger.fatal(e.getMessage());
            throw new RuntimeException("Failed to register database driver ", e);
        }
        DATABASE_URL = (String) properties.get("db.url");
        DATABASE_USERNAME = (String) properties.get("username");
        DATABASE_PASSWORD = (String) properties.get("password");

    }
    private ConnectionFactory(){}

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
