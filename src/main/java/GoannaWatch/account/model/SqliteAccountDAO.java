package GoannaWatch.account.model;

import GoannaWatch.database.DatabaseConnection;
import GoannaWatch.database.DatabaseInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteAccountDAO {

    // Saves an account to the database.
    public void addAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts "
                + "(first_name, last_name, email, password) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getFirstName());
            statement.setString(2, account.getLastName());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPassword());

            statement.executeUpdate();
        }
    }


    // Finds an account by email.
    public Account getAccountByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Account account = new Account(
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getString("email"),
                            result.getString("password")
                    );

                    account.setId(result.getInt("id"));
                    return account;
                }
            }
        }

        return null;
    }

    // Temporary test for reading an account.
    public static void main(String[] args) {
        try {
            DatabaseInitializer.initialize();

            SqliteAccountDAO dao = new SqliteAccountDAO();

            Account account = dao.getAccountByEmail(
                    "min.test2@example.com"
            );

            if (account != null) {
                System.out.println("ID: " + account.getId());
                System.out.println("Name: " + account.getFullName());
                System.out.println("Email: " + account.getEmail());
            } else {
                System.out.println("Account not found.");
            }

        } catch (SQLException e) {
            System.err.println("Failed to read account: "
                    + e.getMessage());
        }
    }
}