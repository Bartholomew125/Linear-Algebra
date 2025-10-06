public class LinearSystem {
    
    private Matrix A;
    private Vector b;

    public LinearSystem(Matrix A, Vector b) {
        this.A = A;
        this.b = b;
    }

    public Vector solve() {
        return new Vector();
    }
}
