package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String text = String.format("The area of a square with a side %.1f = %.1f",
                side, getSquare(side));
        System.out.println(text);
    }

    public static double getSquare(double a) {
        return a * a;
    }
}
