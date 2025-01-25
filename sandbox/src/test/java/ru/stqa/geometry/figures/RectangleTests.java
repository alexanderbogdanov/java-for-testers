package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RectangleTests {
    Rectangle r = new Rectangle(3.0, 5.0);

    @Test
    void canCalculateArea() {
        var result = r.getArea();
        assertEquals(15.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = r.getPerimeter();
        assertEquals(16.0, result);
    }


    @ParameterizedTest
    @CsvSource({
            "-3.0, 5.0, 'Sides of a rectangle must be positive: a=-3.0, b=5.0'",
            "3.0, -5.0, 'Sides of a rectangle must be positive: a=3.0, b=-5.0'",
            "-3.0, -5.0, 'Sides of a rectangle must be positive: a=-3.0, b=-5.0'",
            "0.0, 5.0, 'Sides of a rectangle must be positive: a=0.0, b=5.0'",
            "3.0, 0.0, 'Sides of a rectangle must be positive: a=3.0, b=0.0'",
            "0.0, 0.0, 'Sides of a rectangle must be positive: a=0.0, b=0.0'"
    })
    void canNotHaveNegativeSides(double a, double b, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(a, b)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testEquality() {
        var r1 = new Rectangle(3.0, 5.0);
        var r2 = new Rectangle(3.0, 5.0);
        assertEquals(r1, r2);
    }

    @Test
    void testEqualityWithSwappedSides() {
        var r1 = new Rectangle(3.0, 5.0);
        var r2 = new Rectangle(5.0, 3.0);
        assertEquals(r1, r2);
    }
}
