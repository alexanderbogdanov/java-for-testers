package ru.stqa.addressbook.utils;

import ru.stqa.addressbook.manager.ApplicationManager;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import java.util.List;

public class PreconditionHelper {
    private final ApplicationManager app;

    public PreconditionHelper(ApplicationManager app) {
        this.app = app;
    }

    public GroupData ensureGroupExists() {
        if (app.hbm().getGroupCount() == 0) {
            GroupData newGroup = new GroupData()
                    .withName(CommonFunctions.randomCompany())
                    .withHeader(CommonFunctions.randomHeader())
                    .withFooter(CommonFunctions.randomFooter());
            app.hbm().createGroup(newGroup);
        }
        return app.hbm().getRandomGroup();
    }

    public ContactData ensureContactExists() {
        if (app.hbm().getContactCount() == 0) {
            ContactData newContact = new ContactData()
                    .withFirstName(CommonFunctions.randomFirstName())
                    .withLastName(CommonFunctions.randomLastName());
            app.hbm().createContact(newContact);
        }
        return app.hbm().getRandomContact();
    }

    public ContactData ensureContactExistsViaUI() {
        if (!app.contacts().isContactPresent()) {
            ContactData contact = new ContactData()
                    .withFirstName("mod")
                    .withLastName("target");
            app.contacts().createContact(contact);
            return app.hbm().getContactList()
                    .stream()
                    .filter(c -> c.firstName().equals("mod") && c.lastName().equals("target"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("UI-created contact not found in DB"));
        }
        return app.hbm().getRandomContact();
    }

    public void ensureContactInGroup(ContactData contact, GroupData group) {
        if (!isContactInGroup(contact, group)) {
            app.contacts().addExistingContactToGroup(contact, group);
        }
    }

    public ContactData ensureContactNotInGroup(GroupData group) {
        for (ContactData contact : app.hbm().getContactList()) {
            if (!isContactInGroup(contact, group)) {
                return contact;
            }
        }

        ContactData contact = new ContactData()
                .withFirstName("auto")
                .withLastName("nogroup");
        app.contacts().createContact(contact);

        List<ContactData> updatedList = app.hbm().getContactList();
        return updatedList.get(updatedList.size() - 1);
    }

    public boolean isContactInGroup(ContactData contact, GroupData group) {
        List<ContactData> contactsInGroup = app.hbm().getContactsInGroup(group);
        return contactsInGroup.contains(contact);
    }

}
