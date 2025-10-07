import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void testConstructor() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        assertEquals(1, A.get(0, 0), 0);
        assertEquals(2, A.get(0, 1), 0);
        assertEquals(3, A.get(1, 0), 0);
        assertEquals(4, A.get(1, 1), 0);
    }

    @Test
    public void testGetColumn() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Vector expectedA_1 = new Vector(1, 3);
        Vector actualA_1 = A.getColumn(0);
        assertTrue(expectedA_1.equals(actualA_1));
    }

    @Test
    public void testSize() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        assertEquals(2, A.getNumRows(), 0);
        assertEquals(2, A.getNumCols(), 0);
    }

    @Test
    public void testDeterminate() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        assertEquals(-2, A.determinate(), 0);
        Matrix B = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        assertEquals(0, B.determinate(), 0);
    }

    @Test
    public void testTranspose() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix AT = new Matrix(new double[][]{
            {1, 3},
            {2, 4}
        });
        assertTrue(Matrix.transpose(A).equals(AT));
    }

    @Test
    public void testDeleteRowAndColumn() {
        Matrix A = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Matrix B = Matrix.deleteRowAndColumn(A, 1, 1);
        assertNotEquals(A, B);
        Matrix C = new Matrix(new double[][]{
            {1,3},
            {7,9}
        });
        assertTrue(B.equals(C));
    }

    @Test
    public void testReplaceColumn() {
        Matrix A = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Vector b = new Vector(10,11,12);
        Matrix B = Matrix.replaceColumn(A, b, 1);
        Matrix C = new Matrix(new double[][]{
            {1,10,3},
            {4,11,6},
            {7,12,9}
        });
        assertTrue(B.equals(C));
    }

    @Test
    public void testMultiply() {
        Matrix A = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        Matrix B = new Matrix(new double[][]{
            {1, 2},
            {4, 5},
            {7, 8}
        });
        Matrix C = new Matrix(new double[][]{
            {30, 36},
            {66, 81}
        });
        assertTrue(Matrix.multiply(A, B).equals(C));
        C = new Matrix(new double[][]{
            {9, 12, 15},
            {24, 33, 42},
            {39, 54, 69}
        });
        assertTrue(Matrix.multiply(B, A).equals(C));
    }

    @Test
    public void testSwapRows() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix Actual = Matrix.swapRows(A, 0, 1);
        Matrix Expected = new Matrix(new double[][]{
            {3, 4},
            {1, 2}
        });
        assertTrue(Actual.equals(Expected));
    }

    @Test
    public void testScaleRows() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix Actual = Matrix.scaleRow(A, 1, 2);
        Matrix Expected = new Matrix(new double[][]{
            {1, 2},
            {6, 8}
        });
        assertTrue(Actual.equals(Expected));
    }

    @Test
    public void testToString() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        String string = "\n1.0 2.0 \n3.0 4.0 \n";
        assertEquals(string, A.toString());
    }

    @Test
    public void testCopy() {
        Matrix A = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix B = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        B.equals(A.copy());
    }
}
