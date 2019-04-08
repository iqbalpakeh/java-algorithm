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
    private static final String FILTER = "@TRANSPOSE";

    /**
     * immutable object of picture
     */
    private Picture mPicture;

    /**
     * 2D matrix contain energy of each pixel
     */
    private double[][] mECell;

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

        debug("@MATRIX", "W = " + mPicture.width() + ", H = " + mPicture.height());

        for(int col=0; col<mPicture.width(); col++) {
            for (int row=0; row<mPicture.height(); row++) {
                mECell[col][row] = energy(col, row); 
                debug("@MATRIX", "(" + col + ", " + row + ") = " + mECell[col][row]);
            }
        }

        int[] solution = findVerticalSeam();
        for (int i=0; i<solution.length; i++) {
            debug("@RELAX", "" + solution[i]);
        }
    }

    /**
     * Calculate energy from top to bottom
     * 
     * @param parent of pixel at current observation
     * @param energy of pixel at current observation
     * @return total energy of this path
     */
    private double calcPathEnergy(Pixel parent, double energy) {
        if (parent != null) {
            Pixel minPix = minAdj(parent);
            if (minPix != null) energy += mECell[minPix.col][minPix.row];
            return calcPathEnergy(minPix, energy);
        } else return energy;
    }

    /**
     * Build seam carver solution on queue
     * 
     * @param parent of pixel at current observation
     * @param solution of seamcarver in Bag object
     */
    private void findSolution(Pixel parent, Queue<Pixel> solution) {
        if (parent != null) {
            solution.enqueue(parent);
            Pixel minPix = minAdj(parent);
            findSolution(minPix, solution);
        } 
    }

    /**
     * Select the minimum pixel among the adjacency pixels
     * 
     * @param pixel of current observation
     */
    private Pixel minAdj(Pixel pixel) {
        double minEnergy = Double.MAX_VALUE;
        Pixel minPix = null;
        for (Pixel pix : adj(pixel)) {
            if (minEnergy > mECell[pix.col][pix.row]){
                minEnergy = mECell[pix.col][pix.row];
                minPix = pix;
            } 
        }
        return minPix;
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

    // todo: DELETE THIS FUNCTION BEFORE SUBMIT TO TEST ENGINE!!
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
     * transpose matrix
     * 
     * @param matrix to be transposed
     */
    private double[][] transposeMatrix(double[][] matrix) {

        debug("@TRANSPOSE", "Pic Width = " + mPicture.width() + ", Pic Heigh = " + mPicture.height());
        debug("@TRANSPOSE", "Mat Width = " + matrix.length + ", Mat Heigh = " + matrix[0].length);

        int heigh = matrix.length; // reverse width -> heigh
        int width = matrix[0].length; // rever heigh -> width
        double[][] tMatrix = new double[width][heigh];

        for (int col=0; col<heigh; col++) {
            for (int row=0; row<width; row++) {
                tMatrix[col][row] =  matrix[row][col];
                System.out.print(" " + tMatrix[col][row]);
            }
            System.out.println("");
        }
        
        return null;
    }

    // todo: DELETE THIS FUNCTION BEFORE SUBMIT TO TEST ENGINE!!
    public double[][] debug_transposeMatrix(double[][] matrix) {
        return transposeMatrix(matrix);
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

        // Identify seam
        double minEnergy = Double.MAX_VALUE;
        Pixel startPixel = null;
        for (int i=0; i<mPicture.width(); i++) {
            Pixel pix = Pixel.newInstance(i, 0);
            double energy = calcPathEnergy(pix, mECell[pix.col][pix.row]);
            if (minEnergy > energy) {
                minEnergy = energy;
                startPixel = pix;
            }
        }
        
        // Build solution
        Queue<Pixel> queue = new Queue<>();
        findSolution(startPixel, queue);
        int[] solution = new int[queue.size()];
        int i = 0;
        for (Pixel pixel : queue) solution[i++] = pixel.col;
        return solution;
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