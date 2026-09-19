package GoannaWatch.database;

import GoannaWatch.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests database connections using a temporary SQLite file.
 */

public class DatabaseConnectionTest {

    /**
     * Checks that the connection uses the requested database file.
     *
     * @param folder temporary folder provided by JUnit
     * @throws Exception if the database connection fails
     */

    @Test
    public void connectsToTemporaryDatabase(@TempDir Path folder) throws Exception {

        Path databaseFile = folder.resolve("test.db");
        String testUrl = "jdbc:sqlite:" + databaseFile.toAbsolutePath();


        String previousUrl = System.getProperty("goannawatch.db.url");

        // Use a temporary database instead of the app's database.
        try {
            System.setProperty("goannawatch.db.url", testUrl);

            try (Connection connection = DatabaseConnection.getConnection()) {
                assertEquals(testUrl, connection.getMetaData().getURL());
                assertTrue(Files.exists(databaseFile));
            }
        } finally {
            // Restore the setting so it does not affect other tests.
            if (previousUrl == null) {
                System.clearProperty("goannawatch.db.url");
            } else {
                System.setProperty("goannawatch.db.url", previousUrl);
            }
        }
    }
}