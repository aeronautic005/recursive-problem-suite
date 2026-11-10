import java.util.Objects;

/**
 * Immutable value object representing a 2D coordinate inside a maze grid.
 */
public class MazeCoordinate {
    private final int row;
    private final int col;

    public MazeCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public MazeCoordinate offset(int deltaRow, int deltaCol) {
        return new MazeCoordinate(this.row + deltaRow, this.col + deltaCol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCoordinate that = (MazeCoordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }
}
