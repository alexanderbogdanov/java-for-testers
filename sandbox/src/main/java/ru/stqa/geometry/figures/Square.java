package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(int side) {
        String text = String.format("The area of a square with a side %d = %d%n",
                side, geSquare(side));
        System.out.printf(text);
    }

    public static int geSquare(int a) {
        return a * a;
    }
}
