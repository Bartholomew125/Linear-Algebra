public class Matrix {

    private Vector[] columns;
    private int numRows;
    private int numCols;

    public Matrix(Vector... columnVectors) {
        this.columns = columnVectors;
        this.numRows = columnVectors[0].getSize();
        this.numCols = columnVectors.length;
    }
    
    /**
     * Matrix({{1,2,3},{4,5,6},{7,8,9}}) 
     * Should return a matrix, which when printed looks like this.
     * 1 2 3
     * 4 5 6
     * 7 8 9
     */
    public Matrix(double[][] entries) {
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

    public double get(int row, int column) {
        return this.columns[column].get(row);
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }

    public double determinate() {
        return Matrix.determinate(this);
    }

    public static double determinate(Matrix A) {
        if (A.getNumRows() == 1 && A.getNumCols() == 1) {
            return A.get(0,0);
        }
        else {
            double sum = 0;
            int j = 0;
            for (int i = 0; i < A.getNumRows(); i++){
                Matrix M = Matrix.deleteRowAndColumn(A,i,j);
                double detM = Matrix.determinate(M);
                double C = (i+j)%2==0 ? detM : -detM;
                sum = sum + A.get(i,j)*C;
            }
            return sum;
        }
    }

    public static Matrix deleteRowAndColumn(Matrix A, int row, int col) {
        int newNumRows = A.getNumRows()-1;
        int newNumCols = A.getNumCols()-1;

        Vector[] vectors = new Vector[newNumCols];
        int colOffset = 0;
        for (int j = 0; j < A.getNumCols(); j++) {
            double[] elements = new double[newNumRows];
            int rowOffset = 0;
            for (int i = 0; i < A.getNumRows(); i++) {
                if (i == row) { rowOffset++; }
                else {
                    elements[i-rowOffset] = A.get(i,j);
                }
            }
            if (j == col) { colOffset++; }
            else {
                vectors[j-colOffset] = new Vector(elements);
            }
        }

        return new Matrix(vectors);
    }

    public static Matrix replaceColumn(Matrix A, Vector v, int col) {
        Matrix B = A.copy();
        B.columns[col] = v;
        return B;
    }

    @Override
    public String toString() {
        String string = "\n";
        for (int row = 0; row < this.numRows; row++) {
            for (Vector vector : this.columns) {
                string = string + vector.get(row) + " ";
            }
            string = string + "\n";
        }
        return string;
    }

    public Matrix copy() {
        Vector[] newColumns = new Vector[this.getNumCols()];
        for (int j = 0; j < newColumns.length; j++) {
            newColumns[j] = this.columns[0].copy();
        }
        return new Matrix(newColumns);
    }
}
