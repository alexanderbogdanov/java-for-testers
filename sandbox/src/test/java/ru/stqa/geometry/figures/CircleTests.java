package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTests {
    Circle c = new Circle(5.0);

    @Test
    void canCalculateArea() {
        var result = c.getArea();
        assertEquals(78.5, result, 0.1);
    }

    @Test
    void canCalculatePerimeter() {
        var result = c.getPerimeter();
        assertEquals(31.4, result, 0.1);
    }

    @ParameterizedTest
    @CsvSource({
            "-5.0, 'Radius of a circle must be positive: r=-5.0'",
            "-0.0, 'Radius of a circle must be positive: r=-0.0'"
    })
    void canNotHaveNegativeOrZeroRadius(double radius, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(radius)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testEquality() {
        var c1 = new Circle(5.0);
        var c2 = new Circle(5.0);
        assertEquals(c1, c2);
    }

    @Test
    void testNonEquality() {
        var c1 = new Circle(5.0);
        var c2 = new Circle(4.0);
        assertNotEquals(c1, c2);
    }

    @Test
    void testHashCode() {
        var c1 = new Circle(5.0);
        var c2 = new Circle(5.0);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
