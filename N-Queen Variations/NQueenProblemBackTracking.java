package nQueens;

import java.util.Scanner;

public class NQueenProblemBackTracking {
    private int N;
    private int count = 0;
    private boolean[] column, diagonal, antiDiagonal;

    public NQueenProblemBackTracking(int N) {
        this.N = N;
        column = new boolean[N];
        diagonal = new boolean[2 * N];
        antiDiagonal = new boolean[2 * N];
    }

    void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.println("");
    }

    boolean isSafe(int row, int col) {
        return !column[col] && !diagonal[row + col] && !antiDiagonal[row - col + N - 1];
    }

    boolean solveNQ(int board[][], int row) {
        if (row == N) {
            System.out.println("Solution:");
            printBoard(board);
            return true;
        }
        for (int i = 0; i < N; i++) {
            count++;
            if (isSafe(row, i)) {
                board[row][i] = 1;
                column[i] = diagonal[row + i] = antiDiagonal[row - i + N - 1] = true;

//                printBoard(board);
                if (solveNQ(board, row + 1)) {
                    return true;
                }

                board[row][i] = 0;
                column[i] = diagonal[row + i] = antiDiagonal[row - i + N - 1] = false;
                count--;
//                printBoard(board);
            }
        }
        return false;
    }

    boolean setupNQ(int startPos) {
        int[][] board = new int[N][N];
        board[0][startPos] = 1;
        column[startPos] = diagonal[startPos] = antiDiagonal[-startPos + N - 1] = true;
        if (solveNQ(board, 1) == false) {
            System.out.print("Error: Can't find solution.");
            System.out.println("");
            return false;
        }
        return true;
    }

    public int getCount() {
        return count;
    }

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the board size N: ");
        int N = scan.nextInt();

        System.out.println("Please enter starting column for Q1 (0-" + (N - 1) + "): ");
        int startingColumn = scan.nextInt();

        NQueenProblemBackTracking Queen = new NQueenProblemBackTracking(N);

        long startTime = System.nanoTime();

        System.out.println("\nFinding a Solution . . . \n");
        boolean solved = Queen.setupNQ(startingColumn);

        long endTime = System.nanoTime();

        double duration = (double) (endTime - startTime) / 1_000_000_000.0;


        System.out.println("Iterations: " + Queen.getCount());
        System.out.println("Time taken: " + duration + " seconds");

//        // Array of N values and starting columns as provided in the image
//        int[] nValues = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//        int[] startingColumns = {1, 4, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
//
//        double totalTime = 0; // To sum up the total time for calculating the average
//        int totalIterations = 0; // To sum up the total iterations for all runs
//
//        System.out.printf("%-10s %-15s %-15s\n", "N Value", "Starting Column", "Runtime in Seconds");
//
//        for (int i = 0; i < nValues.length; i++) {
//            int N = nValues[i];
//            int startingColumn = startingColumns[i];
//            NQueenProblemBackTracking Queen = new NQueenProblemBackTracking(N);
//
//            long startTime = System.nanoTime();
//            Queen.setupNQ(startingColumn);
//            long endTime = System.nanoTime();
//
//            double duration = (double)(endTime - startTime) / 1_000_000_000.0;
//            totalTime += duration;
//            totalIterations += Queen.getCount();
//
//            System.out.printf("%-10d %-15d %-15f\n", N, startingColumn, duration);
//        }
//
//        double averageTime = totalTime / nValues.length;
//        int averageIterations = totalIterations / nValues.length;
//        System.out.println("\nAverage Runtime: " + averageTime + " seconds");
//        System.out.println("Average Iterations: " + averageIterations);
    }
}
