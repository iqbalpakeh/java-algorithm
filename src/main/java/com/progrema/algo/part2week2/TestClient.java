/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week2;

import com.progrema.algo.UnitTest;
import com.progrema.algo.part2week2.SeamCarver.Pixel;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;

public class TestClient extends UnitTest {

    public static void main(String[] args) {        
        // testPicture(args);
        // testEnergy(args);
        // testPixelsAdj(args);
        testPrintSeam(args);
    }

    /**
     * To test the constructor of Picture class. This test should 
     * show the picture of chameleon.png.
     * 
     * $ gradle run -Pargs="input/part2week2/chameleon.png"
     */
    public static void testPicture(String[] args) {
        startTest(new Object(){}.getClass().getEnclosingMethod().getName());
        Picture picture = new Picture(args[0]);
        picture.show();
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    /**
     * To test energy api.
     * 
     * $ gradle run -Pargs="input/part2week2/3x4.png"
     * 
     */
    public static void testEnergy(String[] args) {
        startTest(new Object(){}.getClass().getEnclosingMethod().getName());
        Picture picture = new Picture(args[0]);
        SeamCarver seamCarver = new SeamCarver(picture);
        UnitTest.compare(seamCarver.energy(1, 2), Math.sqrt(52024));
        UnitTest.compare(seamCarver.energy(1, 1), Math.sqrt(52225));
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    /**
     * (Whitebox Testing)
     * To test adjacently list of pixel. 
     * 
     * This test require manual observation as it's 
     * not possible to retrieve size and data from 
     * iterable object.
     * 
     * $ gradle run -Pargs="input/part2week2/6x5.png"
     * 
     */
    public static void testPixelsAdj(String[] args) {
        startTest(new Object(){}.getClass().getEnclosingMethod().getName());
        Picture picture = new Picture(args[0]);
        SeamCarver seamCarver = new SeamCarver(picture);      

        {
            Pixel pixel = Pixel.newInstance(0, 0);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }

        {
            Pixel pixel = Pixel.newInstance(5, 0);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }        

        {
            Pixel pixel = Pixel.newInstance(5, 4);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }   
        
        {
            Pixel pixel = Pixel.newInstance(0, 4);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }        

        {
            Pixel pixel = Pixel.newInstance(0, 2);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        } 

        {
            Pixel pixel = Pixel.newInstance(3, 0);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }         

        {
            Pixel pixel = Pixel.newInstance(5, 2);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }        
        
        {
            Pixel pixel = Pixel.newInstance(2, 4);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }    

        {
            Pixel pixel = Pixel.newInstance(3, 2);
            UnitTest.print("\nCheck Pixel " + pixel);
            for (Pixel pix : seamCarver.debug_adj(pixel)) {
                UnitTest.print(pix.toString());
            }
        }            

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    private static final boolean HORIZONTAL   = true;
    private static final boolean VERTICAL     = false;

    private static void printSeam(SeamCarver carver, int[] seam, boolean direction) {
        double totalSeamEnergy = 0.0;

        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction == HORIZONTAL && row == seam[col]) ||
                    (direction == VERTICAL   && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                StdOut.printf("%7.2f%s ", energy, marker);
            }
            StdOut.println();
        }                
        // StdOut.println();
        StdOut.printf("Total energy = %f\n", totalSeamEnergy);
        StdOut.println();
        StdOut.println();
    }

    /**
     * Test from Coursera
     * 
     * $ gradle run -Pargs="input/part2week2/6x5.png"
     */
    public static void testPrintSeam(String[] args) {

        Picture picture = new Picture(args[0]);
        StdOut.printf("%s (%d-by-%d image)\n", args[0], picture.width(), picture.height());
        StdOut.println();
        StdOut.println("The table gives the dual-gradient energies of each pixel.");
        StdOut.println("The asterisks denote a minimum energy vertical or horizontal seam.");
        StdOut.println();

        SeamCarver carver = new SeamCarver(picture);
        
        StdOut.printf("Vertical seam: { ");
        int[] verticalSeam = carver.findVerticalSeam();
        for (int x : verticalSeam)
            StdOut.print(x + " ");
        StdOut.println("}");
        printSeam(carver, verticalSeam, VERTICAL);

        // StdOut.printf("Horizontal seam: { ");
        // int[] horizontalSeam = carver.findHorizontalSeam();
        // for (int y : horizontalSeam)
        //     StdOut.print(y + " ");
        // StdOut.println("}");
        // printSeam(carver, horizontalSeam, HORIZONTAL);
    }
}