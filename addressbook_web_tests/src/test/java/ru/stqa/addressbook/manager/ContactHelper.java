package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
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

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void gotoHomePage() {
        click(By.linkText("home page"));
    }






}
