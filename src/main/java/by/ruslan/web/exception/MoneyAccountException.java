package by.ruslan.web.exception;

/**
 * The {@code MoneyAccountException} class represents money account exception.
 *
 * @author Ruslan Nedvedskiy
 */

public class MoneyAccountException extends Exception{
    private String errorMessage;

    public MoneyAccountException(){
    }

    public MoneyAccountException(String message){
        super(message);
    }

    public MoneyAccountException(String message, Throwable cause){
        super(message, cause);
    }

    public MoneyAccountException(Throwable cause){
        super(cause);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
