package ru.stqa.geometry.figures;

import java.util.Objects;

public record Rectangle(double a, double b) implements GeometricFigure {

    public Rectangle {
        validateSides(a, b);
    }

    private void validateSides(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Sides of a rectangle must be positive: a=%.1f, b=%.1f", a, b));
        }
    }


    public double getArea() {
        return this.a * this.b;
    }

    public double getPerimeter() {
        return 2 * (this.a + this.b);
    }

    public String describe() {
        return String.format("rectangle with sides %.1f and %.1f", this.a, this.b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0 ||
                Double.compare(a, rectangle.b) == 0 && Double.compare(b, rectangle.a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
