public class RecursionLimitExceededException extends Exception {
    private static final long serialVersionUID = 202L;

    private int requestedDepth;
    private int maxPermissibleDepth;

    public RecursionLimitExceededException() {
        super("Recursion depth limit exceeded to prevent StackOverflowError.");
        this.requestedDepth = 0;
        this.maxPermissibleDepth = 0;
    }

    public RecursionLimitExceededException(String message) {
        super(message);
        this.requestedDepth = 0;
        this.maxPermissibleDepth = 0;
    }

    public RecursionLimitExceededException(int requestedDepth, int maxPermissibleDepth, String operation) {
        super(String.format("Recursion depth [%d] exceeded maximum safe limit [%d] during '%s' to safeguard call stack.",
            requestedDepth, maxPermissibleDepth, operation));
        this.requestedDepth = requestedDepth;
        this.maxPermissibleDepth = maxPermissibleDepth;
    }

    public int getRequestedDepth() {
        return requestedDepth;
    }

    public int getMaxPermissibleDepth() {
        return maxPermissibleDepth;
    }
}
