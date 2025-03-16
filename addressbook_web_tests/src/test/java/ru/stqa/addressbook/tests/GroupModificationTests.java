package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupModificationTests extends TestBase {

    @Test
    void testGroupModification() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("Default group"));
        }
        var groupsBefore = app.hbm().getGroupList();
        var index = new Random().nextInt(groupsBefore.size());
        var newName = "modified_" + CommonFunctions.randomString(5);
        var testData = new GroupData().withName(newName);
        app.groups().modifyGroup(groupsBefore.get(index), testData);
        var groupsAfter = app.hbm().getGroupList();
        var expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.set(index, testData.withId(groupsBefore.get(index).id()));
        Comparator<GroupData> compareById = Comparator.comparingInt(g -> Integer.parseInt(g.id()));
        groupsAfter.sort(compareById);
        expectedGroups.sort(compareById);
        assertEquals(groupsAfter, expectedGroups);
    }
}
