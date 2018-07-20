/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekthree;

import com.progrema.algo.Logger;
import com.progrema.algo.UnitTest;

import java.util.Comparator;

public class PointTest extends UnitTest {

    public static void main(String[] args) {
        test_slopeOrder();
        test_slopeTo();
        test_compareTo();
    }

    public static void test_slopeOrder() {
        
        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Point central = new Point(1, 1);
        Comparator<Point> comparator = central.slopeOrder();

        {
            Point p = new Point(2, 5);
            Point q = new Point(2, 4);    
            compare(comparator.compare(p, q), 1);
        }
        
        {
            Point p = new Point(2, 4);
            Point q = new Point(2, 5);    
            compare(comparator.compare(p, q), -1);
        }

        {
            // Positive Inv check
            Point p = new Point(1, 5);
            Point q = new Point(2, 4);
            compare(comparator.compare(p, q), 1);
        }

        {
            // Negative Inv check
            Point p = new Point(2, 4);
            Point q = new Point(1, 1);
            compare(comparator.compare(p, q), 1);
        }        

        {
            // Positive Inv vs Negative Inv check
            Point p = new Point(1, 5);
            Point q = new Point(1, 1);
            compare(comparator.compare(p, q), 1);
        }

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    public static void test_compareTo() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Logger.println("Section 1\n\n");
        {
            Point point = new Point(3, 3);
            compare(point.compareTo(new Point(4, 4)), -1);
            compare(point.compareTo(new Point(5, 5)), -1);
            compare(point.compareTo(new Point(4, 3)), -1);
            compare(point.compareTo(new Point(3, 4)), -1);                
            compare(point.compareTo(new Point(4, 3)), -1);  
            compare(point.compareTo(new Point(5, 3)), -1);
            compare(point.compareTo(new Point(3, 3)), 0);
            compare(point.compareTo(new Point(3, 2)), 1);
            compare(point.compareTo(new Point(2, 3)), 1);
        }

        Logger.println("Section 2\n");
        {
            Point point = new Point(5, 5);            
            compare(point.compareTo(new Point(0, 4)), 1);
            compare(point.compareTo(new Point(1, 4)), 1);
            compare(point.compareTo(new Point(2, 4)), 1);
            compare(point.compareTo(new Point(3, 4)), 1);
            compare(point.compareTo(new Point(4, 4)), 1);
            compare(point.compareTo(new Point(5, 4)), 1);
            compare(point.compareTo(new Point(6, 4)), 1);

            compare(point.compareTo(new Point(0, 5)), 1);
            compare(point.compareTo(new Point(1, 5)), 1);
            compare(point.compareTo(new Point(2, 5)), 1);
            compare(point.compareTo(new Point(3, 5)), 1);
            compare(point.compareTo(new Point(4, 5)), 1);
            compare(point.compareTo(new Point(5, 5)), 0);
            compare(point.compareTo(new Point(6, 5)), -1);

            compare(point.compareTo(new Point(0, 6)), -1);
            compare(point.compareTo(new Point(1, 6)), -1);
            compare(point.compareTo(new Point(2, 6)), -1);
            compare(point.compareTo(new Point(3, 6)), -1);
            compare(point.compareTo(new Point(4, 6)), -1);
            compare(point.compareTo(new Point(5, 6)), -1);
            compare(point.compareTo(new Point(6, 6)), -1);
        }

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    public static void test_slopeTo() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Point point = new Point(5, 5);
        
        compare(point.slopeTo(new Point(10, 10)), 1.0);
        compare(point.slopeTo(new Point(10, 15)), 2.0);
        compare(point.slopeTo(new Point(10, 20)), 3.0);

        compare(point.slopeTo(new Point(5, 5)), Double.NEGATIVE_INFINITY);

        compare(point.slopeTo(new Point(5, 10)), Double.POSITIVE_INFINITY);
        compare(point.slopeTo(new Point(5, 11)), Double.POSITIVE_INFINITY);
        compare(point.slopeTo(new Point(5, 14)), Double.POSITIVE_INFINITY);

        compare(point.slopeTo(new Point(10, 5)), 0.0);
        compare(point.slopeTo(new Point(12, 5)), 0.0);
        compare(point.slopeTo(new Point(13, 5)), 0.0);

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

}