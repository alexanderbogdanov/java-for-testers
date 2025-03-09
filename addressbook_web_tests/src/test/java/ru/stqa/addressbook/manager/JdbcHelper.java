package ru.stqa.addressbook.manager;

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

        String dbUrl = getProperty("db.url");
        String dbUsername = getProperty("db.username");
        String dbPassword = getProperty("db.password");

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
}

