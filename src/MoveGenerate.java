import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//2.C Move Generation
public class MoveGenerate {
//	function getBrickLocation --- iterate through all the spots on board and find all the spots of a given brick
	public static ArrayList<int[]> getBrickLocation(int[][] board, int brickID){
		ArrayList<int[]> spots = new ArrayList<int[]>();
		int w = 0;
		int h = 0;
		for (int[] row : board) {
			for (int num : row) {
				if (num == brickID) {
					spots.add(new int[] {h, w});
				}
				w++;
			}
			h++;
			w = 0; 
		}
		return spots;
	}
	
	public static ArrayList<String> compareMoves(ArrayList<int[]> brickLocation, ArrayList<int[]> targetLocation){
		ArrayList<String> possibleMoves = new ArrayList<String>();
		for (int[] loc: brickLocation){
			int hLoc = loc[0];
			int wLoc = loc[1];
			for (int[] tar: targetLocation){
				int hTar = tar[0];
				int wTar = tar[1]; 
				if (wTar == (wLoc + 1) && (hTar == hLoc)){
					possibleMoves.add("Right");
				} else if ((wTar == (wLoc - 1)) && (hTar == hLoc)) {
					possibleMoves.add("Left");
				} else if ((hTar == (hLoc - 1)) && (wTar == wLoc)) {
					possibleMoves.add("Up");
				} else if ((hTar == (hLoc + 1)) && (wTar == wLoc)) {
					possibleMoves.add("Down");
				}	
			}
		}
		return possibleMoves;
	}
//	function allMovesHelp --- find out all the available for each bricks	
	public static Brick allMovesHelp(int[][] board, int brickID){
		ArrayList<String> avaliableMoves = new ArrayList<String>();
		ArrayList<int[]> brickLocations = getBrickLocation(board, brickID);
		ArrayList<int[]> zeroLocations = getBrickLocation(board, 0);
		avaliableMoves.addAll(compareMoves(brickLocations, zeroLocations));
		if (brickID == 2){
			ArrayList<int[]> negOneLcations = getBrickLocation(board, -1);
			avaliableMoves.addAll(compareMoves(brickLocations, negOneLcations));
		}
		Set<String> avaliableMovesSet = new HashSet<String>();
		for (String move: avaliableMoves){
			if (brickLocations.size() > 1 && (move.equals("Up")|| move.equals("Down"))){
				int num = 0;
				for (int i = 0; i<avaliableMoves.size(); i++){
					if (avaliableMoves.get(i) == move){
						num++;
					}
					if (num == brickLocations.size()){
						avaliableMovesSet.add(move);
					}
				}
			} else {
				avaliableMovesSet.add(move);
			}
		}
		System.out.println("Avaliable: " + brickID + "," + avaliableMovesSet);
		return new Brick(brickID, avaliableMovesSet);
	}
//	function allMoves --- returns all the possible for every bricks based on the board state
	public static Brick[] allMoves(int[][] board) {
		ArrayList<Integer> currentBricks = new ArrayList<Integer>();
		for (int[] row : board) {
			for (int value : row) {
				if (!currentBricks.contains(value) && (value >= 2)) {
					currentBricks.add(value);
				}
			}
		}
		Brick[] allBricks = new Brick[currentBricks.size()];
		for (int i = 0; i < currentBricks.size(); i++) {
			int brickNum = currentBricks.get(i);
			allBricks[i] = allMovesHelp(board, brickNum);
		}
		
		for (int i = 0; i < allBricks.length; i++) {
			Brick thisPiece = allBricks[i];
			System.out.println(thisPiece.getBrickID());
			System.out.println(thisPiece.getPossibleMoves());
		}
		
		return allBricks;
	}
//	function applyMove --- take the selected move into action and change the state of board
	public static void applyMove (int[][] board, Move requestedMove){
		ArrayList<int[]> originalLocations = getBrickLocation(board, requestedMove.getBrickNum());
		Brick  possibleMoves = allMovesHelp(board, requestedMove.getBrickNum());
		int numRight = 0;
		for (int[] location : originalLocations) {
			if (possibleMoves.judgeMoveable(requestedMove.getMove())) {
				int w = location[1];
				int h = location[0];
				if (requestedMove.getMove() == "Up") {
					board[h-1][w] = requestedMove.getBrickNum();					
				} else if (requestedMove.getMove() == "Down") {
					board[h+1][w] = requestedMove.getBrickNum();
				} else if (requestedMove.getMove() == "Left") {
					board[h][w-1] = requestedMove.getBrickNum();
				} else if (requestedMove.getMove() == "Right") {
					board[h][w+1] = requestedMove.getBrickNum();
					numRight++;
				}
				if (requestedMove.getMove() != "Right" || numRight <= 1) {
					board[h][w] = 0; 
				}
			}
		}
		Board.board = board;
	}
//	function applyMoveCloning --- clone the state, apply the move
	public static int[][] applyMoveCloning(int[][] board, Move requestedMove) {
		ArrayList<int[]> originalLocations = getBrickLocation(board, requestedMove.getBrickNum());
		Brick possibleMoves = allMovesHelp(board, requestedMove.getBrickNum());
		int numRight = 0; 
		for (int[] location : originalLocations) {
			if (possibleMoves.judgeMoveable(requestedMove.getMove())) {
				int w = location[1];
				int h = location[0];
				if (requestedMove.getMove() == "Up") {
					board[h-1][w] = requestedMove.getBrickNum();					
				} else if (requestedMove.getMove() == "Down") {
					board[h+1][w] = requestedMove.getBrickNum();
				} else if (requestedMove.getMove() == "Left") {
					board[h][w-1] = requestedMove.getBrickNum();
				} else if (requestedMove.getMove() == "Right") {
					board[h][w+1] = requestedMove.getBrickNum();
					numRight++;
				}
				if (requestedMove.getMove() != "Right" || numRight <= 1) {
					board[h][w] = 0; 
				}
			}
		}
		return board;
	}
}
