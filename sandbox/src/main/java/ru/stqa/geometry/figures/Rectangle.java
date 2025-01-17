package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        System.out.println("The area of a rectangle with sides "
                + a + " and " + b + " is " + getProductOfTwo(a, b));
    }

    public static void printRectangleArea(int a, int b) {
        System.out.println("The area of a rectangle with sides "
                + a + " and " + b + " is " + getProductOfTwo(a, b));
    }

    private static int getProductOfTwo(int a, int b) {
        return a * b;
    }

    private static double getProductOfTwo(double a, double b) {
        return a * b;
    }
}
