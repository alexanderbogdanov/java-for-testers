package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(
                    "Benedict",
                    "Cumberbatch",
                    "221B Baker Street, London, NW1 6XE",
                    "+44 20 7900 9000",
                    "+44 7700 900900",
                    "+44 20 7946 0000",
                    "benedict.cumberbatch@example.com",
                    "ben.cumberbatch@example.net",
                    "benny.cumberbatch@example.co.uk"));
        }
        app.contacts().deleteContact();
    }

    @Test
    public void testAllContactsDeletion() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(
                    "Benedict",
                    "Cumberbatch",
                    "221B Baker Street, London, NW1 6XE",
                    "+44 20 7900 9000",
                    "+44 7700 900900",
                    "+44 20 7946 0000",
                    "benedict.cumberbatch@example.com",
                    "ben.cumberbatch@example.net",
                    "benny.cumberbatch@example.co.uk"));
        }
        app.contacts().deleteAllContacts();
    }
}