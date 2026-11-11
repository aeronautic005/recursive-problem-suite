/**
 * Encapsulates search efficiency analytics and metrics calculated
 * from recursive maze exploration.
 */
public class MazeAnalytics {
    private final int totalCells;
    private final int walkableCells;
    private final int exploredSteps;
    private final int pathLength;
    private final int deadEndsBacktracked;
    private final double searchEfficiencyPercentage;

    public MazeAnalytics(int totalCells, int walkableCells, int exploredSteps,
                         int pathLength, int deadEndsBacktracked) {
        this.totalCells = totalCells;
        this.walkableCells = walkableCells;
        this.exploredSteps = exploredSteps;
        this.pathLength = pathLength;
        this.deadEndsBacktracked = deadEndsBacktracked;
        this.searchEfficiencyPercentage = exploredSteps > 0
                ? (pathLength / (double) exploredSteps) * 100.0
                : 0.0;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public int getWalkableCells() {
        return walkableCells;
    }

    public int getExploredSteps() {
        return exploredSteps;
    }

    public int getPathLength() {
        return pathLength;
    }

    public int getDeadEndsBacktracked() {
        return deadEndsBacktracked;
    }

    public double getSearchEfficiencyPercentage() {
        return searchEfficiencyPercentage;
    }

    public String renderReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=================================================\n");
        sb.append("             MAZE PATHFINDER ANALYTICS           \n");
        sb.append("=================================================\n");
        sb.append(String.format("  Total Grid Cells:           %d%n", totalCells));
        sb.append(String.format("  Walkable Corridor Cells:    %d%n", walkableCells));
        sb.append(String.format("  Total Exploration Steps:    %d%n", exploredSteps));
        sb.append(String.format("  Final Path Length:          %d steps%n", pathLength));
        sb.append(String.format("  Dead-Ends Backtracked ('x'): %d cells%n", deadEndsBacktracked));
        sb.append(String.format("  Search Efficiency:          %.2f%%%n", searchEfficiencyPercentage));
        sb.append("=================================================\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return renderReport();
    }
}
