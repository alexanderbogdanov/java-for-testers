import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        app.openGroupsPage();
        if (!app.isGroupPresent()) {
            app.createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.deleteGroup();
    }

}


