package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
