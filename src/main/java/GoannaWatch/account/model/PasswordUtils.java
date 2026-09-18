package GoannaWatch.account.model;

import com.password4j.Argon2Function;
import com.password4j.Password;
import com.password4j.types.Argon2;

/**
 * Creates salted password hashes and checks passwords.
 */
public class PasswordUtils {

    // Memory in KiB, iterations, parallelism, output bytes, algorithm.
    private static final Argon2Function ARGON2 =
            Argon2Function.getInstance(19456, 2, 1, 32, Argon2.ID);

    /**
     * Creates a hash with a new random salt.
     *
     * @param password the password to hash
     * @return the encoded hash, including its salt and settings
     */
    public static String hashPassword(String password) {
        return Password.hash(password)
                .addRandomSalt(16)
                .with(ARGON2)
                .getResult();
    }

    /**
     * Checks a password using the salt and settings in the stored hash.
     *
     * @param password the password entered by the user
     * @param storedHash the saved hash
     * @return true if the password matches
     */
    public static boolean checkPassword(String password, String storedHash) {
        Argon2Function settings =
                Argon2Function.getInstanceFromHash(storedHash);

        return Password.check(password, storedHash).with(settings);
    }
}