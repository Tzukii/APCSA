/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 * 
 * @author Shivam Maji
 * @version 1/16/2021
 * 
 * @author Period - 6
 * @author Assignment - JM Ch22.6 - Hex
 * 
 * @author Sources - Me Myself and I
 */
public class HexBoard extends CharMatrix {
    public HexBoard(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Checks if the object at a specified row and column is black
     * 
     * @param r Row
     * @param c Column
     * @return True if object is black, false if object is not
     */
    public boolean isBlack(int r, int c) {
        if (charAt(r, c) == 'b' && isInBounds(r, c)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the object at a specified row and column is white
     * 
     * @param r Row
     * @param c Column
     * @return True if object is white, false if object is not
     */
    public boolean isWhite(int r, int c) {
        if (charAt(r, c) == 'w' && isInBounds(r, c)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the object at a specified row and column is gray
     * 
     * @param r
     * @param c
     * @return True if object is gray, false if object is not
     */
    public boolean isGray(int r, int c) {
        if (charAt(r, c) == 'x' && isInBounds(r, c)) {
            return true;
        }
        return false;
    }

    public void setBlack(int r, int c) {
        if (isInBounds(r, c)) {
            setCharAt(r, c, 'b');
        }
    }

    public void setWhite(int r, int c) {
        if (isInBounds(r, c)) {
            setCharAt(r, c, 'w');
        }
    }

    public void setGray(int r, int c) {
        if (isInBounds(r, c)) {
            setCharAt(r, c, 'x');
        }
    }

    /**
     * Checks if there is a contiguous chain of black stones from column 0 to the
     * final column
     * 
     * @return Returns true if there is a contiguous chain of black stones
     *         that starts in col 0 and ends in the last column of the board;
     *         otherwise returns false.
     */
    public boolean blackHasWon() {
        for (int i = 0; i < numRows(); i++) {
            boolean[][] traversed = new boolean[numRows()][numCols()];
            if (isBlack(i, 0) && blackHasWonHelper(i, 0, traversed)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Helper method for blackHasWon
     * 
     * @param r         row
     * @param c         col
     * @param traversed Checks if traversed
     * @return
     */
    private boolean blackHasWonHelper(int r, int c, boolean[][] traversed) {
        if (!isInBounds(r, c) || !isBlack(r, c) || traversed[r][c]) {
            return false;
        }
        traversed[r][c] = true;

        if (c == numCols() - 1) {
            return true;
        }
        return blackHasWonHelper(r - 1, c, traversed) || blackHasWonHelper(r, c + 1, traversed)
                || blackHasWonHelper(r + 1, c + 1, traversed) || blackHasWonHelper(r + 1, c, traversed)
                || blackHasWonHelper(r, c - 1, traversed) || blackHasWonHelper(r - 1, c - 1, traversed);
    }

    /**
     * Fills the contiguous area that contains r,c with gray color.
     * Does nothing if r, c is out of bounds or is not black.
     */
    public void areaFill(int r, int c) {
        if (isInBounds(r, c) && isBlack(r, c)) {
            setGray(r, c);

            areaFill(r - 1, c);
            areaFill(r, c + 1);
            areaFill(r + 1, c + 1);
            areaFill(r + 1, c);
            areaFill(r, c - 1);
            areaFill(r - 1, c - 1);
        }
    }

    public String toString() {
        String s = "";

        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numCols(); c++) {
                if (isBlack(r, c))
                    s += 'B';
                else if (isWhite(r, c))
                    s += 'W';
                else if (isGray(r, c))
                    s += 'X';
                else
                    s += '.';
            }
            s += '\n';
        }
        return s;
    }

    // ****************************************************************

    /**
     * Checks if a coordinate is within bounds
     * 
     * @param row Coordinate of row
     * @param col Coordinate of column
     * @return True if coordinate is in bounds, false if not
     */
    private boolean isInBounds(int row, int col) {
        if (row > numRows() || col > numCols() || row < 0 || col < 0) {
            return false;
        }

        return true;
    }
}
