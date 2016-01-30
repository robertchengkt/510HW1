//all the status checking, state comparison and normalization functions can be found in this class
public class GameHelper {
//	2.B Puzzle Complete Check
//	function gameStateSolved --- find if there is -1 in the matrix to decide whether the game has been solved;
	public static boolean gameStateSolved(int[][] board) {
		for (int[] row : board) {
			for (int value : row) {
				if (value == -1) {
					return false;
				}
			}
		}
		return true;
	}
//	2.D State Comparison
//	function compareStates --- compare two states and see if they are identical, return true if they are.
	public static boolean stateEqual(int[][] board1, int[][] board2) {
		if (board1.equals(board2)) {
			return true;
		} else {
			return false;
		}
	}

//	2.E: Normalization
	public static int[][] normalization(int[][] board){
		int nextIdx = 3; 
		for(int i = 0;i < board.length; i++) { 
			for(int j = 0;j < board[i].length ;j++) { 
				if (board[i][j] == nextIdx) {
					nextIdx++; 
					} else if (board[i][j] > nextIdx) { 
						swapIdx(board, nextIdx, board[i][j]); 
						nextIdx++; 
						} 
				}
			}
		return board;
	}
	private static void swapIdx(int[][] board, int idx1, int idx2) {
		for (int i = 0; i< board.length ;i++){
			for (int j = 0; j< board[i].length; j ++){
				if (board[j][i] == idx1){
					board[j][i]  = idx2;
				} else if (board[j][i] == idx2){
					board[j][i] = idx1;
				}
			}
		}
	}
}
