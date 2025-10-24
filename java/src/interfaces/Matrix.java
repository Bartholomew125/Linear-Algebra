package interfaces;
import implementations.Vector;

/**
 * Matrix
 */
public interface Matrix {
    /**
     * Returns the element at row i and column j.
     */
    public double get(int i, int j);

    /**
     * Return the number of rows of the matrix.
     */
    public int getNumRows();

    /**
     * Return the number of columns of the matrix.
     */
    public int getNumCols();

    /**
     * Return column j of the matrix.
     */
    public Vector getColumn(int j);

    /**
     * Return all of the columns.
     */
    public Vector[] getColumns();
}
