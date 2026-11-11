# Recursive Problem Suite & Maze Pathfinder

An advanced Object-Oriented Java suite implementing fundamental and complex recursive algorithms, mathematical sequence generators, combinatorial permutations/subsequences, recursive 2D maze backtracking pathfinding, real-time animation visualizers, and performance benchmarking hubs.

---

## Project Status: 100% Completed [40 / 40 Commits - 4 / 4 Milestones]

- [x] **Milestone 1: Domain Exceptions & Recursive Mathematical Engine (Commits 1-10)**
  - Custom domain exceptions (`InvalidRecursionInputException`, `RecursionLimitExceededException`)
  - Pure recursive implementations: Factorial ($n!$), Exponentiation ($b^e$), Sum of Digits, Pell Sequences ($P(n)$)
  - Call stack limit safeguards, integer overflow checks & boundary validation
- [x] **Milestone 2: String & Palindrome Recursive Processing (Commits 11-20)**
  - Pure recursive string reversal with recursion depth guards
  - Two-pointer recursive palindrome checking (strict character comparison and normalized multi-word phrase filtering)
  - Recursive character occurrence counting and vowel frequency analysis
  - Recursive substring searching (`containsSubstring`, `indexOf`)
  - Recursive permutation generator with combinatorial factorial safety boundaries ($n!$)
  - Recursive subsequence generator ($2^n$ combinations)
  - Interactive CLI submenus & automated verification suite
- [x] **Milestone 3: 2D Maze Grid Representation & Backtracking Pathfinder (Commits 21-30)**
  - Immutable 2D grid position coordinate model (`MazeCoordinate`)
  - Encapsulated maze grid matrix with cell states (`S`, `E`, `#`, `.`, `*`, `x`)
  - Built-in maze preset generators (Sample 5x5, 7x7 Labyrinth, and Unsolvable Enclosure)
  - Pure recursive backtracking solver marking active path (`*`) and backtracked dead ends (`x`)
  - Full coordinate path tracer recording ordered sequence from Start to Exit
  - Exhaustive non-cyclic all-paths counter discovering all feasible routes
  - Interactive CLI pathfinder and automated verification suite
- [x] **Milestone 4: Interactive Visual CLI, Step-by-Step Animation & Performance Benchmark Hub (Commits 31-40)**
  - Performance metrics and high-resolution timing harness (`BenchmarkResult`)
  - Benchmarking engine measuring Math, String, and Maze execution times and call steps (`RecursionBenchmarkHub`)
  - Formatted ASCII performance reports with microsecond timing accuracy
  - Step-by-step maze exploration frame recorder and animation visualizer (`MazeVisualizer`)
  - Search efficiency analytics engine calculating exploration-to-path ratios (`MazeAnalytics`)
  - Master end-to-end automated test runner verifying all 26 verification cases across all 4 milestones

---

## Architectural Overview

```
                      +-----------------------------+
                      |       RecursionApp          |
                      |   (Interactive CLI Hub)     |
                      +--------------+--------------+
                                     |
       +--------------------+--------+--------+--------------------+
       |                    |                 |                    |
+------v------+      +------v------+   +------v------+      +------v------+
| Recursive   |      | Recursive   |   | Recursive   |      | Recursion   |
| MathEngine  |      | StringProc  |   | MazeSolver  |      | Benchmark   |
+------+------+      +------+------+   +------+------+      +------+------+
       |                    |                 |                    |
       |                    |          +------v------+      +------v------+
       |                    |          |  MazeGrid   |      |  Maze       |
       |                    |          |  & Coord    |      | Visualizer  |
       |                    |          +-------------+      +-------------+
       +--------------------+-----------------+--------------------+
                                     |
                      +--------------v--------------+
                      |   Domain Exception Layer    |
                      | - InvalidRecursionInput     |
                      | - RecursionLimitExceeded    |
                      +-----------------------------+
```

---

## How to Compile & Run

### 1. Compile All Files
```bash
javac *.java
```

### 2. Launch Application
```bash
java RecursionApp
```

---

## Main Menu Capabilities

```text
----------------- MAIN MENU ---------------------
1. Interactive Recursive Math Calculator
2. Interactive Recursive String & Palindrome Engine
3. Interactive 2D Maze Recursive Pathfinder
4. Animated Step-by-Step Maze Replay & Analytics
5. Performance Benchmark Hub & Metrics
6. Run Automated Test Suites (Milestones 1-4)
7. Exit
-------------------------------------------------
```

### Automated Verifier & Master Suite
Select Option `6` from the main menu:
- **1**: Run Milestone 1 Tests (Math Engine) $\rightarrow$ 6 Tests
- **2**: Run Milestone 2 Tests (String Engine) $\rightarrow$ 9 Tests
- **3**: Run Milestone 3 Tests (Maze Pathfinder) $\rightarrow$ 6 Tests
- **4**: Run Milestone 4 Tests (Visualizer & Benchmarks) $\rightarrow$ 5 Tests
- **5**: Run Master Suite (All Milestones 1–4 End-to-End) $\rightarrow$ **26 / 26 PASS**
