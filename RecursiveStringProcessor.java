public class RecursiveStringProcessor {
    public static final int MAX_RECURSION_STRING_LENGTH = 1000;
    public static final int MAX_PERMUTATION_STRING_LENGTH = 8;
    public static final int MAX_SUBSEQUENCE_STRING_LENGTH = 15;

    /**
     * Recursively reverses a string.
     * Base case: null or length <= 1 returns string as-is.
     * Recursive step: reverse(remainder) + first character.
     *
     * @param str input string to reverse
     * @return reversed string
     */
    public static String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }
}
