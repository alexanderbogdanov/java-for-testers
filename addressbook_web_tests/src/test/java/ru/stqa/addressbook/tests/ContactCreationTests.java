package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.stqa.addressbook.utils.CommonFunctions.*;
import static ru.stqa.addressbook.utils.ListUtils.*;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

        result.add(new ContactData()
                .withFirstName("Benedict")
                .withMiddleName("Timothy Carlton")
                .withLastName("Cumberbatch")
                .withNickname("Benny")
                .withCompany("Sherlock Holmes")
                .withTitle("Sherlock")
                .withAddress("221B Baker Street")
                .withHomePhone("+44 20 7900 9000")
                .withMobilePhone("+44 7700 900900")
                .withWorkPhone("+44 20 7946 0000")
                .withFax("+44 7700 9000")
                .withEmail("benedict@example.com")
                .withEmail2("ben.c@example.net")
                .withEmail3("benny@example.co.uk")
                .withHomePage("www.benedictcumberbatch.com")
                .withPhoto(getRandomImagePath(resourceDir)));

        result.add(new ContactData());
        result.add(new ContactData().withFirstName("John"));
        result.add(new ContactData().withLastName("Doe"));
        result.add(new ContactData().withEmail("johndoe@example.com"));
        result.add(new ContactData().withPhoto(getRandomImagePath(resourceDir)));


        ObjectMapper mapper = new ObjectMapper();
        List<ContactData> fileData = mapper.readValue(new File("contacts.json"), new TypeReference<>() {
        });
        result.addAll(fileData);
        return result;
    }

    public static Stream<ContactData> randomContactProvider() {
        Supplier<ContactData> randomContact = () -> new ContactData()
                .withFirstName(randomFirstName())
                .withMiddleName(randomMiddleName())
                .withLastName(randomLastName())
                .withNickname(randomNickname())
                .withCompany(randomCompany())
                .withTitle(randomTitle())
                .withAddress(randomAddress())
                .withHomePhone(randomHomePhone())
                .withMobilePhone(randomMobilePhone())
                .withWorkPhone(randomWorkPhone())
                .withFax(randomFax())
                .withEmail(randomEmail())
                .withEmail2(randomEmail())
                .withEmail3(randomEmail())
                .withHomePage(randomHomePage());
        return Stream.generate(randomContact).limit(3);
    }


    @ParameterizedTest
    @MethodSource("randomContactProvider")
    public void testContactCreation(ContactData contact) {
        var contactsBefore = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var contactsAfter = app.hbm().getContactList();
        Comparator<ContactData> compareById = Comparator.comparingInt(c -> Integer.parseInt(c.id()));
        contactsAfter.sort(compareById);
        var maxId = contactsAfter.get(contactsAfter.size() - 1).id();
        var expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.add(contact.withId(maxId));
        expectedContacts.sort(compareById);
        assertEquals(contactsAfter, expectedContacts);

    }

    public static List<ContactData> NegativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData("", "", "", "last name'", "", "", "",
                        "", "", "", "", "", "", "", "", "", "")));
    }

    @ParameterizedTest
    @MethodSource("NegativeContactProvider")
    public void testContactCreationFail(ContactData contact) {
        var contactsBefore = app.contacts().getCount();
        app.contacts().createContact(contact);
        var contactsAfter = app.contacts().getCount();
        assertEquals(contactsAfter, contactsBefore);
    }

    @Test
    public void testContactListInUIvsDB() {
        List<ContactData> uiContacts = app.contacts().getAll();
        List<ContactData> dbContacts = app.hbm().getContactList();

        List<ContactData> uiContactsForComparison = uiContacts.stream()
                .map(c -> new ContactData().withFirstName(c.firstName()))
                .sorted(Comparator.comparing(ContactData::firstName))
                .collect(Collectors.toList());

        List<ContactData> dbContactsForComparison = dbContacts.stream()
                .map(c -> new ContactData().withFirstName(c.firstName()))
                .sorted(Comparator.comparing(ContactData::firstName))
                .collect(Collectors.toList());

        assumeTrue(!uiContactsForComparison.isEmpty(), "UI contact list is empty. Test skipped.");
        assumeTrue(!dbContactsForComparison.isEmpty(), "DB contact list is empty. Test skipped.");

        assertEquals(uiContactsForComparison, dbContactsForComparison, "The UI contact list should match the DB contact list (by first name).");
    }

    @Test
    public void testAddContactToGroup() {
        GroupData group = preconditions.ensureGroupExists();
        ContactData contact = preconditions.ensureContactNotInGroup(group);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addExistingContactToGroup(contact, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(contact);
        sortContactsById(expectedRelated);
        sortContactsById(newRelated);
        assertEquals(expectedRelated, newRelated, "The contact was not properly added to the group.");
    }
}
