public class Main {

    public static void main(String[] args) {
        Matrix A = new Matrix(new double[][]{
                { 1, 0, 6},
                {-3, 4, 30},
                {-1,-2, 8}
        });
        System.out.println(A.toString());
        System.out.println(A.determinate());

        // Matrix A = new Matrix(new double[][]{{2}});
        // System.out.println(A.determinate());
        //
        // Matrix B = new Matrix(new Vector(2));
        // System.out.println(B.determinate());
    }
    
}
