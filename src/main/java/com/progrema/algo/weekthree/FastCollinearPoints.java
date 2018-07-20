/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekthree;

import java.util.Arrays;

public class FastCollinearPoints {

    /**
     * Track number of linear segment found
     */
    private int mNumber;

    /**
     * Contain found line segment having 4 points in it
     */
    private LineSegment[] mLineSegments;

    /**
     * Pointer to head of reference point list. The list 
     * have original order.
     */
    private Node<Point> mHeadRefPoints;

    /**
     * Pointer to Head of line segment list 
     */
    private Node<LineSegment> mHeadLineSegment;

    /**
     * Node class to build list of line segment
     */
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    /**
     * Finds all line segments containing 4 or more points
     */ 
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }
      
        for (int i=0; i<points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            } 
            addRefPoints(points[i]);            
        }

        for (int z=0; z<points.length; z++) {

            Point refPoint = removeHeadRefPoint();
            double cache_slope = Double.NEGATIVE_INFINITY;            
            int cache_i = 0;
            int cache_counter = 1;
            int counter = 1;

            Arrays.sort(points, refPoint.slopeOrder());

            for (int i=0; i<points.length; i++) {             
                double slope = refPoint.slopeTo(points[i]);
                if (slope == cache_slope) {
                    counter++;
                    if (counter >= 3) { 
                        cache_counter = counter;
                        cache_i = i;
                    }
                } else {
                    cache_slope = slope;
                    counter = 1;
                }        
            }

            if (cache_counter >= 3) {                                
                Point[] segment = new Point[cache_counter + 1];
                segment[0] = refPoint;
                for (int j = 1; j < cache_counter + 1; j++) {
                    int collienarOffset = cache_i + 1 -j;
                    segment[j] = points[collienarOffset];
                }            
                addSegment(new LineSegment(findHead(segment), findTail(segment)));
            }
        }
        prepareLineSegment();        
    }

    private void addRefPoints(Point point) {

        if (mHeadRefPoints == null) {

            mHeadRefPoints = new Node<Point>();
            mHeadRefPoints.item = point;
            mHeadRefPoints.next = null;

        } else {
            
            Node<Point> current = mHeadRefPoints;
            while(current.next != null) {                                
                if (current.item.toString().equals(point.toString())) throw new IllegalArgumentException();
                current = current.next;
            }
            
            if (current.item.toString().equals(point.toString())) throw new IllegalArgumentException();

            Node<Point> newNode = new Node<Point>();
            newNode.item = point;
            newNode.next = null;
            current.next = newNode;
        }        
    }

    private Point removeHeadRefPoint() {
        if (mHeadRefPoints != null) {
            Node<Point> popedHead = mHeadRefPoints;
            mHeadRefPoints = mHeadRefPoints.next;
            popedHead.next = null;            
            return popedHead.item;
        } else {
            return null;
        }
    }

    private void addSegment(LineSegment newSegment) {

        if (mHeadLineSegment == null) {

            mHeadLineSegment = new Node<LineSegment>();
            mHeadLineSegment.item = newSegment;
            mHeadLineSegment.next = null;
            mNumber++;

        } else {
            
            Node<LineSegment> current = mHeadLineSegment;
            while(current.next != null) {                
                if (current.item.toString().equals(newSegment.toString())) return;
                current = current.next;
            }
            
            if (current.item.toString().equals(newSegment.toString())) return;

            Node<LineSegment> newNode = new Node<LineSegment>();
            newNode.item = newSegment;
            newNode.next = null;
            current.next = newNode;
            mNumber++;
        }
    }    

    // private void debug_showList(Node head) {
    //     Node current = head;
    //     while (current.next != null) {
    //         System.out.print(current.item + " ==>> ");
    //         current = current.next;
    //     }
    //     System.out.print(current.item + " ==>> null\n");
    // }

    private void prepareLineSegment() {
        mLineSegments = new LineSegment[mNumber];
        Node<LineSegment> current = mHeadLineSegment;
        for (int i = 0; i < mNumber; i++) {
            mLineSegments[i] = current.item;
            current = current.next;
        }
    }    

    private Point findHead(Point[] points) {
        Point head = points[0];
        for (int i = 0; i < points.length; i++) {
            if (head.compareTo(points[i]) == 1) {
                head = points[i];
            }
        }
        return head;
    }

    private Point findTail(Point[] points) {
        Point tail = points[0];
        for (int i = 0; i < points.length; i++) {
            if (tail.compareTo(points[i]) == -1) {
                tail = points[i];
            }
        }
        return tail;
    }    

    /**
     * The number of line segments
     */ 
    public int numberOfSegments() {
        return mNumber;
    }

    /**
     * The line segments
     */ 
    public LineSegment[] segments() {
        return mLineSegments;
    }
}