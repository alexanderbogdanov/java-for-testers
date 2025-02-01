package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.openGroupsPage();
        app.createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void testGroupCreationWithBlankFields() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void testGroupCreationWithNameOnly() {
        app.openGroupsPage();
        app.createGroup(new GroupData().withName("some name"));
    }

}


