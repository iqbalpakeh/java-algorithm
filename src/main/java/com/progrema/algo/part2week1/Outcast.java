/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week1;

public class Outcast {
    
    /**
     * Debugging flag
     */
    private static final boolean DEBUG = false;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@";

    /**
     * Immutable WordNet object
     */
    private final WordNet mWordNet;
    
    /**
     * Constructor takes a WordNet object
     */
    public Outcast(WordNet wordnet) {
        mWordNet = wordnet;
    }
 
    /**
     * Given an array of WordNet nouns, return an outcast
     */
    public String outcast(String[] nouns) {
        int maxDistance = 0;
        int maxIndex = -1;
        for (int i=0; i<nouns.length; i++) {
            int distance = calculateDistance(nouns, i);
            if (distance > maxDistance) {
                maxDistance = distance;
                maxIndex = i;
            }
        }
        return nouns[maxIndex];
    }

    private int calculateDistance(String[] nouns, int index) {
        int total = 0;
        for (int i=0; i<nouns.length; i++) {
            if (i != index) {
                int distance = mWordNet.distance(nouns[i], nouns[index]);
                debug("@calculateDistance", "distance of " + nouns[i] + " and " + nouns[index] + " = " + distance);
                if (distance != -1) total += distance;
            }
        }
        return total;
    }

    /**
     * Debugging function
     */
    @SuppressWarnings("unused")
    private static void debug(String filter, String message) {
        if (DEBUG && (FILTER.endsWith(filter) || FILTER.equals("@"))) {
            System.out.println("DBG " + filter + " : " + message);
        }
    }  

}