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
        private final static String quoteImage ="Quote.png";
        private final static String mazeImage  ="maze.png" ;

        @FXML
        private Ellipse ellipseQ;
        @FXML
        private Ellipse ellipseM;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            setImage(ellipseQ,getPath(quoteImage));
            setImage(ellipseM,getPath(mazeImage));

        }
        @FXML
        public void switchQuote() throws IOException {
            App.setRoot("quote");
        }
        @FXML
        public void switchMaze() throws IOException {
            App.setRoot("maze");
        }




    private void setImage(Shape shape, String quote){
        Image image = new Image(quote);
        shape.setFill(new ImagePattern(image));
    }
    private static String getPath(String path){
            return AppController.class.getResource("/personal/images/"+path).toExternalForm();
    }
}
