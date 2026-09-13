package GoannaWatch.observations.model;

import GoannaWatch.database.DatabaseConnection;

import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.SqliteAccountDAO;
import GoannaWatch.database.DatabaseInitializer;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteObservationDAO {

    // Saves an observation and assigns its database ID.
    public void addObservation(Observation observation) throws SQLException {
        String sql = """
                INSERT INTO observations
                (observer_id, location, animal_seen, observed_at)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, observation.getObserver().getId());
            statement.setString(2, observation.getLocation());
            statement.setString(3, observation.getAnimalSeen());
            statement.setString(4, observation.getObservedAt().toString());

            statement.executeUpdate();

            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) {
                    observation.setId(result.getInt(1));
                } else {
                    throw new SQLException("No observation ID was returned.");
                }
            }
        }

    }
    // Finds an observation by ID.
    public Observation getObservation(int id) throws SQLException {
        String sql = """
            SELECT observations.*, accounts.email AS observer_email
            FROM observations
            JOIN accounts ON observations.observer_id = accounts.id
            WHERE observations.id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    SqliteAccountDAO accountDAO = new SqliteAccountDAO();
                    Account observer = accountDAO.getAccountByEmail(
                            result.getString("observer_email")
                    );

                    Observation observation = new Observation(
                            observer,
                            result.getString("location"),
                            result.getString("animal_seen"),
                            LocalDate.parse(result.getString("observed_at"))
                    );

                    observation.setId(result.getInt("id"));
                    return observation;
                }
            }
        }

        return null;
    }
    public static void main(String[] args) {
        try {
            DatabaseInitializer.initialize();

            SqliteObservationDAO dao = new SqliteObservationDAO();
            Observation observation = dao.getObservation(1);

            if (observation != null) {
                System.out.println("ID: " + observation.getId());
                System.out.println("Observer: " + observation.getObserver().getFullName());
                System.out.println("Location: " + observation.getLocation());
                System.out.println("Animal: " + observation.getAnimalSeen());
                System.out.println("Date: " + observation.getObservedAt());
            } else {
                System.out.println("Observation not found.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to read observation: " + e.getMessage());
        }
    }
}