package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;


public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquarePerimeter(new Square (5.0));
        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));
        Rectangle.printRectanglePerimeter(new Rectangle(3.0, 5.0));
    }

}
