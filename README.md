# Recursive Problem Suite & Maze Pathfinder

An advanced Object-Oriented Java suite implementing fundamental and complex recursive algorithms, mathematical sequence generators, string permutations/subsequences, recursive 2D maze backtracking, and custom recursion domain exceptions.

## Project Status: Milestone 1, 2 & 3 Completed [75% - 3/4 Complete]

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
- [x] **Milestone 3: 2D Maze Grid Representation & Backtracking Pathfinder (Commits 21-30)**
  - Immutable 2D grid position coordinate model (`MazeCoordinate`)
  - Encapsulated maze grid matrix with cell states (`S`, `E`, `#`, `.`, `*`, `x`)
  - Built-in maze preset generators (Sample 5x5, 7x7 Labyrinth, and Unsolvable Enclosure)
  - Pure recursive backtracking solver marking active path and backtracked dead ends
  - Full coordinate path tracer recording ordered sequence from Start to Exit
  - Exhaustive non-cyclic all-paths counter discovering all feasible routes
  - Interactive CLI pathfinder and automated verification suite
- [ ] **Milestone 4: Interactive Visual CLI, Step-by-Step Animation & Performance Benchmark Hub (Commits 31-40)** *(Part 4)*

## How to Compile & Run

```bash
# Compile all Java source files
javac *.java

# Launch the interactive application & test runner
java RecursionApp
```

### Automated Verification Suites

From the main menu:
- Enter `4` to run the **Milestone 1 Math Engine Verifier**
- Enter `5` to run the **Milestone 2 String Engine Verifier**
- Enter `6` to run the **Milestone 3 Maze Pathfinder Verifier**
