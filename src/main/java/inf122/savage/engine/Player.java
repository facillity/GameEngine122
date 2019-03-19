package inf122.savage.engine;

public class Player{
    private String name;
    private int winCount;
    
    public Player(String newName){
        this.name = newName;
        this.winCount = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getWinCount(){
        return this.winCount;
    }

    public void setWinCount(int amount){
        this.winCount = amount;
    }

    public void addWin(){
        this.winCount++;
    }

}