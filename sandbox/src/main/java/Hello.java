public class Hello {
    public static void main(String[] args) {
        try {
            String z = calculate();
            System.out.println("Hello, World!");

        } catch (ArithmeticException e) {
            System.out.println("Caught an exception: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static String calculate() {
        var x = 1;
        var y = 0;
        int z = divide(x, y);
        return String.format("The result of division of %d by %d is %d%n", x, y, z);
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
