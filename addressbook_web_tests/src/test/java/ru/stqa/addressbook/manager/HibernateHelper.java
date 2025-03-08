package ru.stqa.addressbook.manager;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
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

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

}
