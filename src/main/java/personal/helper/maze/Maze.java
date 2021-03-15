package personal.helper.maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import personal.model.maze.Cell;

import java.util.Arrays;

public class Maze {
    private final static int wall = 60;
    private final static int WIDTH =600;
    private final static int HEIGHT =600;
    private GraphicsContext gc;
    private Cell [][] grids;
    private Cell [][] current;
    private boolean walls ;
    private boolean visited;




    public void setUp(){
        grids = new Cell[getRow()][getCol()];
        current= new Cell[getRow()][getCol()];

        for(int rows = 0; rows<getRow();rows++){
            for(int cols = 0; cols<getCol();cols++){
                grids[rows][cols] =new Cell(rows*wall,cols*wall);
                current = grids;
            }
        }

    }

    public void setUpGc(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKSEAGREEN);
        gc.setLineWidth(1);


    }
    public void draw(){
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                //gc.strokeRect(getX(grids,rows,cols),getY(grids,rows,cols),wall,wall);
                drawLine(grids,rows,cols);
                visited = current[rows][cols] == grids[0][0];
                if(this.visited){
                    gc.fillRect(getX(grids,rows,cols),getY(grids,rows,cols),wall,wall);
                }
            }
        }
    }
    private void drawLine(Cell [][] grids,int rows, int cols){
        /*
          TOP
          ------
         */
        gc.strokeLine(getX(grids,rows,cols),getY(grids,rows,cols),
                getX(grids,rows,cols)+wall,getY(grids,rows,cols));

        /*
               |
               |
           RIGHT
         */
        gc.strokeLine(getX(grids,rows,cols)+wall,getY(grids,rows,cols),
                getX(grids,rows,cols)+wall,getY(grids,rows,cols)+wall);

        /*
          ------
          BOTTOM
         */

        gc.strokeLine(getX(grids,rows,cols),getY(grids,rows,cols)+wall,
                getX(grids,rows,cols)+wall,getY(grids,rows,cols)+wall);

        /*
          |
          |
          LEFT
         */
        gc.strokeLine(getX(grids,rows,cols),getY(grids,rows,cols),
                getX(grids,rows,cols),getY(grids,rows,cols)+wall);
    }

    private int getCol() {
        return Math.abs(HEIGHT/wall);
    }
    private int getRow() {
        return Math.abs(WIDTH/wall);
    }
    private int getX(Cell [][] grids, int rows, int cols){
        return grids[rows][cols].getDimensionX();
    }
    private int getY(Cell [][] grids, int rows, int cols){
        return grids[rows][cols].getDimensionY();
    }
}
