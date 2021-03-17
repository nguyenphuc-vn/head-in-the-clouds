package personal.helper.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import personal.model.maze.Cell;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.*;

/**
 *
 */
public class Maze {
    private final static Logger LOGGER = Logger.getLogger(Maze.class.getName());
    private Stack<Cell> store;//Store visited cell(x,y)
    private GraphicsContext gc;// for drawing
    private Cell[][] grids;//every cell is a cell
    private Cell root;// to track the current cell
    private Timeline timeline;
    //int num=0;

    /**
     * base setup
     */
    public Maze(){
        grids = new Cell[Cell.getRow()][Cell.getCol()];
        store = new Stack<>();
        for (int rows = 0; rows < grids.length; rows++) {
            for (int cols = 0; cols < grids[0].length; cols++) {
                grids[rows][cols] = new Cell(rows, cols);
            }
        }
        //Choose the initial cell,
        //push it to the stack
        root = grids[0][0];
        store.push(root);

    }

    public void setUpGc(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.DARKSEAGREEN);
        gc.setLineWidth(1);
        gc.setStroke(Color.WHITE);
        gc.setGlobalAlpha(200);


    }
    // draw 4 lines : rectangle shape
    public void draw() {
        for (Cell[] grid : grids) {
            for (int cols = 0; cols < grids[0].length; cols++) {

                grid[cols].drawLine(gc);
            }
        }

    }

    public void run()  {
                    root.setVisited();
                //LOGGER.severe(root.toString());
                //Pick random cell
                Cell next = checkNeighbors();
                if (next != null) {
                   // LOGGER.info(next.toString());
                    store.push(root);
                    root.noWall(next);
                    //root.drawLine(gc);
                    root = next;
                    //LOGGER.info("root=next: "+root.toString());
               }
                else if(!store.empty()){
                    root = store.pop();
                    //int rd = getRandom(store.size());
                   // root = store.elementAt(rd);
                   // store.removeElementAt(rd);
                   // LOGGER.info("popped "+root.toString());
                }


               // LOGGER.info("in stack remains : "+ store.size());
        }


    public void drawTheLine(){
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(30),e->{
            //root.currentColor(gc);
            root.currentDot(gc);
            draw();

            run();


            if(store.empty()){
                timeline.stop();
                //LOGGER.info("DONE "+Cell.getCol()*Cell.getRow()+"store: "+ num);

            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void pauseTimeline(){
        timeline.jumpTo(Duration.ZERO);
        timeline.stop();
        LOGGER.info("PAUSE ");
    }
    public void reset(){
        timeline.stop();
        LOGGER.info("RESET");

    }

    /*
     *                    -----------------
     *                    !  top
     *                    -----------------
     *   ---------------  ---------------  --------------
     *   !left            !current (r,c)!  right        !
     *   ---------------  --------------- ---------------
     *                    -----------------
     *                    !bottom
     *                    -----------------
     */
    private Cell checkNeighbors(){
        List<Cell> neighbors = new ArrayList<>();
        //Stack<Cell> neighbors = new Stack<>();
        //Queue<Cell> neighbors = new ArrayDeque<>();

        Cell top = checkTop(root.getDimensionX(),root.getDimensionY());

        Cell right = checkRight(root.getDimensionX(),root.getDimensionY());

        Cell bottom = checkBottom(root.getDimensionX(),root.getDimensionY());

        Cell left = checkLeft(root.getDimensionX(),root.getDimensionY());


        //START CHECK
        /*
                Check edge cases and if cell hasnt visited yet
                push it in List
         */
        if(top!=null&& top.isVisited()){
            neighbors.add(top);
            //neighbors.push(top);
            //LOGGER.info(top.toString());
            //neighbors.offer(top);
        }
        if(right!=null&& right.isVisited()){
            neighbors.add(right);
            //neighbors.push(right);
            //neighbors.offer(right);
            //LOGGER.info(right.toString());
        }
        if(bottom!=null&& bottom.isVisited()){
            neighbors.add(bottom);
            //neighbors.push(bottom);
            //neighbors.offer(bottom);
            // LOGGER.info(bottom.toString());
        }
        if(left!=null&& left.isVisited()){
            neighbors.add(left);
            //neighbors.push(left);
            //neighbors.offer(left);
            //LOGGER.info(left.toString());
        }
        //END CHECK

        //Get unvisited
        if(neighbors.size()>0){
            return neighbors.get(getRandom(neighbors.size()));
            //return neighbors.get(0);

        }else
        return null;
    }

    private int getRandom(int num){
        return Math.round(ThreadLocalRandom.current().nextInt(0,num));
    }

    private Cell checkTop(int row,int col){
        if(withinGrid(row,col-1)){
            return grids[row][col-1];
        }
        return null;
    }
    private Cell checkRight(int row,int col){
        if(withinGrid(row+1,col)){
            return grids[row+1][col];
        }
        return null;
    }
    private Cell checkBottom(int row,int col){
        if(withinGrid(row,col+1)){
            return grids[row][col+1];
        }
        return null;
    }
    private Cell checkLeft(int row,int col){
        if(withinGrid(row-1,col)){
            return grids[row-1][col];
        }
        return null;
    }

    private boolean withinGrid(int row, int col) {

        if ((row < 0) || (col < 0)) {
            return false;
        }
        return (col <= Cell.getCol() - 1) && (row <= Cell.getRow() - 1);
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

 /*private void putItInStack() throws InterruptedException {
        //While the stack is not empty
        while (!store.empty()){
            Thread.sleep(200);
            //Pop a cell from the stack and make it a current cell
            root = store.pop();
            Cell next = checkNeighbors();
            //If the current cell has any neighbours which have not been visited
            if(next!=null){
                LOGGER.info(next.toString());
                //Push the current cell to the stack
                store.push(root);
                //Choose one of the unvisited neighbours
                //Remove the wall between the current cell and the chosen cell
                root.noWall(next);
                root.drawLine(gc);
                root.removeRec(gc);
                //Mark the chosen cell as visited and push it to the stack
                next.setVisited();
                store.push(next);
                root = next;
            }
        }
    }*/
 /* Cell rightTop = checkRightTop(root.getDimensionX(),root.getDimensionY());

        Cell rightBottom = checkRightBottom(root.getDimensionX(),root.getDimensionY());

        Cell leftTop = checkLeftTop(root.getDimensionX(),root.getDimensionY());

        Cell leftBottom = checkLeftBottom(root.getDimensionX(),root.getDimensionY());


if(rightTop!=null&&!rightTop.isVisited()){
        neighbors.add(rightTop);
        //neighbors.push(left);
        //neighbors.offer(left);
        //LOGGER.info(left.toString());
        }

        if(leftTop!=null&&!leftTop.isVisited()){
        neighbors.add(leftTop);
        //neighbors.push(left);
        //neighbors.offer(left);
        //LOGGER.info(left.toString());
        }

        if(leftBottom!=null&&!leftBottom.isVisited()){
        neighbors.add(leftBottom);
        //neighbors.push(left);
        //neighbors.offer(left);
        //LOGGER.info(left.toString());
        }
        if(rightBottom!=null&&!rightBottom.isVisited()){
        neighbors.add(rightBottom);
        //neighbors.push(left);
        //neighbors.offer(left);
        //LOGGER.info(left.toString());
        }
private Cell checkLeftBottom(int row, int col){
        if(withinGrid(row+1,col-1)){
        return grids[row+1][col-1];
        }
        return null;
        }
private Cell checkLeftTop(int row, int col){
        if(withinGrid(row-1,col-1)){
        return grids[row-1][col-1];
        }
        return null;
        }
private Cell checkRightTop(int row, int col){
        if(withinGrid(row-1,col+1)){
        return grids[row-1][col+1];
        }
        return null;
        }
private Cell checkRightBottom(int row, int col){
        if(withinGrid(row+1,col+1)){
        return grids[row+1][col+1];
        }
        return null;

  }*/
