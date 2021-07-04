package by.ruslan.web.exception;

/**
 * The {@code BetException} class represents bet exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class BetException extends Exception{

    private String errorMessage;

    public BetException(){
    }

    public BetException(String message){
        super(message);
    }

    public BetException(String message, Throwable cause){
        super(message, cause);
    }

    public BetException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
