package com.progrema.algo.sorting;

public class Selection extends Sorting {

    public static void sort(Comparable[] a) { 
        // Sort a[] into increasing order.
        int N = a.length; // array length
        for (int i = 0; i < N; i++) { 
            // Exchange a[i] with smallest entry in a[i+1...N).
            int min = i; // index of minimal entr.
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }

    public static void test(String[] input) { 
        sort(input);
        assert isSorted(input);
        show(input);
    }

}