# Recursive Problem Suite & Maze Pathfinder

An advanced Object-Oriented Java suite implementing fundamental and complex recursive algorithms, mathematical sequence generators, string permutations/subsequences, recursive backtracking, and custom recursion domain exceptions.

## Project Status: Milestone 1 & 2 Completed [50% - 2/4 Complete]

- [x] **Milestone 1: Domain Exceptions & Recursive Mathematical Engine (Commits 1-10)**
  - Custom exceptions (`InvalidRecursionInputException`, `RecursionLimitExceededException`)
  - Pure recursive implementations: Factorial, Exponentiation, Sum of Digits, Pell Sequences
  - Call stack limit safeguards & boundary validation
- [x] **Milestone 2: String & Palindrome Recursive Processing (Commits 11-20)**
  - Pure recursive string reversal with call-stack limits
  - Two-pointer recursive palindrome checking (strict and normalized phrase validation)
  - Recursive character occurrence counting and vowel analysis
  - Recursive substring searching (`containsSubstring`, `indexOf`)
  - Recursive permutation generator with combinatorial safety boundaries ($n!$)
  - Recursive subsequence generator ($2^n$ combinations)
  - Interactive CLI submenus & automated verification suite
- [ ] **Milestone 3: 2D Maze Grid Representation & Backtracking Pathfinder (Commits 21-30)** *(Part 3)*
- [ ] **Milestone 4: Interactive Visual CLI & Performance Benchmark Hub (Commits 31-40)** *(Part 4)*

## How to Compile & Run

```bash
# Compile all Java source files
javac *.java

# Launch the interactive application & test runner
java RecursionApp
```

### Automated Verification Suites

From the main menu:
- Enter `3` to run the **Milestone 1 Math Engine Verifier**
- Enter `4` to run the **Milestone 2 String Engine Verifier**
