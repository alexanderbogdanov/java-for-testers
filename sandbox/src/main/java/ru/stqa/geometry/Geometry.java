package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;


public class Geometry {
    public static void main(String[] args) {
        Square square = new Square(5.0);
        square.printSquareArea();
        square.printSquarePerimeter();
        Rectangle.printRectangleArea(15.0, 17.0);
        Rectangle.printRectangleArea(3.0, 5.0);
    }

}
