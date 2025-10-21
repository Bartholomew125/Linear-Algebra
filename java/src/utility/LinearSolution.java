package utility;

import implementations.Vector;

/**
 * LinearSolution
 */
public class LinearSolution {

    private final Vector solutionVector;
    private final boolean scalarConstant;

    public LinearSolution(Vector solutionVector) {
        this(solutionVector, false);
    }

    public LinearSolution(Vector solutionVector, boolean scalarConstant) {
        this.solutionVector = solutionVector;
        this.scalarConstant = scalarConstant;
    }

    @Override
    public String toString() {
        String s = this.scalarConstant ?
            "t * \n" : "";
        return s+this.solutionVector.toString();
    }
}
