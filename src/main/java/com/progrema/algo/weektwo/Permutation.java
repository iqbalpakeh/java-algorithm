package com.progrema.algo.weektwo;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    // How to use jar file:
    // $> java -jar build/libs/exec.jar 1 < input.txt

    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        
        while (!StdIn.isEmpty()) {            
            queue.enqueue(StdIn.readString());
        }

        int iteration = Integer.parseInt(args[0]);
        for (int i = 0; i < iteration; i++) {
            StdOut.print(queue.dequeue() + "\n");
        }
        
    }

}