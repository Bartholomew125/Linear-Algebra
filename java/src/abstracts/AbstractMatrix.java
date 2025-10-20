package abstracts;
import interfaces.Matrix;
import implementations.Vector;

/**
 * AbstractMatrix
 */
public class AbstractMatrix implements Matrix {

    private final Vector[] columns;
    private final int numRows;
    private final int numCols;

    public AbstractMatrix() {
        this.columns = new Vector[0];
        this.numRows = 0;
        this.numCols = 0;
    }

    public AbstractMatrix(Vector... columns) {
        this.columns = columns;
        this.numRows = columns[0].getSize();
        this.numCols = columns.length;
    }

    /**
     * ({{1,2,3},{4,5,6},{7,8,9}}) 
     * Should return a matrix, which when printed looks like this.
     * 1 2 3
     * 4 5 6
     * 7 8 9
     */
    public AbstractMatrix(double[][] entries) {
        this.numRows = entries.length;
        this.numCols = entries[0].length;

        this.columns = new Vector[this.numCols];
        for (int col = 0; col < this.numCols; col++) {
            double[] elements = new double[this.numRows];
            for (int row = 0; row < this.numRows; row++) {
                elements[row] = entries[row][col];
            }
            this.columns[col] = new Vector(elements);
        }
    }

    @Override
    public double get(int i, int j) {
        return this.columns[j].get(i);
    }

    @Override
    public int getNumRows() {
        return this.numRows;
    }

    @Override
    public int getNumCols() {
        return this.numCols;
    }

    @Override
    public Vector getColumn(int j) {
        return this.columns[j].copy();
    }

    @Override
    public Vector[] getColumns() {
        return this.columns;
    }


    @Override
    public String toString() {
        String string = "\n";
        for (int row = 0; row < this.getNumRows(); row++) {
            for (Vector vector : this.getColumns()) {
                string = string + vector.get(row) + " ";
            }
            string = string + "\n";
        }
        return string;
    }

    public AbstractMatrix copy() {
        Vector[] newColumns = new Vector[this.getNumCols()];
        for (int j = 0; j < newColumns.length; j++) {
            newColumns[j] = this.getColumn(j).copy();
        }
        return new AbstractMatrix(newColumns);
    }

    @Override
    public boolean equals(Object obj) {
        AbstractMatrix M = (AbstractMatrix) obj;
        if (this.getNumCols() != M.getNumCols()) {
            return false;
        }
        for (int j = 0; j < this.getNumCols(); j++) {
            if (!this.getColumn(j).equals(M.getColumn(j))) {
                return false;
            }
        }
        return true;
    }

}
