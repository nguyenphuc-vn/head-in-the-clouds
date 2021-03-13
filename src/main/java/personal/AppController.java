package personal;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
        private final static String quoteImage ="/personal/images/Quote.png";

        @FXML
        private Ellipse ellipse;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            setImage(ellipse,getPath(quoteImage));
        }
        @FXML
        public void switchQuote() throws IOException {
            App.setRoot("quote");
        }
        @FXML
        public static void switchBackHome()throws IOException{
            App.setRoot("app");
        }


    private void setImage(Shape shape, String quote){
        Image image = new Image(quote);
        shape.setFill(new ImagePattern(image));
    }
    private static String getPath(String path){
            return AppController.class.getResource(path).toExternalForm();
    }
}
