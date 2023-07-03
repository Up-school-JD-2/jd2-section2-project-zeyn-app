package exceptions;

public class NotFoundApplicationException extends Exception {
    public NotFoundApplicationException(String message){
        super(message);
    }
}
