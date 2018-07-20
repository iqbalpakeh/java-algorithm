package com.progrema.algo.weekone; // Remove this before submission!!

import edu.princeton.cs.algs4.StdRandom;

import java.lang.Math;

public class PercolationStats {

    private double mFractionAccumulator;

    private double[] mFractionArrays;

    private double mTrialsNumber;

    private double mMean;

    private double mStdDev;

    /**
     * perform trials independent experiments on an n-by-n grid
     */ 
    public PercolationStats(int n, int trials) {

        // System.out.println("n, trials : " + n + ", " + trials);

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        mFractionAccumulator = 0;
        mFractionArrays = new double[trials];
        mTrialsNumber = trials;

        for (int i=0; i<trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            double fraction = percolation.numberOfOpenSites() / ((double)n*n);
            mFractionAccumulator += fraction;
            mFractionArrays[i] = fraction;
        }

        // System.out.print("mean = " + mean() + "\n");
        // System.out.print("sdev = " + stddev() + "\n");
        // System.out.print("confLo = " + confidenceLo() + "\n");
        // System.out.print("confHi = " + confidenceHi() + "\n");
        
    } 

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        mMean = mFractionAccumulator / mTrialsNumber;
        return mMean;
    }                          

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        double result = 0.0;
        mean();
        for (int i=0; i < mTrialsNumber; i++) {
            result += (mFractionArrays[i]-mMean)*(mFractionArrays[i]-mMean);
        }
        mStdDev = Math.sqrt(result/(mTrialsNumber-1)); 
        return mStdDev;
    }

    /**
     * low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        stddev();
        return mMean - (1.96*mStdDev/Math.sqrt(mTrialsNumber));
    }      
                
    /**
     * high endpoint of 95% confidence interval
     */ 
    public double confidenceHi() {
        stddev();
        return mMean + (1.96*mStdDev/Math.sqrt(mTrialsNumber));
    }                  

    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);
            PercolationStats percolationStats = new PercolationStats(n, trials);
        } else {
            System.out.println("Wrong arguments!");
        }
    }

} 