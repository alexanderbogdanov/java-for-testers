package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) implements GeometricFigure {
    public double getArea() {
        double semiPerimeter = this.getPerimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - this.a)
                * (semiPerimeter - this.b) * (semiPerimeter - this.c));
    }

    public double getPerimeter() {
        return this.a + this.b + this.c;

    }

    public String describe() {
        return String.format("triangle with sides %.1f, %.1f, and %.1f", this.a, this.b, this.c);
    }
}
