/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.weekfive;

import com.progrema.algo.UnitTest;

import java.awt.Font;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeTest extends UnitTest {

    public static void main(String[] args) {
        // test_insert_contains();
        // test_visual_kdtree();
        // test_drawing_api();
        // test_draw_tree();
        // test_read_input(args);
        // test_range();
        // test_double_input();

        // test_coursera_1();
        // test_coursera_2();
        test_coursera_3();
    }

    private static void test_coursera_3() {

        //     - sequence of points inserted: 
        //     A  0.40625 0.25
        //     B  0.96875 0.65625
        //     C  0.75 0.375
        //     D  0.625 0.53125
        //     E  0.6875 1.0
        //     F  0.65625 0.0625
        //     G  0.9375 0.875
        //     H  0.375 0.28125
        //     I  0.5 0.8125
        //     J  0.3125 0.6875
        //     K  0.09375 0.09375
        //     L  0.28125 0.78125
        //     M  0.25 0.21875
        //     N  0.53125 0.125
        //     O  0.125 0.15625
        //     P  0.71875 0.0
        //     Q  0.46875 0.46875
        //     R  0.34375 0.34375
        //     S  0.0 0.84375
        //     T  0.21875 0.9375
        //   - query point                   = (0.84375, 0.5)
        //   - student   nearest()           = (0.75, 0.375)
        //   - reference nearest()           = (0.75, 0.375)
        //   - student   distanceSquaredTo() = 0.0244140625
        //   - reference distanceSquaredTo() = 0.0244140625
        //   - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
        //     A B C D F P E 
        //   - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
        //     A B C D F P 

        Point2D A = new Point2D(0.40625, 0.25);
        Point2D B = new Point2D(0.96875, 0.65625);
        Point2D C = new Point2D(0.75, 0.375);
        Point2D D = new Point2D(0.625, 0.53125);
        Point2D E = new Point2D(0.6875, 1.0);
        Point2D F = new Point2D(0.65625, 0.0625);
        Point2D G = new Point2D(0.9375, 0.875);
        Point2D H = new Point2D(0.375, 0.28125);
        Point2D I = new Point2D(0.5, 0.8125);
        Point2D J = new Point2D(0.3125, 0.6875);
        Point2D K = new Point2D(0.09375, 0.09375);
        Point2D L = new Point2D(0.28125, 0.78125);
        Point2D M = new Point2D(0.25, 0.21875);
        Point2D N = new Point2D(0.53125, 0.125);
        Point2D O = new Point2D(0.125, 0.15625);
        Point2D P = new Point2D(0.71875, 0.0);
        Point2D Q = new Point2D(0.46875, 0.46875);
        Point2D R = new Point2D(0.34375, 0.34375);
        Point2D S = new Point2D(0.0, 0.84375);
        Point2D T = new Point2D(0.21875, 0.9375);
        Point2D QP = new Point2D(0.84375, 0.5);

        StdDraw.setPenRadius();
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.textLeft(A.x(), A.y(), "A " + A);
        StdDraw.textLeft(B.x(), B.y(), "B " + B);
        StdDraw.textLeft(C.x(), C.y(), "C " + C);
        StdDraw.textLeft(D.x(), D.y(), "D " + D);
        StdDraw.textLeft(E.x(), E.y(), "E " + E);
        StdDraw.textLeft(F.x(), F.y(), "F " + F);
        // StdDraw.textLeft(G.x(), G.y(), "G " + G);
        // StdDraw.textLeft(H.x(), H.y(), "H " + H);
        // StdDraw.textLeft(I.x(), I.y(), "I " + I);
        // StdDraw.textLeft(J.x(), J.y(), "J " + J);
        // StdDraw.textLeft(K.x(), K.y(), "K " + K);
        // StdDraw.textLeft(L.x(), L.y(), "L " + L);
        // StdDraw.textLeft(M.x(), M.y(), "M " + M);
        // StdDraw.textLeft(N.x(), N.y(), "N " + N);
        // StdDraw.textLeft(O.x(), O.y(), "O " + O);
        StdDraw.textLeft(P.x(), P.y(), "P " + P);
        // StdDraw.textLeft(Q.x(), Q.y(), "Q " + Q);
        // StdDraw.textLeft(R.x(), R.y(), "R " + R);
        // StdDraw.textLeft(S.x(), S.y(), "S " + S);
        // StdDraw.textLeft(T.x(), T.y(), "T " + T);
        StdDraw.textLeft(QP.x(), QP.y(), "QP " + QP);

        KdTree tree = new KdTree();
        tree.insert(A);
        tree.insert(B);
        tree.insert(C);
        tree.insert(D);
        tree.insert(E);
        tree.insert(F);
        tree.insert(G);
        tree.insert(H);
        tree.insert(I);
        tree.insert(J);
        tree.insert(K);
        tree.insert(L);
        tree.insert(M);
        tree.insert(N);
        tree.insert(O);
        tree.insert(P);
        tree.insert(Q);
        tree.insert(R);
        tree.insert(S);
        tree.insert(T);

        tree.draw();

        UnitTest.print(tree.nearest(QP).toString());

    }

    private static void test_coursera_2() {

        //   Test 6a: insert points from file; check nearest() with random query points
        //            and check traversal of kd-tree
        //   * input5.txt
        //     - failed on trial 1 of 1000
        //     - performs incorrect traversal of kd-tree during call to nearest()
        //     - sequence of points inserted: 
        //       A  0.7 0.2
        //       B  0.5 0.4
        //       C  0.2 0.3
        //       D  0.4 0.7
        //       E  0.9 0.6
        //     - query point                   = (0.09, 0.47)
        //     - student   nearest()           = (0.2, 0.3)
        //     - reference nearest()           = (0.2, 0.3)
        //     - student   distanceSquaredTo() = 0.041
        //     - reference distanceSquaredTo() = 0.041
        //     - student sequence of kd-tree nodes involved in calls to distanceSquaredTo():
        //       A E B D C 
        //     - reference sequence of kd-tree nodes involved in calls to distanceSquaredTo():
        //       A B D C 

        //   * input10.txt
        //     - failed on trial 1 of 1000
        //     - performs incorrect traversal of kd-tree during call to nearest()
        //     - do not compute the distance between the query point and the point in a node
        //       if the closest point discovered so far is closer than the distance between
        //       the query point and the rectangle corresponding to the node

        Point2D A = new Point2D(0.7, 0.2);
        Point2D B = new Point2D(0.5, 0.4);
        Point2D C = new Point2D(0.2, 0.3);
        Point2D D = new Point2D(0.4, 0.7);
        Point2D E = new Point2D(0.9, 0.6);
        Point2D Q = new Point2D(0.09, 0.47);

        StdDraw.setPenRadius();
        StdDraw.textLeft(A.x(), A.y(), "A " + A);
        StdDraw.textLeft(B.x(), B.y(), "B " + B);
        StdDraw.textLeft(C.x(), C.y(), "C " + C);
        StdDraw.textLeft(D.x(), D.y(), "D " + D);
        StdDraw.textLeft(E.x(), E.y(), "E " + E);
        StdDraw.textLeft(Q.x(), Q.y(), "Q " + Q);

        KdTree tree = new KdTree();
        tree.insert(A);
        tree.insert(B);
        tree.insert(C);
        tree.insert(D);
        tree.insert(E);
        tree.draw();

        Point2D nearest = tree.nearest(Q);
        UnitTest.compare("" + nearest, "(0.2, 0.3)");
    }

    private static void test_coursera_1() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        /**
         *  10 random distinct points in a 4-by-4 grid
         *  - failed on trial 7 of 10000
         *  - sequence of points inserted: 
         *    A  0.0 0.0
         *    B  0.75 0.5
         *    C  1.0 0.25
         *    D  0.75 0.25
         *    E  0.25 0.75
         *    F  0.5 0.0
         *    G  0.25 0.25
         *    H  0.25 0.5
         *    I  0.0 1.0
         *    J  0.5 0.5
         *    - query point                   = (0.0, 0.5)
         *    - student   nearest()           = (0.25, 0.75)
         *    - reference nearest()           = (0.25, 0.5)
         *    - student   distanceSquaredTo() = 0.125
         *    - reference distanceSquaredTo() = 0.0625
         */

        Point2D point_A = new Point2D(0.0, 0.0);
        Point2D point_B = new Point2D(0.75, 0.5);
        Point2D point_C = new Point2D(1.0, 0.25);
        Point2D point_D = new Point2D(0.75, 0.25);
        Point2D point_E = new Point2D(0.25, 0.75);
        Point2D point_F = new Point2D(0.5, 0.0);
        Point2D point_G = new Point2D(0.25, 0.25);
        Point2D point_H = new Point2D(0.25, 0.5);
        Point2D point_I = new Point2D(0.0, 1.0);
        Point2D point_J = new Point2D(0.5, 0.5);
        Point2D point_query = new Point2D(0.0, 0.5);

        KdTree tree = new KdTree();
        tree.insert(point_A);
        tree.insert(point_B);
        tree.insert(point_C);
        tree.insert(point_D);
        tree.insert(point_E);
        tree.insert(point_F);
        tree.insert(point_G);
        tree.insert(point_H);
        tree.insert(point_I);
        tree.insert(point_J);        
        tree.draw();

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.GREEN);  
        point_query.draw();        

        Point2D nearest = tree.nearest(point_query);
        UnitTest.print("Check with nearest point:" + point_query);
        UnitTest.compare(nearest.toString(), "(0.25, 0.5)");

        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.MAGENTA);                
        StdDraw.line(nearest.x(), nearest.y(), point_query.x(), point_query.y());

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static void test_double_input() {

        Point2D point_a = new Point2D(0.1, 0.4);
        Point2D point_b = new Point2D(0.1, 0.4);

        KdTree tree = new KdTree();
        tree.insert(point_a);
        tree.insert(point_b);

        UnitTest.print("size = " + tree.size());
    }

    private static void test_range() {

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
        Point2D point_n = new Point2D(0.2, 0.2);
        Point2D point_o = new Point2D(0.1, 0.1);
                
        KdTree tree = new KdTree();
        tree.insert(point_a);
        tree.insert(point_b);
        tree.insert(point_c);
        tree.insert(point_d);
        tree.insert(point_e);
        tree.insert(point_f);
        tree.insert(point_g);
        tree.insert(point_h);
        tree.insert(point_i);
        tree.insert(point_j);
        tree.insert(point_k);
        tree.insert(point_l);
        tree.insert(point_m);        
        tree.insert(point_n);                      
        tree.draw();

        {
            RectHV rect = new RectHV(0.4, 0.3, 0.8, 0.6);
            StdDraw.setPenColor(StdDraw.GREEN); 
            rect.draw();
    
            UnitTest.print("Manual check for every point inside rect: " + rect);
            for (Point2D point: tree.range(rect)) {
                UnitTest.print(point.toString());
            }
        }

        UnitTest.print("\n");

        {
            RectHV rect = new RectHV(0.8, 0.1, 0.9, 0.2);
            StdDraw.setPenColor(StdDraw.GREEN); 
            rect.draw();
    
            UnitTest.print("Manual check for every point inside rect: " + rect);
            for (Point2D point: tree.range(rect)) {
                UnitTest.print(point.toString());
            }
        }        

        UnitTest.print("\n");

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.GREEN);  
        point_o.draw();

        UnitTest.print("nearest point from point_o: " + point_o);
        UnitTest.print(tree.nearest(point_o).toString());
    }

    private static void test_read_input(String[] args) {

        // initialize the data structures from file
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        // draw the points
        kdtree.draw();

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.GREEN);          
        Point2D point_o = new Point2D(0.81, 0.30);
        point_o.draw();

        UnitTest.print("nearest point from point_o: " + point_o);

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.RED);          
        Point2D nearest = kdtree.nearest(point_o); 
        nearest.draw();

        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.MAGENTA);                
        StdDraw.line(nearest.x(), nearest.y(), point_o.x(), point_o.y());

        UnitTest.print(nearest.toString());
    }

    private static void test_draw_tree() {

        Point2D point_a = new Point2D(0.7, 0.2);
        Point2D point_b = new Point2D(0.5, 0.4);
        Point2D point_c = new Point2D(0.2, 0.3);
        Point2D point_d = new Point2D(0.4, 0.7);
        Point2D point_e = new Point2D(0.9, 0.6);

        KdTree tree = new KdTree();
        tree.insert(point_a);
        tree.insert(point_b);
        tree.insert(point_c);
        tree.insert(point_d);
        tree.insert(point_e);
        tree.draw();

    }

    private static void test_drawing_api() {

        // Draw red line from (0.0, 0.0) to (1.0, 1.0) with default size
        StdDraw.setPenRadius();        
        StdDraw.setPenColor(StdDraw.RED);        
        StdDraw.line(0.0, 0.0, 1.0, 1.0);

        // Draw blue line from (0.0, 1.0) to (1.0, 0.0) with default size
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);                
        StdDraw.line(0.0, 1.0, 1.0, 0.0);

        // Draw black dot at (0.5, 0.5) with size 0.01
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);                
        StdDraw.point(0.5, 0.5);

    }

    private static void test_visual_kdtree() {
        
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);

        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    kdtree.insert(p);
                    StdDraw.clear();
                    kdtree.draw();
                    StdDraw.show();
                }
            }
            StdDraw.pause(20);
        }
    }

    private static void test_insert_contains() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Point2D point_a = new Point2D(0.7, 0.2);
        Point2D point_b = new Point2D(0.5, 0.4);
        Point2D point_c = new Point2D(0.2, 0.3);
        Point2D point_d = new Point2D(0.4, 0.7);
        Point2D point_e = new Point2D(0.9, 0.6);

        KdTree tree = new KdTree();

        // A

        UnitTest.print("Insert point_a to tree: " + point_a);
        tree.insert(point_a);

        UnitTest.print("Check point_a is in the tree: " + point_a);
        compare("" + tree.contains(point_a), "true");

        UnitTest.print("Check size");
        compare(tree.size(), 1);

        // B

        UnitTest.print("Check point_b is NOT in the tree");
        compare("" + tree.contains(point_b), "false");

        UnitTest.print("Insert point_b to tree: " + point_b);
        tree.insert(point_b);

        UnitTest.print("Check point_b is in the tree");
        compare("" + tree.contains(point_b), "true");

        UnitTest.print("Check size");
        compare(tree.size(), 2);

        // C
        
        UnitTest.print("Check point_c is NOT in the tree: " + point_c);
        compare("" + tree.contains(point_c), "false");

        UnitTest.print("Insert point_c to tree: " + point_c);
        tree.insert(point_c);

        UnitTest.print("Check point_c is in the tree: " + point_c);
        compare("" + tree.contains(point_c), "true");        

        UnitTest.print("Check size");
        compare(tree.size(), 3);

        // D

        UnitTest.print("Check point_d is NOT in the tree");
        compare("" + tree.contains(point_d), "false");

        UnitTest.print("Insert point_d to tree: " + point_d);
        tree.insert(point_d);

        UnitTest.print("Check point_d is in the tree");
        compare("" + tree.contains(point_d), "true");

        UnitTest.print("Check size");
        compare(tree.size(), 4);

        // E

        UnitTest.print("Check point_e is NOT in the tree");
        compare("" + tree.contains(point_e), "false");

        UnitTest.print("Insert point_e to tree: " + point_e);
        tree.insert(point_e);

        UnitTest.print("Check point_e is in the tree");
        compare("" + tree.contains(point_e), "true");

        UnitTest.print("Check size");
        compare(tree.size(), 5);

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }
}