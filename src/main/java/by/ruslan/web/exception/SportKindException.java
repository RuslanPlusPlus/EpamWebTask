package by.ruslan.web.exception;

/**
 * The {@code SportKindException} class represents sport kind exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class SportKindException extends Exception{
    private String errorMessage;

    public SportKindException(){
    }

    public SportKindException(String message){
        super(message);
    }

    public SportKindException(String message, Throwable cause){
        super(message, cause);
    }

    public SportKindException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
