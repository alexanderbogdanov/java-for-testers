package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.utils.CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testMultipleContactCreation(ContactData contact) {
        var contactsBefore = app.contacts().getAll();
        app.contacts().createContact(contact);
        var contactsAfter = app.contacts().getAll();
        Comparator<ContactData> compareById = Comparator.comparingInt(g -> Integer.parseInt(g.id()));
        contactsAfter.sort(compareById);
        var expectedContacts = new ArrayList<>(contactsBefore);
        expectedContacts.add(contact
                .withId(contactsAfter.get(contactsAfter.size() - 1).id())
                .withLastName(contactsAfter.get(contactsAfter.size() - 1).lastName())
                .withFirstName(contactsAfter.get(contactsAfter.size() - 1).firstName())
                .withAddress("")
                .withEmail("")
                .withEmail2("")
                .withEmail3("")
                .withHomePhone("")
                .withMobilePhone("")
                .withWorkPhone("")
                .withPhoto(""));
        expectedContacts.sort(compareById);
        assertEquals(contactsAfter, expectedContacts);

    }

    public static List<ContactData> NegativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData("", "", "last name'", "",
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

}
