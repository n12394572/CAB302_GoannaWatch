package GoannaWatch.account.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MockAccountDAOTest {

    @Test
    void addedAccountShouldBeRetrievable() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account (
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        Account retrievedAccount = dao.getAccount(account.getId());

        assertNotNull(retrievedAccount);
        assertEquals(account.getId(), retrievedAccount.getId());
        assertEquals("Luke", retrievedAccount.getFirstName());
        assertEquals("Smith", retrievedAccount.getLastName());
        assertEquals("luke.smith@example.com", retrievedAccount.getEmail());

    }

    @Test
    void addedAccountShouldReceiveAnId() {
        MockAccountDAO dao = new MockAccountDAO();

        Account firstAccount = new Account (
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        Account secondAccount = new Account (
                "Jane",
                "Smith",
                "jane1@example.com",
                "Password1!"
        );

        dao.addAccount(firstAccount);
        dao.addAccount(secondAccount);

        assertNotEquals(firstAccount.getId(), secondAccount.getId());

    }

    @Test
    void allAccountsShouldBeReturned() {
        MockAccountDAO dao = new MockAccountDAO();

        List<Account> accounts = dao.getAllAccounts();

        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void accountShouldBeUpdated() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        account.setFirstName("Lucas");
        account.setLastName("Jones");

        dao.updateAccount(account);

        Account updatedAccount = dao.getAccount(account.getId());

        assertNotNull(updatedAccount);
        assertEquals("Lucas", updatedAccount.getFirstName());
        assertEquals("Jones", updatedAccount.getLastName());

    }

    @Test
    void accountShouldBeDeleted() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        int accountId = account.getId();

        dao.deleteAccount(account);

        assertNull(dao.getAccount(accountId));

    }

    @Test
    void requestingUnknownAccountShouldReturnNull() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = dao.getAccount(Integer.MAX_VALUE);

        assertNull(account);
    }

    @Test
    void returnedAccountListShouldNotExposeInternalList() {
        MockAccountDAO dao = new MockAccountDAO();

        int originalSize = dao.getAllAccounts().size();

        List<Account> returnedAccounts = dao.getAllAccounts();
        returnedAccounts.clear();

        assertEquals(originalSize, dao.getAllAccounts().size());
    }


}