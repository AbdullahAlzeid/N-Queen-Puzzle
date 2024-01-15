package nQueens;
import java.util.*;
import java.util.stream.*;

public class NQueenProblemMRV_LCV {

    private int N;
    private static int count = 0;

    public NQueenProblemMRV_LCV(int N) {
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

    int selectNextRowMRV(Square[][] board) {
        int minThreats = Integer.MAX_VALUE;
        int rowIndex = -1;
        for (int i = 0; i < N; i++) {
            final int currentRow = i;
            int availableCells = (int) IntStream.range(0, N).filter(j -> board[currentRow][j].getThreats() == 0).count();
            if (availableCells < minThreats && availableCells > 0) {
                minThreats = availableCells;
                rowIndex = i;
            }
        }
        return rowIndex;
    }


    List<Integer> getColumnsSortedByLCV(Square[][] board, int row) {
        return IntStream.range(0, N)
                .boxed()
                .filter(col -> board[row][col].getThreats() == 0)
                .sorted(Comparator.comparingInt(col -> {
                    board[row][col].setPlacedQueen(1);
                    int threatsCreated = (int) IntStream.range(0, N).filter(j -> board[row][j].getThreats() > 0).count();
                    board[row][col].setPlacedQueen(0);
                    return threatsCreated;
                }))
                .collect(Collectors.toList());
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

    boolean solveNQ(Square[][] board, int col) {
        if (col >= N) {
//            System.out.println("Solution:");
//            printBoard(board);
            return true;
        }


        int row = selectNextRowMRV(board);
        if (row == -1) {
            return false;
        }

        List<Integer> lcvColumns = getColumnsSortedByLCV(board, row);
        for (int nextCol : lcvColumns) {
            if (board[row][nextCol].getPlacedQueen() == 0 && board[row][nextCol].getThreats() == 0) {
                count++;
                board[row][nextCol].setPlacedQueen(1);
                createThreats(board, row, nextCol);
//                printBoard(board);
                if (solveNQ(board, col + 1)) {
                    return true;
                }
                board[row][nextCol].setPlacedQueen(0);
                removeThreats(board, row, nextCol);
            }
        }
        return false;
    }


    boolean setupNQ(int startPos) {
        Square[][] board = new Square[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Square s1 = new Square(i, j, 0, 0);
                board[i][j] = s1;
            }
        }
        board[0][startPos].setPlacedQueen(1);
        createThreats(board, 0, startPos);

        if (solveNQ(board, 1) == false) {
            System.out.print("Error: Can't find solution.");
            System.out.println("");
            return false;
        }

        return true;
    }

    public static void main(String args[]) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the board size N: ");
//        int N = scan.nextInt();
//        System.out.println("Please enter starting column for Q1 (0-" + (N-1) + "): ");
//        int startingColumn = scan.nextInt();
//
//        NQueenProblemMRV_LCV Queen = new NQueenProblemMRV_LCV(N);
//
//        long startTime = System.nanoTime();
//
//        System.out.println("\nFinding a Solution . . . \n");
//        Queen.setupNQ(startingColumn);
//
//        long endTime = System.nanoTime();
//
//        double duration = (double)(endTime - startTime) / 1_000_000_000.0;
//
//        System.out.println("Iterations: " + count);
//        System.out.println("Time taken: " + duration + " seconds");

        int[] nValues = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] startingColumns = {1, 4, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};

        double totalTime = 0; // To sum up the total time for calculating the average
        System.out.printf("%-10s %-15s %-15s\n", "N Value", "Starting Column", "Runtime in Seconds");

        for (int i = 0; i < nValues.length; i++) {
            int N = nValues[i];
            int startingColumn = startingColumns[i];
            NQueenProblemMRV_LCV Queen = new NQueenProblemMRV_LCV(N);

            long startTime = System.nanoTime();
            Queen.setupNQ(startingColumn);
            long endTime = System.nanoTime();

            double duration = (double)(endTime - startTime) / 1_000_000_000.0;
            totalTime += duration;

            System.out.printf("%-10d %-15d %-15f\n", N, startingColumn, duration);
        }

        double averageTime = totalTime / nValues.length;
        System.out.println("\nAverage Runtime: " + averageTime + " seconds");
    }
}
