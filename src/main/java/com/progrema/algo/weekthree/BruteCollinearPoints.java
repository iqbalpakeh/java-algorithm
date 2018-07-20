/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekthree;

public class BruteCollinearPoints {

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
     * Pointer to Head of linked list 
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
     * Finds all line segments containing 4 points
     */ 
    public BruteCollinearPoints(Point[] points) {
         
        mNumber = 0;
  
        for (int i=0; i<points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            } 
            addRefPoints(points[i]);            
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    for (int l = 0; l < points.length; l++) {

                        if ((i != j) && (i != k) && (i != l) && 
                            (j != i) && (j != k) && (j != l) && 
                            (k != i) && (k != j) && (k != l) && 
                            (l != i) && (l != j) && (l != k) ) {                    

                            if ((points[i] == null) || (points[j] == null) || 
                                (points[k] == null) || (points[l] == null)) {
                                throw new IllegalArgumentException();
                            }

                            if ((points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) && 
                                (points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))) {
                                Point[] segment = new Point[4];
                                segment[0] = points[i];
                                segment[1] = points[j];
                                segment[2] = points[k];
                                segment[3] = points[l];
                                addSegment(new LineSegment(findHead(segment), findTail(segment)));
                            }
                        }
                    }
                }
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
 
 
 