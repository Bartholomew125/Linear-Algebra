import org.junit.Test;
import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void testConstructor() {
        Vector v = new Vector(1,2,3);
        assertEquals(1,v.get(0), 0);
        assertEquals(2,v.get(1), 0);
        assertEquals(3,v.get(2), 0);
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
        assertEquals(1*4+2*5+3*6, v.dotProduct(w), 0);
        assertEquals(1*4+2*5+3*6, w.dotProduct(v), 0);
        assertEquals(1*1+2*2+3*3, v.dotProduct(v), 0);
        assertEquals(4*4+5*5+6*6, w.dotProduct(w), 0);
    }

    @Test
    public void testLength() {
        Vector v = new Vector(1,2,3);
        assertEquals(3.7416573868, v.length(), 0.0001);
        Vector w = new Vector(3,4);
        assertEquals(5, w.length(), 0);
    }
}
