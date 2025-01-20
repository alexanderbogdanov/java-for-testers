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

}
