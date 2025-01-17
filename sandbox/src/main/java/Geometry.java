public class Geometry {
    public static void main(String[] args) {
        var side = 10;
        System.out.println("Square area: " + geSquare(side));
        printSquareArea(34);
        printRectangleArea(10, 20);
        printRectangleArea(10.5, 20.5);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("The area of a rectangle with sides "
                + a + " and " + b + " is " + getProductOfTwo(a, b));
    }

    private static void printRectangleArea(int a, int b) {
        System.out.println("The area of a rectangle with sides "
                + a + " and " + b + " is " + getProductOfTwo(a, b));
    }



    private static int getProductOfTwo(int a, int b) {
        return a * b;
    }

    private static double getProductOfTwo(double a, double b) {
        return a * b;
    }

    static void printSquareArea(int side) {
        System.out.
                println("The area of a square with a side "
                        + side + " is " + geSquare(side));
    }

    private static int geSquare(int a) {
        return a * a;
    }


}
