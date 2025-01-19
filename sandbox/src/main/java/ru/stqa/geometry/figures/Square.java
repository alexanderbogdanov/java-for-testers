package ru.stqa.geometry.figures;

public class Square {

    private final double side;

    public Square(double side) {
        this.side = side;
    }


    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return 4 * this.side;
    }

    public void printSquareArea() {
        String text = String.format("The area of a square with a side %.1f is %.1f",
                this.side, this.getArea());
        System.out.println(text);
    }

    public void printSquarePerimeter() {
        String text = String.format("The perimeter of a square with a side %.1f is %.1f",
                this.side, this.getPerimeter());
        System.out.println(text);
    }


}
