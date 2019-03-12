package inf122.savage.engine;

public class Player{
    private String name;
    private double score;
    private int winCount;
    
    public Player(String newName){
        this.name = newName;
        this.score = 0;
        this.winCount = 0;
    }

    public String getName(){
        return this.name;
    }

    public double getScore(){
        return this.score;
    }

    public int getWinCount(){
        return this.winCount;
    }

    public void setWinCount(int amount){
        this.winCount = amount;
    }

    public boolean addWin(){
        this.winCount++;
        return true;
    }

    public boolean addScore(double points){
        if (points < 0){
            return false;
        }
        this.score = this.score + points;
        return true;
    }

    public boolean subtractScore(double points){
        if (points < 0){
            return false;
        }
        this.score = this.score - points;
        return false;
    }

    public boolean setName(String newName){
        if (newName.length() == 0){
            return false;
        }
        this.name = newName;
        return true;
    }
}