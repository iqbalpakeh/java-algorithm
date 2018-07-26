/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week2;

import com.progrema.algo.UnitTest;
import com.progrema.algo.part2week2.SeamCarver.Pixel;

import edu.princeton.cs.algs4.Picture;
import java.lang.Math;

public class TestClient extends UnitTest {

    public static void main(String[] args) {        
        // testPicture(args);
        // testEnergy(args);
        testPixelsAdj(args);
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
}