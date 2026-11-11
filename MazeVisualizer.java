import java.util.ArrayList;
import java.util.List;

/**
 * Captures, records, and renders step-by-step animation frames
 * during recursive maze backtracking traversal.
 */
public class MazeVisualizer {
    private final List<String> recordedFrames;
    private final List<MazeCoordinate> stepCoordinates;
    private boolean solved;

    public MazeVisualizer() {
        this.recordedFrames = new ArrayList<>();
        this.stepCoordinates = new ArrayList<>();
        this.solved = false;
    }

    public List<String> getRecordedFrames() {
        return recordedFrames;
    }

    public int getFrameCount() {
        return recordedFrames.size();
    }

    public String getFrame(int index) {
        if (index >= 0 && index < recordedFrames.size()) {
            return recordedFrames.get(index);
        }
        return "";
    }

    public List<MazeCoordinate> getStepCoordinates() {
        return stepCoordinates;
    }

    public boolean isSolved() {
        return solved;
    }

    /**
     * Records exploration frames step-by-step from Start to Exit.
     *
     * @param inputMaze initial maze state
     * @return true if exit reached, false otherwise
     */
    public boolean recordExploration(MazeGrid inputMaze) throws Exception {
        recordedFrames.clear();
        stepCoordinates.clear();

        RecursiveMazeSolver.validateMazeStructure(inputMaze);
        MazeGrid maze = inputMaze.cloneGrid();
        boolean[][] visited = new boolean[maze.getRows()][maze.getCols()];

        // Record Initial Frame
        recordFrame("FRAME 0: Starting state at " + maze.getStartPos(), maze);

        int[] stepCounter = new int[] {0};
        this.solved = recordBacktrack(maze, maze.getStartPos(), visited, stepCounter);

        recordFrame("FINAL FRAME: Exploration complete (Solved=" + solved + ")", maze);
        return this.solved;
    }

    private boolean recordBacktrack(MazeGrid maze, MazeCoordinate curr,
                                    boolean[][] visited, int[] steps) throws Exception {
        steps[0]++;
        if (steps[0] > RecursiveMazeSolver.MAX_EXPLORATION_STEPS) {
            throw new RecursionLimitExceededException(steps[0],
                    RecursiveMazeSolver.MAX_EXPLORATION_STEPS, "MazeVisualizer");
        }

        visited[curr.getRow()][curr.getCol()] = true;
        stepCoordinates.add(curr);

        if (!curr.equals(maze.getStartPos()) && !curr.equals(maze.getExitPos())) {
            maze.setCell(curr, MazeGrid.PATH);
        }

        recordFrame(String.format("Step %d: Advancing to %s", steps[0], curr), maze);

        if (curr.equals(maze.getExitPos())) {
            return true;
        }

        for (int[] dir : RecursiveMazeSolver.DIRECTIONS) {
            MazeCoordinate next = curr.offset(dir[0], dir[1]);
            if (maze.isInBounds(next) && maze.isWalkable(next) && !visited[next.getRow()][next.getCol()]) {
                if (recordBacktrack(maze, next, visited, steps)) {
                    return true;
                }
            }
        }

        // Backtrack
        if (!curr.equals(maze.getStartPos()) && !curr.equals(maze.getExitPos())) {
            maze.setCell(curr, MazeGrid.VISITED);
        }
        recordFrame(String.format("Step %d: [BACKTRACKING] Dead end encountered at %s, rolling back", steps[0], curr), maze);
        return false;
    }

    private void recordFrame(String caption, MazeGrid maze) {
        StringBuilder frame = new StringBuilder();
        frame.append("-------------------------------------------------\n");
        frame.append(" ").append(caption).append("\n");
        frame.append("-------------------------------------------------\n");
        frame.append(maze.render());
        recordedFrames.add(frame.toString());
    }

    /**
     * Plays through all recorded animation frames sequentially.
     *
     * @param delayMs millisecond pause between frames (0 for instant dump)
     */
    public void playAnimation(int delayMs) {
        for (int i = 0; i < recordedFrames.size(); i++) {
            System.out.println(recordedFrames.get(i));
            if (delayMs > 0 && i < recordedFrames.size() - 1) {
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * Prints an abbreviated animation highlight reel showing first, sample intermediate, and final frames.
     */
    public void printSummaryReplay() {
        if (recordedFrames.isEmpty()) {
            System.out.println("[Notice] No recorded frames to display.");
            return;
        }

        System.out.println("[REPLAY START] " + recordedFrames.get(0));
        if (recordedFrames.size() > 2) {
            int mid = recordedFrames.size() / 2;
            System.out.println("[REPLAY MIDPOINT FRAME " + mid + "] " + recordedFrames.get(mid));
        }
        System.out.println("[REPLAY COMPLETE] " + recordedFrames.get(recordedFrames.size() - 1));
    }

    /**
     * Computes search efficiency metrics on the explored maze.
     *
     * @param initialMaze the maze to inspect
     * @return MazeAnalytics object containing performance metrics
     */
    public static MazeAnalytics computeAnalytics(MazeGrid initialMaze) throws Exception {
        MazeGrid maze = initialMaze.cloneGrid();
        int totalCells = maze.getRows() * maze.getCols();
        int walkableCells = 0;

        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {
                if (maze.getCell(r, c) != MazeGrid.WALL) {
                    walkableCells++;
                }
            }
        }

        List<MazeCoordinate> solutionPath = RecursiveMazeSolver.findPath(maze);
        int pathLen = solutionPath.size();

        MazeGrid solvedMaze = initialMaze.cloneGrid();
        RecursiveMazeSolver.solve(solvedMaze);

        int deadEnds = 0;
        int activeSteps = 0;
        for (int r = 0; r < solvedMaze.getRows(); r++) {
            for (int c = 0; c < solvedMaze.getCols(); c++) {
                char ch = solvedMaze.getCell(r, c);
                if (ch == MazeGrid.VISITED) {
                    deadEnds++;
                    activeSteps++;
                } else if (ch == MazeGrid.PATH) {
                    activeSteps++;
                }
            }
        }

        return new MazeAnalytics(totalCells, walkableCells, (activeSteps + 1), pathLen, deadEnds);
    }
}
