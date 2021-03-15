package personal.helper.maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import personal.model.maze.Cell;
import java.util.Stack;
import java.util.logging.*;

public class Maze {
    private final static Logger LOGGER = Logger.getLogger(Maze.class.getName());
    private final static int wall = 100;
    private final static int WIDTH =800;
    private final static int HEIGHT =600;
    private GraphicsContext gc;
    private Cell [][] grids;
    private Cell [][] current;
    private boolean [][] visited ;
    private Cell top,right,bottom,left;
    private boolean topBoo, rightBoo, bottomBoo, leftBoo;






    public void setUp(){
        grids = new Cell[getRow()][getCol()];
        current= new Cell[getRow()][getCol()];
        visited = new boolean[getRow()][getCol()];

       // walls = new boolean[]{top,right,bottom,left};
        for(int rows = 0; rows<getRow();rows++){
            for(int cols = 0; cols<getCol();cols++){
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
        visited[0][0] = true;
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                drawLine(grids,rows,cols);

               }
            }
    }
    public void run(){
        for(int rows = 0; rows<grids.length;rows++){
            for(int cols = 0; cols<grids[0].length;cols++){
                //LOGGER.info(rows+" "+cols);
                Cell next = checkNeighbors(current,rows,cols);
                LOGGER.info("boolean "+visited[rows][cols]);
                if(visited[rows][cols]) {
                    drawLine(current, rows, cols);
                }
                if(next!=null){
                    next.setVisited();
                    current[rows][cols]= next;


                }

            }
        }

    }

    /**
     *            ---------
     *            !  top  !
     *            ---------
     *   -------  ---------  -------
     *   !left !  !current!  !right!
     *   -------  ---------  -------
     *            ---------
     *            !bottom !
     *            ---------
     */
    private Cell checkNeighbors(Cell[][] grids,int rows, int cols){
        Stack<Cell> storeCell = new Stack<>();
        top =grids[checkRow(rows-1)][checkCol(cols)];
        right =grids[checkRow(rows)][checkCol(cols+1)];
        bottom =grids[checkRow(rows+1)][checkCol(cols)];
        left =grids[checkRow(rows)][checkCol(cols-1)];

        if(top!=null&&!top.isVisited()){
            storeCell.push(top);
        }
        if(right!=null&&!right.isVisited()){
            storeCell.push(right);
        }
        if(bottom!=null&&!bottom.isVisited()){
            storeCell.push(bottom);
        }
        if(left!=null&&!left.isVisited()){
            storeCell.push(left);
        }
         //1.Choose the initial cell, mark it as visited
         // and push it to the stack

         //2.While the stack is not empty
        Cell currentCell = null;
         while (!storeCell.empty()){
             currentCell = storeCell.pop();
             currentCell.setVisited();
             visited[currentCell.getDimensionX()][currentCell.getDimensionY()] = currentCell.isVisited();
             if(!currentCell.isVisited()){
                 storeCell.push(currentCell);
             }
             currentCell = storeCell.pop();
         }
         return currentCell;
    }


    private void drawLine(Cell [][] grids,int rows, int cols){
        /*
          TOP
          ------
         */
        if(!topBoo){
            gc.strokeLine(getX(grids, rows, cols), getY(grids, rows, cols),
                    getX(grids, rows, cols) + wall, getY(grids, rows, cols));
        }
        /*
               |
               |
           RIGHT
         */
        if(!rightBoo){
            gc.strokeLine(getX(grids,rows,cols)+wall,getY(grids,rows,cols),
                    getX(grids,rows,cols)+wall,getY(grids,rows,cols)+wall);
        }


        /*
          ------
          BOTTOM
         */
        if(!bottomBoo) {
            gc.strokeLine(getX(grids, rows, cols), getY(grids, rows, cols) + wall,
                    getX(grids, rows, cols) + wall, getY(grids, rows, cols) + wall);
        }
        /*
          |
          |
          LEFT
         */
        if(!leftBoo) {
            gc.strokeLine(getX(grids, rows, cols), getY(grids, rows, cols),
                    getX(grids, rows, cols), getY(grids, rows, cols) + wall);
        }
        if(visited[rows][cols]) {
            gc.fillRect(getX(grids, rows, cols), getY(grids, rows, cols), wall, wall);

        }

    }
    private int checkRow(int rows){
        if(rows<0||rows>getRow()-1){
            return 0;
        }
        return rows;
    }
    private int checkCol(int cols){
        if(cols<0||cols>getCol()-1){
            return 0;
        }
        return cols;
    }
    private int getCol() {
        return Math.abs(HEIGHT/wall);
    }
    private int getRow() {
        return Math.abs(WIDTH/wall);
    }
    private int getX(Cell [][] grids, int rows, int cols){
        return grids[rows][cols].getDimensionX()*wall;
    }

    private int getY(Cell [][] grids, int rows, int cols){
        return grids[rows][cols].getDimensionY()*wall;
    }
}
