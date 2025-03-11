package ru.stqa.addressbook.manager;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, getProperty("db.url"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, getProperty("db.username"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, getProperty("db.password"))
                .buildSessionFactory();
    }


    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new java.util.ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

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

//    private static ContactData convert(ContactRecord record) {
//        return new ContactData("" + record.id, record.firstName,
//                record.lastName, record.address, record.email,
//                record.email2, record.email3, record.homePhone,
//                record.mobilePhone, record.workPhone);
//    }



    private static GroupRecord convert(GroupData data) {
        String id = data.id();
        if (id == null || id.isEmpty()) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }


    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

//    public Object getContactList() {
//        return convertList()
//    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }


}
