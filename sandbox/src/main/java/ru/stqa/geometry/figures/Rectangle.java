package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        String text = String.format("The area of a rectangle with sides %f and %f" +
                " is %f%n", a, b, getProductOfTwo(a, b));
        System.out.printf(text);
    }

    public static void printRectangleArea(int a, int b) {
        String text = String.format("The area of a rectangle with sides %d and %d" +
                " is %d", a, b, getProductOfTwo(a, b));
        System.out.printf(text);
    }

    private static int getProductOfTwo(int a, int b) {
        return a * b;
    }

    private static double getProductOfTwo(double a, double b) {
        return a * b;
    }
}
