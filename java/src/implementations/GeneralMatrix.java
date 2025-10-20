package implementations;

import abstracts.AbstractMatrix;

/**
 * GeneralMatrix
 */
public class GeneralMatrix extends AbstractMatrix {

    public GeneralMatrix(Vector... columns) {
        super(columns);
    }
    
    public GeneralMatrix(double[][] entries) {
        super(entries);
    }
}
