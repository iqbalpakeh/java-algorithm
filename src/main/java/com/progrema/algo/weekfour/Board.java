/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfour;

import edu.princeton.cs.algs4.Stack;

public class Board {

    /**
     * Debugging flag
     */
    private static final boolean DEBUG = false;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@";

    /**
     * Immutable board object
     */
    private final int[][] mBlocks;

    /**
     * Immutable block dimension;
     */
    private final int N;

    /**
     * Stack of neighbours
     */
    private Stack<Board> mNeighbours;

    /**
     * Cache of hamming value
     */
    private int mHamming;

    /**
     * Cache of manhattan value
     */
    private int mManhattan;

    /**
     * construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks) {
        mNeighbours = new Stack<>();
        N = blocks[0].length;                
        mBlocks = new int[N][N];
        __debug__("@Board", "N = " + N);
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                mBlocks[row][col] = blocks[row][col];
                __debug__("@Board", "blocks[" + row + "][" + col + "] = " + mBlocks[row][col]);
            }
        }
        mManhattan = calcManhattan(mBlocks);
        mHamming = calcHamming(mBlocks);
    }      
    
    private Board(int[][] blocks, int hamming, int manhattan) {
        mNeighbours = new Stack<>();
        N = blocks[0].length;                
        mBlocks = new int[N][N];
        __debug__("@Board", "N = " + N);
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                mBlocks[row][col] = blocks[row][col];
                __debug__("@Board", "blocks[" + row + "][" + col + "] = " + mBlocks[row][col]);
            }
        }
        mHamming = hamming;
        mManhattan = manhattan;
    }

    /**
     * Calculate board manhattan value
     */
    private int calcManhattan(int[][] scratch) {
        int manhattan = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (scratch[row][col] != 0) {
                    int distanceRow = distance(row, expRow(scratch[row][col]));
                    int distanceCol = distance(col, expCol(scratch[row][col]));
                    manhattan += distanceRow + distanceCol;
                    __debug__("@Board", "blocks[" + row + "][" + col + "] = " + scratch[row][col]);
                    __debug__("@Board", "distance row = " + distanceRow + ", distance col = " + distanceCol);
                }                
            }
        }
        __debug__("@calcManhattan", this.toString());
        __debug__("@calcManhattan", "manhattan = " + mManhattan);
        return manhattan;
    }

    /**
     * Calculate board hamming value
     */
    private int calcHamming(int[][] scratch) {
        int hamming = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if ((scratch[row][col] != expBlock(row, col)) && (scratch[row][col] != 0)) {
                    hamming++;
                    __debug__("@Board", "blocks[" + row + "][" + col + "] = " + scratch[row][col]);                    
                }
            }
        }
        __debug__("@calcManhattan", this.toString());
        __debug__("@calcHamming", "hamming = " + hamming);        
        return hamming;
    }
                                           
    /**
     * board dimension n
     */
    public int dimension() {
        return N;
    }   
    
    /**
     * number of blocks out of place
     */
    public int hamming() {        
        return mHamming;
    }   

    /**
     * Calculate expected block number for position 
     * row and col
     */
    private int expBlock(int row, int col) {        
        int result = row * N + col + 1;
        __debug__("@expectedBlock", "result @[" + row + "][" + col + "] = " + result);
        return result;
    }

    /**
     * Calculate the expected row of this block
     */
    private int expRow(int block) {
        int result = (block - 1) / N;
        __debug__("@expectedRow", "row of block " + block + " = " + result);
        return result;
    }
    
    /**
     * Calculate the expected col of this block
     */
    private int expCol(int block) {
        int result = (block - 1) % N;
        __debug__("@expectedCol", "col of block " + block + " = " + result);
        return result;
    }

    /**
     * calculate distance between two blocks
     */
    private int distance(int src, int dst) {
        int distance = 0;
        if (src >= dst) {
            distance = src - dst;
        } else {
            distance = dst - src;
        }
        __debug__("@distance", "distance between " + src + " and " + dst + " = " + distance);
        return distance;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {        
        return mManhattan;
    }

    /**
     * is this board the goal board?
     */
    public boolean isGoal() {
        return mManhattan == 0;
    }   
    
    /**
     * a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        
        int[][] twin = new int[N][N];
        copyArr(mBlocks, twin);

        int randPosA = 0;
        int randPosB = 0;
        for (int row_a = 0; row_a < N; row_a++) {
            for (int col_a = 0; col_a < N; col_a++) {   
                if (twin[row_a][col_a] != 0) {             
                    // Get random position a   
                    randPosA = expBlock(row_a, col_a);
                    for (int row_b = 0; row_b < N; row_b++) {
                        for (int col_b = 0; col_b < N; col_b++) {   
                            if ((row_b != row_a) && (col_b != col_a) && (twin[row_b][col_b] != 0)) {
                                // Get random position b
                                randPosB = expBlock(row_b, col_b);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        __debug__("@twin", "ranPosA = " + randPosA + ", ranPosB = " + randPosB);
        exchange(twin, randPosA, randPosB);
        return new Board(twin);
    }

    /**
     * Swap the entry of the array
     */
    private void exchange(int[][] arr, int a, int b) {
        int tmp = valueAt(arr, a);
        setAt(arr, a, valueAt(arr, b));
        setAt(arr, b, tmp);
    }

    /**
     * Get the value of block at the position
     */
    private int valueAt(int[][] arr, int pos) {
        return arr[expRow(pos)][expCol(pos)];
    }

    /**
     * Set the value of block at the position
     */
    private void setAt(int[][] arr, int pos, int val) {
        arr[expRow(pos)][expCol(pos)] = val;
    }

    /**
     * Copy array value from src to dst
     */
    private void copyArr(int[][] src, int[][] dst) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                dst[row][col] = src[row][col];
            }
        }
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object other) {

        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        
        Board that = (Board) other;
        __debug__("@equals", "this = " + this.toString());
        __debug__("@equals", "that = " + that.toString());        

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (this.mBlocks[row][col] != that.mBlocks[row][col]) return false;
            }
        }
        return true;
    }   

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {

        int blankRow = 0;
        int blankCol = 0;
        int[][] scratch = new int[N][N]; 

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                scratch[row][col] = mBlocks[row][col];
                if (mBlocks[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                } 
            }
        }

        // North
        if (blankRow > 0) {            

            // calculate new manhattan
            int mannSrc = distance(blankRow-1, expRow(mBlocks[blankRow-1][blankCol])) + distance(blankCol, expCol(mBlocks[blankRow-1][blankCol]));
            int mannDst = distance(blankRow, expRow(mBlocks[blankRow-1][blankCol])) + distance(blankCol, expCol(mBlocks[blankRow-1][blankCol]));
            int manhattan = mManhattan - mannSrc + mannDst;

            // calculate new hamming
            int hammSrc = 1;
            int hammDst = 1;
            if ((expRow(mBlocks[blankRow-1][blankCol]) == blankRow-1) && (expCol(mBlocks[blankRow-1][blankCol]) == blankCol)) hammSrc--;
            if ((expRow(mBlocks[blankRow-1][blankCol]) == blankRow)   && (expCol(mBlocks[blankRow-1][blankCol]) == blankCol)) hammDst--;
            int hamming = mHamming - hammSrc + hammDst;

            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow-1, blankCol));
            mNeighbours.push(new Board(scratch, hamming, manhattan));
            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow-1, blankCol));
        }        

        // West
        if (blankCol < N-1) {

            // calculate new manhattan
            int distSrc = distance(blankRow, expRow(mBlocks[blankRow][blankCol+1])) + distance(blankCol+1, expCol(mBlocks[blankRow][blankCol+1]));
            int distDst = distance(blankRow, expRow(mBlocks[blankRow][blankCol+1])) + distance(blankCol, expCol(mBlocks[blankRow][blankCol+1]));
            int manhattan = mManhattan - distSrc + distDst;

            // calculate new hamming
            int hammSrc = 1;
            int hammDst = 1;
            if ((expRow(mBlocks[blankRow][blankCol+1]) == blankRow) && (expCol(mBlocks[blankRow][blankCol+1]) == blankCol+1)) hammSrc--;
            if ((expRow(mBlocks[blankRow][blankCol+1]) == blankRow) && (expCol(mBlocks[blankRow][blankCol+1]) == blankCol))   hammDst--;
            int hamming = mHamming - hammSrc + hammDst;

            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow, blankCol+1));
            mNeighbours.push(new Board(scratch, hamming, manhattan));
            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow, blankCol+1));
        }

        // South
        if (blankRow < N-1) {

            // calculate new manhattan
            int distSrc = distance(blankRow+1, expRow(mBlocks[blankRow+1][blankCol])) + distance(blankCol, expCol(mBlocks[blankRow+1][blankCol]));
            int distDst = distance(blankRow, expRow(mBlocks[blankRow+1][blankCol])) + distance(blankCol, expCol(mBlocks[blankRow+1][blankCol]));
            int manhattan = mManhattan - distSrc + distDst;

            // calculate new hamming
            int hammSrc = 1;
            int hammDst = 1;
            if ((expRow(mBlocks[blankRow+1][blankCol]) == blankRow+1) && (expCol(mBlocks[blankRow+1][blankCol]) == blankCol)) hammSrc--;
            if ((expRow(mBlocks[blankRow+1][blankCol]) == blankRow)   && (expCol(mBlocks[blankRow+1][blankCol]) == blankCol)) hammDst--;
            int hamming = mHamming - hammSrc + hammDst;            

            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow+1, blankCol));
            mNeighbours.push(new Board(scratch, hamming, manhattan));
            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow+1, blankCol));
        }

        // East
        if (blankCol > 0) {

            // calculate new manhattan
            int distSrc = distance(blankRow, expRow(mBlocks[blankRow][blankCol-1])) + distance(blankCol-1, expCol(mBlocks[blankRow][blankCol-1]));
            int distDst = distance(blankRow, expRow(mBlocks[blankRow][blankCol-1])) + distance(blankCol, expCol(mBlocks[blankRow][blankCol-1]));
            int manhattan = mManhattan - distSrc + distDst;

            // calculate new hamming
            int hammSrc = 1;
            int hammDst = 1;
            if ((expRow(mBlocks[blankRow][blankCol-1]) == blankRow) && (expCol(mBlocks[blankRow][blankCol-1]) == blankCol-1)) hammSrc--;
            if ((expRow(mBlocks[blankRow][blankCol-1]) == blankRow) && (expCol(mBlocks[blankRow][blankCol-1]) == blankCol))   hammDst--;
            int hamming = mHamming - hammSrc + hammDst;            

            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow, blankCol-1));
            mNeighbours.push(new Board(scratch, hamming, manhattan));
            exchange(scratch, expBlock(blankRow, blankCol), expBlock(blankRow, blankCol-1));
        }

        return mNeighbours;
    }   

    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n" + N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", mBlocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }           

    /**
     * Debugging function
     */
    private static void __debug__(String filter, String message) {
        if (DEBUG && (FILTER.endsWith(filter) || FILTER.equals("@"))) {
            System.out.println("DBG " + filter + " : " + message);
        }
    }
}