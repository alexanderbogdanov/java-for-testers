package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.stqa.addressbook.model.GroupData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupCreationTests extends TestBase {


    @ParameterizedTest
    @ValueSource(strings = {"group name", "group name 2", "group name 3"})
    public void testGroupCreation(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    public void testGroupCreationWithBlankFields() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void testGroupCreationWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

    @Test
    public void testMultipleGroupCreation() {
        int n = 10;
        int groupCount = app.groups().getCount();
        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i * 10), "group header", "group footer"));
        }
        int newGroupCount = app.groups().getCount();
        assertEquals(groupCount + n, newGroupCount);
    }

}


