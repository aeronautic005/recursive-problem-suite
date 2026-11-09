public class RecursionLimitExceededException extends Exception {
    public RecursionLimitExceededException() {
        super("Recursion depth limit exceeded to prevent StackOverflowError.");
    }

    public RecursionLimitExceededException(String message) {
        super(message);
    }
}
