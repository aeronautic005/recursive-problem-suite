public class InvalidRecursionInputException extends Exception {
    public InvalidRecursionInputException() {
        super("Input value is invalid for recursive calculation.");
    }

    public InvalidRecursionInputException(String message) {
        super(message);
    }
}
