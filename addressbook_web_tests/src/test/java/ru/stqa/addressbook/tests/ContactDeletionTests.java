package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ru.stqa.addressbook.utils.CommonFunctions.*;

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

    @Test
    public void testRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(new GroupData()
                    .withName(randomCompany()));
        }

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomFirstName())
                    .withLastName(CommonFunctions.randomLastName()));
        }

        GroupData group = app.hbm().getRandomGroup();

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        ContactData contact;
        if (oldRelated.isEmpty()) {
            contact = app.hbm().getRandomContact();
            if (!oldRelated.contains(contact)) {
                app.contacts().addExistingContactToGroup(contact, group);
                oldRelated = app.hbm().getContactsInGroup(group);
            }
        } else {
            contact = oldRelated.get(0);
        }

        app.contacts().removeContactFromGroup(contact, group);

        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);

        var expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.remove(contact);

        Comparator<ContactData> compareById = Comparator.comparingInt(c -> Integer.parseInt(c.id()));
        newRelated.sort(compareById);
        expectedRelated.sort(compareById);

        assertEquals(expectedRelated, newRelated, "Contact was not properly removed from the group in the DB.");
    }
}