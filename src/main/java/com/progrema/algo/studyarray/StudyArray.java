package com.progrema.algo.studyarray;

import com.progrema.algo.Logger;

public class StudyArray {

    public static void test_inverse2DArrays() {

        int[][] b = {{1,2,3}, {4,5,6}, {7,8,9}};

        showArray(b);

        Logger.print("\nafter");

        showArray(inverse2DArrays(b));

    }

    public static int[][] inverse2DArrays(int[][] a) {
        
        int M = a.length;
        int N = a[0].length;

        int[][] aa = new int[N][M];

        for (int n=0; n<N; n++) {
            for (int m=0; m<M; m++) {
                aa[n][m] = a[m][n]; 
            }
        }

        return aa;
    }

    public static void showArray(int[][] a) {
        for (int row=0; row<a.length; row++) {
            for (int col=0; col<a[0].length; col++) {
                Logger.print("" + a[row][col]);
            }
        }
    }

    public static void reverseElement() {

        int[] a = {1, 2, 3, 4, 5};
        int N = a.length;

        for (int i = 0; i < N/2; i++) {
            
           int temp = a[i];
           a[i] = a[N-1-i];
           a[N-i-1] = temp;

           Logger.debug("temp = " + temp);
        }

        for (int i = 0; i < N; i++) {
            Logger.print("" + a[i]);
        }

    }

}