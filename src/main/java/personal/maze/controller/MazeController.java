package personal.maze.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import personal.App;
import personal.maze.helper.Maze;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *Control the UI maze
 *process input
 */
public class MazeController implements Initializable {
    private Maze maze;
    private int wall; // input wall
    private int speed;// input speed
    @FXML
    private Button runButton;
    @FXML
    private Button setButton;
    @FXML
    private Label wallLabel;
    @FXML
    private Label speedLabel;
    @FXML
    private ComboBox<String> wallCombo;
    @FXML
    private ComboBox<String> speedCombo;
    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wall = 10;speed=60;
        wallBox();
        speedBox();
        setup();


    }

    /**
     * Run button
     * Call {@link Maze#drawTheLine()}
     */
    @FXML
    public void draw(){
        runButton.setDisable(true);
        maze.drawTheLine();

    }

    /**
     * Pause button
     * call {@link Maze#pauseTimeline()}
     * set speed = 0;
     */
    @FXML
    public void pauseTimeline(){
       runButton.setDisable(false);
        maze.pauseTimeline();

    }

    /**
     * set new state
     * call {@link Maze#reset()}
     * && {@link MazeController#setup()} set things up again
     */
    @FXML
    public void reset(){
        runButton.setDisable(false);
        setButton.setDisable(false);
        maze.reset();
        setup();

    }

    /**
     * Confirm button speed and wall value
     */
    @FXML
    private void set(){
        setButton.setDisable(true);
        setup();

    }

    /**
     * new object  Maze
     * call {@link Maze#setUpGc(Canvas)}
     * && {@link Maze#draw()}
     */
    private void setup(){
        maze = new Maze(wall, speed);
        maze.setUpGc(canvas);
        maze.draw();

    }
    @FXML
    private void backHome() throws IOException {
        App.switchBackHome();
    }

    /**
     * Set value Wall combobox
     * Make it dynamic , Bind label to it value
     */
    private void wallBox(){
        wallCombo.getItems().setAll("8","10","20","40","60","100");
        wallLabel.textProperty().bind((wallCombo.getSelectionModel().selectedItemProperty()));
        wallCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> switchWall());

    }

    /**
     * switch case of wall
     */
    private void switchWall(){
        switch (wallCombo.getValue()) {
            case "8" : wall = 8;break;
            case "10" : wall = 10;break;
            case "20" : wall = 20;break;
            case "40" : wall = 40;break;
            case "60" : wall = 60;break;
            case "100" : wall = 100;break;
        }
    }
    /**
     * Set value Speed combobox
     * Make it dynamic , Bind label to it value
     */
    private void speedBox(){
            speedCombo.getItems().setAll("x1", "x2", "x3");
            speedLabel.textProperty().bind(speedCombo.getSelectionModel().selectedItemProperty());
            speedCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> switchSpeed());

    }

    /**
     * switch case of speed
     */
    private void switchSpeed(){
        switch (speedCombo.getValue()) {
            case "x1" : speed = 60 ;break;
            case "x2" : speed = 60 / 2;break;
            case "x3" : speed = 60 / 3;break;
        }
    }
}
