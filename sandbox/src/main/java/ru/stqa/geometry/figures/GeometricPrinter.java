package ru.stqa.geometry.figures;

public class GeometricPrinter {

    public static void printArea(GeometricFigure figure) {
    System.out.printf("The area of а %s is %.1f%n",
                figure.describe(),
                figure.getArea());
    }

    public static void printPerimeter(GeometricFigure figure) {
        System.out.printf("The perimeter of а %s is %.1f%n",
                figure.describe(),
                figure.getPerimeter());
    }
}
