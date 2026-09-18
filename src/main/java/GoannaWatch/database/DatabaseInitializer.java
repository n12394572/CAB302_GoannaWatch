package GoannaWatch.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    /**
     * Creates the accounts table if it does not already exist.
     *
     * @throws SQLException if database initialization fails
     */

    public static void initialize() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS accounts (
                    id INTEGER PRIMARY KEY,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    email TEXT NOT NULL COLLATE NOCASE UNIQUE,
                    password TEXT NOT NULL
                )
                """;
        String observationSql = """
            CREATE TABLE IF NOT EXISTS observations (
                id INTEGER PRIMARY KEY,
                observer_id INTEGER NOT NULL,
                location TEXT NOT NULL,
                animal_seen TEXT NOT NULL,
                observed_at TEXT NOT NULL,
                FOREIGN KEY (observer_id) REFERENCES accounts(id)
            )
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            statement.executeUpdate(observationSql);
        }

    }


    public static void main(String[] args) {
        try {
            initialize();

            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement();
                 java.sql.ResultSet result =
                         statement.executeQuery("PRAGMA table_info(accounts)")) {

                while (result.next()) {
                    System.out.println(result.getString("name"));
                }
            }

            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }
}


