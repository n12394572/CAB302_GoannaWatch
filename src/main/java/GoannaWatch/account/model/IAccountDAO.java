package GoannaWatch.account.model;

import java.util.List;

/**
 * Interface for the Account Data Access Object that handles
 * the CRUD operations for the Account class with the database.
 */
public interface IAccountDAO {
    /**
     * Adds a new account to the database.
     *
     * @param account The account to add.
     */
    public void addAccount(Account account);

    /**
     * Updates an existing account in the database.
     *
     * @param account The account to update.
     */
    public void updateAccount(Account account);

    /**
     * Deletes an account from the database.
     *
     * @param account The account to delete.
     */
    public void deleteAccount(Account account);

    /**
     * Retrieves an account from the database.
     *
     * @param id The id of the account to retrieve.
     * @return The account with the given id, or null if not found.
     */
    public Account getAccount(int id);

    /**
     * Retrieves all accounts from the database.
     *
     * @return A list of all accounts in the database.
     */
    public List<Account> getAllAccounts();
}