
public class Move {
	public static int brickNum;
	public static String move;
	public Move(int givenNum, String givenMove){
		brickNum = givenNum;
		move = givenMove;
	}
	public int getBrickNum(){
		return brickNum;
	}
	public String getMove(){
		return move;
	}
}
