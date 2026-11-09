public class RecursiveMathEngine {
    public static final int MAX_FACTORIAL_LIMIT = 20; // 21! overflows 64-bit signed long
    public static final int MAX_POWER_EXPONENT = 60;
    public static final int MAX_PELL_LIMIT = 40;     // Beyond 40 naive recursion causes stack explosion

    /**
     * Recursive calculation of factorial: n! = n * (n-1)!
     * Validates input bounds and overflow risks.
     */
    public static long factorial(int n) throws InvalidRecursionInputException, RecursionLimitExceededException {
        if (n < 0) {
            throw new InvalidRecursionInputException("n", n, "Factorial is undefined for negative numbers.");
        }
        if (n > MAX_FACTORIAL_LIMIT) {
            throw new RecursionLimitExceededException(n, MAX_FACTORIAL_LIMIT, "factorial");
        }
        return computeFactorialInternal(n);
    }

    private static long computeFactorialInternal(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * computeFactorialInternal(n - 1);
    }

    /**
     * Recursive calculation of base^exp with input boundary guards.
     */
    public static long power(int base, int exp) throws InvalidRecursionInputException, RecursionLimitExceededException {
        if (exp < 0) {
            throw new InvalidRecursionInputException("exp", exp, "Negative exponents not supported for integer power.");
        }
        if (exp > MAX_POWER_EXPONENT) {
            throw new RecursionLimitExceededException(exp, MAX_POWER_EXPONENT, "power");
        }
        return computePowerInternal(base, exp);
    }

    private static long computePowerInternal(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        return base * computePowerInternal(base, exp - 1);
    }

    /**
     * Recursively calculates the sum of all digits of an integer:
     * sumOfDigits(1234) = 4 + sumOfDigits(123) = 4 + 3 + 2 + 1 = 10.
     */
    public static int sumOfDigits(long number) {
        long absNum = Math.abs(number);
        if (absNum < 10) {
            return (int) absNum;
        }
        return (int) (absNum % 10) + sumOfDigits(absNum / 10);
    }

    /**
     * Recursive calculation of Pell numbers:
     * P(0) = 0, P(1) = 1, P(n) = 2*P(n-1) + P(n-2)
     */
    public static long calculatePell(int n) throws InvalidRecursionInputException, RecursionLimitExceededException {
        if (n < 0) {
            throw new InvalidRecursionInputException("n", n, "Pell sequence index cannot be negative.");
        }
        if (n > MAX_PELL_LIMIT) {
            throw new RecursionLimitExceededException(n, MAX_PELL_LIMIT, "calculatePell");
        }
        return computePellInternal(n);
    }

    private static long computePellInternal(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return 2 * computePellInternal(n - 1) + computePellInternal(n - 2);
    }
}
