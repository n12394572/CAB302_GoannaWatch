package GoannaWatch;

import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The main application class for the account.App application. This class extends the JavaFX Application class and starts the JavaFx lifecycle.
 */
public class App extends Application {
    public static final double WIDTH = 800;
    public static final double HEIGHT = 600;

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle("GoannaWatch");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for the application. This method is used to launch the JavaFX application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
