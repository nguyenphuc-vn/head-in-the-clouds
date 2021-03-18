package  personal.maze.model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Demonstrate the cell position X anv Y.
 * Check visited cell.
 * Draw or not to draw a line between cells
 */
public class Cell {
    private int dimensionX;
    private int dimensionY;
    private boolean isVisited;
    //private boolean top,right,bottom,left;
    private final boolean[] walls = {true,true,true,true};

    public Cell(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    /**
     *
     * @param gc drawing
     */
    public void drawLine(GraphicsContext gc, int wall){
        int xWall = xMulWall(wall)+wall;
        int yWall = yMulWall(wall)+wall;

        /*
          TOP
          ------
         */

        if(walls[0]) {
            gc.strokeLine(xMulWall(wall), yMulWall(wall),xWall, yMulWall(wall));

        }

        /*
               |
               |
           RIGHT
         */
        if(walls[1]){
            gc.strokeLine(xWall, yMulWall(wall),xWall,yWall);
        }


        /*
          ------
          BOTTOM
         */
        if(walls[2]) {
            gc.strokeLine(xWall,yWall,xMulWall(wall),yWall);
        }


        /*
          |
          |
          LEFT
         */
        if(walls[3]) {
            gc.strokeLine(xMulWall(wall), yWall, xMulWall(wall), yMulWall(wall));
        }

        if(this.isVisited) {
           gc.setFill(Color.DARKSEAGREEN);
           gc.fillRect(xMulWall(wall),yMulWall(wall), wall+2, wall+2);
        }

    }
    public void currentDot(GraphicsContext gc, int wall){
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(xMulWall(wall),yMulWall(wall), wall+2, wall+2);
    }

    /**
     * Check left of current cell and right of next cell
     * Check top of current cell and bottom of next cell
     * @param next is the chosen cell
     */
    public void noWall(Cell next){
         // 0 = TOP line, 1= RIGHT line, 2=BOTTOM line, 3= LEFT line
         int posX = this.dimensionX-next.dimensionX;
         if(posX ==1){
             walls[3] = false;
             next.walls[1] = false;
         }else if(posX==-1){
             walls[1] = false;
             next.walls[3] = false;
         }

        int posY = this.dimensionY-next.dimensionY;
         if(posY==1){
             walls[0] = false;
             next.walls[2] = false;
         }else if(posY ==-1){
             walls[2] = false;
             next.walls[0] = false;
         }

    }



    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    /**
     *
     * @return Int Position Cell ( DimensionX * Wall ,?)
     */
    public int xMulWall(int wall) {
        return dimensionX*wall;
    }
    /**
     *
     * @return Int Position Cell( ?, DimensionY * Wall)
     */
    public int yMulWall(int wall) {
        return dimensionY*wall;
    }

    public boolean isVisited() {
        return !isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "dimensionX=" + dimensionX +
                ", dimensionY=" + dimensionY +
                ", isVisited=" + isVisited +
                '}';
    }
}
    /*public void removeRec(GraphicsContext gc){
        int previousX = xMulWall();
        int previousY = yMulWall();
        gc.clearRect(previousX, previousY, wall - Maze.STROKEWIDTH, wall - Maze.STROKEWIDTH);
    }*/
