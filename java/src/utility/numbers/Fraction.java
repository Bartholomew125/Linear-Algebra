package utility.numbers;

import utility.MyMath;

public class Fraction {
    
    private int numerator;
    private int denominator;

    public Fraction(double value) {
        this(Fraction.fromValue(value));
    }

    public Fraction(Fraction fraction) {
        this(fraction.getNumerator(), fraction.getDenominator());
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double getValue() {
        return (double) this.numerator/(double) this.denominator;
    }

    public Fraction minimize() {
        int gcd = MyMath.gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
        return this;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public static Fraction fromValue(double value) {
        Fraction f = new Fraction(1,1);
        while (f.getValue() != value) {
            if (f.getValue() > value) {
                f.setDenominator(f.getDenominator() + 1);
            }
            else {
                f.setNumerator(f.getNumerator() + 1);
            }
        }
        return f;
    }

    public void add(Fraction fraction) {
        this.setNumerator(this.getNumerator() + fraction.getNumerator());
        this.setDenominator(this.getDenominator() + fraction.getDenominator());
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}
