package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
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

}


