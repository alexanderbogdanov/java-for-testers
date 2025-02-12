package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        confirmAction();
        returnToGroupsPage();
    }

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }


    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void deleteSelectedGroups() {
        click(By.name("delete"));

    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        deleteSelectedGroups();
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }


    public int getCount() {
        openGroupsPage();
        return findElements(By.name("selected[]")).size();
    }

    private void selectAllGroups() {
        var checkboxes = findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
