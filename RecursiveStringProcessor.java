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

    /**
     * Standard recursive palindrome check ignoring case and non-alphanumeric symbols.
     *
     * @param str input string
     * @return true if palindrome, false otherwise
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static boolean isPalindrome(String str) throws RecursionLimitExceededException {
        return isPalindromeNormalized(str);
    }

    /**
     * Recursively checks if a string is a palindrome strictly (exact character match).
     *
     * @param str input string
     * @return true if palindrome strictly
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static boolean isPalindromeStrict(String str) throws RecursionLimitExceededException {
        if (str == null) return false;
        validateStringDepth(str, MAX_RECURSION_STRING_LENGTH, "isPalindromeStrict");
        return checkPalindromeStrictInternal(str, 0, str.length() - 1);
    }

    private static boolean checkPalindromeStrictInternal(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        return checkPalindromeStrictInternal(str, left + 1, right - 1);
    }

    /**
     * Recursively checks if a string is a palindrome normalized
     * (skipping non-alphanumeric characters and case differences).
     *
     * @param str input string
     * @return true if normalized palindrome
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static boolean isPalindromeNormalized(String str) throws RecursionLimitExceededException {
        if (str == null) return false;
        validateStringDepth(str, MAX_RECURSION_STRING_LENGTH, "isPalindromeNormalized");
        return checkPalindromeNormalizedInternal(str, 0, str.length() - 1);
    }

    private static boolean checkPalindromeNormalizedInternal(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        char leftChar = str.charAt(left);
        char rightChar = str.charAt(right);

        if (!Character.isLetterOrDigit(leftChar)) {
            return checkPalindromeNormalizedInternal(str, left + 1, right);
        }
        if (!Character.isLetterOrDigit(rightChar)) {
            return checkPalindromeNormalizedInternal(str, left, right - 1);
        }
        if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
            return false;
        }
        return checkPalindromeNormalizedInternal(str, left + 1, right - 1);
    }
}
