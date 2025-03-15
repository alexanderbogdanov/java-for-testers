package ru.stqa.addressbook.manager;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class HibernateHelper extends HelperBase {
    private final SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class) //?
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, dbUrl)
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, dbUsername)
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, dbPassword)
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }


//    static List<GroupData> convertList(List<GroupRecord> records) {
//        List<GroupData> result = new java.util.ArrayList<>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
//    }
//
//    static List<ContactData> convertList(List<ContactRecord> records) {
//        List<ContactData> result = new java.util.ArrayList<>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
//    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData("" + record.id, record.firstName, record.middleName,
                record.lastName, record.address, record.homePhone,
                record.mobilePhone, record.workPhone, record.email,
                record.email2, record.email3, "");
    }


    private static GroupRecord convert(GroupData data) {
        String id = data.id();
        if (id == null || id.isEmpty()) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convert(ContactData data) {
        String id = data.id();
        if (id == null || id.isEmpty()) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(),
                data.middleName(), data.lastName(), data.address(),
                data.homePhone(), data.mobilePhone(), data.workPhone(),
                data.email(), data.email2(), data.email3());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(
                sessionFactory.fromSession(session -> {
                    return session.createQuery("from GroupRecord", GroupRecord.class).list();
                }));
    }

    public List<ContactData> getContactList() {
        return convertContactList(
                sessionFactory.fromSession(session -> {
                    return session.createQuery("from ContactRecord", ContactRecord.class).list();
                }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }
}
