package account.model;

import java.util.InputMismatchException;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public Account(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
            throw new InputMismatchException();
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
        this.lastName = capitaliseName(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isBlank() || password == null){
            throw new InputMismatchException();
        }
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

