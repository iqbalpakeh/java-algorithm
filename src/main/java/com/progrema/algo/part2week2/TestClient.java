/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week2;

import com.progrema.algo.UnitTest;

import edu.princeton.cs.algs4.Picture;
import java.lang.Math;

public class TestClient extends UnitTest {

    public static void main(String[] args) {        
        // testPicture(args);
        testEnergy(args);
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
     */
    public static void testEnergy(String[] args) {
        startTest(new Object(){}.getClass().getEnclosingMethod().getName());
        Picture picture = new Picture(args[0]);
        SeamCarver seamCarver = new SeamCarver(picture);
        UnitTest.compare(seamCarver.energy(1, 2), Math.sqrt(52024));
        UnitTest.compare(seamCarver.energy(1, 1), Math.sqrt(52225));
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }
}