package ru.stqa.addressbook.utils;

import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtil {

    public static GroupData convert(GroupRecord record) {
        return new GroupData(String.valueOf(record.id), record.name, record.header, record.footer);
    }

    public static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream()
                .map(ConversionUtil::convert)
                .collect(Collectors.toList());
    }

    public static ContactData convert(ContactRecord record) {
        return new ContactData(String.valueOf(record.id),
                record.firstName,
                record.middleName,
                record.lastName,
                record.nickname,
                record.company,
                record.title,
                record.address,
                record.homePhone,
                record.mobilePhone,
                record.workPhone,
                record.fax,
                record.email,
                record.email2,
                record.email3,
                record.homePage,
                "");
    }

    public static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream()
                .map(ConversionUtil::convert)
                .collect(Collectors.toList());
    }

    public static GroupRecord convert(GroupData data) {
        int id = 0;
        if (data.id() != null && !data.id().isEmpty()) {
            id = Integer.parseInt(data.id());
        }
        return new GroupRecord(id, data.name(), data.header(), data.footer());
    }

    public static ContactRecord convert(ContactData data) {
        int id = 0;
        if (data.id() != null && !data.id().isEmpty()) {
            id = Integer.parseInt(data.id());
        }
        return new ContactRecord(id,
                data.firstName(),
                data.middleName(),
                data.lastName(),
                data.nickname(),
                data.company(),
                data.title(),
                data.address(),
                data.homePhone(),
                data.mobilePhone(),
                data.workPhone(),
                data.fax(),
                data.email(),
                data.email2(),
                data.email3(),
                data.homePage());
    }
}
