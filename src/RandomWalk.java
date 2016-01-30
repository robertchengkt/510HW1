import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

//Part 2.F Random Walks
public class RandomWalk {
	public static void main(String[] fileName) throws FileNotFoundException{
		Board.loadGameState("/Users/Robert/Documents/workspace/ChangChengHW1/SBP-level0.txt");
		randomWalks(Board.board, 40);
	}
//	function randomWalk --- perform random walk by listing all the possible moves on board, select one at random, execute
//	normalize the board, if goal reached or N moves executed, stop, otherwise, start over
	public static void randomWalks(int[][] board, int N) {
		Board.outputGameState();
		for (int i = 0; i < N; i++) {
			Move selectedMove = generateRandomMove(board);
			System.out.println("\n(" + selectedMove.getBrickNum() + "," + selectedMove.getMove() + ")\n");
			MoveGenerate.applyMove(board, selectedMove);
			Board.board = GameHelper.normalization(Board.board);
			Board.outputGameState();
			if (GameHelper.gameStateSolved(board)) {
				System.out.println("\nGoal reached.");
				break;
			}
			i++;
		}
		System.out.println("\nGame terminated.");
	}
	// function generateRandomMove --- find all possible moves in board, and pick one at random
	public static Move generateRandomMove(int[][] board) {

		Brick[] allPossibleMoves = MoveGenerate.allMoves(board);
		HashMap<Integer, Move> allMovesTable = new HashMap<Integer, Move>();
		int i = 0;
		for (Brick brick : allPossibleMoves) {
			for (String move : brick.getPossibleMoves()) {
				allMovesTable.put(i, new Move(brick.getBrickID(), move));
				i++;
			}
		}
		int randomVal = new Random().nextInt(allMovesTable.size());
		return allMovesTable.get(randomVal);		
	}
}
