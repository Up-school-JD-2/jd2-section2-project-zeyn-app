package exceptions;

public class NoEnoughEmptySpaceException extends Exception{
    public NoEnoughEmptySpaceException(String message){
        super(message);
    }
}
