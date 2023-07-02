package exceptions;

public class PhoneNotFoundException extends Throwable {
    public PhoneNotFoundException(String message) {
        super(message);
    }
}
