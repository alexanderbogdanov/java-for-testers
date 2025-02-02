package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {
//    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        click(By.name("new"));
        click(By.name("group_name"));
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
        click(By.name("submit"));
        click(By.linkText("group page"));
    }

    public void deleteGroup() {
        openGroupsPage();
        click(By.name("selected[]"));
        click(By.name("delete"));
        click(By.linkText("group page"));
    }
}
