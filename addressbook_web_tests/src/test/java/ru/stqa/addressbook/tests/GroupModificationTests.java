package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupModificationTests extends TestBase {

 @Test
    void testGroupModification() {
        preconditions.ensureGroupExists();
        var groupsBefore = app.hbm().getGroupList();
        GroupData groupToModify = app.hbm().getRandomGroup();
        var newName = "modified_" + CommonFunctions.randomString(5);
        var testData = new GroupData().withName(newName);
        app.groups().modifyGroup(groupToModify, testData);
        var groupsAfter = app.hbm().getGroupList();
        var expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.removeIf(g -> g.id().equals(groupToModify.id()));
        expectedGroups.add(testData.withId(groupToModify.id()));
        Comparator<GroupData> compareById = Comparator.comparingInt(g -> Integer.parseInt(g.id()));
        expectedGroups.sort(compareById);
        groupsAfter.sort(compareById);
        assertEquals(expectedGroups, groupsAfter);
    }
}
