package account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The main application class for the App application. This class extends the JavaFX Application class and starts the JavaFx lifecycle.
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Account Manager");
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
