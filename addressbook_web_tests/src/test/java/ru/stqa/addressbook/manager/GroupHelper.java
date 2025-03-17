package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        deleteSelected();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitChanges();
        returnToGroupsPage();
    }

    private void selectGroup(GroupData group) {
        waitAndClick(By.cssSelector(String.format("input[value='%s']", group.id())));
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


    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        deleteSelected();
    }

    private void returnToGroupsPage() {
        waitForElement(By.linkText("group page"));
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

    public List<GroupData> getAll() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var elements = findElements(By.cssSelector("span.group"));
        for (var element : elements) {
            var name = element.getText();
            var id = element.findElement(By.name("selected[]")).getDomAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
