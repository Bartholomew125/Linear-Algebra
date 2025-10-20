import org.junit.Test;
import static org.junit.Assert.*;

import abstracts.AbstractMatrix;
import implementations.SquareMatrix;
import implementations.Vector;
import utility.LinearAlgebra;


/**
 * LinearAlgebraTest
 */
public class LinearAlgebraTest {

    @Test
    public void testTranspose() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        AbstractMatrix AT = new AbstractMatrix(new double[][]{
            {1, 3},
            {2, 4}
        });
        assertTrue(LinearAlgebra.transpose(A).equals(AT));
    }

    @Test
    public void testDeleteRowAndColumn() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        AbstractMatrix B = LinearAlgebra.deleteRowAndColumn(A, 1, 1);
        assertNotEquals(A, B);
        AbstractMatrix C = new AbstractMatrix(new double[][]{
            {1,3},
            {7,9}
        });
        assertTrue(B.equals(C));
    }

    @Test
    public void testReplaceColumn() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Vector b = new Vector(10,11,12);
        AbstractMatrix B = LinearAlgebra.replaceColumn(A, b, 1);
        AbstractMatrix C = new AbstractMatrix(new double[][]{
            {1,10,3},
            {4,11,6},
            {7,12,9}
        });
        assertTrue(B.equals(C));
    }

    @Test
    public void testMultiply() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        AbstractMatrix B = new AbstractMatrix(new double[][]{
            {1, 2},
            {4, 5},
            {7, 8}
        });
        AbstractMatrix C = new AbstractMatrix(new double[][]{
            {30, 36},
            {66, 81}
        });
        assertTrue(LinearAlgebra.multiply(A, B).equals(C));
        C = new AbstractMatrix(new double[][]{
            {9, 12, 15},
            {24, 33, 42},
            {39, 54, 69}
        });
        assertTrue(LinearAlgebra.multiply(B, A).equals(C));
    }

    @Test
    public void testSwapRows() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        AbstractMatrix Actual = LinearAlgebra.swapRows(A, 0, 1);
        AbstractMatrix Expected = new AbstractMatrix(new double[][]{
            {3, 4},
            {1, 2}
        });
        assertTrue(Actual.equals(Expected));
    }

    @Test
    public void testScaleRow() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        AbstractMatrix Actual = LinearAlgebra.scaleRow(A, 1, 2);
        AbstractMatrix Expected = new AbstractMatrix(new double[][]{
            {1, 2},
            {6, 8}
        });
        assertTrue(Actual.equals(Expected));
    }

    @Test
    public void testAddMultipleOfRow() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        AbstractMatrix Actual = LinearAlgebra.addMultipleOfRow(A, 0, 1, 2);
        AbstractMatrix Expected = new AbstractMatrix(new double[][]{
            {7, 10},
            {3, 4}
        });
        assertTrue(Actual.equals(Expected));
    }

    @Test
    public void testSubMatrix() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        SquareMatrix subA = LinearAlgebra.subMatrix(A, 1, 1, 2, 2);
        SquareMatrix B = new SquareMatrix(new double[][]{
            {5, 6},
            {8, 9}
        });
        assertTrue(subA.equals(B));
    }

    @Test
    public void testReplaceInside() {
        AbstractMatrix A = new AbstractMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        AbstractMatrix B = new AbstractMatrix(new double[][]{
            {10, 11},
            {12, 13}
        });
        AbstractMatrix C = new AbstractMatrix(new double[][]{
            {1, 2, 3},
            {4, 10, 11},
            {7, 12, 13}
        });
        AbstractMatrix A_B = LinearAlgebra.replaceInside(A, B, 1, 1);
        assertTrue(A_B.equals(C));
    }

    @Test
    public void test1MakeREF() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2},
            {0, -2}
        });
        SquareMatrix C = LinearAlgebra.makeREF(A);
        assertTrue(B.equals(C));
    }

    @Test
    public void test2MakeREF() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {0, -3, -6},
            {0, 0, 0}
        });
        SquareMatrix C = LinearAlgebra.makeREF(A);
        assertTrue(B.equals(C));
    }

    @Test
    public void test3MakeREF() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {0, 1, 4},
            {0, 0, 1}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {0, 1, 4},
            {0, 0, 1}
        });
        SquareMatrix C = LinearAlgebra.makeREF(A);
        assertTrue(B.equals(C));
    }

    @Test
    public void test4MakeREF() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {0, 2, 3},
            {1, 4, 5},
            {0, 0, 6}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 4, 5},
            {0, 2, 3},
            {0, 0, 6}
        });
        SquareMatrix C = LinearAlgebra.makeREF(A);
        assertTrue(B.equals(C));
    }

    @Test
    public void test5MakeREF() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2, -1, 3},
            {2, 4, 1, 7},
            {-1, -2, 2, -1},
            {3, 6, 0, 9}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2, -1, 3},
            {0, 0, 3, 1},
            {0, 0, 0, 5.0/3.0},
            {0, 0, 0, 0}
        });
        SquareMatrix C = LinearAlgebra.makeREF(A);
        assertTrue(B.equals(C));
    }
}
