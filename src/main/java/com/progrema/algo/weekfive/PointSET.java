/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfive;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    /**
     * Debugging flag
     */
    private final boolean mDebug = false;

    /**
     * Debugging filter
     */
    private final String mFilter = "@range";    

    /**
     * Red-Black Tree to collect all inserted Point2D
     */
    private SET<Point2D> mSetAll;

    /**
     * Construct an empty set of points
     */
    public PointSET() {
        mSetAll = new SET<Point2D>();
    }

    /**
     * Is the set empty?
     */
    public boolean isEmpty() {
        return mSetAll.isEmpty();
    }

    /**
     * Number of points in the set
     */
    public int size() {
        return mSetAll.size();
    }

    /**
     * Add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        debug("@insert", "p = " + p);
        if (!contains(p)) mSetAll.add(p);         
    }

    /**
     * Does the set contain point p?
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return mSetAll.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D point: mSetAll) {
            point.draw();
        }
    }

    /**
     * All points that are inside the rectangle (or on the boundary)
     */
    public Iterable<Point2D> range(RectHV rect) {
        debug("@range", "range()");
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> setInside = new SET<>();
        for (Point2D point: mSetAll) {
            if (rect.contains(point)) {
                debug("@range", "point = " + point);
                setInside.add(point); 
            }
        }
        return setInside;
    }

    /**
     * A nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {        
        if (p == null) throw new IllegalArgumentException();
        Point2D nearest = null;
        double distance = Double.POSITIVE_INFINITY;
        for (Point2D point: mSetAll) {
            if (point.distanceSquaredTo(p) < distance) {
                distance = point.distanceSquaredTo(p);
                nearest = point;
            }
        }
        return nearest;
    }

    /**
     * Debugging function
     */
    private void debug(String filter, String message) {
        if (mDebug && (mFilter.endsWith(filter) || mFilter.equals("@"))) {
            System.out.println("DBG " + filter + " : " + message);
        }
    }

}
