/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week2;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Bag;

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
    private static final String FILTER = "@MATRIX";

    /**
     * immutable object of picture
     */
    private Picture mPicture;

    /**
     * 2D matrix contain energy of each pixel
     */
    private double[][] mEnergyCell;

    /**
     * Energy level from starting pixel to current pixel
     */
    private double[][] mEnergyPath;

    /**
     * Previous pixel to current pixel 
     * (to create smallest energy path)
     */
    private int[][] mPreviousPixel;

    /**
     * Helper class contain pixel column and row information
     */
    public static class Pixel {
        int row;
        int col;
        
        private Pixel(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public static Pixel newInstance(int row, int col) {
            return new Pixel(row, col);
        }
    }

    /**
     * Create a seam carver object based on the given picture
     */
    public SeamCarver(Picture picture) {

        mPicture = new Picture(picture);
        mEnergyCell = new double[mPicture.width()][mPicture.height()];
        mEnergyPath = new double[mPicture.width()][mPicture.height()];
        mPreviousPixel = new int[mPicture.width()][mPicture.height()];

        debug("@MATRIX", "W = " + mPicture.width() + ", H = " + mPicture.height());

        for(int i=0; i<mPicture.width(); i++) {
            for (int j=0; j<mPicture.height(); j++) {
                mEnergyCell[i][j] = energy(i, j); 
                debug("@MATRIX", "(" + i + ", " + j + ") = " + mEnergyCell[i][j]);
            }
        }

        /**
         * Topological topological = new Topological(G);
         * for (int v : topological.order())
         *      for (DirectedEdge e : G.adj(v))
         *          relax(e);
         *
         **** Topological order for seamcarver is always from left to right, top to bottom
         *
         *
         * private void relax(DirectedEdge e) {
         *      int v = e.from(), w = e.to();
         *      if (distTo[w] > distTo[v] + e.weight()) {
		 *          distTo[w] = distTo[v] + e.weight();
		 *          edgeTo[w] = e;
	     *       }
         * }
         *
         **** seam carver adjancy list is always known 
         **** from pixel (x, y) to pixels (x − 1, y + 1), (x, y + 1), and (x + 1, y + 1)
         */
    }

    /**
     * Return the iterable object contain the adjacenty list 
     * from pixel with column x and row y
     * 
     * @param x is pixel column
     * @param y is pixel row
     */
    private Iterable<Pixel> adj(Pixel pixel) {

        // seam carver adjancy list is always known 
        // from pixel (x, y) to pixels (x − 1, y + 1), (x, y + 1), and (x + 1, y + 1)

        Bag<Pixel> bag = new Bag<>();
        int row = pixel.row;
        int col = pixel.col;

        // todo: add boundary checking

        bag.add(Pixel.newInstance(row - 1, col + 1));
        bag.add(Pixel.newInstance(row, col + 1));
        bag.add(Pixel.newInstance(row + 1, col + 1));

        return bag;
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