package personal.model.maze;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Cell {
    public final static int wall = 20;
    private final static int WIDTH =800;
    private final static int HEIGHT =600;
    private int dimensionX;
    private int dimensionY;
    private boolean isVisited;
    //private boolean top,right,bottom,left;
    private final boolean[] walls = {true,true,true,true};

    public Cell(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public void drawLine(GraphicsContext gc){
        int xWall = xMulWall()+wall;
        int yWall = yMulWall()+wall;

        /*
          TOP
          ------
         */

        if(walls[0]) {
            gc.strokeLine(xMulWall(), yMulWall(),xWall, yMulWall());

        }

        /*
               |
               |
           RIGHT
         */
        if(walls[1]){
            gc.strokeLine(xWall, yMulWall(),xWall,yWall);
        }


        /*
          ------
          BOTTOM
         */
        if(walls[2]) {
            gc.strokeLine(xWall,yWall,xMulWall(),yWall);
        }


        /*
          |
          |
          LEFT
         */
        if(walls[3]) {
            gc.strokeLine(xMulWall(), yWall, xMulWall(), yMulWall());
        }

        if(this.isVisited) {
           gc.setFill(Color.DARKSEAGREEN);
           gc.fillRect(xMulWall(),yMulWall(), wall+2, wall+2);
        }

    }
    public void currentDot(GraphicsContext gc){
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(xMulWall(),yMulWall(), wall+2, wall+2);
    }

    public void noWall(Cell next){

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

    public static int getCol() {
        return Math.abs(HEIGHT/wall);
    }
    public static int getRow() {
        return Math.abs(WIDTH/wall);
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
    public int xMulWall() {
        return dimensionX*wall;
    }
    /**
     *
     * @return Int Position Cell( ?, DimensionY * Wall)
     */
    public int yMulWall() {
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
