/**
 * Provides GoannaWatch management functionality, including GoannaWatch creation,
 * login, and session tracking, along with the JavaFX UI controllers
 * that drive the application's screens.
 */
module GoannaWatch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.validator;
    requires atlantafx.base;


    opens GoannaWatch to javafx.fxml;
    exports GoannaWatch;
    exports GoannaWatch.account.controller;
    opens GoannaWatch.account.controller to javafx.fxml;
    exports GoannaWatch.account.model;
    opens GoannaWatch.account.model to javafx.fxml;
    exports GoannaWatch.observations.model;
    opens GoannaWatch.observations.model to javafx.fxml;
    exports GoannaWatch.observations.controller;
    opens GoannaWatch.observations.controller to javafx.fxml;
}