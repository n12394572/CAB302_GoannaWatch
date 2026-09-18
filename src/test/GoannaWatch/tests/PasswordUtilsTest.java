package GoannaWatch.tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import GoannaWatch.account.model.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests password hashing and verification.
 */
public class PasswordUtilsTest {

    /** Checks that the original password is accepted. */
    @Test
    public void testCorrectPassword() {
        String hash = PasswordUtils.hashPassword("Test123!");

        boolean matches = PasswordUtils.checkPassword("Test123!", hash);

        assertTrue(matches);
    }
    /** Checks that a wrong password is rejected. */
    @Test
    public void testWrongPassword() {
        String hash = PasswordUtils.hashPassword("Test123!");

        assertFalse(PasswordUtils.checkPassword("Wrong123!", hash));
    }

    /** Checks that random salts produce different valid hashes. */
    @Test
    public void testRandomSalt() {
        String firstHash = PasswordUtils.hashPassword("Test123!");
        String secondHash = PasswordUtils.hashPassword("Test123!");

        assertNotEquals(firstHash, secondHash);
        assertTrue(PasswordUtils.checkPassword("Test123!", firstHash));
        assertTrue(PasswordUtils.checkPassword("Test123!", secondHash));
    }
}