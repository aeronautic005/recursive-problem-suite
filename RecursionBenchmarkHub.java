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
     * Executes benchmarks across recursive string and combinatorial algorithms.
     *
     * @return list of benchmark results
     */
    public static List<BenchmarkResult> benchmarkStringEngine() {
        List<BenchmarkResult> results = new ArrayList<>();

        // String Reversal Scaling
        int[] reverseLengths = {10, 100, 500};
        for (int len : reverseLengths) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append((char) ('a' + (i % 26)));
            String testStr = sb.toString();

            long start = System.nanoTime();
            try {
                String rev = RecursiveStringProcessor.reverse(testStr);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("ReverseString", "len=" + len, elapsed, len, "Length " + rev.length()));
            } catch (Exception e) {
                results.add(new BenchmarkResult("ReverseString", "len=" + len, 0, 0, "ERROR"));
            }
        }

        // Palindrome Verification
        String[] palTests = {"racecar", "A man, a plan, a canal: Panama"};
        for (String p : palTests) {
            long start = System.nanoTime();
            try {
                boolean isPal = RecursiveStringProcessor.isPalindromeNormalized(p);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("PalindromeNorm", "len=" + p.length(), elapsed, p.length(), String.valueOf(isPal)));
            } catch (Exception e) {
                results.add(new BenchmarkResult("PalindromeNorm", "len=" + p.length(), 0, 0, "ERROR"));
            }
        }

        // Permutations Benchmark (Factorial scaling)
        String[] permInputs = {"ABC", "ABCDE", "ABCDEFG"};
        for (String s : permInputs) {
            long start = System.nanoTime();
            try {
                List<String> perms = RecursiveStringProcessor.generatePermutations(s);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("Permutations", "n=" + s.length(), elapsed, perms.size(), perms.size() + " perms"));
            } catch (Exception e) {
                results.add(new BenchmarkResult("Permutations", "n=" + s.length(), 0, 0, "ERROR"));
            }
        }

        // Subsequences Benchmark (2^n exponential scaling)
        String[] subInputs = {"ABCD", "ABCDEFGH", "ABCDEFGHIJKL"};
        for (String s : subInputs) {
            long start = System.nanoTime();
            try {
                List<String> subs = RecursiveStringProcessor.generateSubsequences(s);
                long elapsed = System.nanoTime() - start;
                results.add(new BenchmarkResult("Subsequences", "n=" + s.length(), elapsed, subs.size(), subs.size() + " subs"));
            } catch (Exception e) {
                results.add(new BenchmarkResult("Subsequences", "n=" + s.length(), 0, 0, "ERROR"));
            }
        }

        return results;
    }

    /**
     * Executes benchmarks across 2D Maze backtracking pathfinding.
     *
     * @return list of benchmark results
     */
    public static List<BenchmarkResult> benchmarkMazeEngine() {
        List<BenchmarkResult> results = new ArrayList<>();

        // Sample 5x5 Maze
        MazeGrid sample = MazeGrid.createSampleMaze();
        long startSample = System.nanoTime();
        try {
            boolean solved = RecursiveMazeSolver.solve(sample);
            long elapsed = System.nanoTime() - startSample;
            results.add(new BenchmarkResult("MazeSolve5x5", "5x5 Grid", elapsed, 25, "Solved=" + solved));
        } catch (Exception e) {
            results.add(new BenchmarkResult("MazeSolve5x5", "5x5 Grid", 0, 0, "ERROR"));
        }

        // Challenging 7x7 Labyrinth
        MazeGrid chal = MazeGrid.createChallengingMaze();
        long startChal = System.nanoTime();
        try {
            boolean solved = RecursiveMazeSolver.solve(chal);
            long elapsed = System.nanoTime() - startChal;
            results.add(new BenchmarkResult("MazeSolve7x7", "7x7 Labyrinth", elapsed, 49, "Solved=" + solved));
        } catch (Exception e) {
            results.add(new BenchmarkResult("MazeSolve7x7", "7x7 Labyrinth", 0, 0, "ERROR"));
        }

        // Unsolvable Exhaustion
        MazeGrid unsolve = MazeGrid.createUnsolvableMaze();
        long startUnsolve = System.nanoTime();
        try {
            boolean solved = RecursiveMazeSolver.solve(unsolve);
            long elapsed = System.nanoTime() - startUnsolve;
            results.add(new BenchmarkResult("MazeUnsolvable", "5x5 Blocked", elapsed, 25, "Solved=" + solved));
        } catch (Exception e) {
            results.add(new BenchmarkResult("MazeUnsolvable", "5x5 Blocked", 0, 0, "ERROR"));
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
