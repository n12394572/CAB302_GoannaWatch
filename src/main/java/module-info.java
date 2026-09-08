module account {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens account to javafx.fxml;
    exports account;
    exports account.controller;
    opens account.controller to javafx.fxml;
    exports account.model;
    opens account.model to javafx.fxml;
}