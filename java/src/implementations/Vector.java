package implementations;
import utility.LinearAlgebra;

public class Vector {

    private final double[] entries;
    private final int size;
    private Basis basis;

    public Vector(double... entries) {
        this.entries = entries;
        this.size = entries.length;
        this.basis = null;
    }

    public Vector(Basis B, double... entries) {
        this.entries = entries;
        this.size = entries.length;
        this.basis = B;
    }

    public double get(int i) {
        return this.entries[i];
    }

    public double[] getEntries() {
        return this.entries.clone();
    }

    public int getSize() {
        return this.size;
    }

    public Basis getBasis() {
        return this.basis;
    }

    public double length() {
        return Math.sqrt(LinearAlgebra.dotProduct(this,this));
    }
    
    public static Vector scale(Vector v, double k) {
        double[] entries = new double[v.getSize()];
        for (int i = 0; i < v.getSize(); i++) {
             entries[i] = v.get(i) * k;
        }
        return new Vector(entries);
    }

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
            if (Math.abs(this.get(i) - v.get(i)) > 0.000000000000000000001) {
                return false;
            }
        }
        return true;
    }

    
}
