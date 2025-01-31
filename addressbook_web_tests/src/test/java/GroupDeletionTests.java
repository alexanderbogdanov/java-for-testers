import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeletionTests extends TestBase{


    @Test
    public void testGroupDeletion() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GroupData("group name", "group header", "group footer"));
        }
        deleteGroup();
    }

}


