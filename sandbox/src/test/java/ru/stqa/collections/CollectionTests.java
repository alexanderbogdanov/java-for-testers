package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionTests {

    @Test
    void arrayTests() {
//        var array = new String[]{"one", "two", "three"};
        var array = new String[3];
        array[0] = "one";
        array[1] = "two";
        array[2] = "three";

        Assertions.assertEquals(3, array.length);
        Assertions.assertEquals("one", array[0]);

        array[0] = "five";
        Assertions.assertEquals("five", array[0]);
    }
}
