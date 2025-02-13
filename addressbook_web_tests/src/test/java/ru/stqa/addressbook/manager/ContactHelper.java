package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        confirmAction();
        gotoHomePage();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
    }


    private void gotoHomePage() {
        click(By.linkText("home"));
    }


    public boolean isContactPresent() {
        gotoHomePage();
        return isElementPresent(By.name("selected[]"));
    }

    public void deleteContact() {
        gotoHomePage();
        selectContact();
        deleteSelected();
        gotoHomePage();
    }


    private void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteAllContacts() {
        gotoHomePage();
        selectAllContacts();
        deleteSelected();
    }

    public int getCount() {
        gotoHomePage();
        return findElements(By.name("selected[]")).size();
    }


    private void selectAllContacts() {
        click(By.id("MassCB"));
    }


    public List<ContactData> getAll() {
        var contacts = new ArrayList<ContactData>();
        gotoHomePage();
        var rows = findElements(By.name("entry"));
        for (var row : rows) {
            var cells = row.findElements(By.tagName("td"));
            var id = cells.get(0).findElement(By.tagName("input")).getDomAttribute("id");
            var lastName = cells.get(1).getText();
            var firstName = cells.get(2).getText();
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }
}
