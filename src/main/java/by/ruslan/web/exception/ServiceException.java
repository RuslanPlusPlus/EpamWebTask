package by.ruslan.web.exception;

/**
 * The {@code ServiceException} class represents service exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class ServiceException extends Exception{
    public ServiceException(){
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }
}
