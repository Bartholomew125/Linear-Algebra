package implementations;
import abstracts.AbstractMatrix;
import utility.LinearAlgebra;

public class SquareMatrix extends AbstractMatrix {

    private final double determinate;

    public SquareMatrix(Vector... columnVectors) {
        super(columnVectors);
        assert this.getNumRows() == this.getNumCols();
        this.determinate = LinearAlgebra.determinate(this);
    }
    
    public SquareMatrix(double[][] entries) {
        super(entries);
        assert this.getNumRows() == this.getNumCols();
        this.determinate = LinearAlgebra.determinate(this);
    }

    public double determinate() {
        return this.determinate;
    }
}
