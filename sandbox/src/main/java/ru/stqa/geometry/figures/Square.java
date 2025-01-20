package ru.stqa.geometry.figures;

public record Square(double side) {
    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return 4 * this.side;
    }

    public static void printSquareArea(Square s) {
        String text = String.format("The area of a square with a side %.1f is %.1f",
                s.side, s.getArea());
        System.out.println(text);
    }

    public static void printSquarePerimeter(Square s) {
        String text = String.format("The perimeter of a square with a side %.1f is %.1f",
                s.side, s.getPerimeter());
        System.out.println(text);
    }


}
