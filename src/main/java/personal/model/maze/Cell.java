package personal.model.maze;


import javafx.scene.canvas.GraphicsContext;

import java.util.Stack;

public class Cell {
    private final static int wall = 100;
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

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }

    public void drawLine(GraphicsContext gc){
        int xWall = dimensionX+wall;
        int yWall = dimensionY+wall;
        /*
          TOP
          ------
         */
        if(this.walls[0]) {
            gc.strokeLine(dimensionX,dimensionY,xWall,dimensionY);
        }
        /*
               |
               |
           RIGHT
         */
        if(this.walls[1]){
            gc.strokeLine(xWall,dimensionY,xWall,yWall);
        }


        /*
          ------
          BOTTOM
         */
        if(this.walls[2]) {
            gc.strokeLine(dimensionX,yWall,xWall,yWall);
        }
        /*
          |
          |
          LEFT
         */
        if(this.walls[3]) {
            gc.strokeLine(dimensionX,dimensionY,dimensionX,yWall);
        }
        if(this.isVisited) {
            gc.fillRect(xWall,yWall, wall, wall);

        }

    }

    /*
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



}
