package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "-3.0, 4.0, 5.0, 'Sides of a triangle must be positive: a=-3.0, b=4.0, c=5.0'",
            "3.0, 0.0, 5.0, 'Sides of a triangle must be positive: a=3.0, b=0.0, c=5.0'",
            "1.0, 2.0, 3.0, 'Sides do not satisfy the triangle inequality: a=1.0, b=2.0, c=3.0'",
            "5.0, 1.0, 1.0, 'Sides do not satisfy the triangle inequality: a=5.0, b=1.0, c=1.0'"
    })
    void throwsExceptionForInvalidSides(double a, double b, double c, String expectedMessage) {
        System.out.printf("Testing invalid triangle with sides: a=%.1f, b=%.1f, c=%.1f%n", a, b, c);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(a, b,c)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}
