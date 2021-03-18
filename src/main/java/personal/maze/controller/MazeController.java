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

public class MazeController implements Initializable {
    private Maze maze;
    private int wall;
    private int speed;
    @FXML
    private Button runButton;
    @FXML
    private Button setButton;
    @FXML
    private Label wLabel;
    @FXML
    private Label sLabel;
    @FXML
    private ComboBox<String> wCombo;
    @FXML
    private ComboBox<String> sCombo;
    @FXML
    private Canvas canvas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wall = 10;speed=60;
        wallBox();
        speedBox();
        setup();


    }
    @FXML
    public void draw(){
        runButton.setDisable(true);
        maze.drawTheLine();

    }
    @FXML
    public void pauseTimeline(){
       runButton.setDisable(false);
        maze.pauseTimeline();

    }
    @FXML
    public void reset(){
        runButton.setDisable(false);
        setButton.setDisable(false);
        maze.reset();
        setup();

    }
    @FXML
    private void set(){
        setButton.setDisable(true);
        setup();

    }
    private void setup(){
        maze = new Maze(wall, speed);
        maze.setUpGc(canvas);
        maze.draw();

    }
    @FXML
    private void backHome() throws IOException {
        App.switchBackHome();
    }

    private void wallBox(){
        wCombo.getItems().setAll("8","10","20","40","60","100");
        wLabel.textProperty().bind((wCombo.getSelectionModel().selectedItemProperty()));
        wCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> switchWall());

    }
    private void switchWall(){
        switch (wCombo.getValue()) {
            case "8" -> wall = 8;
            case "10" -> wall = 10;
            case "20" -> wall = 20;
            case "40" -> wall = 40;
            case "60" -> wall = 60;
            case "100" -> wall = 100;
        }
    }
    private void speedBox(){
            sCombo.getItems().setAll("x1", "x2", "x3");
            sLabel.textProperty().bind(sCombo.getSelectionModel().selectedItemProperty());
            sCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> switchSpeed());

    }
    private void switchSpeed(){
        switch (sCombo.getValue()) {
            case "x1" -> speed = 60;
            case "x2" -> speed = 60 / 2;
            case "x3" -> speed = 60 / 3;
        }
    }
}
