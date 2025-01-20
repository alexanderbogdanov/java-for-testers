package ru.stqa.geometry.figures;

import static java.lang.Math.PI;

public record Circle(double radius) implements GeometricFigure {
    public double getArea() {
        return PI * this.radius * this.radius;
    }

    public double getPerimeter() {
        return 2 * PI * this.radius;
    }

    public String describe() {
        return String.format("circle with a radius %.1f", this.radius);
    }

    public static void printCircleArea(Circle c) {
        String text = String.format("The area of a circle with a radius %.1f is %.1f", c.radius, c.getArea());
        System.out.println(text);
    }

    public static void printCirclePerimeter(Circle c) {
        String text = String.format("The perimeter of a circle with a radius %.1f is %.1f", c.radius, c.getPerimeter());
        System.out.println(text);
    }
}
