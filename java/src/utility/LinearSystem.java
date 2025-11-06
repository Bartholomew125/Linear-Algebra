package utility;
import implementations.SquareMatrix;
import implementations.Vector;
import utility.LinearAlgebra;

public class LinearSystem {
    
    private SquareMatrix A;
    private Vector b;

    public LinearSystem(SquareMatrix A, Vector b) {
        this.A = A;
        this.b = b;
    }

    public LinearSolution solve() {
        if (this.A.determinate() == 0) {
            System.out.println("NOT IMPLEMENTED. There are infinitely many solution");
            return new LinearSolution(this.b, true);
        }
        else {
            return new LinearSolution(this.solveWithCramersRule());
        }
    }

    public Vector solveWithCramersRule() {
        double[] entries = new double[this.b.getSize()];
        double detA = this.A.determinate();
        for (int j = 0; j < entries.length; j++) {
            SquareMatrix Aj = LinearAlgebra.replaceColumn(A, b, j);
            double detAj = Aj.determinate();
            entries[j] = detAj/detA;
        }
        return new Vector(entries);
    }
}
