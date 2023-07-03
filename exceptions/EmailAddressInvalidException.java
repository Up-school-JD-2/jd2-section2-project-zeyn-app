package exceptions;

public class EmailAddressInvalidException extends Throwable {
    public EmailAddressInvalidException(String s) {
        super(s);
    }
}
