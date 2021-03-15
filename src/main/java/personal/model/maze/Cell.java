package personal.model.maze;


public class Cell {
    private int dimensionX;
    private int dimensionY;
    private boolean isVisited;

    public Cell() {
    }

    public Cell(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
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

}
