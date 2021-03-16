package personal.helper.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import personal.model.maze.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.*;

public class Maze {
    private final static Logger LOGGER = Logger.getLogger(Maze.class.getName());

    private GraphicsContext gc;
    private Cell[][] grids;
    //private boolean[][] visited;
    private Cell root;
    //private Stack<Cell> store;

    public Maze(){
        grids = new Cell[Cell.getRow()][Cell.getCol()];
        //visited = new boolean[Cell.getRow()][Cell.getCol()];
        //store = new Stack<>();
        for (int rows = 0; rows < grids.length; rows++) {
            for (int cols = 0; cols < grids[0].length; cols++) {
                grids[rows][cols] = new Cell(rows, cols);

            }
        }
        //Choose the initial cell,
        // mark it as visited and push it to the stack
        root = grids[0][0];
        root.setVisited();
        //store.push(root);
    }

    public void setUpGc(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKSEAGREEN);
        gc.setLineWidth(1);

    }

    public void draw() {
        for (int rows = 0; rows < grids.length; rows++) {
            for (int cols = 0; cols < grids[0].length; cols++) {
                grids[rows][cols].drawLine(gc);
            }
        }

    }

    public void run() {

                Cell next = checkNeighbors();
                if (next != null) {
                    LOGGER.info(next.toString());
                    next.setVisited();
                    root.drawCell(gc);
                    root = next;
               }
        }
    public void drawTheLine(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),e->{
            run();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    /*
     *                    -----------------
     *                    !  top (r-1,c)  !
     *                    -----------------
     *   ---------------  ---------------  ---------------
     *   !left (r,c-1) !  !current (r,c)!  !right (r, c+1!
     *   ---------------  --------------- ---------------
     *                    -----------------
     *                    !bottom (r+1,c) !
     *                    -----------------
     */
    private Cell checkNeighbors(){
        List<Cell> neighbors = new ArrayList<>();

        Cell top = checkTop(grids,root.getDimensionX(),root.getDimensionY());
        Cell right = checkRight(grids,root.getDimensionX(),root.getDimensionY());
        Cell bottom = checkBottom(grids,root.getDimensionX(), root.getDimensionY());
        Cell left = checkLeft(grids,root.getDimensionX(),root.getDimensionY());


        if(top!=null&&!top.isVisited()){
            neighbors.add(top);
            //LOGGER.info(top.toString());
        }
        if(right!=null&&!right.isVisited()){
            neighbors.add(right);
            //LOGGER.info(right.toString());
        }
        if(bottom!=null&&!bottom.isVisited()){
            neighbors.add(bottom);
            // LOGGER.info(bottom.toString());
        }
        if(left!=null&&!left.isVisited()){
            neighbors.add(left);
            //LOGGER.info(left.toString());
        }

        if(neighbors.size()>0){
            int random = Math.abs(ThreadLocalRandom.current().nextInt(0,neighbors.size()));
            return neighbors.get(random);
        }
        return null;
    }
    private Cell checkTop(Cell [][] grids,int row,int col){
        if(withinGrid(row-1,col)){
            return grids[row-1][col];
        }
        else return null;
    }
    private Cell checkRight(Cell [][] grids,int row,int col){
        if(withinGrid(row,col+1)){
            return grids[row][col+1];
        }
        else return null;
    }
    private Cell checkBottom(Cell [][] grids,int row,int col){
        if(withinGrid(row+1,col)){
            return grids[row+1][col];
        }
        else return null;
    }
    private Cell checkLeft(Cell [][] grids,int row,int col){
        if(withinGrid(row,col+1)){
            return grids[row][col+1];
        }
        else return null;
    }
    private boolean withinGrid(int row, int col) {

        if ((row < 0) || (col < 0)) {
            return false;
        }
        if ((col >= Cell.getCol()) || (row >= Cell.getRow())) {
            return false;
        }
        return true;
    }

}

    /*private Cell checkNext(){
        int row = root.getDimensionX();
        int col = root.getDimensionY();
        Cell top =current[checkRow(row-1)][checkCol(col)];
        Cell right =current[checkRow(row)][checkCol(col+1)];
        Cell bottom =current[checkRow(row+1)][checkCol(col)];
        Cell left =current[checkRow(row)][checkCol(col-1)];

        // must check null first or NullpointEx
        if(top!=null&&!top.isVisited()){
            store.push(top);
            //LOGGER.info(top.toString());
        }
        if(right!=null&&!right.isVisited()){
            store.push(right);
            //LOGGER.info(right.toString());
        }
        if(bottom!=null&&!bottom.isVisited()){
            store.push(bottom);
           // LOGGER.info(bottom.toString());
        }
        if(left!=null&&!left.isVisited()){
            store.push(left);
            //LOGGER.info(left.toString());
        }

        //2 While the stack is not empty
        while (!store.empty()) {
            //Pop a cell from the stack and make it a current cell
            root = store.pop();
            //set it to true
            root.setVisited();
            // put it in 2d boolean as visited
            visited[root.getDimensionX()][root.getDimensionY()] = root.isVisited();

            //If the current cell has any neighbours which have not been visited
            if(!store.empty()){
                if(!store.peek().isVisited()){
                    store.push(root);
                    int rd = ThreadLocalRandom.current().nextInt(0,store.size());
                    root = store.elementAt(rd);
                    root.setVisited();
                    visited[root.getDimensionX()][root.getDimensionY()] = root.isVisited();
                    store.push(root);
                }
            }
        }
        return root;

    }


    private int checkRow(int rows){
        if(rows<0){
            return 0;
        }else if(rows>Cell.getRow()-1) {
        return Cell.getRow()-1;
    }
        return rows;
    }
    private int checkCol(int cols){
        if(cols<0){
            return 0;
        }else if(cols>Cell.getCol()-1){
            return Cell.getCol()-1;
        }
        return cols;
    }*/





