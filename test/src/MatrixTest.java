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
