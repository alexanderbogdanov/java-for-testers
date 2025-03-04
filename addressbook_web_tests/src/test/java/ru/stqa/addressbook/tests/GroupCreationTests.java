package ru.stqa.addressbook.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void testMultipleGroupCreation(GroupData group) {
        var groupsBefore = app.groups().getAll();
        app.groups().createGroup(group);
        var groupsAfter = app.groups().getAll();
        Comparator<GroupData> compareById = Comparator.comparingInt(g -> Integer.parseInt(g.id()));
        groupsAfter.sort(compareById);
        var expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.add(group.withId(groupsAfter.get(groupsAfter.size() - 1).id()).withHeader("").withFooter(""));
        expectedGroups.sort(compareById);
        assertEquals(groupsAfter, expectedGroups);
    }


    public static List<GroupData> NegativeGroupProvider() {
        return new ArrayList<>(List.of(
                new GroupData().withName("group name'").withHeader("").withFooter("")));
    }

    @ParameterizedTest
    @MethodSource("NegativeGroupProvider")
    public void testGroupCreationFail(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        assertEquals(groupCount, newGroupCount);
    }

}


