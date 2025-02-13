package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        List<GroupData> groupsBefore = app.groups().getAll();
        var index = new Random().nextInt(groupsBefore.size());
        app.groups().deleteGroup(groupsBefore.get(index));
        List<GroupData> groupsAfter = app.groups().getAll();
        List<GroupData> expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.remove(index);
        assertEquals(expectedGroups, groupsAfter);
    }

    @Test
    void testAllGroupsDeletion() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().deleteAllGroups();
        assertEquals(0, app.groups().getCount());
    }

}


