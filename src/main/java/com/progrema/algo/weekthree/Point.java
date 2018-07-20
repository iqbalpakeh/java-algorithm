/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekthree;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    /**
     * x-coordinate of this point
     */ 
    private final int x;     

    /**
     * y-coordinate of this point
     */
    private final int y;     

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        
        if (that == null) {
            throw new NullPointerException();
        }

        double x0 = this.x;
        double y0 = this.y;

        double x1 = that.x;
        double y1 = that.y;

        // Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal
        if ((x1 == x0) && (y1 == y0)) {
            return Double.NEGATIVE_INFINITY;
        }

        // Double.POSITIVE_INFINITY if the line segment is vertical
        if ((x1 == x0) && (y1 != y0)) {
            return Double.POSITIVE_INFINITY;
        }

        // +0.0 if the line segment connecting the two points is horizontal
        if ((x1 != x0) && (y1 == y0)) {
            return 0.0;
        }

        return (y1 - y0) / (x1 - x0);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {

        if (that == null) {
            throw new NullPointerException();
        }

        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder(this.x, this.y);
    }

    private class SlopeOrder implements Comparator<Point> {

        private Point mPoint;

        public SlopeOrder(int x, int y) {
            mPoint = new Point(x, y);
        }

        /**
         * Formally, the point (x1, y1) is less than the point (x2, y2) 
         * if and only if the slope (y1 − y0) / (x1 − x0) is less than 
         * the slope (y2 − y0) / (x2 − x0).
         */
         public int compare(Point p, Point q) {

            if ((p == null) || (q == null)) {
                throw new NullPointerException();
            }

            double slope_p = mPoint.slopeTo(p);
            double slope_q = mPoint.slopeTo(q);

            if (slope_p > slope_q) return 1; 
            if (slope_p < slope_q) return -1;            
            return 0;
        }
    }
    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}