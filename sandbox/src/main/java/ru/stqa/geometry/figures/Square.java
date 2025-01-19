package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String text = String.format("The area of a square with a side %.1f is %.1f",
                side, getArea(side));
        System.out.println(text);
    }

    public static double getArea(double a) {
        return a * a;
    }

    public static double getPerimeter(double a) {
        return 4 * a;
    }
}
