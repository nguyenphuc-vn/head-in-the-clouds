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
        private final static String quoteImage =
                AppController.class.getResource( "/personal/images/Quote.png").toExternalForm();
        @FXML
        private Ellipse ellipse;

        private void setImage(Shape shape, String quote){
            Image image = new Image(quote);
            shape.setFill(new ImagePattern(image));
        }
        @FXML
        public void switchQuote() throws IOException {
            App.setRoot("quote");
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setImage(ellipse,quoteImage);
    }
}
