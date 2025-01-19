package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;


public class Geometry {
    public static void main(String[] args) {
        var side = 10.0;
        System.out.println("Square area for the square with the side " + side + " is " + Square.getSquare(side));
        Square.printSquareArea(5.0);
        Rectangle.printRectangleArea(10.0, 20.0);
    }

}
