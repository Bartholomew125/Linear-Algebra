package implementations;

/**
 * StandardBasis
 */
public class StandardBasis extends Basis {

    public StandardBasis(int n) {
        super(generate(n));
    }

    private static Vector[] generate(int n) {
        Vector[] columns = new Vector[n];
        for (int i = 0; i < n; i++) {
            double[] entries = new double[n];
            entries[i] = 1;
            columns[i] = new Vector(entries);
        }
        return columns;
    }
    
}
