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

        result.add(new ContactData()
                        .withFirstName("Benedict")
                        .withLastName("Cumberbatch")
                        .withAddress("221B Baker Street")
                        .withHomePhone("+44 20 7900 9000")
                        .withMobilePhone("+44 7700 900900")
                        .withWorkPhone("+44 20 7946 0000")
                        .withEmail("benedict@example.com")
                        .withEmail2("ben.c@example.net")
                        .withEmail3("benny@example.co.uk"));

        result.add(new ContactData());
        result.add(new ContactData().withFirstName("John"));
        result.add(new ContactData().withLastName("Doe"));
        result.add(new ContactData().withEmail("johndoe@example.com"));

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 5))
                    .withLastName(randomString(i * 5))
                    .withAddress(randomString(i * 10))
                    .withHomePhone(randomString(i * 10))
                    .withMobilePhone(randomString(i * 10))
                    .withWorkPhone(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withEmail2(randomString(i * 10))
                    .withEmail3(randomString(i * 10))
            );
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
