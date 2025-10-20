import org.junit.Test;
import static org.junit.Assert.*;

import implementations.GeneralMatrix;
import implementations.SquareMatrix;
import implementations.Vector;
import utility.LinearAlgebra;

public class VectorTest {

    @Test
    public void testConstructor() {
        Vector v = new Vector(1,2,3);
        assertEquals(1,v.get(0), 0);
        assertEquals(2,v.get(1), 0);
        assertEquals(3,v.get(2), 0);
    }

    @Test
    public void testGetEntries() {
        Vector v = new Vector(1,2,3);
        assertArrayEquals(new double[]{1,2,3}, v.getEntries(), 0);
    }

    @Test
    public void testGetSize() {
        Vector v = new Vector();
        assertEquals(0, v.getSize(), 0);
        v = new Vector(1);
        assertEquals(1, v.getSize(), 0);
        v = new Vector(new double[200]);
        assertEquals(200, v.getSize(), 0);
    }

    @Test
    public void testDotProduct() {
        Vector v = new Vector(1,2,3);
        Vector w = new Vector(4,5,6);
        assertEquals(1*4+2*5+3*6, LinearAlgebra.dotProduct(v,w), 0);
        assertEquals(1*4+2*5+3*6, LinearAlgebra.dotProduct(w,v), 0);
        assertEquals(1*1+2*2+3*3, LinearAlgebra.dotProduct(v,v), 0);
        assertEquals(4*4+5*5+6*6, LinearAlgebra.dotProduct(w,w), 0);
    }

    @Test
    public void testLength() {
        Vector v = new Vector(1,2,3);
        assertEquals(3.7416573868, v.length(), 0.0001);
        Vector w = new Vector(3,4);
        assertEquals(5, w.length(), 0);
    }

    @Test
    public void testScale() {
        Vector v = new Vector(1,2,3);
        Vector w = new Vector(2,4,6);
        Vector u = Vector.scale(v, 2);
        assertTrue(w.equals(u));
    }

    @Test
    public void testAsMatrix() {
        Vector v = new Vector(1,2,3);
        GeneralMatrix V = LinearAlgebra.asMatrix(v);
        GeneralMatrix M = new GeneralMatrix(new double[][]{
            {1},
            {2},
            {3}
        });
        assertTrue(V.equals(M));
    }

    @Test
    public void testProject() {
        Vector v = new Vector(1, 1);
        Vector u = new Vector(1, 0);
        Vector w = LinearAlgebra.project(v, u);
        assertTrue(w.equals(u));
        v = new Vector(-2, 3);
        u = new Vector(2,-5);
        w = LinearAlgebra.project(v, u);
        assertEquals(-1.31, w.get(0), 0.01);
        assertEquals(3.28, w.get(1), 0.01);
    }
}
