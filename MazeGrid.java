public class MazeGrid {
    public static final char START = 'S';
    public static final char EXIT = 'E';
    public static final char WALL = '#';
    public static final char OPEN = '.';
    public static final char PATH = '*';
    public static final char VISITED = 'x';

    private final int rows;
    private final int cols;
    private final char[][] grid;
    private MazeCoordinate startPos;
    private MazeCoordinate exitPos;

    public MazeGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = OPEN;
            }
        }
    }

    public MazeGrid(String[] template) {
        this.rows = template.length;
        this.cols = template[0].length();
        this.grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = template[r].charAt(c);
                grid[r][c] = ch;
                if (ch == START) {
                    startPos = new MazeCoordinate(r, c);
                } else if (ch == EXIT) {
                    exitPos = new MazeCoordinate(r, c);
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isInBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public boolean isInBounds(MazeCoordinate coord) {
        return coord != null && isInBounds(coord.getRow(), coord.getCol());
    }

    public char getCell(int r, int c) {
        if (!isInBounds(r, c)) {
            return WALL;
        }
        return grid[r][c];
    }

    public char getCell(MazeCoordinate coord) {
        return getCell(coord.getRow(), coord.getCol());
    }

    public void setCell(int r, int c, char val) {
        if (isInBounds(r, c)) {
            grid[r][c] = val;
            if (val == START) startPos = new MazeCoordinate(r, c);
            if (val == EXIT) exitPos = new MazeCoordinate(r, c);
        }
    }

    public void setCell(MazeCoordinate coord, char val) {
        if (coord != null) {
            setCell(coord.getRow(), coord.getCol(), val);
        }
    }

    public boolean isWalkable(int r, int c) {
        if (!isInBounds(r, c)) return false;
        char cell = grid[r][c];
        return cell == OPEN || cell == EXIT;
    }

    public boolean isWalkable(MazeCoordinate coord) {
        return coord != null && isWalkable(coord.getRow(), coord.getCol());
    }

    public MazeCoordinate getStartPos() {
        return startPos;
    }

    public MazeCoordinate getExitPos() {
        return exitPos;
    }

    public MazeGrid cloneGrid() {
        String[] copy = new String[rows];
        for (int r = 0; r < rows; r++) {
            copy[r] = new String(grid[r]);
        }
        return new MazeGrid(copy);
    }
}
