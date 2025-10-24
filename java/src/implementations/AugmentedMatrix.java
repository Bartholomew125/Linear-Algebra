package implementations;

import abstracts.AbstractMatrix;

public class AugmentedMatrix extends AbstractMatrix {

    private final SquareMatrix leftMatrix;
    private final SquareMatrix rightMatrix;

    public AugmentedMatrix(SquareMatrix A, SquareMatrix B) {
        super(A.getNumRows(), A.getNumCols() + B.getNumCols());
        assert A.getNumRows() == B.getNumRows();
        this.leftMatrix = A;
        this.rightMatrix = B;
    }

    @Override
    public double get(int row, int column) {
        return column < this.leftMatrix.getNumCols() ?
            this.leftMatrix.get(row, column) : 
            this.rightMatrix.get(row, column-this.leftMatrix.getNumCols());
    }

    @Override
    public Vector getColumn(int j) {
        return j < this.leftMatrix.getNumCols() ?
            this.leftMatrix.getColumn(j) :
            this.rightMatrix.getColumn(j);
    }

    public SquareMatrix getLeftMatrix() {
        return this.leftMatrix;
    }

    public SquareMatrix getRightMatrix() {
        return this.rightMatrix;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < this.getNumCols(); j++) {
                if (j == this.getNumCols()/2) {
                    s += "| ";
                }
                s += this.get(i, j) + " ";
            }
            s += "\n";
        }
        return s;
    }
}
