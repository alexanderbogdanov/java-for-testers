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
        IllegalArgumentException exeption = assertThrows(
                IllegalArgumentException.class,
                () -> new Square(side)
        );
        assertEquals(expectedMessage, exeption.getMessage());
    }
}
