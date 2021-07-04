package by.ruslan.web.exception;

/**
 * The {@code DAOException} class represents dao exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class DAOException extends Exception{
    public DAOException(){
    }

    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Throwable cause){
        super(message, cause);
    }

    public DAOException(Throwable cause){
        super(cause);
    }
}
