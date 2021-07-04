package by.ruslan.web.exception;

/**
 * The {@code EventResultException} class represents event result exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class EventResultException extends Exception{
    private String errorMessage;

    public EventResultException(){
    }

    public EventResultException(String message){
        super(message);
    }

    public EventResultException(String message, Throwable cause){
        super(message, cause);
    }

    public EventResultException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
