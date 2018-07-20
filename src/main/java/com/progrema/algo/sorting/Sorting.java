package com.progrema.algo.sorting;

import edu.princeton.cs.algs4.*;

public class Sorting {

    public static boolean less(Comparable v, Comparable w) { 
        return v.compareTo(w) < 0; 
    }

    public static void exch(Comparable[] a, int i, int j) { 
        Comparable t = a[i]; 
        a[i] = a[j]; 
        a[j] = t; 
    }

    public static void show(Comparable[] a) { 
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) { 
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

}