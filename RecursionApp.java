import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecursionApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================================");
        System.out.println("    RECURSIVE ALGORITHM SUITE & MAZE SOLVER     ");
        System.out.println("      [MILESTONES 1, 2 & 3 - PATHFINDER]         ");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-6): ");
            int choice = readIntChoice();

            switch (choice) {
                case 1:
                    runInteractiveMathMenu();
                    break;
                case 2:
                    runInteractiveStringMenu();
                    break;
                case 3:
                    runInteractiveMazeMenu();
                    break;
                case 4:
                    runMilestoneOneAutomatedTests();
                    break;
                case 5:
                    runMilestoneTwoAutomatedTests();
                    break;
                case 6:
                    running = false;
                    System.out.println("\nExiting Recursive Suite. Happy problem solving!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Select 1 through 6.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("----------------- MAIN MENU ---------------------");
        System.out.println("1. Interactive Recursive Math Calculator");
        System.out.println("2. Interactive Recursive String & Palindrome Engine");
        System.out.println("3. Interactive 2D Maze Recursive Pathfinder");
        System.out.println("4. Run Milestone 1 Tests (Math Engine)");
        System.out.println("5. Run Milestone 2 Tests (String Engine)");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------------------");
    }

    private static void runInteractiveMathMenu() {
        System.out.println("\n--- RECURSIVE MATH OPERATIONS ---");
        System.out.println("1. Factorial (n!)");
        System.out.println("2. Power (base ^ exp)");
        System.out.println("3. Sum of Digits");
        System.out.println("4. Pell Number Sequence P(n)");
        System.out.print("Select operation (1-4): ");
        int op = readIntChoice();

        try {
            switch (op) {
                case 1:
                    System.out.print("Enter integer n (0 - 20): ");
                    int n = readIntChoice();
                    long fact = RecursiveMathEngine.factorial(n);
                    System.out.printf("[RESULT] %d! = %d%n", n, fact);
                    break;
                case 2:
                    System.out.print("Enter base: ");
                    int base = readIntChoice();
                    System.out.print("Enter exponent (0 - 60): ");
                    int exp = readIntChoice();
                    long pow = RecursiveMathEngine.power(base, exp);
                    System.out.printf("[RESULT] %d ^ %d = %d%n", base, exp, pow);
                    break;
                case 3:
                    System.out.print("Enter number to sum digits: ");
                    long num = scanner.nextLong();
                    scanner.nextLine();
                    int sum = RecursiveMathEngine.sumOfDigits(num);
                    System.out.printf("[RESULT] Sum of digits of %d = %d%n", num, sum);
                    break;
                case 4:
                    System.out.print("Enter Pell index n (0 - 40): ");
                    int pellIdx = readIntChoice();
                    long pellVal = RecursiveMathEngine.calculatePell(pellIdx);
                    System.out.printf("[RESULT] Pell P(%d) = %d%n", pellIdx, pellVal);
                    break;
                default:
                    System.out.println("[ERROR] Invalid operation.");
            }
        } catch (InvalidRecursionInputException e) {
            System.out.println("\n[CAUGHT InvalidRecursionInputException]: " + e.getMessage());
        } catch (RecursionLimitExceededException e) {
            System.out.println("\n[CAUGHT RecursionLimitExceededException]: " + e.getMessage());
            System.out.printf("  Call Depth: %d | Max Safe Permissible: %d%n",
                    e.getRequestedDepth(), e.getMaxPermissibleDepth());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("[ERROR] Invalid numeric input.");
        }
    }

    private static void runInteractiveStringMenu() {
        System.out.println("\n--- RECURSIVE STRING & PALINDROME OPERATIONS ---");
        System.out.println("1. Reverse String");
        System.out.println("2. Palindrome Check (Strict & Normalized)");
        System.out.println("3. Count Specific Character Occurrences");
        System.out.println("4. Count Total Vowels");
        System.out.println("5. Recursive Substring Search");
        System.out.println("6. Generate All String Permutations");
        System.out.println("7. Generate All Subsequences (Combinations)");
        System.out.print("Select operation (1-7): ");
        int op = readIntChoice();

        try {
            switch (op) {
                case 1:
                    System.out.print("Enter string to reverse: ");
                    String sRev = scanner.nextLine();
                    String reversed = RecursiveStringProcessor.reverse(sRev);
                    System.out.printf("[RESULT] Reversed: \"%s\"%n", reversed);
                    break;
                case 2:
                    System.out.print("Enter text to verify palindrome: ");
                    String sPal = scanner.nextLine();
                    boolean strict = RecursiveStringProcessor.isPalindromeStrict(sPal);
                    boolean norm = RecursiveStringProcessor.isPalindromeNormalized(sPal);
                    System.out.printf("[RESULT] Strict Palindrome: %b | Normalized Palindrome: %b%n", strict, norm);
                    break;
                case 3:
                    System.out.print("Enter text: ");
                    String sCount = scanner.nextLine();
                    System.out.print("Enter character to count: ");
                    String charInput = scanner.nextLine();
                    if (!charInput.isEmpty()) {
                        char targetChar = charInput.charAt(0);
                        int occ = RecursiveStringProcessor.countOccurrences(sCount, targetChar, false);
                        System.out.printf("[RESULT] Occurrences of '%c' (case-insensitive): %d%n", targetChar, occ);
                    }
                    break;
                case 4:
                    System.out.print("Enter text to count vowels: ");
                    String sVowel = scanner.nextLine();
                    int vowels = RecursiveStringProcessor.countVowels(sVowel);
                    System.out.printf("[RESULT] Total vowels: %d%n", vowels);
                    break;
                case 5:
                    System.out.print("Enter main text: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter substring pattern to search: ");
                    String pattern = scanner.nextLine();
                    int idx = RecursiveStringProcessor.indexOf(text, pattern);
                    System.out.printf("[RESULT] Pattern %s in text (first index: %d)%n",
                            (idx != -1 ? "FOUND" : "NOT FOUND"), idx);
                    break;
                case 6:
                    System.out.print("Enter short string for permutations (<= 8 chars): ");
                    String sPerm = scanner.nextLine();
                    List<String> perms = RecursiveStringProcessor.generatePermutations(sPerm);
                    System.out.printf("[RESULT] Total permutations: %d%n", perms.size());
                    System.out.println("  Permutations: " + perms);
                    break;
                case 7:
                    System.out.print("Enter short string for subsequences (<= 15 chars): ");
                    String sSub = scanner.nextLine();
                    List<String> subs = RecursiveStringProcessor.generateSubsequences(sSub);
                    System.out.printf("[RESULT] Total subsequences (2^n): %d%n", subs.size());
                    System.out.println("  Subsequences: " + subs);
                    break;
                default:
                    System.out.println("[ERROR] Invalid operation.");
            }
        } catch (RecursionLimitExceededException e) {
            System.out.println("\n[CAUGHT RecursionLimitExceededException]: " + e.getMessage());
            System.out.printf("  Depth: %d | Limit: %d%n", e.getRequestedDepth(), e.getMaxPermissibleDepth());
        }
    }

    private static void runInteractiveMazeMenu() {
        System.out.println("\n--- 2D MAZE RECURSIVE BACKTRACKING PATHFINDER ---");
        System.out.println("1. Solve Sample 5x5 Maze");
        System.out.println("2. Solve Challenging 7x7 Labyrinth");
        System.out.println("3. Test Unsolvable Enclosed Maze");
        System.out.println("4. Trace Step-by-Step Coordinates");
        System.out.println("5. Count Total Distinct Feasible Paths");
        System.out.print("Select operation (1-5): ");
        int op = readIntChoice();

        try {
            MazeGrid maze;
            switch (op) {
                case 1:
                    maze = MazeGrid.createSampleMaze();
                    System.out.println("\n[INITIAL MAZE]:\n" + maze.render());
                    boolean solvedSample = RecursiveMazeSolver.solve(maze);
                    System.out.println("[SOLVED RESULT]: " + (solvedSample ? "PATH FOUND (* = path, x = visited/backtracked)" : "NO PATH FOUND"));
                    System.out.println(maze.render());
                    break;
                case 2:
                    maze = MazeGrid.createChallengingMaze();
                    System.out.println("\n[INITIAL LABYRINTH]:\n" + maze.render());
                    boolean solvedChallenging = RecursiveMazeSolver.solve(maze);
                    System.out.println("[SOLVED RESULT]: " + (solvedChallenging ? "PATH FOUND (* = path, x = visited/backtracked)" : "NO PATH FOUND"));
                    System.out.println(maze.render());
                    break;
                case 3:
                    maze = MazeGrid.createUnsolvableMaze();
                    System.out.println("\n[INITIAL UNSOLVABLE MAZE]:\n" + maze.render());
                    boolean solvedUnsolvable = RecursiveMazeSolver.solve(maze);
                    System.out.println("[SOLVED RESULT]: " + (solvedUnsolvable ? "PATH FOUND" : "NO PATH FOUND (Correctly verified by backtracking!)"));
                    System.out.println(maze.render());
                    break;
                case 4:
                    maze = MazeGrid.createSampleMaze();
                    List<MazeCoordinate> path = RecursiveMazeSolver.findPath(maze);
                    System.out.println("\n[START TO EXIT COORDINATES]:");
                    if (path.isEmpty()) {
                        System.out.println("No coordinates found.");
                    } else {
                        for (int i = 0; i < path.size(); i++) {
                            System.out.printf("  Step %2d: %s%n", (i + 1), path.get(i));
                        }
                    }
                    break;
                case 5:
                    maze = MazeGrid.createSampleMaze();
                    int totalPaths = RecursiveMazeSolver.countAllPaths(maze);
                    System.out.printf("\n[RESULT] Total distinct non-revisiting paths to exit: %d%n", totalPaths);
                    break;
                default:
                    System.out.println("[ERROR] Invalid operation.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR during maze solving]: " + e.getMessage());
        }
    }

    private static void runMilestoneOneAutomatedTests() {
        System.out.println("\n=================================================");
        System.out.println("   MILESTONE 1 AUTOMATED TESTS & VERIFIERS       ");
        System.out.println("=================================================");

        // Test 1: Factorial 5! = 120
        try {
            long res = RecursiveMathEngine.factorial(5);
            System.out.printf("[Test 1] Factorial (5! = 120): %d -> %s%n", res, (res == 120 ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 1] FAIL: " + e.getMessage());
        }

        // Test 2: Factorial negative input
        System.out.print("[Test 2] Negative factorial (-5!): ");
        try {
            RecursiveMathEngine.factorial(-5);
            System.out.println("FAIL (No exception thrown)");
        } catch (InvalidRecursionInputException e) {
            System.out.println("PASS -> Caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL with unexpected: " + e.getMessage());
        }

        // Test 3: Power 2^8 = 256
        try {
            long res = RecursiveMathEngine.power(2, 8);
            System.out.printf("[Test 3] Power (2^8 = 256): %d -> %s%n", res, (res == 256 ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 3] FAIL: " + e.getMessage());
        }

        // Test 4: Sum of Digits of 12345 = 15
        int sum = RecursiveMathEngine.sumOfDigits(12345);
        System.out.printf("[Test 4] Sum of Digits (12345 -> 15): %d -> %s%n", sum, (sum == 15 ? "PASS" : "FAIL"));

        // Test 5: Pell Number P(6) = 70
        try {
            long pell6 = RecursiveMathEngine.calculatePell(6);
            System.out.printf("[Test 5] Pell Sequence P(6) = 70: %d -> %s%n", pell6, (pell6 == 70 ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 5] FAIL: " + e.getMessage());
        }

        // Test 6: Recursion Limit Safeguard (Pell index > 40)
        System.out.print("[Test 6] Recursion limit guard on Pell P(45): ");
        try {
            RecursiveMathEngine.calculatePell(45);
            System.out.println("FAIL (No exception thrown)");
        } catch (RecursionLimitExceededException e) {
            System.out.println("PASS -> Caught expected limit guard: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        System.out.println("=================================================");
        System.out.println("       MILESTONE 1 VERIFICATION COMPLETED!       ");
        System.out.println("=================================================");
    }

    private static void runMilestoneTwoAutomatedTests() {
        System.out.println("\n=================================================");
        System.out.println("   MILESTONE 2 AUTOMATED TESTS & VERIFIERS       ");
        System.out.println("=================================================");

        // Test 1: Recursive String Reversal
        try {
            String reversed = RecursiveStringProcessor.reverse("recursion");
            boolean pass = "noisrucer".equals(reversed);
            System.out.printf("[Test 1] Reverse (\"recursion\" -> \"noisrucer\"): %s -> %s%n",
                    reversed, (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 1] FAIL: " + e.getMessage());
        }

        // Test 2: Palindrome Strict Check
        try {
            boolean p1 = RecursiveStringProcessor.isPalindromeStrict("racecar");
            boolean p2 = RecursiveStringProcessor.isPalindromeStrict("hello");
            boolean pass = p1 && !p2;
            System.out.printf("[Test 2] Palindrome Strict (\"racecar\"=true, \"hello\"=false): -> %s%n",
                    (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 2] FAIL: " + e.getMessage());
        }

        // Test 3: Palindrome Normalized Check (Mixed Case & Punctuation)
        try {
            String phrase = "A man, a plan, a canal: Panama";
            boolean pass = RecursiveStringProcessor.isPalindromeNormalized(phrase);
            System.out.printf("[Test 3] Palindrome Normalized (\"%s\"): -> %s%n",
                    phrase, (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 3] FAIL: " + e.getMessage());
        }

        // Test 4: Recursive Character Count
        try {
            int count = RecursiveStringProcessor.countOccurrences("Object Oriented Programming", 'o', false);
            boolean pass = (count == 3);
            System.out.printf("[Test 4] Count occurrences of 'o' in OOP title (expect 3): %d -> %s%n",
                    count, (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 4] FAIL: " + e.getMessage());
        }

        // Test 5: Recursive Vowel Count
        try {
            int vowels = RecursiveStringProcessor.countVowels("Hello World");
            boolean pass = (vowels == 3);
            System.out.printf("[Test 5] Vowel Count in \"Hello World\" (expect 3): %d -> %s%n",
                    vowels, (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 5] FAIL: " + e.getMessage());
        }

        // Test 6: Recursive Substring Search
        try {
            int idx = RecursiveStringProcessor.indexOf("recursive pattern matching", "pattern");
            boolean pass = (idx == 10);
            System.out.printf("[Test 6] Substring search for \"pattern\" (expect idx 10): %d -> %s%n",
                    idx, (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 6] FAIL: " + e.getMessage());
        }

        // Test 7: Recursive Permutations
        try {
            List<String> perms = RecursiveStringProcessor.generatePermutations("abc");
            boolean pass = (perms.size() == 6 && perms.contains("abc") && perms.contains("cba"));
            System.out.printf("[Test 7] Permutations of \"abc\" (3! = 6 elements): %d generated -> %s%n",
                    perms.size(), (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 7] FAIL: " + e.getMessage());
        }

        // Test 8: Recursive Subsequences
        try {
            List<String> subs = RecursiveStringProcessor.generateSubsequences("xyz");
            boolean pass = (subs.size() == 8 && subs.contains("") && subs.contains("xyz"));
            System.out.printf("[Test 8] Subsequences of \"xyz\" (2^3 = 8 elements): %d generated -> %s%n",
                    subs.size(), (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 8] FAIL: " + e.getMessage());
        }

        // Test 9: Stack safeguard on excessive permutation length
        System.out.print("[Test 9] Safeguard on 9-character permutation: ");
        try {
            RecursiveStringProcessor.generatePermutations("123456789");
            System.out.println("FAIL (No exception thrown)");
        } catch (RecursionLimitExceededException e) {
            System.out.println("PASS -> Caught expected guard: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        System.out.println("=================================================");
        System.out.println("       MILESTONE 2 VERIFICATION COMPLETED!       ");
        System.out.println("=================================================");
    }

    private static int readIntChoice() {
        try {
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
}
