package by.ruslan.web.exception;

/**
 * The {@code EventException} class represents event exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class EventException extends Exception{

    private String errorMessage;

    public EventException(){
    }

    public EventException(String message){
        super(message);
    }

    public EventException(String message, Throwable cause){
        super(message, cause);
    }

    public EventException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
