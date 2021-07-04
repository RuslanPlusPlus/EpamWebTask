package by.ruslan.web.exception;

/**
 * The {@code MemberException} class represents member exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class MemberException extends Exception{
    private String errorMessage;

    public MemberException(){
    }

    public MemberException(String message){
        super(message);
    }

    public MemberException(String message, Throwable cause){
        super(message, cause);
    }

    public MemberException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
