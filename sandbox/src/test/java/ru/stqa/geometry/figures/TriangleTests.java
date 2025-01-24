package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        var result = t.getArea();
        assertEquals(6.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        var result = t.getPerimeter();
        assertEquals(12.0, result);
    }

    @Test
    void canCalculateAreaForEquilateralTriangle() {
        Triangle t = new Triangle(3.0, 3.0, 3.0);
        var result = t.getArea();
        assertEquals(3.897, result, 0.001);
    }

    @Test
    void canCalculatePerimeterForEquilateralTriangle() {
        Triangle t = new Triangle(3.0, 3.0, 3.0);
        var result = t.getPerimeter();
        assertEquals(9.0, result);
    }

    @Test
    void canCalculateAreaForIsoscelesTriangle() {
        Triangle t = new Triangle(5.0, 5.0, 8.0);
        var result = t.getArea();
        assertEquals(12.0, result, 0.001);
    }

    @Test
    void canCalculatePerimeterForIsoscelesTriangle() {
        Triangle t = new Triangle(5.0, 5.0, 8.0);
        var result = t.getPerimeter();
        assertEquals(18.0, result);
    }

    @Test
    void canCalculateAreaForRightTriangle() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        var result = t.getArea();
        assertEquals(6.0, result);
    }

    @Test
    void canCalculatePerimeterForRightTriangle() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        var result = t.getPerimeter();
        assertEquals(12.0, result);
    }

    @Test
    void canNotHaveNegativeOrZeroSides() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(-3.0, 4.0, 5.0)

        );
        assertEquals("Sides of a triangle must be positive: a=-3.0, b=4.0, c=5.0", exception.getMessage());
    }
}
