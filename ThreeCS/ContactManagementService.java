package ThreeCS;

import DoubleCS.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Class;

public class ContactManagementService {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/glkt_user";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public List<Contact> retrieveContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM contacts";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );
                contacts.add(contact);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public void addContact(Contact contact) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getAddress());
            statement.setString(3, contact.getPhone());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(int index, Contact updatedContact) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String query = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedContact.getName());
            statement.setString(2, updatedContact.getAddress());
            statement.setString(3, updatedContact.getPhone());
            statement.setInt(4, index + 1); // MySQL uses 1-based indexing
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int index) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            String query = "DELETE FROM contacts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, index + 1); // MySQL uses 1-based indexing
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

