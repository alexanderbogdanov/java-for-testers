package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("Default")
                    .withLastName("Contact"));
        }
        List<ContactData> contactsBefore = app.hbm().getContactList();
        var index = new Random().nextInt(contactsBefore.size());
        app.contacts().deleteContact(contactsBefore.get(index));
        List<ContactData> contactsAfter = app.hbm().getContactList();
        List<ContactData> expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.remove(index);
        assertEquals(expectedContacts, contactsAfter);
    }

    @Test
    public void testAllContactsDeletion() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("Default")
                    .withLastName("Contact"));
        }
        app.contacts().deleteAllContacts();
        assertEquals(0, app.hbm().getContactCount());
    }
}