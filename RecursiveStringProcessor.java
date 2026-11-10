import java.util.ArrayList;
import java.util.List;

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

    /**
     * Recursively counts occurrences of a specific character in a string.
     *
     * @param str input string
     * @param target character to search for
     * @param caseSensitive whether to match case exactly
     * @return count of occurrences
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static int countOccurrences(String str, char target, boolean caseSensitive)
            throws RecursionLimitExceededException {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        validateStringDepth(str, MAX_RECURSION_STRING_LENGTH, "countOccurrences");
        return countOccurrencesInternal(str, target, caseSensitive, 0);
    }

    private static int countOccurrencesInternal(String str, char target, boolean caseSensitive, int index) {
        if (index >= str.length()) {
            return 0;
        }
        char current = str.charAt(index);
        boolean matches = caseSensitive ? (current == target)
                : (Character.toLowerCase(current) == Character.toLowerCase(target));
        int matchCount = matches ? 1 : 0;
        return matchCount + countOccurrencesInternal(str, target, caseSensitive, index + 1);
    }

    /**
     * Recursively counts total vowel characters (a, e, i, o, u) in a string.
     *
     * @param str input string
     * @return count of vowels
     * @throws RecursionLimitExceededException if length exceeds safe limit
     */
    public static int countVowels(String str) throws RecursionLimitExceededException {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        validateStringDepth(str, MAX_RECURSION_STRING_LENGTH, "countVowels");
        return countVowelsInternal(str, 0);
    }

    private static int countVowelsInternal(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        char c = Character.toLowerCase(str.charAt(index));
        int isVowel = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return isVowel + countVowelsInternal(str, index + 1);
    }

    /**
     * Pure recursive substring search to verify if pattern occurs within text.
     *
     * @param text string to search within
     * @param pattern substring pattern to find
     * @return true if pattern exists in text, false otherwise
     * @throws RecursionLimitExceededException if text length exceeds safe limit
     */
    public static boolean containsSubstring(String text, String pattern)
            throws RecursionLimitExceededException {
        return indexOf(text, pattern) != -1;
    }

    /**
     * Recursively finds the first index of pattern in text, or -1 if not found.
     *
     * @param text string to search within
     * @param pattern substring pattern to find
     * @return 0-based start index of pattern, or -1
     * @throws RecursionLimitExceededException if text length exceeds safe limit
     */
    public static int indexOf(String text, String pattern) throws RecursionLimitExceededException {
        if (text == null || pattern == null) {
            return -1;
        }
        if (pattern.isEmpty()) {
            return 0;
        }
        if (pattern.length() > text.length()) {
            return -1;
        }
        validateStringDepth(text, MAX_RECURSION_STRING_LENGTH, "indexOf");
        return findSubstringInternal(text, pattern, 0);
    }

    private static int findSubstringInternal(String text, String pattern, int textIndex) {
        if (textIndex + pattern.length() > text.length()) {
            return -1;
        }
        if (matchesPrefix(text, textIndex, pattern, 0)) {
            return textIndex;
        }
        return findSubstringInternal(text, pattern, textIndex + 1);
    }

    private static boolean matchesPrefix(String text, int textIdx, String pattern, int patternIdx) {
        if (patternIdx >= pattern.length()) {
            return true;
        }
        if (text.charAt(textIdx) != pattern.charAt(patternIdx)) {
            return false;
        }
        return matchesPrefix(text, textIdx + 1, pattern, patternIdx + 1);
    }

    /**
     * Generates all permutations of a string recursively.
     * Input length is capped by MAX_PERMUTATION_STRING_LENGTH to prevent memory exhaustion.
     *
     * @param str input string
     * @return list of distinct and ordered permutations
     * @throws RecursionLimitExceededException if input string exceeds permutation threshold
     */
    public static List<String> generatePermutations(String str) throws RecursionLimitExceededException {
        if (str == null) {
            return new ArrayList<>();
        }
        validateStringDepth(str, MAX_PERMUTATION_STRING_LENGTH, "generatePermutations");
        List<String> results = new ArrayList<>();
        generatePermutationsInternal("", str, results);
        return results;
    }

    private static void generatePermutationsInternal(String prefix, String remaining, List<String> results) {
        int n = remaining.length();
        if (n == 0) {
            results.add(prefix);
            return;
        }
        for (int i = 0; i < n; i++) {
            generatePermutationsInternal(
                prefix + remaining.charAt(i),
                remaining.substring(0, i) + remaining.substring(i + 1, n),
                results
            );
        }
    }

    /**
     * Recursively generates all 2^n subsequences (combinations) of the characters in a string.
     *
     * @param str input string
     * @return list of all subsequences including the empty string
     * @throws RecursionLimitExceededException if string length exceeds safe subsequence limit
     */
    public static List<String> generateSubsequences(String str) throws RecursionLimitExceededException {
        if (str == null) {
            return new ArrayList<>();
        }
        validateStringDepth(str, MAX_SUBSEQUENCE_STRING_LENGTH, "generateSubsequences");
        List<String> results = new ArrayList<>();
        generateSubsequencesInternal(str, 0, "", results);
        return results;
    }

    private static void generateSubsequencesInternal(String str, int index, String current, List<String> results) {
        if (index == str.length()) {
            results.add(current);
            return;
        }
        // Branch 1: Exclude character at current index
        generateSubsequencesInternal(str, index + 1, current, results);
        // Branch 2: Include character at current index
        generateSubsequencesInternal(str, index + 1, current + str.charAt(index), results);
    }
}
