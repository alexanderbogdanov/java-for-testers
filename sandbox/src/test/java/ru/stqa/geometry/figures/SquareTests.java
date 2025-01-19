package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    Square s = new Square(5.0);

    @Test
    void canCalculateArea() {
        var result = s.getArea();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = s.getPerimeter();
        Assertions.assertEquals(20.0, result);
    }
}
