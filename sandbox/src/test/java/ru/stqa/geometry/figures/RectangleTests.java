package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
