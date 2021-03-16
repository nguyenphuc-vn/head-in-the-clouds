package personal.model.maze;


import javafx.scene.canvas.GraphicsContext;

public class Cell {
    private final static int wall = 20;
    private final static int WIDTH =800;
    private final static int HEIGHT =600;
    private int dimensionX;
    private int dimensionY;
    private boolean isVisited;
    private boolean[] walls = {true,true,true,true};



    public Cell(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
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

    public int xMulWall() {
        return dimensionX*wall;
    }

    public int yMulWall() {
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

    public void drawLine(GraphicsContext gc){
        int xWall = xMulWall()+wall;
        int yWall = yMulWall()+wall;
        /*
          TOP
          ------
         */
        if(this.walls[0]) {
            gc.strokeLine(xMulWall(), yMulWall(),xWall, yMulWall());
        }
        /*
               |
               |
           RIGHT
         */
        if(this.walls[1]){
            gc.strokeLine(xWall, yMulWall(),xWall,yWall);
        }


        /*
          ------
          BOTTOM
         */
        if(this.walls[2]) {
            gc.strokeLine(xMulWall(),yWall,xWall,yWall);
        }
        /*
          |
          |
          LEFT
         */
        if(this.walls[3]) {
            gc.strokeLine(xMulWall(), yMulWall(), xMulWall(),yWall);
        }
        drawCell(gc);
    }
    public void drawCell(GraphicsContext gc){
        if(this.isVisited) {
            gc.fillRect(xMulWall(),yMulWall(), wall, wall);
        }
    }


}
