/**
 * Provides account management functionality, including account creation,
 * login, and session tracking, along with the JavaFX UI controllers
 * that drive the application's screens.
 */
module account {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.validator;


    opens account to javafx.fxml;
    exports account;
    exports account.controller;
    opens account.controller to javafx.fxml;
    exports account.model;
    opens account.model to javafx.fxml;
    exports observations;
    opens observations to javafx.fxml;
    exports observations.model;
    opens observations.model to javafx.fxml;
    exports observations.controller;
    opens observations.controller to javafx.fxml;
}