import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	static int[][] board = null;
	static int width = 0;
	static int height = 0;	
//	2.A State Representation	
//	function loadGameState --- extract game board attributes in text file and then parse it into a loaded game board matrix
	public static void loadGameState(String fileName) throws FileNotFoundException{
		ArrayList<Integer> input = new ArrayList<>();
		Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNext()) {
			Scanner scLine = new Scanner(sc.next());
			scLine.useDelimiter(","); 
			while (scLine.hasNext()){
				input.add(scLine.nextInt());
			}
			scLine.close();
		}
		sc.close();	
		parseInput(input);
	}
//  function parseInput --- parse numbers in arraylist into a int[][] game board matrix 
	public static void parseInput(ArrayList<Integer> input){
		width = input.get(0);
		height = input.get(1);
		board = new int[height][width];
		int column = 0;
		int line = 0;
		for (int i = 2; i < input.size(); i++) {
			if (column < width) {
				board [line][column] = input.get(i);
				column++;
			} else {
				column = 0;
				line++;
				board[line][column] = input.get(i);
				column++;
			}
		}
	}
// outputGameState --- print current game to the console
	public static void outputGameState() {
		System.out.println(width + "," + height + ",");
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				System.out.print(board[h][w] + ",");
			}
			System.out.print("\n");
		}
	}
//  cloneGameState --- clone the game state 
	public static int[][] cloneGameState() {
		return board.clone();
	}
}
