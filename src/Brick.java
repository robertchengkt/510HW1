import java.util.ArrayList;
import java.util.Set;
//class bricks abstracting all the bricks on the board.

public class Brick {
	public static int brickID;
	public static Set<String> moves; 
	public Brick(int givenID, Set<String> givenMove) {
		brickID = givenID;
		moves = givenMove;
	}
	public ArrayList<Move> getBrickMoves(){
		ArrayList<Move> brickMoves = new ArrayList<Move>();
		for (String move: moves){
			brickMoves.add(new Move(brickID, move));
		}
		return brickMoves;
	}
	public boolean judgeMoveable(String requiredMove) {
		if (moves.contains(requiredMove)){
			return true;
		}else{
		return false;
		}
	}
	public Set<String> getPossibleMoves(){
		return moves;
	}
	
	public int getBrickID(){
		return brickID;
	}
}
