/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfive;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;

/**
 * A mutable data type KdTree.java that uses a 2d-tree to implement the same API 
 * (but replace PointSET with KdTree). A 2d-tree is a generalization of a BST to 
 * two-dimensional keys. The idea is to build a BST with points in the nodes, using 
 * the x- and y-coordinates of the points as keys in strictly alternating sequence.
 * 
 */
public class KdTree {

    /**
     * Debugging flag
     */
    private final boolean mDebug = true;

    /**
     * Debugging filter
     */
    private final String mFilter = "@nearest";

    /**
     * Root node 
     */
    private Node mRoot;

    /**
     * Number of nodes
     */
    private int mSize;

    /**
     * Hold temporary shortest value
     */
    private double mShortest;

    /**
     * Hold temporary shortest point
     */
    private Point2D mShortestPoint;

    /**
     * Internal Node Class
     */
    private static class Node {

        /**
         * The point
         */
        private Point2D p;

        /**
         * The axis-aligned rectangle corresponding to this node
         */
        private RectHV rect;

        /**
         * The left/bottom subtree
         */
        private Node leftBottom;

        /**
         * The right/top subtree
         */
        private Node rightTop;

        public Node(Point2D point, RectHV rect) {
            this.p = point;
            this.rect = rect;
        }

    }

    /**
     * Construct an empty set of points
     */
    public KdTree() { 
        mSize = 0;
    }

    /**
     * Is the set empty?
     */
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Number of points in the set
     */
    public int size() {
        return mSize;
    }

    /**
     * Add the point to the set (if it is not already in the set)
     * 
     * The algorithms for search and insert are similar to those for BSTs, 
     * but at the root we use the x-coordinate (if the point to be inserted has 
     * a smaller x-coordinate than the point at the root, go left; otherwise go right); 
     * then at the next level, we use the y-coordinate (if the point to be inserted has a 
     * smaller y-coordinate than the point in the node, go left; otherwise go right); then at 
     * the next level the x-coordinate, and so forth.
     * 
     */
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        mRoot = insert(mRoot, p, new RectHV(0.0, 0.0, 1.0, 1.0) ,0);
    }

    private Node insert(Node node, Point2D p, RectHV rect, int level) {   
        
        debug("@insert", "node = " + node);
        debug("@insert", "p = " + p);
        debug("@insert", "level = " + level);

        if (node == null) {
            debug("@insert", "node inserted");
            mSize++;
            return new Node(p, rect);        
        }

        debug("@insert", "node.p = " + node.p);
        if (p.compareTo(node.p) == 0) return node;

        if ((level % 2) == 0) { 
            if (p.x() >= node.p.x()) node.rightTop = insert(node.rightTop, p, rectRight(rect, node.p), ++level);
            else node.leftBottom  = insert(node.leftBottom, p, rectLeft(rect, node.p), ++level);
        } else { 
            if (p.y() >= node.p.y()) node.rightTop = insert(node.rightTop, p, rectTop(rect, node.p), ++level);
            else node.leftBottom  = insert(node.leftBottom, p, rectBottom(rect, node.p), ++level);
        }
        return node;
    }

    private RectHV rectLeft(RectHV rect, Point2D p) {        
        double xmin = rect.xmin();
        double ymin = rect.ymin();
        double xmax = p.x();
        double ymax = rect.ymax();
        return new RectHV(xmin, ymin, xmax, ymax);
    }

    private RectHV rectRight(RectHV rect, Point2D p) {
        double xmin = p.x();
        double ymin = rect.ymin();
        double xmax = rect.xmax();
        double ymax = rect.ymax();
        return new RectHV(xmin, ymin, xmax, ymax);
    }

    private RectHV rectTop(RectHV rect, Point2D p) {
        double xmin = rect.xmin();
        double ymin = p.y();
        double xmax = rect.xmax();
        double ymax = rect.ymax();
        return new RectHV(xmin, ymin, xmax, ymax);
    }

    private RectHV rectBottom(RectHV rect, Point2D p) {
        double xmin = rect.xmin();
        double ymin = rect.ymin();
        double xmax = rect.xmax();
        double ymax = p.y();
        return new RectHV(xmin, ymin, xmax, ymax);
    }

    /**
     * Does the set contain point p
     * 
     * The algorithms for search and insert are similar to those for BSTs, 
     * but at the root we use the x-coordinate (if the point to be inserted has 
     * a smaller x-coordinate than the point at the root, go left; otherwise go right); 
     * then at the next level, we use the y-coordinate (if the point to be inserted has a 
     * smaller y-coordinate than the point in the node, go left; otherwise go right); then at 
     * the next level the x-coordinate, and so forth.
     * 
     */
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return contains(mRoot, p, 0);
    }

    private boolean contains(Node node, Point2D p, int level) {
        
        debug("@contains", "node = " + node);
        debug("@contains", "p = " + p);
        debug("@contains", "level = " + level);

        if (node == null) {
            debug("@contains", "node NOT found");
            return false;
        }

        debug("@contains", "node.p = " + node.p);

        if (p.compareTo(node.p) == 0) {
            debug("@contains", "node found");
            return true;
        }
    
        if ((level % 2) == 0) { 
            if (p.x() >= node.p.x()) return contains(node.rightTop, p, ++level);
            else return contains(node.leftBottom, p, ++level);
        } else { 
            if (p.y() >= node.p.y()) return contains(node.rightTop, p, ++level);
            else return contains(node.leftBottom, p, ++level);
        }
    }

    /**
     * Draw all points to standard draw.
     * 
     * A 2d-tree divides the unit square in a simple way: all the points to the left of 
     * the root go in the left subtree; all those to the right go in the right subtree; 
     * and so forth, recursively. 
     * 
     * Your draw() method should draw all of the points to standard draw in black and the subdivisions 
     * in red (for vertical splits) and blue (for horizontal splits). This method need not be 
     * efficient—it is primarily for debugging.
     */
    public void draw() {
        draw(mRoot, 0);
    }

    private void draw(Node node, int level) {        

        if (node == null) return;

        if ((level % 2) == 0) drawRedLine(new Point2D(node.p.x(), node.rect.ymax()), new Point2D(node.p.x(), node.rect.ymin()));
        else drawBlueLine(new Point2D(node.rect.xmin(), node.p.y()), new Point2D(node.rect.xmax(), node.p.y()));
        
        drawPoint(node.p);
        draw(node.leftBottom, level+1);
        draw(node.rightTop, level+1);
    }

    private void drawPoint(Point2D point) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);                
        StdDraw.point(point.x(), point.y());
    }

    private void drawBlueLine(Point2D start, Point2D end) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);            
        StdDraw.line(start.x(), start.y(), end.x(), end.y());
    }

    private void drawRedLine(Point2D start, Point2D end) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.RED);                
        StdDraw.line(start.x(), start.y(), end.x(), end.y());
    }

    /**
     * All points that are inside the rectangle (or on the boundary)
     * 
     * To find all points contained in a given query rectangle, start at the root and 
     * recursively search for points in both subtrees using the following pruning rule: 
     * 
     * if the query rectangle does not intersect the rectangle corresponding to a node, 
     * there is no need to explore that node (or its subtrees). 
     * 
     * A subtree is searched only if it might contain a point contained in the query rectangle.
     * 
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> insides = new SET<>();
        range(insides, mRoot, rect, 0);
        return insides;
    }

    private void range(SET<Point2D> set, Node node, RectHV rect, int level) {        
        if ((node != null) && (rect.intersects(node.rect))) {
            if (rect.contains(node.p)) set.add(node.p);
            range(set, node.leftBottom, rect, level+1);
            range(set, node.rightTop, rect, level+1);
        }        
    }

    /**
     * A nearest neighbor in the set to point p; null if the set is empty
     * 
     * To find a closest point to a given query point, start at the root and 
     * recursively search in both subtrees using the following pruning rule: 
     * 
     * if the closest point discovered so far is closer than the distance between 
     * the query point and the rectangle corresponding to a node, there is no need 
     * to explore that node (or its subtrees). That is, search a node only only if 
     * it might contain a point that is closer than the best one found so far. 
     * 
     * The effectiveness of the pruning rule depends on quickly finding a nearby point. 
     * To do this, organize the recursive method so that when there are two possible 
     * subtrees to go down, you always choose the subtree that is on the same side of 
     * the splitting line as the query point as the first subtree to explore—the closest 
     * point found while exploring the first subtree may enable pruning of the second subtree.
     * 
     */
    public Point2D nearest(Point2D p) {        
        if (p == null) throw new IllegalArgumentException();
        if (mSize == 0) return null;
        mShortest = Double.POSITIVE_INFINITY;
        nearest(mRoot, p, 0);
        return mShortestPoint;
    }

    private void nearest(Node node, Point2D p, int level) {           

        double rectDistance = node.rect.distanceSquaredTo(p);
        if (mShortest <= rectDistance) return;

        double distance = node.p.distanceSquaredTo(p);
        debug("@nearest", "ref point = " + node.p + 
                ", shortest = " + mShortest + ", rectdistance = " + rectDistance + ", distance = " + distance);

        if (distance <= mShortest) {
            mShortest = distance;      
            mShortestPoint = node.p;      
        }

        boolean goRightTop = (node.rightTop != null) && (mShortest > node.rightTop.rect.distanceSquaredTo(p));
        boolean goLeftBottom = (node.leftBottom != null) && (mShortest > node.leftBottom.rect.distanceSquaredTo(p));

        if (goRightTop && goLeftBottom) {
            if ((level % 2) == 0) { 
                if (p.x() >= node.p.x()) {
                    nearest(node.rightTop, p, level + 1);
                    nearest(node.leftBottom, p, level + 1);
                } else {
                    nearest(node.leftBottom, p, level + 1);
                    nearest(node.rightTop, p, level + 1);
                }
            } else { 
                if (p.y() >= node.p.y()) {
                    nearest(node.rightTop, p, level + 1);
                    nearest(node.leftBottom, p, level + 1);
                } else {
                    nearest(node.leftBottom, p, level + 1);
                    nearest(node.rightTop, p, level + 1);
                }
            }   
        } else {
            if (goRightTop) nearest(node.rightTop, p, level + 1);
            if (goLeftBottom) nearest(node.leftBottom, p, level + 1);
        }
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