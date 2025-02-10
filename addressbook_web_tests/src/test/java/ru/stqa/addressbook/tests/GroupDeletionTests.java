package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void testAllGroupsDeletion() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().deleteAllGroups();
        assertEquals(0, app.groups().getCount());
    }

}


