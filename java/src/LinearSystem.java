public class LinearSystem {
    
    private Matrix A;
    private Vector b;

    public LinearSystem(Matrix A, Vector b) {
        this.A = A;
        this.b = b;
    }

    public Vector solveWithCramersRule() {
        double[] entries = new double[this.b.getSize()];
        double detA = this.A.determinate();
        for (int j = 0; j < entries.length; j++) {
            Matrix Aj = Matrix.replaceColumn(A, b);
            double detAj = Aj.determinate();
            entries[j] = detA/detAj;
        }
        return new Vector(entries);
    }
}
