import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Qu*lt "lab" 🤮🤮🤮🤮🤮
 *
 * @author Shivam Maji
 * @version 4/5/2021
 * @author Period: 6
 * @author Assignment: AB Free Response Quilt
 *
 * @author Sources: Me Myself and I
 */
public class Quilt {
    char[][] myBlock; // stores pattern for one block
    int myRowsOfBlocks; // number of rows of blocks in the quilt
    int myColsOfBlocks; // number of columns of blocks in the quilt

    // precondition: quiltBlock refers to an initialized quilt block,
    // rowsOfBlocks > 0, colsOfBlocks > 0
    // postcondition: myRowsOfBlocks and myColsOfBlocks are initialized to
    // the number of rows and columns of blocks that make up
    // the quilt; myBlock has been initialized to the block
    // pattern of quiltBlock.
    public Quilt(char[][] quiltBlock, int rowsOfBlocks, int colsOfBlocks) {
        myRowsOfBlocks = rowsOfBlocks;
        myColsOfBlocks = colsOfBlocks;
        myBlock = quiltBlock;
    }

    // precondition: startRow >= 0; startCol >= 0;
    // startRow + myBlock.numrows() <= qmat.numrows();
    // startCol + myBlock.numcols() <= qmat.numcols();
    // postcondition: myBlock has been copied into the matrix
    // qmat with its upper-left corner at the position
    // startRow, startCol
    public void placeBlock(int startRow, int startCol, char[][] qmat) {
        for (int r = 0; r < myBlock.length; r++) {
            for (int c = 0; c < myBlock[r].length; c++) {
                qmat[startRow + r][startCol + c] = myBlock[r][c];
            }
        }
    }

    // precondition: startRow >= 0; startCol >= 0;
    // startRow + myBlock.length <= qmat.length;
    // startCol + myBlock[0].length <= qmat[0].length;
    // postcondition: a flipped version of myBlock has been copied into the
    // matrix qmat with its upper-left corner at the position
    // startRow, startCol
    public void placeFlipped(int startRow, int startCol, char[][] qmat) {
        for (int r = 0; r < myBlock.length; r++) {
            for (int c = 0; c < myBlock[0].length; c++) {
                qmat[startRow + myBlock.length - 1 - r][startCol + c] = myBlock[r][c];
            }
        }
    }

    public char[][] quiltToMat() // checkerboard alternation
    {
        boolean a = false;
        int r;
        int c;
        char[][] mat = new char[myRowsOfBlocks * myBlock.length][myColsOfBlocks * myBlock[0].length];

        for (r = 0; r < mat.length; r += myBlock.length) {
            for (c = 0; c < mat[r].length; c += myBlock[0].length) {
                if (!a) {
                    System.out.println("placeBlock: " + r + " " + c);
                    placeBlock(r, c, mat);
                    a = true;
                } else {
                    System.out.println("placeFlipped: " + r + " " + c);
                    placeFlipped(r, c, mat);
                    a = false;
                }

            }
            if (myRowsOfBlocks % 2 == 0 && myColsOfBlocks % 2 == 0) {
                a = !a;
            } else if (myColsOfBlocks % 2 == 0 && myRowsOfBlocks % 2 != 0) {
                a = !a;

            }
        }
        return mat;

    }

    /*
     * Intended only for testing.
     */
    public char[][] getMyBlock() {
        return myBlock;
    }

    public int getMyRowsOfBlocks() {
        return myRowsOfBlocks;
    }

    public int getMyColsOfBlocks() {
        return myColsOfBlocks;
    }

    /**
     * Test Quilt class.
     * 
     * @param args command-line parameters (not used)
     */
    public static void main(String[] args) {
        char[][] qBlock = { { 'x', '.', '.', '.', 'x' },
                { '.', 'x', '.', 'x', '.' },
                { '.', '.', 'x', '.', '.' },
                { '.', '.', 'x', '.', '.' } };

        Quilt q = new Quilt(qBlock, 3, 3);

        char[][] quilt = q.quiltToMat();

        for (char r[] : quilt) {
            for (char ch : r) {
                System.out.print(ch);
            }
            System.out.println();
        }

    }
}
