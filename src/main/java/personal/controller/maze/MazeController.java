package personal.controller.maze;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import personal.helper.maze.Maze;

import java.net.URL;
import java.util.ResourceBundle;

public class MazeController implements Initializable {
    private Maze maze;
    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        maze = new Maze();
        maze.setUp();
        maze.setUpGc(canvas);
        maze.draw();

    }
    @FXML
    public void draw(){
        maze.run();

    }
}
