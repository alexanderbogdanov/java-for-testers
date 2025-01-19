package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var result = Square.getArea(5.0);
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = Square.getPerimeter(5.0);
        Assertions.assertEquals(20.0, result);
    }
}
