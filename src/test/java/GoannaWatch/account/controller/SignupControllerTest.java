package GoannaWatch.account.controller;

import GoannaWatch.account.model.Account;
import GoannaWatch.account.model.MockAccountDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignupControllerTest {

    @Test
    void existingEmailShouldBeDetected() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "signup-existing@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        SignupController controller = new SignupController(dao);

        assertTrue(controller.isEmailAlreadyRegistered("signup-existing@example.com"));
    }

    @Test
    void existingEmailShouldBeDetectedIgnoringCase() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "signup-case@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        SignupController controller = new SignupController(dao);

        assertTrue(controller.isEmailAlreadyRegistered("SIGNUP-CASE@EXAMPLE.COM"));
    }

    @Test
    void unusedEmailShouldNotBeDetectedAsRegistered() {
        MockAccountDAO dao = new MockAccountDAO();

        Account account = new Account(
                "Luke",
                "Smith",
                "signup-used@example.com",
                "Password1!"
        );

        dao.addAccount(account);

        SignupController controller = new SignupController(dao);

        assertFalse(controller.isEmailAlreadyRegistered("signup-unused@example.com"));
    }


}