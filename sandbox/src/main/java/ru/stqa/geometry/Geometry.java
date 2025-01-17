package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        var side = 10;
        System.out.println("ru.stqa.geometry.figures.Square area: " + Square.geSquare(side));
        Square.printSquareArea(34);
        Rectangle.printRectangleArea(10, 20);
        Rectangle.printRectangleArea(10.5, 20.5);
    }

}
