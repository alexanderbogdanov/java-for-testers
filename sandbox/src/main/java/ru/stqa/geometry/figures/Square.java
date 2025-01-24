package ru.stqa.geometry.figures;

public record Square(double side) implements GeometricFigure {

    public Square {
        validateSide(side);
    }

    private void validateSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Side of a square must be positive: side=%.1f", side));
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
