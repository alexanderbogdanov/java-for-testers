package ru.stqa.addressbook.utils;

import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ListUtils {

    public static void sortContactsById(List<ContactData> contacts) {
        contacts.sort(Comparator.comparingInt(c -> Integer.parseInt(c.id())));
    }

    public static void sortGroupsById(List<GroupData> groups) {
        groups.sort(Comparator.comparingInt(g -> Integer.parseInt(g.id())));
    }
}
