import java.util.ArrayList;
import java.util.List;

/**
 * Performance benchmark suite measuring execution duration and call steps
 * across recursive algorithms.
 */
public class RecursionBenchmarkHub {

    /**
     * Executes benchmarks across all core recursive math functions.
     *
     * @return list of benchmark results
     */
    public static List<BenchmarkResult> benchmarkMathEngine() {
        List<BenchmarkResult> results = new ArrayList<>();

        // Warm up JIT
        try {
            RecursiveMathEngine.factorial(10);
            RecursiveMathEngine.power(2, 10);
        } catch (Exception ignored) {}

        // Factorial Benchmarks
        int[] factorialInputs = {5, 12, 20};
        for (int n : factorialInputs) {
            long start = System.nanoTime();
            try {
                long val = RecursiveMathEngine.factorial(n);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("Factorial", "n=" + n, elapsed, n, String.valueOf(val)));
            } catch (Exception e) {
                results.add(new BenchmarkResult("Factorial", "n=" + n, 0, 0, "ERROR: " + e.getMessage()));
            }
        }

        // Power Benchmarks
        int[][] powerInputs = {{2, 10}, {3, 15}, {2, 50}};
        for (int[] pair : powerInputs) {
            int b = pair[0];
            int e = pair[1];
            long start = System.nanoTime();
            try {
                long val = RecursiveMathEngine.power(b, e);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("Power", b + "^" + e, elapsed, e, String.valueOf(val)));
            } catch (Exception ex) {
                results.add(new BenchmarkResult("Power", b + "^" + e, 0, 0, "ERROR: " + ex.getMessage()));
            }
        }

        // Sum of Digits Benchmarks
        long[] digitInputs = {99999L, 1234567890123L};
        for (long num : digitInputs) {
            long start = System.nanoTime();
            int sum = RecursiveMathEngine.sumOfDigits(num);
            long elapsed = System.nanoTime() - start;
            int digits = String.valueOf(Math.abs(num)).length();
            results.add(new BenchmarkResult("SumOfDigits", "num=" + num, elapsed, digits, String.valueOf(sum)));
        }

        // Pell Benchmarks
        int[] pellInputs = {5, 12, 22};
        for (int p : pellInputs) {
            long start = System.nanoTime();
            try {
                long val = RecursiveMathEngine.calculatePell(p);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("PellSequence", "P(" + p + ")", elapsed, p, String.valueOf(val)));
            } catch (Exception ex) {
                results.add(new BenchmarkResult("PellSequence", "P(" + p + ")", 0, 0, "ERROR: " + ex.getMessage()));
            }
        }

        return results;
    }

    /**
     * Formats benchmark metrics into an ASCII table report.
     *
     * @param results collection of benchmark measurements
     * @return formatted report table
     */
    public static String formatBenchmarkReport(String suiteTitle, List<BenchmarkResult> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================================================\n");
        sb.append(String.format("                   BENCHMARK REPORT: %-30s%n", suiteTitle));
        sb.append("========================================================================================\n");
        sb.append(String.format("| %-22s | %-16s | %13s | %10s | %-15s |%n",
                "Operation", "Input", "Elapsed", "Steps/Depth", "Output"));
        sb.append("|------------------------+------------------+---------------+------------+-----------------|\n");
        for (BenchmarkResult res : results) {
            sb.append(res.formatRow()).append("\n");
        }
        sb.append("========================================================================================\n");
        return sb.toString();
    }
}
