package utility;
import implementations.Vector;
import implementations.GeneralMatrix;
import implementations.Basis;
import implementations.SquareMatrix;
import implementations.StandardBasis;
import implementations.IdentityMatrix;
import implementations.AugmentedMatrix;
import abstracts.AbstractMatrix;

/**
 * LinearAlgebra
 */
public final class LinearAlgebra {

    public static double dotProduct(Vector v1, Vector v2) {
        assert v1.getSize() == v2.getSize();
        double dotProduct = 0;
        for (int i = 0; i < v1.getSize(); i++) {
            dotProduct = dotProduct + v1.get(i)*v2.get(i);
        }
        return dotProduct;
    }

    public static GeneralMatrix asMatrix(Vector v) {
        return new GeneralMatrix(new Vector[]{v.copy()});
    }

    public static Vector asVector (AbstractMatrix M) {
        assert M.getNumCols() == 1;
        return M.getColumn(0).copy();
    }

    public static Vector project(Vector v, Vector w) {
        GeneralMatrix Mw = LinearAlgebra.asMatrix(w);
        AbstractMatrix top = LinearAlgebra.multiply(Mw, LinearAlgebra.transpose(Mw));
        AbstractMatrix transformation = LinearAlgebra.divide(top, LinearAlgebra.dotProduct(w, w));
        AbstractMatrix result = LinearAlgebra.multiply(transformation, LinearAlgebra.asMatrix(v));
        return LinearAlgebra.asVector(result);
    }

    public static Vector vectorInBasis(Vector v, Basis B) {
        SquareMatrix inverseB = LinearAlgebra.inverse(B);
        GeneralMatrix MV = LinearAlgebra.asMatrix(v);
        return LinearAlgebra.asVector(LinearAlgebra.multiply(inverseB, MV));
    }

    public static AbstractMatrix multiply(AbstractMatrix A, AbstractMatrix B) {
        assert A.getNumCols() == B.getNumRows();
        AbstractMatrix AT = LinearAlgebra.transpose(A);
        int newNumRows = A.getNumRows();
        int newNumCols = B.getNumCols();
        Vector[] newColumns = new Vector[newNumCols];
        for (int j = 0; j < newNumCols; j++) {
            double[] entries = new double[newNumRows];
            for (int i = 0; i < newNumRows; i++) {
                entries[i] = LinearAlgebra.dotProduct(AT.getColumn(i), B.getColumn(j));
            }
            newColumns[j] = new Vector(entries);
        }
        return new AbstractMatrix(newColumns);
    }

    public static SquareMatrix multiply(SquareMatrix A, SquareMatrix B) {
        return new SquareMatrix(LinearAlgebra.multiply((AbstractMatrix) A, (AbstractMatrix) B).getColumns());
    }

    public static GeneralMatrix multiply(GeneralMatrix A, GeneralMatrix B) {
        return new GeneralMatrix(LinearAlgebra.multiply((AbstractMatrix) A, (AbstractMatrix) B).getColumns());
    }

    public static GeneralMatrix multiply(SquareMatrix A, GeneralMatrix B) {
        return new GeneralMatrix(LinearAlgebra.multiply((AbstractMatrix) A, (AbstractMatrix) B).getColumns());
    }

    public static GeneralMatrix multiply(GeneralMatrix A, SquareMatrix B) {
        return new GeneralMatrix(LinearAlgebra.multiply((AbstractMatrix) A, (AbstractMatrix) B).getColumns());
    }

    public static AbstractMatrix transpose(AbstractMatrix A) {
        int newNumRows = A.getNumCols();
        int newNumCols = A.getNumRows();
        Vector[] newColumns = new Vector[newNumCols];
        for (int i = 0; i < A.getNumRows(); i++) {
            double[] entries = new double[newNumRows];
            for (int j = 0; j < A.getNumCols(); j++) {
                entries[j] = A.get(i, j);
            }
            newColumns[i] = new Vector(entries);
        }
        return new AbstractMatrix(newColumns);
    }

    public static double determinate(SquareMatrix A) {
        if (A.getNumRows() == 1 && A.getNumCols() == 1) {
            return A.get(0,0);
        }
        else {
            double sum = 0;
            int j = 0;
            for (int i = 0; i < A.getNumRows(); i++){
                SquareMatrix M = LinearAlgebra.deleteRowAndColumn(A,i,j);
                double detM = LinearAlgebra.determinate(M);
                double C = (i+j)%2==0 ? detM : -detM;
                sum = sum + A.get(i,j)*C;
            }
            return sum;
        }
    }

    public static AbstractMatrix deleteRowAndColumn(AbstractMatrix A, int row, int col) {
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
        return new AbstractMatrix(vectors);
    }

    public static SquareMatrix deleteRowAndColumn(SquareMatrix A, int row, int col) {
        return new SquareMatrix(LinearAlgebra.deleteRowAndColumn((AbstractMatrix) A, row, col).getColumns());
    }

    public static GeneralMatrix deleteRowAndColumn(GeneralMatrix A, int row, int col) {
        return new GeneralMatrix(LinearAlgebra.deleteRowAndColumn((AbstractMatrix) A, row, col).getColumns());
    }

    public static AbstractMatrix replaceColumn(AbstractMatrix A, Vector v, int col) {
        AbstractMatrix B = A.copy();
        B.getColumns()[col] = v;
        return B;
    }

    public static SquareMatrix replaceColumn(SquareMatrix A, Vector v, int col) {
        return new SquareMatrix(LinearAlgebra.replaceColumn((AbstractMatrix) A, v, col).getColumns());
    }


    public static AbstractMatrix divide(AbstractMatrix A, double k) {
        Vector[] newColumns = new Vector[A.getNumCols()];
        for (int j = 0; j < newColumns.length; j++) {
            newColumns[j] = Vector.scale(A.getColumn(j), 1.0/k);
        }
        return new AbstractMatrix(newColumns);
    }

    public static AbstractMatrix swapRows(AbstractMatrix A, int a, int b) {
        assert a < A.getNumRows() && b < A.getNumRows();
        Vector[] newColumns = new Vector[A.getNumCols()];
        for (int j = 0; j < A.getNumCols(); j++) {
            double[] newEntries = A.getColumn(j).getEntries();
            double temp = newEntries[a];
            newEntries[a] = newEntries[b];
            newEntries[b] = temp;
            newColumns[j] = new Vector(newEntries);
        }
        return new AbstractMatrix(newColumns);
    }

    public static SquareMatrix swapRows(SquareMatrix A, int a, int b) {
        return new SquareMatrix(LinearAlgebra.swapRows((AbstractMatrix) A, a, b).getColumns());
    }

    public static GeneralMatrix swapRows(GeneralMatrix A, int a, int b) {
        return new GeneralMatrix(LinearAlgebra.swapRows((AbstractMatrix) A, a, b).getColumns());
    }

    public static AbstractMatrix scaleRow(AbstractMatrix A, int i, double k) {
        Vector[] newColumns = new Vector[A.getNumCols()];
        for (int j = 0; j < A.getNumCols(); j++) {
            double[] newEntries = A.getColumn(j).getEntries();
            newEntries[i] = newEntries[i] * k;
            newColumns[j] = new Vector(newEntries);
        }
        return new AbstractMatrix(newColumns);
    }

    public static SquareMatrix scaleRow(SquareMatrix A, int i, int k) {
        return new SquareMatrix(LinearAlgebra.scaleRow((AbstractMatrix) A, i, k).getColumns());
    }

    public static GeneralMatrix scaleRow(GeneralMatrix A, int i, int k) {
        return new GeneralMatrix(LinearAlgebra.scaleRow((AbstractMatrix) A, i, k).getColumns());
    }

    public static AbstractMatrix addMultipleOfRow(AbstractMatrix A, int a, int b, double k) {
        assert a < A.getNumRows() && b < A.getNumRows();
        Vector[] newColumns = new Vector[A.getNumCols()];
        for (int j = 0; j < A.getNumCols(); j++) {
            double[] newEntries = A.getColumn(j).getEntries();
            newEntries[a] = newEntries[a] + newEntries[b] * k;
            newColumns[j] = new Vector(newEntries);
        }
        return new SquareMatrix(newColumns);
    }

    public static SquareMatrix addMultipleOfRow(SquareMatrix A, int a, int b, double k) {
        return new SquareMatrix(LinearAlgebra.addMultipleOfRow((AbstractMatrix) A, a, b, k).getColumns());
    }

    public static GeneralMatrix addMultipleOfRow(GeneralMatrix A, int a, int b, double k) {
        return new GeneralMatrix(LinearAlgebra.addMultipleOfRow((AbstractMatrix) A, a, b, k).getColumns());
    }

    public static SquareMatrix subMatrix(SquareMatrix A, int i, int j, int rows, int cols) {
        assert i+rows <= A.getNumRows();
        assert j+cols <= A.getNumCols();
        Vector[] newColumns = new Vector[cols];
        for (int l = 0; l < cols; l++) {
            double[] entries = new double[rows];
            for (int k = 0; k < rows; k++) {
                entries[k] = A.get(i+k, j+l);
            }
            newColumns[l] = new Vector(entries);
        }
        return new SquareMatrix(newColumns);
   }

   public static AbstractMatrix replaceInside(AbstractMatrix A, AbstractMatrix B, int i, int j) {
        assert i+B.getNumRows() <= A.getNumRows();
        assert j+B.getNumCols() <= A.getNumCols();
        Vector[] newColumns = new Vector[A.getNumCols()];
        for (int l = 0; l < newColumns.length; l++) {
            double[] entries = new double[A.getNumRows()];
            for (int k = 0; k < entries.length; k++) {
                if (k >= i && k <= i+B.getNumRows() &&
                    l >= j && l <= j+B.getNumCols()) {
                    entries[k] = B.get(k-i, l-j);
                }
                else {
                    entries[k] = A.get(k, l);
                }
            }
            newColumns[l] = new Vector(entries);
        }
        return new AbstractMatrix(newColumns);
   }

   public static SquareMatrix replaceInside(SquareMatrix A, SquareMatrix B, int i, int j) {
       return new SquareMatrix(LinearAlgebra.replaceInside((AbstractMatrix) A, (AbstractMatrix) B, i, j).getColumns());
   }

    public static SquareMatrix makeREF(SquareMatrix A) {
        // Using Gaussian Elimination
        if (A.getNumRows() <= 1 || A.getNumCols() <= 1) { return A; }
        // 1 Position a leading entry
        int i = 0;
        int j = 0;
        while (A.get(i, j) == 0) {
            i++;
            if (i == A.getNumRows()) { i = 0; j++; }
            if (j == A.getNumCols()) { return A; }
        }
        SquareMatrix B = LinearAlgebra.swapRows(A, 0, i);

        // 2 Zero out the leading entry's column
        double leadingEntry = B.get(0, j);
        for (i = 1; i < B.getNumRows(); i++) {
            double multiple = - B.get(i, j)/leadingEntry;
            B = LinearAlgebra.addMultipleOfRow(B, i, 0, multiple);
        }

        // 3 Repeat until we cannot
        return LinearAlgebra.replaceInside(B, 
                LinearAlgebra.makeREF(
                    LinearAlgebra.subMatrix(B, 1, 1, B.getNumRows()-1, B.getNumCols()-1)
                    ),
                1, 1);
    }

    public static SquareMatrix inverse(SquareMatrix A) {
        IdentityMatrix id = new IdentityMatrix(A.getNumRows());
        AugmentedMatrix M = new AugmentedMatrix(A, id);
        return M.getRightMatrix();
    }

    public static Vector coordinateVector(Vector v, Basis B) {
        return new LinearSystem(B,v).solveWithCramersRule();
    }

    public static SquareMatrix calculateChangeOfBasisMatrix(Basis B, Basis C){
        Vector[] coordinateVectors = new Vector[B.getNumCols()];
        for (int j = 0; j < B.getNumCols(); j++) {
            coordinateVectors[j] = coordinateVector(B.getColumn(j), C);
        }
        return new SquareMatrix(coordinateVectors);
    }

    
}
