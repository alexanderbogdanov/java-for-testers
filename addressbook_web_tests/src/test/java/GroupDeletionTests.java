import org.junit.jupiter.api.Test;

public class GroupDeletionTests extends TestBase{


    @Test
    public void testGroupDeletion() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("group name", "group header", "group footer");
        }
        deleteGroup();
    }

}


