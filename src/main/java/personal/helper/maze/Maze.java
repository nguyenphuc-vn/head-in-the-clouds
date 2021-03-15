package personal.helper.maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import personal.model.maze.Cell;

public class Maze {
    private final static int wall = 20;
    private final static int size =800;
    private Cell [][] grids;

    public int getCol() {
        return Math.abs(size/wall);
    }

    public int getRow() {
        return Math.abs(size/wall);
    }

    public void setUp(){
        grids = new Cell[getRow()][getCol()];
        for(int rows = 0; rows<getRow();rows++){
            for(int cols = 0; cols<getCol();cols++){
                grids[rows][cols] =new Cell(rows*wall,cols*wall);
            }
        }
    }

    public GraphicsContext show(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.setStroke(Color.BLACK);

        gc.setFill(Color.DARKSEAGREEN);

        draw(gc);
        return gc;
    }
    private void draw(GraphicsContext gc){
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                gc.fillRect(grids[rows][cols].getDimensionX(),grids[rows][cols].getDimensionY(),wall,wall);
                gc.strokeRect(grids[rows][cols].getDimensionX(),grids[rows][cols].getDimensionY(),wall,wall);
            }
        }
    }
}
