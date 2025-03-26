package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        preconditions.ensureGroupExists();
        List<GroupData> groupsBefore = app.hbm().getGroupList();
        GroupData groupToDelete = app.hbm().getRandomGroup();
        app.groups().deleteGroup(groupToDelete);
        List<GroupData> groupsAfter = app.hbm().getGroupList();
        List<GroupData> expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.removeIf(g -> g.id().equals(groupToDelete.id()));
        assertEquals(expectedGroups, groupsAfter);
    }

    @Test
    void testAllGroupsDeletion() {
        preconditions.ensureGroupExists();
        app.groups().deleteAllGroups();
        assertEquals(0, app.hbm().getGroupCount());
    }

}


