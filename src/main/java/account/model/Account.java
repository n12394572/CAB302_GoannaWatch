package account.model;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Account {
    // At least 8 chars, 1 digit, special, lower, and uppercase char, with no whitespace
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public Account(String firstName, String lastName, String email, String password) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        if (password != null && !password.isBlank()){
            setPassword(password);
        } else {
            this.password = password;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isBlank() || firstName==null){
            throw new InputMismatchException("First name cannot be blank.");
        }
        this.firstName = capitaliseName(firstName);
    }

    private String capitaliseName(String entry) {
        return entry.substring(0,1).toUpperCase() + entry.substring(1);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isBlank() || lastName==null){
            throw new InputMismatchException("Last name cannot be blank.");
        }
        this.lastName = capitaliseName(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (email==null || !emailValidator.isValid(email)){
            throw new InputMismatchException("Email must be a valid email address.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isBlank() || !PASSWORD_PATTERN.matcher(password).matches()){
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters and include an uppercase letter, a number, and a special character."
            );
        }
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

