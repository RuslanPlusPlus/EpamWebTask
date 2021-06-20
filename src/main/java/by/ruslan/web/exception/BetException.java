package by.ruslan.web.exception;

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
