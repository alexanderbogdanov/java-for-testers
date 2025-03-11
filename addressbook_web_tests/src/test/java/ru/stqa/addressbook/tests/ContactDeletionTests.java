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
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "", "Benedict",
                    "Benya",
                    "Cumberbatch",
                    "221B Baker Street, London, NW1 6XE",
                    "+44 20 7900 9000",
                    "+44 7700 900900",
                    "+44 20 7946 0000",
                    "benedict.cumberbatch@example.com",
                    "ben.cumberbatch@example.net",
                    "benny.cumberbatch@example.co.uk",
                    ""));
        }
        List<ContactData> contactsBefore = app.contacts().getAll();
        var index = new Random().nextInt(contactsBefore.size());
        app.contacts().deleteContact(contactsBefore.get(index));
        List<ContactData> contactsAfter = app.contacts().getAll();
        List<ContactData> expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.remove(index);
        assertEquals(expectedContacts, contactsAfter);
    }

    @Test
    public void testAllContactsDeletion() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(
                    "", "Benedict",
                    "Benya",
                    "Cumberbatch",
                    "221B Baker Street, London, NW1 6XE",
                    "+44 20 7900 9000",
                    "+44 7700 900900",
                    "+44 20 7946 0000",
                    "benedict.cumberbatch@example.com",
                    "ben.cumberbatch@example.net",
                    "benny.cumberbatch@example.co.uk",
                    ""));
        }
        app.contacts().deleteAllContacts();
        assertEquals(0, app.contacts().getCount());
    }
}