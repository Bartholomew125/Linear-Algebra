package implementations;

public class Basis extends SquareMatrix {

    public Basis(Vector... basisVectors) {
        super(basisVectors);
    }

    public Basis(double[][] entries) {
        super(entries);
    }
}
