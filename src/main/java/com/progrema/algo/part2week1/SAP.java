/*********************************************************************
 * Copyright (C) 2018 MPAKEH
 **********************************************************************/

package com.progrema.algo.part2week1;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

/**
 * Shortest Ancestral Path (SAP)
 * 
 * An ancestral path between two vertices v and w in a digraph is 
 * a directed path from v to a common ancestor x, together with 
 * a directed path from w to the same ancestor x. A shortest ancestral 
 * path is an ancestral path of minimum total length. 
 */
public final class SAP {

    /**
     * Debugging flag
     */
    private static final boolean DEBUG = false;

    /**
     * Debugging filter
     */
    private static final String FILTER = "@";

    /**
     * Not found value
     */
    private final int NOT_FOUND = -1;

    /**
     * Immutable Reverse Digraph
     */
    private final Digraph mRG;

    /**
     * Constructor takes a digraph (not necessarily a DAG)
     */ 
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        mRG = G.reverse();
    }

    /**
     * Length of shortest ancestral path between v and w; -1 if no such path
     */ 
    public int length(int v, int w) {

        validateVertex(v);
        validateVertex(w);        

        int minLength = Integer.MAX_VALUE;
        int commAncestor = NOT_FOUND;

        for (int i=0; i<mRG.V(); i++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(mRG, i);
            if (bfs.hasPathTo(v) && bfs.hasPathTo(w)) {
                int pathLength = bfs.distTo(v) + bfs.distTo(w);
                if (pathLength < minLength) {
                    minLength = pathLength;
                    commAncestor = i;
                }
            }
        }     

        if (commAncestor == NOT_FOUND) return NOT_FOUND;
        else return minLength;
    }

    /**
     * A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */ 
    public int ancestor(int v, int w) {
        
        validateVertex(v);
        validateVertex(w);

        int minLength = Integer.MAX_VALUE;
        int commAncestor = NOT_FOUND;

        for (int i=0; i<mRG.V(); i++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(mRG, i);
            if (bfs.hasPathTo(v) && bfs.hasPathTo(w)) {
                int pathLength = bfs.distTo(v) + bfs.distTo(w);
                if (pathLength < minLength) {
                    minLength = pathLength;
                    commAncestor = i;
                }
            }
        }

        return commAncestor;
    }

    /**
     * Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */ 
    public int length(Iterable<Integer> vv, Iterable<Integer> ww) {

        if ((vv == null) || (ww == null)) throw new IllegalArgumentException();

        int minLength = Integer.MAX_VALUE;
        int commAncestor = NOT_FOUND;

        for (int v : vv) {
            validateVertex(v);
            for (int w : ww) {
                validateVertex(w);
                int pathLength = length(v, w);
                debug("@length", "pathLength = " + pathLength + " v = " + v + " w = " + w);
                if ((pathLength != NOT_FOUND) && (pathLength < minLength)) {
                    minLength = pathLength;
                    commAncestor = ancestor(v, w);
                    debug("@length", "commAncestor = " + commAncestor);
                }
            }
        }

        if (commAncestor == NOT_FOUND) return NOT_FOUND;
        else return minLength;
    }

    /**
     * A common ancestor that participates in shortest ancestral path; -1 if no such path
     */ 
    public int ancestor(Iterable<Integer> vvv, Iterable<Integer> www) {

        if ((vvv == null) || (www == null)) throw new IllegalArgumentException();

        int minLength = Integer.MAX_VALUE;
        int commAncestor = NOT_FOUND;

        for (int v : vvv) {
            validateVertex(v);
            for (int w : www) {
                validateVertex(w);
                int pathLength = length(v, w);
                debug("@ancestor", "pathLength = " + pathLength + " v = " + v + " w = " + w);
                if ((pathLength != NOT_FOUND) && (pathLength < minLength)) {
                    minLength = pathLength;
                    commAncestor = ancestor(v, w);
                }
            }
        }

        return commAncestor;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= mRG.V()) throw new IllegalArgumentException();
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