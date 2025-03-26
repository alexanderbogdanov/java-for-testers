package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ru.stqa.addressbook.utils.ListUtils.sortContactsById;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        preconditions.ensureContactExists();
        List<ContactData> contactsBefore = app.hbm().getContactList();
        ContactData contactToDelete = app.hbm().getRandomContact();
        app.contacts().deleteContact(contactToDelete);
        List<ContactData> contactsAfter = app.hbm().getContactList();
        List<ContactData> expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.removeIf(c -> c.id().equals(contactToDelete.id()));
        assertEquals(expectedContacts, contactsAfter);
    }

    @Test
    public void testAllContactsDeletion() {
        preconditions.ensureContactExists();
        app.contacts().deleteAllContacts();
        assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    public void testRemoveContactFromGroup() {
        GroupData group = preconditions.ensureGroupExists();
        ContactData contact = preconditions.ensureContactExists();
        preconditions.ensureContactInGroup(contact, group);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.removeIf(c -> c.id().equals(contact.id()));
        sortContactsById(expectedRelated);
        sortContactsById(newRelated);
        assertEquals(expectedRelated, newRelated, "The contact was not properly removed from the group.");
    }

}