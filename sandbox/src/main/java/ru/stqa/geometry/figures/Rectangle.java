package ru.stqa.geometry.figures;

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


}
