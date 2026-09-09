package GoannaWatch.account.model;

/**
 * Holds reference to the {@link Account} currently logged in.
 */
public class Session {

    private static Account currentAccount;

    /**
     * Private constructor to prevent instantiation.
     */
    private Session() {}

    /**
     * Returns the account currently logged in.
     * @return The account currently logged in.
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Sets the account for the current session.
     * @param account The account to set as logged in.
     */
    public static void setCurrentAccount(Account account){
        currentAccount = account;
    }

    /**
     * Clears the current session.
     */
    public static void clear() {
        currentAccount = null;
    }
}
