/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfour;

import com.progrema.algo.UnitTest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SolverTest extends UnitTest {

    public static void main(String[] args) {
        test_main(args);
    }

    private static void test_main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        
        // solve the puzzle
        Board initial = new Board(blocks);        
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            int move = 0;
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
                StdOut.println("move = " + (move++));
            }                
        } 
    }
}