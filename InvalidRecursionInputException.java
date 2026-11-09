public class InvalidRecursionInputException extends Exception {
    private static final long serialVersionUID = 201L;

    private String paramName;
    private long invalidValue;

    public InvalidRecursionInputException() {
        super("Input value is invalid for recursive calculation.");
        this.paramName = "Unknown";
        this.invalidValue = 0;
    }

    public InvalidRecursionInputException(String message) {
        super(message);
        this.paramName = "Unknown";
        this.invalidValue = 0;
    }

    public InvalidRecursionInputException(String paramName, long invalidValue, String message) {
        super(String.format("Invalid recursion argument '%s' = %d: %s", paramName, invalidValue, message));
        this.paramName = paramName;
        this.invalidValue = invalidValue;
    }

    public String getParamName() {
        return paramName;
    }

    public long getInvalidValue() {
        return invalidValue;
    }
}
