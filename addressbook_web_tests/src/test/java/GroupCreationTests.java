import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        openGroupsPage();
        createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void testGroupCreationWithBlankFields() {
        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void testGroupCreationWithNameOnly() {
        openGroupsPage();
        createGroup(new GroupData().withName("some name"));
    }

}


