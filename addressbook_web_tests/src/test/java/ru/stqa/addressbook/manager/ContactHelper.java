package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        waitForElement(By.name("entry"));
    }


    public boolean isContactPresent() {
        gotoHomePage();
        return isElementPresent(By.name("selected[]"));
    }

    public ContactData getRandomContact() {
        List<ContactData> contacts = getAll();
        if (contacts.isEmpty()) {
            throw new IllegalStateException("No contacts available to select randomly.");
        }
        return contacts.get(new Random().nextInt(contacts.size()));
    }

    public void deleteContact(ContactData contact) {
        gotoHomePage();
        selectContact(contact);
        deleteSelected();
        gotoHomePage();
    }


    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));

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

            var id = cells.get(0).findElement(By.tagName("input")).getDomAttribute("value");
            var lastName = cells.get(1).getText().trim();
            var firstName = cells.get(2).getText().trim();

            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName));
        }

        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        gotoHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitChanges();
        gotoHomePage();
    }

    private void initContactModification(ContactData contact) {
        var rows = findElements(By.name("entry"));
        for (var row : rows) {
            var cells = row.findElements(By.tagName("td"));
            var idElement = cells.get(0).findElement(By.tagName("input"));
            var id = idElement.getDomAttribute("value");
            if (id == null || id.isEmpty()) {
                continue;
            }
            if (id.equals(contact.id())) {
                cells.get(7).findElement(By.cssSelector(".center img[alt='Edit']")).click();
                break;
            }
        }
    }

}