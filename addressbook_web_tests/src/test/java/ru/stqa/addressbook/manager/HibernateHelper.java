package ru.stqa.addressbook.manager;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;
import java.util.Random;

import static ru.stqa.addressbook.utils.ConversionUtil.*;


public class HibernateHelper extends HelperBase {
    private final SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, dbUrl)
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, dbUsername)
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, dbPassword)
                .buildSessionFactory();
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

    public ContactData getRandomContact() {
        List<ContactData> contacts = getContactList();
        if (contacts.isEmpty()) {
            throw new IllegalStateException("No contacts available to select randomly.");
        }
        return contacts.get(new Random().nextInt(contacts.size()));
    }

    public GroupData getRandomGroup() {
        List<GroupData> groups = getGroupList();
        if (groups.isEmpty()) {
            throw new IllegalStateException("No groups available to select randomly.");
        }
        return groups.get(new Random().nextInt(groups.size()));
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

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

}
