import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("form.fxml")); // adjust file name
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 600, 400)); // adjust size as needed
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

