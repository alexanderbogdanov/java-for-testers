package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

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

    @Test
    void canNotHaveNegativeSide() {
        try {
            new Square(-5.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Side of a square can not be negative: -5.0", e.getMessage());
        }
    }
}
