import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");
    }

    @Test
    public void testGroupCreationWithBlankFields() {
        openGroupsPage();
        createGroup("", "", "");
    }

}


