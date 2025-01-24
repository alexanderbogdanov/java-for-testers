public class Hello {
    public static void main(String[] args) {
            var x = 1;
            var y = 1;
            if (y == 0) {
                System.out.println("Can't divide by zero");
            } else {
                var z = divide(x, y);
                System.out.println(z);
            }
            System.out.println("Hello, World!");
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
