/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;

public final class WordNet {

    /**
     * Debugging flag
     */
    private static final boolean DEBUG = false;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@";

    /**
     * Symbol Table of synset data iterated by noun
     */
    private final ST<String, Stack<Integer>> mNouns;

    /**
     * Symbol Table of synset data iterated by id
     */
    private final ST<Integer, String> mIDs;

    /**
     * SAP object to process SAP query
     */
    private final SAP mSAP;
    
    /**
     * Constructor takes the name of the two input files
     */ 
    public WordNet(String synsets, String hypernyms) {

        if ((synsets == null) || (hypernyms == null)) throw new IllegalArgumentException();

        In synsetIn = new In(synsets);  
        In hypernymsIn = new In(hypernyms);
        
        // Process synsetIn to build mNouns
        mNouns = new ST<>();
        mIDs = new ST<>();
        String[] synsetLines = synsetIn.readAllLines();
        int vertexNumber = synsetLines.length;
        for (int j=0; j<vertexNumber; j++) {
            String[] strings = synsetLines[j].split(",");
            String[] nouns = strings[1].split(" ");
            Integer id = Integer.parseInt(strings[0]);
            for (int i=0; i<nouns.length; i++) {
                mIDs.put(id, strings[1]);
                if (mNouns.contains(nouns[i])) {
                    Stack<Integer> stack = mNouns.get(nouns[i]);
                    stack.push(id);
                    mNouns.put(nouns[i], stack);
                } else {
                    Stack<Integer> stack = new Stack<>();
                    stack.push(id);
                    mNouns.put(nouns[i], stack);
                }                
            }
        }

        // Process hypernymsIn to build mSAP
        String[] hypernymsLines = hypernymsIn.readAllLines();
        Digraph G = new Digraph(vertexNumber);
        for (int i=0; i<hypernymsLines.length; i++) {
            String[] datas = hypernymsLines[i].split(",");
            for (int j=1; j<datas.length; j++) {
                int head = Integer.parseInt(datas[0]);
                int tail = Integer.parseInt(datas[j]);
                G.addEdge(head, tail);
            }
        }
        mSAP = new SAP(G);
    }

    /**
     * Returns all WordNet nouns
     */ 
    public Iterable<String> nouns() {
        return mNouns;
    }

    /**
     * Is the word a WordNet noun?
     */ 
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return mNouns.contains(word);
    }

    /**
     * Distance between nounA and nounB (defined below)
     */ 
    public int distance(String nounA, String nounB) {
        
        if ((nounA == null) || (nounB == null)) throw new IllegalArgumentException();
        
        debug("@distance", "ids of " + nounA + " = ");
        for (int id : mNouns.get(nounA)) debug("@distance", "" + id);
        
        debug("@distance", "ids of " + nounB + " = ");
        for (int id : mNouns.get(nounB)) debug("@distance", "" + id);
        
        return mSAP.length(mNouns.get(nounA), mNouns.get(nounB));
    }

    /** 
     * A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     */
    public String sap(String nounA, String nounB) { 
        
        if ((nounA == null) || (nounB == null)) throw new IllegalArgumentException();
        
        debug("@sap", "ids of " + nounA + " = ");
        for (int id : mNouns.get(nounA)) debug("@sap", "" + id);
        
        debug("@sap", "ids of " + nounB + " = ");
        for (int id : mNouns.get(nounB)) debug("@sap", "" + id);
        
        int ancestor = mSAP.ancestor(mNouns.get(nounA), mNouns.get(nounB));

        return mIDs.get(ancestor);
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