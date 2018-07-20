package com.progrema.algo.studyinout;

import edu.princeton.cs.algs4.StdOut;

import com.progrema.algo.Logger;

import edu.princeton.cs.algs4.StdIn;

public class Average {

    public static void testInOut() {
        
        // Average the numbers on StdIn.
        double sum = 0.0;
        int cnt = 0;
        while (!StdIn.isEmpty()) {  
            // Read a number and cumulate the sum.
            sum += StdIn.readDouble();
            Logger.print("" + sum);
            cnt++; 
        }
        double avg = sum / cnt;
        StdOut.printf("Average is %.5f\n", avg);
    }

}