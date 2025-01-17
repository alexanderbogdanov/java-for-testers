package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(int side) {
        System.out.
                println("The area of a square with a side "
                        + side + " is " + geSquare(side));
    }

    public static int geSquare(int a) {
        return a * a;
    }
}
