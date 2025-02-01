package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void testGroupCreationWithBlankFields() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void testGroupCreationWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

}


