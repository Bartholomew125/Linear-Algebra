package implementations;

/**
 * IdentityMatrix
 */
public class IdentityMatrix extends SquareMatrix {
    
    public IdentityMatrix(int size) {
        super(createColumns(size));
    }

    private static Vector[] createColumns(int size) {
        Vector[] columns = new Vector[size];
        for (int i = 0; i < size; i++) {
            double[] entries = new double[size];
            entries[i] = 1;
            columns[i] = new Vector(entries);
        }
        return columns;
    }
}
