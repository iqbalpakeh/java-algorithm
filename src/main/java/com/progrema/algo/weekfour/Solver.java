/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfour;

import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    /**
     * Debugging flag
     */
    private static final boolean DEBUG = false;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@";

    /**
     * Immutable initial board
     */
    private final Board mInitialBoard;

    /**
     * Flag showing if board is solveable or not
     */
    private boolean mSolveAble;

    /**
     * Number of minimum moves solving the puzzle
     */
    private int mMinMoves;

    /**
     * Contain stack of the solution board from initial to goal
     */
    private LinkedStack<Board> mStackSolution;

    /**
     * Contain information of 
     * - a board
     * - number of move to reach the board
     * - predecessor board
     */
    private static class SearchNode implements Comparable<SearchNode> {

        /**
         * Current board 
         */
        private Board mBoard;

        /**
         * previous board
         */
        private SearchNode mPreNode;

        /**
         * Number of move to reach the board
         */
        private int mMoves;

        /**
         * Manhattan value of this board
         */
        private int mManhattan;

        /**
         * Create one SearchNode object
         */
        public SearchNode(Board board, SearchNode preNode, int moves) {
            
            mBoard = board;
            mPreNode = preNode;
            mMoves = moves;
            mManhattan = board.manhattan();

            __debug__("@Solver", "manhattan = " + mManhattan);
            __debug__("@Solver", "moves = " + mMoves);
            __debug__("@Solver", "priority = " + (mManhattan + mMoves));
        }

        public Board getBoard() {
            return mBoard;
        }

        public SearchNode getPrevNode() {
            return mPreNode;
        }

        public int getMoveNumber() {
            return mMoves;
        }

        public int compareTo(SearchNode that) {   
            return (this.mManhattan + this.mMoves) - (that.mManhattan + that.mMoves);   
        }
    }

    /**
     * Find a solution to the initial board (using the A* algorithm):
     * 
     * Define a search node of the game to be a board, the number of moves made to reach the board, 
     * and the predecessor search node. 
     * 
     * First, insert the initial search node (the initial board, 0 moves, and a null predecessor search node) 
     * into a priority queue. Then, delete from the priority queue the search node with the minimum priority, 
     * and insert onto the priority queue all neighboring search nodes (those that can be reached in one move 
     * from the dequeued search node). 
     * 
     * Repeat this procedure until the search node dequeued corresponds to a goal board. 
     * The success of this approach hinges on the choice of priority function for a search node
     */
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        mMinMoves = 0;
        mInitialBoard = initial;              
        mSolveAble = false;  
        mStackSolution = buildSolution();
    }

    /**
     * Build the solution solving the puzzle
     */
    private LinkedStack<Board> buildSolution() {
        
        Board objBoard;            
        SearchNode objSearchNode;
        LinkedStack<Board> objSolution = new LinkedStack<>();
        MinPQ<SearchNode> objMinPQ = new MinPQ<>();
        
        int twinMove = 0;
        Board twinBoard;
        SearchNode twinSearchNode;
        MinPQ<SearchNode> twinMinPQ = new MinPQ<>();

        objMinPQ.insert(new SearchNode(mInitialBoard, null, mMinMoves));
        twinMinPQ.insert(new SearchNode(mInitialBoard.twin(), null, twinMove));

        do {

            // Search puzzle solution
            objSearchNode = objMinPQ.delMin();
            objBoard = objSearchNode.getBoard();
            mMinMoves = objSearchNode.getMoveNumber();            

            for (Board neighbor : objSearchNode.getBoard().neighbors()) {                                
                if (checkHistory(objSearchNode, neighbor)) objMinPQ.insert(new SearchNode(neighbor, objSearchNode, mMinMoves+1));                     
            }

            // Search twin puzzle solution
            twinSearchNode = twinMinPQ.delMin();
            twinBoard = twinSearchNode.getBoard();
            twinMove = twinSearchNode.getMoveNumber();

            for (Board neighbor : twinSearchNode.getBoard().neighbors()) {
                if (checkHistory(twinSearchNode, neighbor)) twinMinPQ.insert(new SearchNode(neighbor, twinSearchNode, twinMove+1));                    
            }

            // Check if twin puzzle reach the goal
            if (twinBoard.isGoal()) {
                mSolveAble = false;
                mMinMoves = -1;
                return null;
            }

            // Check if puzzle reach the goal
        } while (!objBoard.isGoal());

        mSolveAble = true;

        // Build solution        
        while (objSearchNode.getPrevNode() != null) {
            objSolution.push(objSearchNode.getBoard());
            objSearchNode = objSearchNode.getPrevNode();
        }
        objSolution.push(mInitialBoard);
        return objSolution;
    }

    /**
     * Check board history to implement critical optimization
     */
    private boolean checkHistory(SearchNode node, Board board) {
        if (node.getPrevNode() == null) return true;
        if (node.getPrevNode().getBoard().equals(board)) return false;
        return true;
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return mSolveAble;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return mMinMoves;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return mStackSolution;
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