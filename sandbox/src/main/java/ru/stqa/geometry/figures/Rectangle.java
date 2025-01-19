package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        String text = String.format("The area of a rectangle with sides %.1f and %.1f is %.1f", a, b, getProductOfTwo(a, b));
        System.out.println(text);
    }

    private static double getProductOfTwo(double a, double b) {
        return a * b;
    }
}
