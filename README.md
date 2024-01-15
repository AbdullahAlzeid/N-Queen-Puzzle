# N-Queen-Puzzle

![Alt text](/images/Nq.png)

## Overview
The N-Queen Puzzle is a classic problem in computer science and artificial intelligence, involving placing N queens on an N×N chessboard so that no two queens threaten each other. This repository explores various algorithms to solve the N-Queen Puzzle.

## Components
### Board
- [Square.java](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/Board/Square.java): Defines the `Square` class for the puzzle representation.

### N-Queen Variations
Different algorithmic approaches to solve the N-Queen Puzzle.
- [NQueenHillClimbing.java](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/N-Queen%20Variations/NQueenHillClimbing.java): Implements the Hill Climbing algorithm.
- [NQueenProblemBackTracking.java](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/N-Queen%20Variations/NQueenProblemBackTracking.java): Solves the puzzle using Backtracking.
- [NQueenProblemForwardChecking.java](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/N-Queen%20Variations/NQueenProblemForwardChecking.java): Applies Forward Checking technique.
- [NQueenProblemMRV_LCV.java](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/N-Queen%20Variations/NQueenProblemMRV_LCV.java): Uses MRV and LCV heuristics.

## Algorithms Overview
### Hill Climbing
A heuristic search for mathematical optimization problems, adjusting to reduce conflicts.

### Backtracking
A recursive strategy that builds candidates to solutions and abandons them if they can't be completed to a valid solution.

### Forward Checking
Keeps track of remaining legal values for unassigned variables and terminates early if any variable has no legal values.

### MRV and LCV
Selects the variable with the fewest legal moves (MRV) and chooses the least constraining value (LCV) for that variable.

## How to Run
To run any of the N-Queen Puzzle solutions:
1. Navigate to the `N-Queen Variations` directory.
2. Compile and run the desired Java file. For example:
   javac NQueenHillClimbing.java
   java NQueenHillClimbing

   
## License
This project is licensed under the [MIT License](https://github.com/AbdullahAlzeid/N-Queen-Puzzle/blob/main/LICENSE).

