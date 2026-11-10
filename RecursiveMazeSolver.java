public class RecursiveMazeSolver {
    public static final int MAX_EXPLORATION_STEPS = 10000;

    // Movement vectors: UP, RIGHT, DOWN, LEFT
    public static final int[][] DIRECTIONS = {
        {-1, 0}, // UP
        {0, 1},  // RIGHT
        {1, 0},  // DOWN
        {0, -1}  // LEFT
    };

    public static final String[] DIRECTION_NAMES = {"UP", "RIGHT", "DOWN", "LEFT"};

    /**
     * Validates that the maze contains both a valid start and an exit coordinate.
     *
     * @param maze the maze grid to validate
     * @throws InvalidRecursionInputException if start or exit point is missing
     */
    public static void validateMazeStructure(MazeGrid maze) throws InvalidRecursionInputException {
        if (maze == null) {
            throw new InvalidRecursionInputException("maze", 0, "Maze grid cannot be null.");
        }
        if (maze.getStartPos() == null) {
            throw new InvalidRecursionInputException("startPos", 0, "Maze has no defined Start ('S') position.");
        }
        if (maze.getExitPos() == null) {
            throw new InvalidRecursionInputException("exitPos", 0, "Maze has no defined Exit ('E') position.");
        }
    }

    /**
     * Solves the maze in-place using pure recursive backtracking.
     * Solved path cells are marked with '*' while dead ends backtracked are marked with 'x'.
     *
     * @param maze the maze to solve
     * @return true if a path from Start to Exit was discovered, false otherwise
     * @throws InvalidRecursionInputException if maze structure is invalid
     * @throws RecursionLimitExceededException if exploration steps exceed safety limit
     */
    public static boolean solve(MazeGrid maze)
            throws InvalidRecursionInputException, RecursionLimitExceededException {
        validateMazeStructure(maze);
        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];
        int[] stepCounter = new int[] {0};

        return solveBacktrackInternal(maze, maze.getStartPos(), visited, stepCounter);
    }

    private static boolean solveBacktrackInternal(MazeGrid maze, MazeCoordinate curr,
                                                 boolean[][] visited, int[] steps)
            throws RecursionLimitExceededException {
        steps[0]++;
        if (steps[0] > MAX_EXPLORATION_STEPS) {
            throw new RecursionLimitExceededException(steps[0], MAX_EXPLORATION_STEPS, "solveMaze");
        }

        // Reached exit
        if (curr.equals(maze.getExitPos())) {
            return true;
        }

        visited[curr.getRow()][curr.getCol()] = true;
        if (!curr.equals(maze.getStartPos())) {
            maze.setCell(curr, MazeGrid.PATH);
        }

        // Explore 4 cardinal directions recursively
        for (int[] dir : DIRECTIONS) {
            MazeCoordinate next = curr.offset(dir[0], dir[1]);
            if (maze.isInBounds(next) && maze.isWalkable(next) && !visited[next.getRow()][next.getCol()]) {
                if (solveBacktrackInternal(maze, next, visited, steps)) {
                    return true;
                }
            }
        }

        // Backtrack: if this path leads to no solution, mark dead end
        if (!curr.equals(maze.getStartPos())) {
            maze.setCell(curr, MazeGrid.VISITED);
        }
        return false;
    }
}
