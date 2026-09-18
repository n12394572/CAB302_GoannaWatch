package GoannaWatch.account.controller;

import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.MockAccountDAO;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void existingAccountShouldBeFoundByEmail() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "loginlookup1@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        LoginController controller = new LoginController(dao);

        Optional<Account> result = controller.findAccountByEmail("loginlookup1@example.com");

        assertTrue(result.isPresent());
        assertSame(account, result.get());
    }

    @Test
    void emailLookupShouldIgnoreCase() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "loginlookup2@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        LoginController controller = new LoginController(dao);

        Optional<Account> result = controller.findAccountByEmail("LOGINLOOKUP2@EXAMPLE.COM");

        assertTrue(result.isPresent());
        assertSame(account, result.get());
    }

    @Test
    void unknownEmailShouldReturnEmptyOptional() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "loginlookup3@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        LoginController controller = new LoginController(dao);

        Optional<Account> result = controller.findAccountByEmail("definitelyunknown@example.com");

        assertTrue(result.isEmpty());
    }

}