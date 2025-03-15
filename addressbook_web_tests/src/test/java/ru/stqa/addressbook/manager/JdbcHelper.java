package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {

        var groups = new ArrayList<GroupData>();

        String query = "SELECT group_id, group_name, group_header, group_footer FROM group_list";

        try (var connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             var preparedStatement = connection.prepareStatement(query);
             var result = preparedStatement.executeQuery()) {

            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching groups from database", e);
        }

        return groups;
    }

    public List<ContactData> getContactList() {

        var contacts = new ArrayList<ContactData>();

        String query = "SELECT id, firstname, middlename, lastname, address, email, email2, email3, home, mobile, `work` FROM addressbook";

        try (var connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             var preparedStatement = connection.prepareStatement(query);
             var result = preparedStatement.executeQuery()) {

            while (result.next()) {
                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withMiddleName(result.getString("middlename"))
                        .withLastName(result.getString("lastname"))
                        .withAddress(result.getString("address"))
                        .withEmail(result.getString("email"))
                        .withEmail2(result.getString("email2"))
                        .withEmail3(result.getString("email3"))
                        .withHomePhone(result.getString("home"))
                        .withMobilePhone(result.getString("mobile"))
                        .withWorkPhone(result.getString("work")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching contacts from database", e);
        }

        return contacts;
    }
}

