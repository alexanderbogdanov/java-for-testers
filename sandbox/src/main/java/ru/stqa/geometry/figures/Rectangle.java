package ru.stqa.geometry.figures;

public record Rectangle(double a, double b) {
    private double getProductOfTwo() {
        return this.a * this.b;
    }

    private double getPerimeter() {
        return 2 * (this.a + this.b);
    }

    public static void printRectangleArea(Rectangle s) {
        String text = String.format("The area of a rectangle with sides %.1f and %.1f is %.1f", s.a, s.b, s.getProductOfTwo());
        System.out.println(text);
    }

    public static void printRectanglePerimeter(Rectangle s) {
        String text = String.format("The perimeter of a rectangle with sides %.1f and %.1f is %.1f", s.a, s.b, s.getPerimeter());
        System.out.println(text);
    }

}
