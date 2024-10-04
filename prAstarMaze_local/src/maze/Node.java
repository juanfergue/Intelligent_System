package maze;

public class Node {

    private int row;
    private int col;
    private int g;
    private int h;
    private int f;
    
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.g = Integer.MAX_VALUE;
        h = 0;
        f = g + h;
    }

    public Node(int row, int col, int g) {
        this.row = row;
        this.col = col;
        this.g = g;
        h = 0;
        f = g + h;
    }
    
    public int getROW() {
        return row;
    }

    public int getCOL() {
        return col;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
        f = g + h;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
        f = g + h;
    }

    public int getF() {
        return f;
    }

}
