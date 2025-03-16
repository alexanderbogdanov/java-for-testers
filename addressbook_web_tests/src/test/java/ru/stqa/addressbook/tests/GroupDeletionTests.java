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
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("Default group"));
        }
        List<GroupData> groupsBefore = app.hbm().getGroupList();
        var index = new Random().nextInt(groupsBefore.size());
        app.groups().deleteGroup(groupsBefore.get(index));
        List<GroupData> groupsAfter = app.hbm().getGroupList();
        List<GroupData> expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.remove(index);
        assertEquals(expectedGroups, groupsAfter);
    }

    @Test
    void testAllGroupsDeletion() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("Default group"));
        }
        app.groups().deleteAllGroups();
        assertEquals(0, app.hbm().getGroupCount());
    }

}


