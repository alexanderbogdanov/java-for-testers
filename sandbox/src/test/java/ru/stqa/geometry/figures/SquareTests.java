package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SquareTests {
    Square s = new Square(5.0);

    @Test
    void canCalculateArea() {
        var result = s.getArea();
        assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = s.getPerimeter();
        assertEquals(20.0, result);
    }

    @ParameterizedTest
    @CsvSource({
            "-5.0, 'Side of a square must be positive: side=-5.0'",
            "0.0, 'Side of a square must be positive: side=0.0'"
    })
    void canNotHaveNegativeOrZeroSide(double side, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Square(side)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        assertEquals(s1, s2);
    }

    @Test
    void testNonEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(4.0);
        assertNotEquals(s1, s2);
    }

    @Test
    void testHashCode() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testToString() {
        var s = new Square(5.0);
        assertEquals("square with a side 5.0", s.describe());
    }

    @Test
    void testPass() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        assertTrue(s1.equals(s2));
    }

}
