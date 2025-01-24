package ru.stqa.geometry.figures;

public record Square(double side) implements GeometricFigure {

    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Side of a square can not be negative: " + side);
        }
    }


    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return 4 * this.side;
    }

    public String describe() {
        return String.format("square with a side %.1f", this.side);
    }

}
