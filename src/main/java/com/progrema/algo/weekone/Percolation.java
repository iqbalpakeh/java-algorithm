package com.progrema.algo.weekone; // Remove this before submission!!

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    /**
     * Union object to check if the system percolates
     */
    private WeightedQuickUnionUF mUF;

    /**
     * virtual top site of Quick Union
     */
    private int mTopOffset;

    /**
     * virtual bottom site of Quick Union
     */
    private int mBottomOffset;

    /**
     * Grid object to be tested
     */
    private int[][] mGrid;

    /**
     * Row and Column size of the grid
     */
    private int mSizeN;

    /**
     * Blocked State condition for each grid
     */
    private static final int STATE_BLOCKED = 0;

    /**
     * Open State condition for each grid
     */
    private static final int STATE_OPEN = 1;

    /**
     * Store number of open site
     */
    private int mOpenSiteNumber; 

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();

        } else {
            //Initialize GRID
            mSizeN = n;
            mGrid = new int[mSizeN][mSizeN];           
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mGrid[i][j] = STATE_BLOCKED;
                }
            }
            mOpenSiteNumber = 0;

            // Initialize Union Find
            mUF = new WeightedQuickUnionUF(mSizeN*mSizeN + 2);

            // Virtual TOP = Size - 2 => mUF[(mSizeN*mSizeN + 2) - 2];
            // Virtual BOTOM = Size - 1 => mUF[(mSizeN*mSizeN + 2) - 1];
            mTopOffset = (mSizeN*mSizeN + 2) - 2;
            mBottomOffset = (mSizeN*mSizeN + 2) - 1;
        }
    }   

    /** 
     * open site (row, col) if it is not open already
     */ 
    public void open(int row, int col) {

        int absRow = row-1;
        int absCol = col-1;

        int offsetSource;
        int offsetDestination;

        if (isOpen(row, col) != true) {
            mGrid[absRow][absCol] = STATE_OPEN;
            mOpenSiteNumber++;
        }

        // Add 4 direction union (union to all adjacent sites)

        offsetSource = convert2Dto1D(absRow, absCol);

        // Top row:
        if (offsetSource < mSizeN) {
            mUF.union(offsetSource, mTopOffset);
        }

        // Bottom row:        
        if (offsetSource >= mSizeN*(mSizeN-1)) {
            mUF.union(offsetSource, mBottomOffset);
        }        

        // North:
        offsetDestination = getNorthUnionOffset(offsetSource);
        if ((offsetDestination != -1) && checkNorthOpen(absRow, absCol)) {
            mUF.union(offsetSource, offsetDestination);
        }

        // East:
        offsetDestination = getEastUnionOffset(offsetSource);
        if ((offsetDestination != -1) && checkEastOpen(absRow, absCol)) {
            mUF.union(offsetSource, offsetDestination);
        }

        // South:
        offsetDestination = getSouthUnionOffset(offsetSource);
        if ((offsetDestination != -1) && checkSouthOpen(absRow, absCol)) {
            mUF.union(offsetSource, offsetDestination);
        }

        // West:
        offsetDestination = getWestUnionOffset(offsetSource);
        if ((offsetDestination != -1) && checkWestOpen(absRow, absCol)) {
            mUF.union(offsetSource, offsetDestination);
        }

    }

    private int convert2Dto1D(int row, int col) {
        return mSizeN*row + col;
    }
    
    private int getNorthUnionOffset(int offsetSource) {
        if (offsetSource > mSizeN-1)
            return offsetSource - mSizeN;
        else 
            return -1;
    }

    private boolean checkNorthOpen(int row, int col) {
        int offset = convert2Dto1D(row, col);
        if (offset > mSizeN-1) 
            return isOpen(row+1-1, col+1);
        else 
            return false;
    }

    private int getEastUnionOffset(int offsetSource) {
        if (offsetSource % mSizeN < mSizeN-1)
            return ++offsetSource;
        else 
            return -1;
    }

    private boolean checkEastOpen(int row, int col) {
        int offset = convert2Dto1D(row, col);
        if (offset % mSizeN < mSizeN-1) 
            return isOpen(row+1, col+1+1);
        else 
            return false;
    }

    private int getSouthUnionOffset(int offsetSource) {
        if (offsetSource < mSizeN*(mSizeN-1))
            return offsetSource + mSizeN;
        else 
            return -1;
    }

    private boolean checkSouthOpen(int row, int col) {
        int offset = convert2Dto1D(row, col);
        if (offset < mSizeN*(mSizeN-1))
            return isOpen(row+1+1, col+1);
        else 
            return false;
    }

    private int getWestUnionOffset(int offsetSource) {
        if (offsetSource%mSizeN > 0)
            return --offsetSource;
        else 
            return -1;
    }

    private boolean checkWestOpen(int row, int col) {
        int offset = convert2Dto1D(row, col);
        if (offset%mSizeN > 0)
            return isOpen(row+1, col+1-1);
        else 
            return false;  
    }

    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if ((row <= 0) || (row > mSizeN) || (col <= 0) || (col > mSizeN)) {
            throw new IllegalArgumentException();
        }
        return (mGrid[--row][--col] == STATE_OPEN);
    }

    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if ((row <= 0) || (row > mSizeN) || (col <= 0) || (col > mSizeN)) {
            throw new IllegalArgumentException();
        }
        return mUF.connected(mTopOffset, convert2Dto1D(--row, --col));
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return mOpenSiteNumber;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return mUF.connected(mBottomOffset, mTopOffset);
    }

    // public void debug_showGrid() {
    //     System.out.print("Number of Open Sites: " + numberOfOpenSites() + "\n");
    //     for (int row=0; row<mSizeN; row++) {
    //         for (int col=0; col<mSizeN; col++) {
    //             System.out.print(mGrid[row][col] + ", ");
    //         }
    //         System.out.print("\n");
    //     }
    // }

    // private boolean debug_isConnectedToTop(int row, int col) {
    //     return mUF.connected(mTopOffset, mSizeN*row + col);
    // }

    // private boolean debug_isConnectedToBottom(int row, int col) {
    //     return mUF.connected(mBottomOffset, mSizeN*row + col);
    // }

    // public void debug_showConnectedGrid() {
    //     System.out.print("Connected Grid\n");
    //     for (int row=0; row<mSizeN; row++) {
    //         for (int col=0; col<mSizeN; col++) {
    //             char stat = '0';
    //             if (debug_isConnectedToTop(row, col)) {
    //                 stat = 'T';
    //             }
    //             if (debug_isConnectedToBottom(row, col)) {
    //                 stat = 'B';
    //             }
    //             if (debug_isConnectedToTop(row, col) && debug_isConnectedToBottom(row, col)) {
    //                 stat = 'X';
    //             }
    //             System.out.print(stat + ", ");
    //         }
    //         System.out.print("\n");
    //     }
    // }

}