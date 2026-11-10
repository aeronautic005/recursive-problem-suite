public class RecursiveStringProcessor {
    public static final int MAX_RECURSION_STRING_LENGTH = 1000;
    public static final int MAX_PERMUTATION_STRING_LENGTH = 8;
    public static final int MAX_SUBSEQUENCE_STRING_LENGTH = 15;

    /**
     * Validates that the string does not exceed safe recursion limits.
     * Prevents call stack overflow on overly long inputs.
     *
     * @param str string input to validate
     * @param maxLimit maximum allowed string length
     * @param operation name of operation being performed
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static void validateStringDepth(String str, int maxLimit, String operation)
            throws RecursionLimitExceededException {
        if (str != null && str.length() > maxLimit) {
            throw new RecursionLimitExceededException(str.length(), maxLimit, operation);
        }
    }

    /**
     * Recursively reverses a string with stack overflow safeguards.
     * Base case: null or length <= 1 returns string as-is.
     * Recursive step: reverse(remainder) + first character.
     *
     * @param str input string to reverse
     * @return reversed string
     * @throws RecursionLimitExceededException if string length exceeds maximum safe depth
     */
    public static String reverse(String str) throws RecursionLimitExceededException {
        if (str == null) {
            return null;
        }
        validateStringDepth(str, MAX_RECURSION_STRING_LENGTH, "reverse");
        return reverseInternal(str);
    }

    private static String reverseInternal(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return reverseInternal(str.substring(1)) + str.charAt(0);
    }
}
