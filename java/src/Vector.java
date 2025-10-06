public class Vector {

    private double[] entries;
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

    
}
