package personal.model.maze;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import personal.helper.maze.Maze;

public class Cell {
    private final static int wall = 20;
    private final static int WIDTH =600;
    private final static int HEIGHT =600;
    private int dimensionX;
    private int dimensionY;
    private boolean isVisited;

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
        if(Wall.top) {
            //gc.setStroke(Color.BLUE);
            gc.strokeLine(xMulWall(), yMulWall(),xWall, yMulWall());

        }
        /*
               |
               |
           RIGHT
         */
        if(Wall.right){
            //gc.setStroke(Color.GREEN);
            gc.strokeLine(xWall, yMulWall(),xWall,yWall);

        }

        /*
          ------
          BOTTOM
         */
        if(Wall.bottom) {
            //gc.setStroke(Color.WHITE);
            //gc.strokeLine(xMulWall(),yWall,xWall,yWall);
            gc.strokeLine(xWall,yWall,xMulWall(),yWall);

        }
        /*
          |
          |
          LEFT
         */
        if(Wall.left) {
            //gc.setStroke(Color.BROWN);
            //gc.strokeLine(xMulWall(), yMulWall(), xMulWall(),yWall);
            gc.strokeLine(xMulWall(),yWall,xMulWall(),yMulWall());

        }
        if(this.isVisited) {
            gc.fillRect(xMulWall(),yMulWall(), wall- Maze.STROKEWIDTH, wall-Maze.STROKEWIDTH);

        }
    }
    public void removeRec(GraphicsContext gc){
        int previousX = xMulWall();
        int previousY = yMulWall();
        gc.clearRect(previousX, previousY, wall - Maze.STROKEWIDTH, wall - Maze.STROKEWIDTH);
    }
    public void noWall(Cell next){
         Wall.top =true;
         Wall.bottom= true;
         Wall.left =true;
         Wall.right =true;
         int posX = this.dimensionX-next.dimensionX;
         int posY = this.dimensionY-next.dimensionY;
         if(posX ==1){
             Wall.left =false;


         }else if(posX==-1){
             Wall.right = false;
         }
         if(posY==1){
             Wall.top = false;
         }else if(posY ==-1){
             Wall.bottom =false;
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
    private int xMulWall() {
        return dimensionX*wall;
    }
    /**
     *
     * @return Int Position Cell( ?, DimensionY * Wall)
     */
    private int yMulWall() {
        return dimensionY*wall;
    }

    public boolean isVisited() {
        return isVisited;
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
