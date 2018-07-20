package com.progrema.algo.studysearch;

import com.progrema.algo.Logger;

public class BinarySearch {

    public static void testBinarySearch() {

        int[] arr = {1, 3, 5, 7, 9, 11, 13, 14, 15};

        Logger.print("array = {1, 3, 5, 7, 9, 11, 13, 14, 15}");
        Logger.print("index of 5 = " + rank(5, arr));
        Logger.print("index of 9 = " + rank(9, arr));
        Logger.print("index of 14 = " + rank(14, arr));
        Logger.print("index of 12 = " + rank(12, arr));
    }

    private static int rank(int key, int[] a) {  

        return rank(key, a, 0, a.length - 1);  
    }

    private static int rank(int key, int[] a, int lo, int hi) {  

        // Index of key in a[], if present, is not smaller than lo
        // and not larger than hi.

        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;
        
        if (key < a[mid]) 
            return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) 
            return rank(key, a, mid + 1, hi);
        else 
            return mid;
    }

}