package utility.numbers;

public class Number extends Fraction {

    public Number(double value) {
        super(value);
    }

    @Override
    public String toString() {
        if (this.getValue() == (int) this.getValue()) {
            return String.valueOf((int) this.getValue());
        }
        else {
            return super.toString();
        }
    }
}
