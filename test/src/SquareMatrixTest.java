import org.junit.Test;
import static org.junit.Assert.*;
import implementations.SquareMatrix;
import implementations.Vector;

public class SquareMatrixTest {

    @Test
    public void testDeterminate() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        assertEquals(-2, A.determinate(), 0);
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        assertEquals(0, B.determinate(), 0);
    }

    @Test
    public void testConstructor() {
        SquareMatrix A = new SquareMatrix(new double[][]{
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
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Vector expectedA_1 = new Vector(1, 3);
        Vector actualA_1 = A.getColumn(0);
        assertTrue(expectedA_1.equals(actualA_1));
    }

    @Test
    public void testSize() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        assertEquals(2, A.getNumRows(), 0);
        assertEquals(2, A.getNumCols(), 0);
    }


    @Test
    public void testToString() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        String string = "\n1.0 2.0 \n3.0 4.0 \n";
        assertEquals(string, A.toString());
    }

    @Test
    public void testCopy() {
        SquareMatrix A = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        SquareMatrix B = new SquareMatrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        B.equals(A.copy());
    }
}
