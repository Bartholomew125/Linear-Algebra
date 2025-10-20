package interfaces;
import implementations.Vector;

/**
 * Matrix
 */
public interface Matrix {
    public double get(int i, int j);
    public int getNumRows();
    public int getNumCols();
    public Vector getColumn(int j);
    public Vector[] getColumns();
}
