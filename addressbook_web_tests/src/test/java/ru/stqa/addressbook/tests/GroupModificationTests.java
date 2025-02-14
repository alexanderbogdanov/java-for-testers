package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupModificationTests extends TestBase {

    @Test
    void testGroupModification() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var groupsBefore = app.groups().getAll();
        var index = new Random().nextInt(groupsBefore.size());
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(groupsBefore.get(index), testData);
        var groupsAfter = app.groups().getAll();
        var expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.set(index, testData.withId(groupsBefore.get(index).id()));
        Comparator<GroupData> compareById = (g1, g2) -> {
            return Integer.compare(Integer.parseInt(g1.id()), Integer.parseInt(g2.id()));
        };
        groupsAfter.sort(compareById);
        expectedGroups.sort(compareById);
        assertEquals(groupsAfter, expectedGroups);
    }
}
