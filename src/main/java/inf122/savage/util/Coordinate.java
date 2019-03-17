package inf122.savage.util;

public class Coordinate {
    private int row, col;
    public Coordinate(){
        this.row = 0;
        this.col = 0;
    }

    public Coordinate(int r, int c){
        this.row = r;
        this.col = c;
    }

    public boolean isValid(){
        return ((this.row > 0) && (this.col > 0));
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
