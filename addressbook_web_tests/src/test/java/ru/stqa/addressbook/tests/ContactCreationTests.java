package ru.stqa.addressbook.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();

        result.add(new ContactData("", "Benedict", "Cumberbatch", "221B Baker Street",
                "+44 20 7900 9000", "+44 7700 900900", "+44 20 7946 0000",
                "benedict@example.com", "ben.c@example.net", "benny@example.co.uk"));

        result.add(new ContactData());

        result.add(new ContactData().withFirstName("John"));
        result.add(new ContactData().withLastName("Doe"));
        result.add(new ContactData().withEmail("johndoe@example.com"));

        for (int i = 1; i <= 5; i++) {
            result.add(new ContactData(
                    "", randomString(5), randomString(5), randomString(10),
                    randomString(10), randomString(10), randomString(10),
                    randomString(10), randomString(10), randomString(10)
            ));
        }

        return result;
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testMultipleContactCreation(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        assertEquals(contactCount + 1, newContactCount);
    }


    public static List<ContactData> NegativeContactProvider() {
        return new ArrayList<>(List.of(
                new ContactData("", "", "last name'", "",
                        "", "", "", "", "", "")));
    }

    @ParameterizedTest
    @MethodSource("NegativeContactProvider")
    public void testContactCreationFail(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        assertEquals(contactCount, newContactCount);
    }

}
