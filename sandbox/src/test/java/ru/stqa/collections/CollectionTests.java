package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTests {

    @Test
    void arrayTests() {
//        var array = new String[]{"one", "two", "three"};
        var array = new String[3];
        array[0] = "one";
        array[1] = "two";
        array[2] = "three";

        assertEquals(3, array.length);
        assertEquals("one", array[0]);

        array[0] = "five";
        assertEquals("five", array[0]);
    }


    @Test
    void listTests() {
        var list = new ArrayList<String>(); // Immutable
        list.add("one");
        list.add("two");
        list.add("three");
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));

        list.set(0, "five");
        assertEquals("five", list.get(0));

        var list2 = List.of("one", "two", "three"); // Immutable, fixed size
        assertEquals(3, list2.size());
        assertEquals("one", list2.get(0));

//        list2.set(0, "five"); // UnsupportedOperationException

        var list3 = new ArrayList<>(List.of("one", "two", "three")); // Mutable
        list3.set(0, "five");
        assertEquals("five", list3.get(0));


    }
}
