package GoannaWatch.account.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @AfterEach
    void clearSession() {
        Session.clear();
    }

    @Test
    void currentAccountShouldInitiallyBeNullAfterClear() {
        Session.clear();

        assertNull(Session.getCurrentAccount());
    }

    @Test
    void currentAccountShouldBeSet() {
        Account account = new Account(
                "Luke",
                "Smith",
                "luke@example.com",
                "Password1!"
        );

        Session.setCurrentAccount(account);

        assertSame(account, Session.getCurrentAccount());
    }

    @Test
    void currentAccountShouldBeCleared() {
        Account account = new Account (
                "Luke",
                "Smith",
                "luke@example.com",
                "Password1!"
        );

        Session.setCurrentAccount(account);

        Session.clear();

        assertNull(Session.getCurrentAccount());
    }













}