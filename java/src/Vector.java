public class Vector {

    private final double[] entries;
    private final int size;

    public Vector(double... entries) {
        this.entries = entries;
        this.size = entries.length;
    }

    public double get(int i) {
        return this.entries[i];
    }

    public int getSize() {
        return this.size;
    }

    public double dotProduct(Vector other) {
        assert other.getSize() == this.getSize();
        double dotProduct = 0;
        for (int i = 0; i < this.getSize(); i++) {
            dotProduct = dotProduct + this.get(i)*other.get(i);
        }
        return dotProduct;
    }

    public double length() {
        return Math.sqrt(this.dotProduct(this));
    }

    public static Matrix asMatrix(Vector v) {
        return new Matrix(new Vector[]{v.copy()});
    }

    // public static Vector project(Vector v, Vector w) {
    //     Matrix top = Matrix.mult(w, Vector.transpose(w));
    // }

    @Override
    public String toString() {
        String string = "\n";
        for (int i = 0; i < this.size; i++) {
            string = string + this.entries[i] + "\n";
        }
        return string;
    }

    public Vector copy() {
        return new Vector(this.entries);
    }

    @Override
    public boolean equals(Object obj) {
        Vector v = (Vector) obj;
        if (this.getSize() != v.getSize()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.get(i) != v.get(i)) {
                return false;
            }
        }
        return true;
    }

    
}
