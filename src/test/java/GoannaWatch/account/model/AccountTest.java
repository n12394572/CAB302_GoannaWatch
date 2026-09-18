package GoannaWatch.account.model;

import org.junit.jupiter.api.Test;
import java.util.InputMismatchException;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void validAccountShouldBeCreated() {
        Account account = new Account(
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        assertEquals("Luke", account.getFirstName());
        assertEquals("Smith", account.getLastName());
        assertEquals("luke.smith@example.com", account.getEmail());
        assertEquals("Password1!", account.getPassword());

    }

    @Test
    void accountShouldReturnFullName() {
        Account account = new Account(
                "Luke",
                "Smith",
                "luke.smith@example.com",
                "Password1!"
        );

        assertEquals("Luke Smith", account.getFullName());
    }

    @Test
    void namesShouldBeCapitalised() {
        Account account = new Account(
                "luke",
                "smith",
                "luke.smith@example.com",
                "Password1!"
        );

        assertEquals("Luke", account.getFirstName());
        assertEquals("Smith", account.getLastName());
    }

    @Test
    void blankFirstNameShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        "",
                        "Smith",
                        "luke.smith@example.com",
                        "Password1!"
                )
        );
    }

    @Test
    void blankLastNameShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        "Luke",
                        "",
                        "luke.smith@example.com",
                        "Password1!"
                )
        );
    }

    @Test
    void invalidEmailShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "not-an-email",
                        "Password1!"
                )
        );
    }

    @Test
    void shortPasswordShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "Pass1!"
                )
        );
    }

    @Test
    void nullFirstNameShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        null,
                        "Smith",
                        "luke.smith@example.com",
                        "Password1!"
                )
        );
    }

    @Test
    void nullLastNameShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        "Luke",
                        null,
                        "luke.smith@example.com",
                        "Password1!"
                )
        );
    }

    @Test
    void nullEmailShouldBeRejected() {
        assertThrows(InputMismatchException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        null,
                        "Password1!"
                )
        );
    }

    @Test
    void passwordWithoutUppercaseShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "password1!"
                )
        );
    }

    @Test
    void passwordWithoutLowercaseShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "PASSWORD1!"
                )
        );
    }

    @Test
    void passwordWithoutNumberShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "Password!"
                )
        );
    }

    @Test
    void passwordWithoutSpecialCharacterShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "Password1"
                )
        );
    }

    @Test
    void passwordContainingWhiteSpaceShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        "Password 1!"
                )
        );
    }

    @Test
    void blankPasswordShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        ""
                )
        );
    }

    @Test
    void nullPasswordShouldBeRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(
                        "Luke",
                        "Smith",
                        "luke.smith@example.com",
                        null
                )
        );
    }


}