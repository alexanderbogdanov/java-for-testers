package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)
//        ) {
//            var line = breader.readLine();
//            while (line != null) {
//                json += line;
//                line = breader.readLine();
//            }
//        }
        var json = Files.readString(Paths.get("groups.json"));

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
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


