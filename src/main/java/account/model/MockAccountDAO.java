package account.model;

import java.util.ArrayList;
import java.util.List;

public class MockAccountDAO implements IAccountDAO{
    /**
     * A static list of contacts to be used as a mock database.
     */
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static int autoIncrementedId = 0;

    public MockAccountDAO() {
        // Add some initial accounts to the mock database
        addAccount(new Account("John", "Doe", "johndoe@example.com", "Password1!"));
        addAccount(new Account("Jane", "Doe", "janedoe@example.com", "Password1!"));
        addAccount(new Account("Jay", "Doe", "jaydoe@example.com", "Password1!"));
    }

    @Override
    public void addAccount(Account account) {
        account.setId(autoIncrementedId);
        autoIncrementedId++;
        accounts.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == account.getId()) {
                accounts.set(i, account);
                break;
            }
        }
    }

    @Override
    public void deleteAccount(Account account) {
        accounts.remove(account);
    }

    @Override
    public Account getAccount(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
}
