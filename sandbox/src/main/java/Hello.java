import java.io.File;

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
            var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
        System.out.println(new File("").getAbsolutePath());
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
