package utility;

public class MyMath {
    
    public static int gcd(int a, int b) {
        if (a > b) return gcd(a-b, b);
        else if (a < b) return gcd(a, b-a);
        else return a;
    }
}
