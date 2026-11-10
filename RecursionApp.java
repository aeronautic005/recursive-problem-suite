import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecursionApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================================");
        System.out.println("    RECURSIVE ALGORITHM SUITE & MAZE SOLVER     ");
        System.out.println("        [MILESTONE 1 & 2 - DUAL ENGINES]         ");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-5): ");
            int choice = readIntChoice();

            switch (choice) {
                case 1:
                    runInteractiveMathMenu();
                    break;
                case 2:
                    runInteractiveStringMenu();
                    break;
                case 3:
                    runMilestoneOneAutomatedTests();
                    break;
                case 4:
                    System.out.println("\n[NOTICE] Milestone 3 (2D Maze Backtracking Pathfinder) is next in queue!");
                    break;
                case 5:
                    running = false;
                    System.out.println("\nExiting Recursive Suite. Happy problem solving!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Select 1 through 5.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("----------------- MAIN MENU ---------------------");
        System.out.println("1. Interactive Recursive Math Calculator");
        System.out.println("2. Interactive Recursive String & Palindrome Engine");
        System.out.println("3. Run Automated Tests & Verifiers");
        System.out.println("4. 2D Maze Pathfinder (Coming in Milestone 3)");
        System.out.println("5. Exit");
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
