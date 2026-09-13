package GoannaWatch.observations.model;

import GoannaWatch.database.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

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
            List<Observation> observations = dao.getAllObservations();
            SqliteAccountDAO accountDAO = new SqliteAccountDAO();
            Account account = accountDAO.getAccountByEmail("min.test2@example.com");

            if (account == null) {
                System.out.println("Test account not found.");
                return;
            }

            Observation secondObservation = new Observation(
                    account,
                    "Gold Coast",
                    "Koala",
                    LocalDate.now()
            );
            dao.addObservation(secondObservation);




            System.out.println("Total observations: " + observations.size());

            for (Observation observation : observations) {
                System.out.println(
                        observation.getId() + " | "
                                + observation.getObserver().getFullName() + " | "
                                + observation.getLocation() + " | "
                                + observation.getAnimalSeen() + " | "
                                + observation.getObservedAt()
                );
            }
        } catch (SQLException e) {
            System.err.println("Failed to read observations: " + e.getMessage());
        }
    }
    public List<Observation> getAllObservations() throws SQLException {
        List<Observation> observations = new ArrayList<>();

        String sql = """
            SELECT observations.*, accounts.email AS observer_email
            FROM observations
            JOIN accounts ON observations.observer_id = accounts.id
            ORDER BY observations.id
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            SqliteAccountDAO accountDAO = new SqliteAccountDAO();

            while (result.next()) {
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
                observations.add(observation);
            }
        }

        return observations;
    }
}