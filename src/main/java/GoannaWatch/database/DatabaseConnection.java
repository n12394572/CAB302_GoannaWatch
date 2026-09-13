package GoannaWatch.database;

// These classes are used to connect to the database and run SQL.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Opens connections to the goannawatch database.
 */
public class DatabaseConnection {

    // SQLite stores the data in this file.
    // The path is relative to the program's working directory.
    private static final String DATABASE_URL = "jdbc:sqlite:goannawatch.db";

    /**
     * Opens a database connection and turns on foreign key checks.
     * The code using this connection needs to close it afterwards.
     *
     * @return an open connection to the database
     * @throws SQLException if opening or setting up the connection fails
     */
    public static Connection getConnection() throws SQLException {

        // Open the database file. SQLite creates it if it does not exist.
        Connection connection = DriverManager.getConnection(DATABASE_URL);

        // The statement is closed automatically when this block finishes.
        try (Statement statement = connection.createStatement()) {

            // Check links between tables, such as an observation's account ID.
            // SQLite needs this setting enabled for each connection.
            statement.execute("PRAGMA foreign_keys = ON");

        } catch (SQLException e) {

            // If setup fails, close the connection before passing on the error.
            connection.close();
            throw e;
        }

        // Keep the connection open so the DAO can use it.
        return connection;
    }

    /**
     * Runs a small manual check of the database connection.
     *
     * @param args command line arguments, not used in this check
     */
    public static void main(String[] args) {

        // This connection is closed automatically after the check.
        try (Connection conn = getConnection()) {

            // isClosed() returns false when the connection is open.
            // The ! changes false to true.
            System.out.println("Database connected: " + !conn.isClosed());

        } catch (SQLException e) {

            // Print the reason if the database check fails.
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}