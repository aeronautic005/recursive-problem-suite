public class RecursiveMathEngine {

    /**
     * Pure recursive calculation of factorial: n! = n * (n-1)!
     */
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * Pure recursive calculation of base^exp using exponent reduction.
     */
    public static long power(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        return base * power(base, exp - 1);
    }
}
