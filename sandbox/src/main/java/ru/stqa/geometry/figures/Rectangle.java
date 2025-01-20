package ru.stqa.geometry.figures;

public record Rectangle(double a, double b) implements GeometricFigure {
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
