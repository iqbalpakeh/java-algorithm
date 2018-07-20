package com.progrema.algo;

public class UnitTest {

    private static int mErrorNumber;

    public static boolean compare(double actual, double expected) {
        if (actual != expected) {
            Logger.print("ERROR :>\n");
            Logger.print("  Actual   :> " + actual + "\n");
            Logger.print("  Expected :> " + expected + "\n"); 
            Logger.print("\n");                       
            mErrorNumber++;
            return false;
        } else {
            Logger.print("PASS :> " + actual + "\n");
            Logger.print("\n");
            return true;
        }
    }

    public static boolean compare(int actual, int expected) {
        if (actual != expected) {
            Logger.print("ERROR :>\n");
            Logger.print("  Actual   :> " + actual + "\n");
            Logger.print("  Expected :> " + expected + "\n"); 
            Logger.print("\n");                       
            mErrorNumber++;
            return false;
        } else {
            Logger.print("PASS :> " + actual + "\n");
            Logger.print("\n");
            return true;
        }
    }

    public static boolean compare(String actual, String expected) {
        if (!actual.equals(expected)) {
            Logger.print("ERROR\n");
            Logger.print("  Actual   :> " + actual + "\n");
            Logger.print("  Expected :> " + expected + "\n");
            mErrorNumber++;
            return false;
        } else {
            Logger.print("PASS :> " + actual + "\n");
            Logger.print("\n");
            return true;
        }
    }

    public static void error() {
        mErrorNumber++;
    }

    public static void resetError() {
        mErrorNumber = 0; 
    }

    public static boolean hasError() {
        return mErrorNumber != 0;
    }

    public static void showResult() {
        if (hasError()) {
            Logger.print("TEST RESULT : FAILED\n\n");
        } else {
            Logger.print("\nTEST RESULT : PASS\n");
        }
    }

    public static void startTest(String testName) {
        Logger.print("================================================\n");
        Logger.print("TEST NAME : " + testName + "\n\n");
        resetError();
    }

    public static void endTest(String testName) {
        showResult();
        Logger.print("================================================\n\n");        
    }

    public static void print(String message) {
        Logger.println(message);
    }

}