/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week2;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;

import java.awt.Color;
import java.lang.Math;


public class SeamCarver {

    /**
     * Debugging flag
     */
    private static final boolean DEBUG = true;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@RELAX";

    /**
     * immutable object of picture
     */
    private Picture mPicture;

    /**
     * 2D matrix contain energy of each pixel
     */
    private double[][] mECell;

    /**
     * Energy level from starting pixel to current pixel
     */
    private double[][] mEPath;

    /**
     * Previous pixel to current pixel 
     * (to create smallest energy path)
     */
    private Pixel[][] mPrevPixel;

    /**
     * Used to iterate 
     * 
     */
    private boolean[][] mMarked;

    /**
     * Helper class contain pixel column and row information
     */
    public static class Pixel {

        int col; // 0 -> width - 1
        int row; // 0 -> heigh - 1
            
        private Pixel(int col, int row) {            
            this.col = col;
            this.row = row;
        }
        
        public static Pixel newInstance(int col, int row) {
            return new Pixel(col, row);
        }

        public String toString() {
            return "(" + col + ", " + row + ")";
        }
    }

    /**
     * Create a seam carver object based on the given picture
     */
    public SeamCarver(Picture picture) {

        mPicture = new Picture(picture);
        mECell = new double[mPicture.width()][mPicture.height()];
        mEPath = new double[mPicture.width()][mPicture.height()];
        mPrevPixel = new Pixel[mPicture.width()][mPicture.height()];
        mMarked = new boolean[mPicture.width()][mPicture.height()];

        debug("@MATRIX", "W = " + mPicture.width() + ", H = " + mPicture.height());

        for(int col=0; col<mPicture.width(); col++) {
            for (int row=0; row<mPicture.height(); row++) {
                mECell[col][row] = energy(col, row); 
                mEPath[col][row] = Double.POSITIVE_INFINITY;
                mPrevPixel[col][row] = null;
                mMarked[col][row] = false;
                debug("@MATRIX", "(" + col + ", " + row + ") = " + mECell[col][row]);
            }
        }

        // debug --start
        Pixel source = Pixel.newInstance(3, 0);        
        Queue<Pixel> queue = new Queue<Pixel>();
        queue.enqueue(source);
        mEPath[source.col][source.row] = 1000;
        mMarked[source.col][source.row] = true;
        while(!queue.isEmpty()) {
            Pixel pixel = queue.dequeue();
            for (Pixel adjPixel : adj(pixel)) {
                if (!mMarked[adjPixel.col][adjPixel.row]) {
                    queue.enqueue(adjPixel);
                    mMarked[adjPixel.col][adjPixel.row] = true;
                    relax(pixel, adjPixel);
                    debug("@RELAX", adjPixel.toString());
                }
            }
        }

        debug("@RELAX", "-------------------");
        Pixel end = Pixel.newInstance(2, 4);
        debug("@RELAX", end.toString());
        while (mPrevPixel[end.col][end.row] != null) {        
            end = mPrevPixel[end.col][end.row];
            debug("@RELAX", end.toString());
        }
        // debug --end
    }

    /**
     * Relax energy path of current pixel
     * 
     * @param pixel of current observation
     * @param adjPixxel is adjacency of current observation pixel
     */
    private void relax(Pixel pixel, Pixel adjPixel) {

        debug("@RELAX", "\npixel = " + pixel + ", adjPixel = " + adjPixel);

        debug("@RELAX", "Before");
        debug("@RELAX", "mEPath" + adjPixel + "= " + mEPath[adjPixel.col][adjPixel.row]);
        debug("@RELAX", "mEPath" + pixel + " = " + mEPath[pixel.col][pixel.row]);
        debug("@RELAX", "mECell" + adjPixel + " = " + mECell[adjPixel.col][adjPixel.row]);
        debug("@RELAX", "mPrevPixel" + adjPixel + " = " + mPrevPixel[adjPixel.col][adjPixel.row]);

        if (mEPath[adjPixel.col][adjPixel.row] > mEPath[pixel.col][pixel.row] + mECell[adjPixel.col][adjPixel.row]) {
            mEPath[adjPixel.col][adjPixel.row] = mEPath[pixel.col][pixel.row] + mECell[adjPixel.col][adjPixel.row];
            mPrevPixel[adjPixel.col][adjPixel.row] = pixel;
        }

        debug("@RELAX", "After");
        debug("@RELAX", "mEPath" + adjPixel + "= " + mEPath[adjPixel.col][adjPixel.row]);
        debug("@RELAX", "mEPath" + pixel + " = " + mEPath[pixel.col][pixel.row]);
        debug("@RELAX", "mECell" + adjPixel + " = " + mECell[adjPixel.col][adjPixel.row]);
        debug("@RELAX", "mPrevPixel" + adjPixel + " = " + mPrevPixel[adjPixel.col][adjPixel.row]);
    }

    /**
     * Return the iterable object contain the adjacenty list 
     * from pixel with column x and row y.
     * 
     * Seam Carver adjacency list is always known. If pixel is (x, y), than
     * adjacency list are (x − 1, y + 1), (x, y + 1), and (x + 1, y + 1)
     * 
     * @param pixel from picture
     * @return iterable adjacency list
     */
    private Iterable<Pixel> adj(Pixel pixel) {

        Bag<Pixel> bag = new Bag<>();
        int col = pixel.col;
        int row = pixel.row;        

        validateColumnIndex(col);
        validateRowIndex(row);
        
        if (row < mPicture.height() - 1) {
            if (col > 0) bag.add(Pixel.newInstance(col - 1, row + 1));
            if (col < mPicture.width() - 1) bag.add(Pixel.newInstance(col + 1, row + 1));
            bag.add(Pixel.newInstance(col, row + 1));
        }

        return bag;
    }

    // DELETE THIS FUNCTION BEFORE SUBMIT TO TEST ENGINE!!
    public Iterable<Pixel> debug_adj(Pixel pixel) {
        return adj(pixel);
    }

    /**
     * current picture
     */
    public Picture picture() {
        return mPicture;
    }               

    /**
     * width of current picture
     */
    public int width() {
        return mPicture.width();
    }   
    
    /**
     * height of current picture
     */
    public int height() {
        return mPicture.height();
    } 

    /**
     * energy of pixel at column x and row y
     */
    public double energy(int x, int y) {
    
        validateColumnIndex(x);
        validateRowIndex(y);
    
        if (checkLimit(x, y)) return 1000;

        double deltaSquareX = calcDeltaSquare(mPicture.get(x+1, y), mPicture.get(x-1, y));
        debug("@ENERGY", "deltaSquareX = " + deltaSquareX);

        double deltaSquareY = calcDeltaSquare(mPicture.get(x, y-1), mPicture.get(x, y+1));
        debug("@ENERGY", "deltaSquareY = " + deltaSquareY);
    
        return Math.sqrt(deltaSquareX + deltaSquareY);
    } 

    private boolean checkLimit(int x, int y) {
        boolean result = false;
        if (y == 0) result = true;
        if (x == 0) result = true;
        if (y == mPicture.height() - 1) result = true;
        if (x == mPicture.width() - 1) result = true;
        debug("@ENERGY", "x = " + x + ", y = " + y + ", result = " + result);
        return result;
    }

    private double calcDeltaSquare(Color first, Color last) {
        int R = last.getRed() - first.getRed();
        int G = last.getGreen() - first.getGreen();
        int B = last.getBlue() - first.getBlue();
        debug("@ENERGY", "R = " + R + ", G = " + G + ", B = " + B);
        return Math.pow(R, 2) + Math.pow(G, 2) + Math.pow(B, 2);
    }

    /**
     * sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        return null;
    } 
    
    /**
     * sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        // 1. Build SPT along TOP Border
        // 2. Use Topological Sort to find shortest path for each pixel on TOP Border
        // 3. Get the shortest from all SPT on TOP Border. This is the the Vertical Seam
        return null;
    }            
    
    /**
     * remove horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("input contain null object");
        if (seam.length != mPicture.width()) throw new IllegalArgumentException("seam length is wrong");
        if (mPicture.height() <= 1) throw new IllegalArgumentException();   
        // todo: check array content
        // if the array is not a valid seam (i.e., either an entry is outside 
        // its prescribed range or two adjacent entries differ by more than 1).        

    } 
    
    /**
     * remove vertical seam from current picture
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("input contain null object");
        if (seam.length != mPicture.height()) throw new IllegalArgumentException("seam length is wrong"); 
        if (mPicture.width() <= 1) throw new IllegalArgumentException();   
        // todo: check array content
        // if the array is not a valid seam (i.e., either an entry is outside 
        // its prescribed range or two adjacent entries differ by more than 1).

    }

    private void validateRowIndex(int row) {
        if (row < 0 || row >= height())
            throw new IllegalArgumentException("row index must be between 0 and " + (height() - 1) + ": " + row);
    }

    private void validateColumnIndex(int col) {
        if (col < 0 || col >= width())
            throw new IllegalArgumentException("column index must be between 0 and " + (width() - 1) + ": " + col);
    }    

    /**
     * Debugging function
     */
    @SuppressWarnings("unused")
    private static void debug(String filter, String message) {
        if (DEBUG && (FILTER.endsWith(filter) || FILTER.equals("@"))) {
            System.out.println("DBG " + filter + " : " + message);
        }
    }    

}