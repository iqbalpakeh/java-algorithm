/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfive;

import com.progrema.algo.UnitTest;
import com.progrema.algo.weekthree.Point;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSETTest extends UnitTest {

    public static void main(String[] args) {
        // test_sample();
        // test_double_input();
        test_range();
    }

    private static void test_range() {

        // Test 5: insert n random points and check range() for n random query rectangles
        // * 1000 random rectangles and points in a 65536-by-65536 grid
        //     DBG @range : point = (0.994293212890625, 6.103515625E-5)
        //     DBG @range : point = (0.569854736328125, 0.0031280517578125)
        //   - failed on trial 2 of 1000
        //   - rectangle query        = [0.569854736228125, 0.569854736428125] x [0.0031280516578125, 0.0031280518578125]
        //   - student   range() size = 2
        //   - reference range() size = 1

        Point2D A = new Point2D(0.994293212890625, 6.103515625E-5);
        Point2D B = new Point2D(0.569854736328125, 0.0031280517578125);

        RectHV RQ = new RectHV(0.419952392478125, 3.814696265625E-4, 0.419952392678125, 3.814698265625E-4);

        StdDraw.setPenRadius(0.01);        
        StdDraw.setPenColor(StdDraw.BLACK);                        
        StdDraw.point(A.x(), A.y());

        StdDraw.setPenRadius(0.01);        
        StdDraw.setPenColor(StdDraw.RED); 
        StdDraw.point(B.x(), B.y());

        StdDraw.setPenRadius(0.01);        
        StdDraw.setPenColor(StdDraw.GREEN);  
        RQ.draw();

        PointSET tree = new PointSET();
        tree.insert(A);
        tree.insert(B);
        tree.range(RQ);
    }

    private static void test_double_input() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Point2D point_a = new Point2D(0.1, 0.4);
        Point2D point_b = new Point2D(0.1, 0.4);
        Point2D point_c = new Point2D(0.419952392478125, 3.814696265625E-4);

        PointSET tree = new PointSET();
        UnitTest.print("Check size");
        UnitTest.compare(tree.size(), 0);

        tree.insert(point_a);
        tree.insert(point_b);

        UnitTest.print("Check size");
        UnitTest.compare(tree.size(), 1);

        tree.insert(point_c);

        RectHV rect = new RectHV(0.419952392478125, 3.814696265625E-4, 0.419952392678125, 3.814698265625E-4);
        for (Point2D p: tree.range(rect)) //[0.419952392478125, 0.419952392678125] x [3.814696265625E-4, 3.814698265625E-4]
        {
            UnitTest.print(p.toString());
        }

        rect.draw();

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 

    }

    private static void test_sample() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Point2D point_a = new Point2D(0.0, 0.0);
        Point2D point_b = new Point2D(0.1, 0.4);
        Point2D point_c = new Point2D(0.6, 0.5);
        Point2D point_d = new Point2D(0.5, 0.5);
        Point2D point_e = new Point2D(0.4, 0.4);
        Point2D point_f = new Point2D(0.5, 0.3);
        Point2D point_g = new Point2D(0.8, 0.5);
        Point2D point_h = new Point2D(0.6, 0.6);        
        Point2D point_i = new Point2D(0.4, 0.3);
        Point2D point_j = new Point2D(0.8, 0.6);
        Point2D point_k = new Point2D(0.4, 0.6);
        Point2D point_l = new Point2D(0.8, 0.3);
        Point2D point_m = new Point2D(0.9, 0.9);
 
        RectHV rect = new RectHV(0.4, 0.3, 0.8, 0.6);
        // rect.draw();

        PointSET set = new PointSET();
        set.insert(point_a);
        set.insert(point_b);
        set.insert(point_c);
        set.insert(point_d);
        set.insert(point_e);
        set.insert(point_f);
        set.insert(point_g);
        set.insert(point_h);
        set.insert(point_i);
        set.insert(point_j);
        set.insert(point_k);
        set.insert(point_l);

        UnitTest.print("Check distance from rect to point_a");
        compare(rect.distanceTo(point_a), 0.5);

        UnitTest.print("Check distance from rect to point_b");
        compare(rect.distanceTo(point_b), 0.30000000000000004);

        UnitTest.print("Check distance from rect to point_c");
        compare(rect.distanceTo(point_c), 0.0);

        UnitTest.print("Check distance from rect to point_d");
        compare(rect.distanceTo(point_d), 0.0);

        UnitTest.print("Check rect contains point_a");
        compare("" + rect.contains(point_a), "false");

        UnitTest.print("Check rect contains point_b");
        compare("" + rect.contains(point_b), "false");

        UnitTest.print("Check rect contains point_c");
        compare("" + rect.contains(point_c), "true");

        UnitTest.print("Check rect contains point_d");
        compare("" + rect.contains(point_d), "true");
        
        UnitTest.print("Show set range from rect");
        for (Point2D point: set.range(rect)) {
            UnitTest.print(point.toString());
        }

        UnitTest.print("\nShow nearest set point from external point");
        UnitTest.print(set.nearest(point_m).toString());

        // set.draw();

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 

    }

}