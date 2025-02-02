package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().deleteGroup();
    }

}


