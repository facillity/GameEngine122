package GameState;

public class Player {
	
	private String name;
	private double score = 0;
	private int winCount = 0;
	
	public void Player(String name) {this.name = name;}

	public String getName() {return name;}
	
	public void setScore(double score) {this.score = score;}

	public double getScore() {return score;}
	
	public void addWin() {++winCount;}
	
	public int getWin() {return winCount;}
}
