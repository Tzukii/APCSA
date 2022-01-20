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
        // TODO complete method
    }

    /**
     * Checks if the object at a specified row and column is black
     * 
     * @param r Row
     * @param c Column
     * @return True if object is black, false if object is not
     */
    public boolean isBlack(int r, int c) {
        if (charAt(r, c) == 'b') {
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
        if (charAt(r, c) == 'w') {
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
        if (charAt(r, c) == 'x') {
            return true;
        }
        return false;
    }

    public void setBlack(int r, int c) {
        setCharAt(r, c, 'b');
    }

    public void setWhite(int r, int c) {
        setCharAt(r, c, 'w');
    }

    public void setGray(int r, int c) {
        setCharAt(r, c, 'x');
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
        int flag = 0;
        for (int c = 0; c < numCols(); c++) {
            if (charAt(0, c) == 'b') {
                for (int r = 0; r < numRows(); r++) {
                    if (charAt(r, c) != 'b') {
                        flag++;
                    } else {
                        flag = -999999999;
                    }
                }
            }
        }

        return flag < 0;
    }

    /**
     * Fills the contiguous area that contains r,c with gray color.
     * Does nothing if r, c is out of bounds or is not black.
     */
    public void areaFill(int r, int c) {
        if (!isInBounds(r, c)) {
            System.out.println("Out of Bounds!");
            return;
        } else if (charAt(r, c) != 'b') {
            System.out.println("Not Black!");
            return;
        } else {
            for (int rowCount = 0; rowCount <= r; rowCount++) {
                for (int colCount = 0; colCount <= c; colCount++) {
                    setGray(rowCount, colCount);
                }
            }
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
        if (row > numRows() || col > numCols()) {
            return false;
        }

        return true;
    }
}
