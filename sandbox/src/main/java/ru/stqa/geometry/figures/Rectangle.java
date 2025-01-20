package ru.stqa.geometry.figures;

public class Rectangle {

    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static void printRectangleArea(Rectangle s) {
        String text = String.format("The area of a rectangle with sides %.1f and %.1f is %.1f", s.a, s.b, s.getProductOfTwo());
        System.out.println(text);
    }

    private double getProductOfTwo() {
        return this.a * this.b;
    }
}
