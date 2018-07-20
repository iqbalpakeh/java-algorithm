package com.progrema.algo;

/**
 * Class responsible to show message to user. This class also handle 
 * debug log if needed. 
 */
public class Logger {

    public static String mFilter = "";

    /**
     * Variable controls show or hide the debug log.
     */
    public static boolean mDebug = false;

    /**
     * Show only debug function if debug mode is active.
     * 
     * @param message of debug log
     */
    public static void debug(String message) {
        if (mDebug)
            print(message);
    }

    /**
     * Use this to only show debug message. This function can be deactivate by 
     * setting 
     * 
     * @param tag to select which debug message to show
     * @param message to show to user
     */
    public static void debug(String tag, String message) {
        if (mDebug == true && tag.equals(mFilter)) {
            System.out.println("\nDBG " + tag + " => " + message);
        }
    }  

    /**
     * Always show message to user with new line.
     * 
     * @param message to show to user
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Always show message to user.
     * 
     * @param message to show to user
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     * Activate debug mode if needed
     * 
     * @param debugMode set to true if user want to show all debug mode. 
     *                  Otherwise, set to false.
     */
    public static void setDebugMode(boolean debugMode) {
        mDebug = debugMode;
    }

    /**
     * Show content of array
     * 
     * @param a input array
     */
    public static void showArray(String[] a) {
        for (int row=0; row<a.length; row++) {
                Logger.print(a[row] + " ");
        }
        Logger.print("\n");
    }

}