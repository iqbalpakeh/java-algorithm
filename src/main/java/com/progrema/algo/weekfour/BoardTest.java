/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfour;

import com.progrema.algo.UnitTest;

public class BoardTest extends UnitTest {

    public static void main(String[] args) {
        // test_hamming();
        // test_manhattan();
        // test_twin();
        // test_equal();
        // test_neighbours();
        test_debug_1();
    }

    private static void test_debug_1() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        UnitTest.print("Create board {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}}");
        int[][] blocks = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        UnitTest.print(board.toString());

        for (Board neighbor : board.neighbors()) {
            UnitTest.print("Neighbour" + neighbor.toString());
        }

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static void test_neighbours() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}}");
            int[][] blocks = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
            Board board = new Board(blocks);
            UnitTest.print(board.toString());
    
            int counter = 0;
            UnitTest.print("All neighbor:");        
            for (Board neighbor : board.neighbors()) {
                if (counter == 3) {
                    UnitTest.print("North:");
                    UnitTest.print(neighbor.toString());
                    int[][] checkBlocks = {{1, 0, 3}, {4, 2, 6}, {7, 8, 5}};
                    Board check = new Board(checkBlocks);
                    compare("" + check.equals(neighbor), "true");
                    counter++;
                    continue;
                } 
                if (counter == 2) {
                    UnitTest.print("West:");
                    UnitTest.print(neighbor.toString());
                    int[][] checkBlocks = {{1, 2, 3}, {4, 6, 0}, {7, 8, 5}};
                    Board check = new Board(checkBlocks);
                    compare("" + check.equals(neighbor), "true");                    
                    counter++;
                    continue;
                } 
                if (counter == 1) {
                    UnitTest.print("South:");
                    UnitTest.print(neighbor.toString());
                    int[][] checkBlocks = {{1, 2, 3}, {4, 8, 6}, {7, 0, 5}};
                    Board check = new Board(checkBlocks);
                    compare("" + check.equals(neighbor), "true");                      
                    counter++;
                    continue;
                } 
                if (counter == 0) {
                    UnitTest.print("East:");
                    UnitTest.print(neighbor.toString());
                    int[][] checkBlocks = {{1, 2, 3}, {0, 4, 6}, {7, 8, 5}};
                    Board check = new Board(checkBlocks);
                    compare("" + check.equals(neighbor), "true");                     
                    counter++;
                    continue;
                } 
            }
        }
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static void test_equal() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}");
            int[][] blocks1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Board board1 = new Board(blocks1);

            UnitTest.print("Create board {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}");
            int[][] blocks2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Board board2 = new Board(blocks2);

            UnitTest.print("board1 = " + board1.toString());
            UnitTest.print("board2 = " + board2.toString());
            UnitTest.print("Are these block equal?");
            compare("" + board1.equals(board2), "true");
        }

        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}");
            int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Board board = new Board(blocks);
            
            UnitTest.print("Create twin board");
            Board twin = board.twin();

            UnitTest.print("board = " + board.toString());
            UnitTest.print("twin = " + twin.toString());
            UnitTest.print("Are these block equal?");
            compare("" + board.equals(twin), "false");
        }
        
        endTest(new Object(){}.getClass().getEnclosingMethod().getName());         
    }

    private static void test_twin() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());
        
        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}");
            int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Board board = new Board(blocks);
            
            UnitTest.print("Create twin board");
            Board twin1 = board.twin();

            UnitTest.print("Is this goal board?");
            compare("" + twin1.isGoal(), "false");

            UnitTest.print("Create twin board");
            Board twin2 = board.twin();

            UnitTest.print("Is this goal board?");
            compare("" + twin2.isGoal(), "false");
        }

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static void test_hamming() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}");
            int[][] blocks = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check hamming value must be 0");
            compare(board.hamming(), 0);

            UnitTest.print("Is this goal board?");
            compare("" + board.isGoal(), "true");
        }

        {
            UnitTest.print("Create board {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}}");
            int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check hamming value must be 0");
            compare(board.hamming(), 5); 

            UnitTest.print("Is this goal board?");
            compare("" + board.isGoal(), "false");
        }
       
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static void test_manhattan() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        {
            UnitTest.print("Create board {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}");
            int[][] blocks = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check manhattan value must be 4");
            compare(board.manhattan(), 4);
        }

        {
            UnitTest.print("Create board {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}");
            int[][] blocks = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check manhattan value must be 2");
            compare(board.manhattan(), 2);
        }

        {
            UnitTest.print("Create board {{1, 3, 0}, {4, 2, 5}, {7, 8, 6}}");
            int[][] blocks = {{1, 3, 0}, {4, 2, 5}, {7, 8, 6}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check manhattan value must be 4");
            compare(board.manhattan(), 4);
        }  
        
        {
            UnitTest.print("Create board {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}");
            int[][] blocks = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check manhattan value must be 4");
            compare(board.manhattan(), 4);
        }   
        
        {
            UnitTest.print("Create board {{4, 1, 3}, {0, 2, 5}, {7, 8, 6}}");
            int[][] blocks = {{4, 1, 3}, {0, 2, 5}, {7, 8, 6}};
            Board board = new Board(blocks);
            
            UnitTest.print("Check manhattan value must be 4");
            compare(board.manhattan(), 5);
        }           

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }

}