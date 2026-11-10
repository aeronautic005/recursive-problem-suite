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
}
