package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;


public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(4.0);
        Square.printSquareArea(5.0);
        Rectangle.printRectangleArea(15.0, 17.0);
        Rectangle.printRectangleArea(3.0, 5.0);
    }

}
