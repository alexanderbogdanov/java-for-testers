package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactModificationTests extends TestBase {

    @Test
    void testContactModification() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName("Default")
                    .withLastName("Contact"));
        }

        var contactsBefore = app.contacts().getAll();
        var contactToModify = app.contacts().getRandomContact();
        var newFirstName = "modified_" + CommonFunctions.randomString(5);
        var testData = new ContactData()
                .withFirstName(newFirstName)
                .withId(contactToModify.id())
                .withLastName(contactToModify.lastName());
        app.contacts().modifyContact(contactToModify, testData);
        var contactsAfter = app.contacts().getAll();
        int index = contactsBefore.indexOf(contactToModify);
        var expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.set(index, testData.withId(contactToModify.id()));
        Comparator<ContactData> compareById = (c1, c2) -> {
            int id1 = c1.id().isEmpty() ? 0 : Integer.parseInt(c1.id());
            int id2 = c2.id().isEmpty() ? 0 : Integer.parseInt(c2.id());
            return Integer.compare(id1, id2);
        };
        contactsAfter.sort(compareById);
        expectedContacts.sort(compareById);
        assertEquals(contactsAfter, expectedContacts);
    }


}
