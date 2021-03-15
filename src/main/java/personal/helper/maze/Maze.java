package personal.helper.maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import personal.model.maze.Cell;
import java.util.logging.*;

public class Maze {
    private final static Logger LOGGER = Logger.getLogger(Maze.class.getName());

    private GraphicsContext gc;
    private Cell [][] grids;
    private Cell [][] current;
    private boolean [][] visited ;


    public void setUp(){
        grids = new Cell[Cell.getRow()][Cell.getCol()];
        current= new Cell[Cell.getRow()][Cell.getCol()];
        visited = new boolean[Cell.getRow()][Cell.getCol()];
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                grids[rows][cols] =new Cell(rows,cols);
            }
        }
        current[0][0] = grids[0][0];

    }


    public void setUpGc(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKSEAGREEN);
        gc.setLineWidth(1);

    }
    public void draw(){
        //visited[0][0] = true;
        grids[0][0].setVisited();
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                grids[rows][cols].drawLine(gc);

               }
            }
    }
    public void run(){



    }








}
