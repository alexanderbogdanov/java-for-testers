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
        ContactData contactToModify = preconditions.ensureContactExistsViaUI();
        var contactsBefore = app.hbm().getContactList();
        var newFirstName = "modified_" + CommonFunctions.randomString(5);
        var testData = new ContactData()
                .withFirstName(newFirstName)
                .withId(contactToModify.id())
                .withLastName(contactToModify.lastName());
        app.contacts().modifyContact(contactToModify, testData);
        var contactsAfter = app.hbm().getContactList();
        var expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.removeIf(c -> c.id().equals(contactToModify.id()));
        expectedContacts.add(testData.withId(contactToModify.id()));
        Comparator<ContactData> compareById = Comparator.comparingInt(c -> Integer.parseInt(c.id()));
        contactsAfter.sort(compareById);
        expectedContacts.sort(compareById);
        assertEquals(expectedContacts, contactsAfter);
    }

}
