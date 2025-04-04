package ru.stqa.geometry;

import ru.stqa.geometry.figures.*;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(1, 100.0));
        var squares = Stream.generate(randomSquare).limit(5);

//        Square square = new Square(7.0);
//        Circle circle = new Circle(5.0);
//        Rectangle rectangle = new Rectangle(3.0, 5.0);
//        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
//
//
//        GeometricPrinter.printArea(square);
//        GeometricPrinter.printPerimeter(square);
//
//        GeometricPrinter.printArea(circle);
//        GeometricPrinter.printPerimeter(circle);
//
//        GeometricPrinter.printArea(rectangle);
//        GeometricPrinter.printPerimeter(rectangle);
//
//        GeometricPrinter.printArea(triangle);
//        GeometricPrinter.printPerimeter(triangle);

//        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//        for (var square : squares) {
//            GeometricPrinter.printArea(square);
//        }

        Consumer<Square> print = GeometricPrinter::printArea;
        squares.forEach(print);
    }

}
