package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static ru.stqa.addressbook.utils.CommonFunctions.*;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

        result.add(new ContactData()
                .withFirstName("Benedict")
                .withLastName("Cumberbatch")
                .withAddress("221B Baker Street")
                .withHomePhone("+44 20 7900 9000")
                .withMobilePhone("+44 7700 900900")
                .withWorkPhone("+44 20 7946 0000")
                .withEmail("benedict@example.com")
                .withEmail2("ben.c@example.net")
                .withEmail3("benny@example.co.uk")
                .withPhoto(CommonFunctions.getRandomImagePath("src/test/resources/images/")));

        result.add(new ContactData());
        result.add(new ContactData().withFirstName("John"));
        result.add(new ContactData().withLastName("Doe"));
        result.add(new ContactData().withEmail("johndoe@example.com"));
        result.add(new ContactData().withPhoto(CommonFunctions.getRandomImagePath("src/test/resources/images/")));


        ObjectMapper mapper = new ObjectMapper();
        List<ContactData> fileData = mapper.readValue(new File("contacts.json"), new TypeReference<>() {
        });
        result.addAll(fileData);
        return result;
    }

    public static List<ContactData> singleRandomContactProvider() {
        return new ArrayList<>(List.of(new ContactData()
                .withFirstName(randomString(5))
                .withMiddleName(randomString(4))
                .withLastName(randomString(5))
                .withAddress(randomString(10))
                .withHomePhone(randomPhone())
                .withMobilePhone(randomPhone())
                .withWorkPhone(randomPhone())
                .withEmail(randomEmail())
                .withEmail2(randomEmail())
                .withEmail3(randomEmail())));

    }


    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
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
                new ContactData("", "", "", "last name'", "",
                        "", "", "", "", "", "", "")));
    }

    @ParameterizedTest
    @MethodSource("NegativeContactProvider")
    public void testContactCreationFail(ContactData contact) {
        var contactsBefore = app.contacts().getAll();
        app.contacts().createContact(contact);
        var contactsAfter = app.contacts().getAll();
        assertEquals(contactsAfter, contactsBefore);
    }

    @Test
    public void testContactListInUIvsDB() {
        List<ContactData> uiContacts = app.contacts().getAll();
        List<ContactData> dbContacts = app.jdbc().getContactList();

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

}
