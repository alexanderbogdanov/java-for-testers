package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.contacts().createContact(
                new ContactData(
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

}
