package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) implements GeometricFigure {
    public Triangle {
        validateSides(a, b, c);
    }

    private static void validateSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Sides of a triangle must be positive: a=%.1f, b=%.1f, c=%.1f", a, b, c));
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(String.format(
                    "Sides do not satisfy the triangle inequality: a=%.1f, b=%.1f, c=%.1f", a, b, c));
        }
    }


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
