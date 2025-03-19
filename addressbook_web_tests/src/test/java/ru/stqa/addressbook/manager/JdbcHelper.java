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

        String query = "SELECT id, firstname, middlename, lastname, nickname, company, " +
                "title, address, email, email2, email3, homepage, home, mobile, `work`, fax FROM addressbook";

        try (var connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             var preparedStatement = connection.prepareStatement(query);
             var result = preparedStatement.executeQuery()) {

            while (result.next()) {
                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withMiddleName(result.getString("middlename"))
                        .withLastName(result.getString("lastname"))
                        .withNickname(result.getString("nickname"))
                        .withCompany(result.getString("company"))
                        .withTitle(result.getString("title"))
                        .withAddress(result.getString("address"))
                        .withEmail(result.getString("email"))
                        .withEmail2(result.getString("email2"))
                        .withEmail3(result.getString("email3"))
                        .withHomePage(result.getString("homepage"))
                        .withHomePhone(result.getString("home"))
                        .withMobilePhone(result.getString("mobile"))
                        .withWorkPhone(result.getString("work"))
                        .withFax(result.getString("fax")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching contacts from database", e);
        }

        return contacts;
    }

    public void cleanupOrphanReferences() {
        String deleteQuery = "DELETE FROM address_in_groups WHERE id NOT IN (SELECT id FROM addressbook)";
        try (var connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             var preparedStatement = connection.prepareStatement(deleteQuery)) {
            int deleted = preparedStatement.executeUpdate();
            System.out.println("Deleted orphan references: " + deleted);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to cleanup orphan references", e);
        }
    }


    public void checkConsistency() {
        String query = "SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL";
        try (var connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             var preparedStatement = connection.prepareStatement(query);
             var result = preparedStatement.executeQuery()) {

            if (result.next()) {
                throw new IllegalStateException("DB is corrupted: there are contacts in groups that are not in the addressbook");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

