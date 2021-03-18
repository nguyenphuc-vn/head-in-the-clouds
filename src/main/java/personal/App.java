package personal;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private final static int MAX_WIDTH = 1020;
    private final static int MAX_HEIGHT = 620;
    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("app"), MAX_WIDTH, MAX_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("HEAD IN THE CLOUDS");
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/personal/images/clouds.jpg")));
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void switchBackHome()throws IOException {
        App.setRoot("app");
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/personal/view/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}