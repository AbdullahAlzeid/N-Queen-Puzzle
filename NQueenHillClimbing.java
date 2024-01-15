package nQueens;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class NQueenHillClimbing {

    private int[] board;
    private int N;
    private static final int MAX_RETRIES = 100;

    public NQueenHillClimbing(int N) {
        this.N = N;
        board = new int[N];
    }

    private void initializeBoard() {
        List<Integer> rows = IntStream.range(0, N).boxed().collect(Collectors.toList());
        Collections.shuffle(rows);
        for (int i = 0; i < N; i++) {
            board[i] = rows.get(i);
        }
    }

    private int getConflicts(int[] state) {
        int conflicts = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = i + 1; j < state.length; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == j - i) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    public void solve() {
        System.out.println("Initial state:");
        initializeBoard();
        printBoard();
        System.out.println("\nFinding a Solution . . . \n");

        int currentConflicts = getConflicts(board);
        int retries = 0;

        while (currentConflicts != 0) {
            if (retries > MAX_RETRIES) {
                System.out.println("No Solution is Found");
                return;
            }

            int[] nextState = Arrays.copyOf(board, board.length);
            int minConflicts = Integer.MAX_VALUE;

            for (int col = 0; col < N; col++) {
                int originalRow = nextState[col];
                for (int row = 0; row < N; row++) {
                    if (row == originalRow) continue;

                    nextState[col] = row;
                    int newConflicts = getConflicts(nextState);

                    if (newConflicts < minConflicts) {
                        minConflicts = newConflicts;
                        board[col] = row;
                    }
                }
                nextState[col] = originalRow;
            }

            if (minConflicts >= currentConflicts) {
                retries++;
                initializeBoard();
            } else {
                retries = 0;
            }

            currentConflicts = getConflicts(board);
        }
        System.out.println("Solution:");
        printBoard();
    }

    public void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j] == i) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the board size N: ");
        int N = scanner.nextInt();
        if (N < 4) {
            System.out.println("There are no solutions for N less than 4.");
            return;
        }

        NQueenHillClimbing solver = new NQueenHillClimbing(N);

        long startTime = System.nanoTime();
        solver.solve();
        long endTime = System.nanoTime();

        double duration = (double) (endTime - startTime) / 1_000_000_000.0;

        System.out.println("Runtime:" + duration + " seconds");

//        int[] nValues = IntStream.rangeClosed(4, 30).toArray();
//
//        double totalTime = 0;
//        int totalSolutionsFound = 0;
//
//        System.out.printf("%-10s %-20s\n", "N Value", "Runtime in Seconds");
//
//        for (int N : nValues) {
//            NQueenHillClimbing solver = new NQueenHillClimbing(N);
//
//            long startTime = System.nanoTime();
//            solver.solve();
//            long endTime = System.nanoTime();
//
//            double duration = (double) (endTime - startTime) / 1_000_000_000.0;
//            totalTime += duration;
//
//            System.out.printf("%-10d %-20f\n", N, duration);
//
//
//
//        }
//
//        double averageTime = totalTime / nValues.length;
//        System.out.println("\nAverage Runtime: " + averageTime + " seconds");

    }
}
