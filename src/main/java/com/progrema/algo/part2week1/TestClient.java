/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week1;

import com.progrema.algo.UnitTest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestClient extends UnitTest {

    public static void main(String[] args) {        
        // wordnet_testapi(args);
        // sap_testuserinput(args);
        // sap_testapi(args);
        // outcast_test(args);
        coursera_debug_1(args);
    }

    @SuppressWarnings("unused")
    private static void coursera_debug_1(String[] args) {

        /**
         * Test 2: check distance() with all noun pairs
         * synsets = synsets15.txt; hypernyms = hypernyms15Path.txt
         * - failed before processing first pair
         * 
         * java.lang.IllegalArgumentException: vertex 14 is not between 0 and 13
         * 
         * edu.princeton.cs.algs4.Digraph.validateVertex(Digraph.java:162)
         * edu.princeton.cs.algs4.Digraph.addEdge(Digraph.java:171)
         * WordNet.<init>(WordNet.java:79)
         * TestWordNet.checkAllDistance(TestWordNet.java:80)
         * TestWordNet.test2(TestWordNet.java:133)
         * TestWordNet.main(TestWordNet.java:820)
         */

        // gradle run -Pargs="input/part2week1/synsets15.txt input/part2week1/hypernyms15Path.txt"

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        WordNet wn = new WordNet(args[0], args[1]);

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 

    }

    @SuppressWarnings("unused")
    private static void outcast_test(String[] args) {

        // gradle run -Pargs="input/part2week1/synsets.txt input/part2week1/hypernyms.txt input/part2week1/outcast5.txt input/part2week1/outcast8.txt input/part2we1/outcast11.txt"

        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

    @SuppressWarnings("unused")
    private static void wordnet_testapi(String[] args) {

        // gradle run -Pargs="input/part2week1/synsets.txt input/part2week1/hypernyms.txt"

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        WordNet wn = new WordNet(args[0], args[1]);
        // for (String noun : wn.nouns()) System.out.println(noun);
    
        UnitTest.print("Check living_dead is a listed noun");
        UnitTest.compare("" + wn.isNoun("living_dead"), "true");

        UnitTest.print("Check distance and ancestor of worm and bird");
        UnitTest.compare(wn.distance("worm", "bird"), 5);
        UnitTest.compare(wn.sap("worm", "bird"), "animal animate_being beast brute creature fauna");
        
        UnitTest.print("Check ancestor of individual and edible_fruit");
        UnitTest.compare(wn.sap("individual", "edible_fruit"), "physical_entity");

        UnitTest.print("Check distance of white_marlin and mileage");
        UnitTest.compare(wn.distance("white_marlin", "mileage"), 23);

        UnitTest.print("Check distance of Black_Plague and black_marlin");
        UnitTest.compare(wn.distance("Black_Plague", "black_marlin"), 33);

        UnitTest.print("Check distance of American_water_spaniel and histology");
        UnitTest.compare(wn.distance("American_water_spaniel", "histology"), 27);

        UnitTest.print("Check distance of American_water_spaniel and histology");
        UnitTest.compare(wn.distance("Brown_Swiss", "barrel_roll"), 29);

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
        
    }

    @SuppressWarnings("unused")
    private static void sap_testuserinput(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();          
            int length   = sap.length(v, w);  
            int ancestor = sap.ancestor(v, w);            
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

    @SuppressWarnings("unused")
    private static void sap_testapi(String[] args) {

        // gradle run -Pargs="input/part2week1/digraph1.txt"

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        {
            int v = 3;
            int w = 11;
            UnitTest.print("v = " + v + ", w = " + w);

            UnitTest.print("Check length");
            UnitTest.compare(sap.length(v, w), 4);

            UnitTest.print("Check common ancestor");
            UnitTest.compare(sap.ancestor(v, w), 1);
        }

        {
            int v = 9;
            int w = 12;
            UnitTest.print("v = " + v + ", w = " + w);

            UnitTest.print("Check length");
            UnitTest.compare(sap.length(v, w), 3);

            UnitTest.print("Check common ancestor");
            UnitTest.compare(sap.ancestor(v, w), 5);            
        }

        {
            int v = 7;
            int w = 2;
            UnitTest.print("v = " + v + ", w = " + w);

            UnitTest.print("Check length");
            UnitTest.compare(sap.length(v, w), 4);

            UnitTest.print("Check common ancestor");
            UnitTest.compare(sap.ancestor(v, w), 0);            
        }        

        {
            int v = 1;
            int w = 6;
            UnitTest.print("v = " + v + ", w = " + w);

            UnitTest.print("Check length");
            UnitTest.compare(sap.length(v, w), -1);

            UnitTest.print("Check common ancestor");
            UnitTest.compare(sap.ancestor(v, w), -1);            
        }        

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

}