package account;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class SignupApplication extends Application {
    // Defines running the Login Page window and the window's various qualities
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SignupApplication.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Goanna Watch | Create your Account");
        stage.setScene(scene);
        stage.show();
    }
}
