package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;
import static ru.stqa.addressbook.utils.CommonFunctions.randomString;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        List<GroupData> fileData = mapper.readValue(new File("groups.json"), new TypeReference<>() {
        });
        result.addAll(fileData);
        return result;
    }

    public static List<GroupData> singleRandomGroupProvider() {
        return new ArrayList<>(List.of(new GroupData()
                .withName(randomString(10))
                .withHeader(randomString(20))
                .withFooter(randomString(30))));
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroupProvider")
    public void testGroupCreation(GroupData group) {
        var groupsBefore = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var groupsAfter = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = Comparator.comparingInt(g -> Integer.parseInt(g.id()));
        groupsAfter.sort(compareById);
        var maxId = groupsAfter.get(groupsAfter.size() - 1).id();
        var expectedGroups = new ArrayList<>(groupsBefore);
        expectedGroups.add(group.withId(maxId));
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

    @Test
    public void testGroupListInUIvsDB() {
        List<GroupData> uiGroups = app.groups().getAll();
        List<GroupData> dbGroups = app.jdbc().getGroupList();

        List<GroupData> uiGroupsForComparison = uiGroups.stream()
                .map(g -> new GroupData().withName(g.name()))
                .sorted(Comparator.comparing(GroupData::name))
                .collect(Collectors.toList());

        List<GroupData> dbGroupsForComparison = dbGroups.stream()
                .map(g -> new GroupData().withName(g.name()))
                .sorted(Comparator.comparing(GroupData::name))
                .collect(Collectors.toList());

        assumeTrue(!uiGroupsForComparison.isEmpty(), "UI group list is empty. Test skipped.");
        assumeTrue(!dbGroupsForComparison.isEmpty(), "DB group list is empty. Test skipped.");

        assertEquals(uiGroupsForComparison, dbGroupsForComparison, "The UI group list should match the DB group list (by name).");
    }

}


