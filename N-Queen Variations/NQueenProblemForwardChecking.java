package nQueens;

import java.util.Scanner;

public class NQueenProblemForwardChecking {

	private int N;
	private static int count = 0;

	public NQueenProblemForwardChecking(int N) {
		this.N = N;
	}

	void printBoard(Square[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + board[i][j] + " ");
			System.out.println();
		}
		System.out.println("");
		System.out.println("---------------------------------------");
		System.out.println("");
	}

	void createThreats(Square[][] board, int row, int col) {

		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			board[i][j].setThreats(board[i][j].getThreats() + 1);
		}

		for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
			board[i][j].setThreats(board[i][j].getThreats() + 1);
		}

		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			board[i][j].setThreats(board[i][j].getThreats() + 1);
		}

		for (int i = row, j = col; i < N && j < N; i++, j++) {
			board[i][j].setThreats(board[i][j].getThreats() + 1);
		}

		for (int i = 0; i < N; i++) {
			board[row][i].setThreats(board[row][i].getThreats() + 1);
		}

		for (int i = 0; i < N; i++) {
			board[i][col].setThreats(board[i][col].getThreats() + 1);
		}
	}

	void removeThreats(Square[][] board, int row, int col) {

		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			board[i][j].setThreats(board[i][j].getThreats()-1);
		}

		for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
			board[i][j].setThreats(board[i][j].getThreats()-1);
		}

		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			board[i][j].setThreats(board[i][j].getThreats()-1);
		}

		for (int i = row, j = col; i < N  && j < N; i++, j++) {
			board[i][j].setThreats(board[i][j].getThreats()-1);
		}

		for (int i = 0; i < N; i++) {
			board[row][i].setThreats(board[row][i].getThreats()-1);
		}

		for (int i = 0; i < N; i++) {
			board[i][col].setThreats(board[i][col].getThreats()-1);
		}

	}

	boolean solveNQ(Square board[][], int row, int startPos) {

		if (row == N) {
			return true;
		}
		if (row == 0) {
			row++;
		}

		for (int i = 0; i < N; i++) {

			if (board[row][i].getPlacedQueen() == 0 && board[row][i].getThreats() == 0)  {
				count++;

				board[row][i].setPlacedQueen(1);
				createThreats(board,row,i);

				if (solveNQ(board, row + 1, startPos) == true)
					return true;

				// backtrack
				board[row][i].setPlacedQueen(0);
				removeThreats(board,row,i);
			}
		}
		return false;
	}

	boolean setupNQ(int startPos) {
		Square[][] board = new Square[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Square s1 = new Square(i,j,0, 0);
				board[i][j] = s1;
			}
		}
		board[0][startPos].setPlacedQueen(1);
		createThreats(board, 0, startPos);

		if (solveNQ(board, 0, startPos) == false) {
			System.out.print("Error: Can't find solution.");
			System.out.println("");
			return false;
		}

		System.out.println("Solution:");
		printBoard(board);

		return true;
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the board size N: ");
		int N = scan.nextInt();
		System.out.println("Please enter starting column for Q1 (0-" + (N-1) + "): ");
		int startingColumn = scan.nextInt();

		NQueenProblemForwardChecking Queen = new NQueenProblemForwardChecking(N);

		long startTime = System.nanoTime();

		System.out.println("\nFinding a Solution . . . \n");
		Queen.setupNQ(startingColumn);

		long endTime = System.nanoTime();

		double duration = (double)(endTime - startTime) / 1_000_000_000.0;

		System.out.println("Iterations: " + count);
		System.out.println("Time taken: " + duration + " seconds");

//		// Array of N values and starting columns as provided in the image
//		int[] nValues = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//		int[] startingColumns = {1, 4, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
//
//		double totalTime = 0; // To sum up the total time for calculating the average
//		System.out.printf("%-10s %-15s %-15s\n", "N Value", "Starting Column", "Runtime in Seconds");
//
//		for (int i = 0; i < nValues.length; i++) {
//			int N = nValues[i];
//			int startingColumn = startingColumns[i];
//			NQueenProblemForwardChecking Queen = new NQueenProblemForwardChecking(N);
//
//			long startTime = System.nanoTime();
//			Queen.setupNQ(startingColumn);
//			long endTime = System.nanoTime();
//
//			double duration = (double)(endTime - startTime) / 1_000_000_000.0;
//			totalTime += duration;
//
//			System.out.printf("%-10d %-15d %-15f\n", N, startingColumn, duration);
//		}
//
//		double averageTime = totalTime / nValues.length;
//		System.out.println("\nAverage Runtime: " + averageTime + " seconds");
	}
	}
