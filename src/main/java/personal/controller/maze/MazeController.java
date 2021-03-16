package personal.controller.maze;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import personal.App;
import personal.helper.maze.Maze;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MazeController implements Initializable {
    private Maze maze;
    @FXML
    private Canvas canvas;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup();

    }
    @FXML
    public void draw(){
        maze.drawTheLine();

    }
    @FXML
    public void pauseTimeline()  {
        maze.pauseTimeline();

    }
    @FXML
    public void reset(){
        maze.reset();
        setup();
    }
    private void setup(){
        maze = new Maze();
        maze.setUpGc(canvas);
        maze.draw();
    }
    @FXML
    private void backHome() throws IOException {
        App.switchBackHome();
    }


}
