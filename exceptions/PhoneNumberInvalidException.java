package exceptions;

public class PhoneNumberInvalidException extends Throwable{
    public PhoneNumberInvalidException(String message){
        super(message);
    }
}
