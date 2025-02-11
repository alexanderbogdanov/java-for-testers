package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(),
                new GroupData().withName("some name"),
                new GroupData("group name", "group header", "group footer"),
                new GroupData("group name 2", "group header 2", "group footer 2"),
                new GroupData("group name 3", "group header 3", "group footer 3")));
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void testMultipleGroupCreation(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        assertEquals(groupCount + 1, newGroupCount);
    }

}


