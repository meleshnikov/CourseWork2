package exceptions;

public class InvalidInputDataException extends Exception {

    public InvalidInputDataException() {
    }

    public InvalidInputDataException(String message) {
        super(message);
    }
}
