package com.progrema.algo.weektwo;

import com.progrema.algo.UnitTest;

public class RandomizedQueueTest extends UnitTest {

    public static void main(String[] args) {
        unit_test_enqueue_dequeue();
        unit_test_iterator();
        unit_test_courseera_1();
        unit_test_courseera_2();
    }

    public static void unit_test_iterator() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        compare(queue.isEmpty() + "", "true");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        for (int i : queue) {
            System.out.println("i = " + i);
        }
        compare(queue.isEmpty() + "", "true");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        for (int i : queue) {
            System.out.println("i = " + i);
        }
        compare(queue.isEmpty() + "", "true");        

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    public static void unit_test_courseera_1() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(5);
        rq.enqueue(15);
        compare(rq.dequeue(1, true), 15);
        rq.enqueue(18);
        rq.enqueue(31);
        rq.enqueue(48);
        rq.dequeue();

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    public static void unit_test_courseera_2() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(5);
        rq.enqueue(10);
        rq.enqueue(15);
        compare(rq.dequeue(2, true), 15);
        rq.enqueue(18);
        rq.enqueue(31);
        rq.enqueue(48);
        rq.dequeue();

        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

    public static void unit_test_enqueue_dequeue() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        compare(queue.isEmpty() + "", "true");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        compare(queue.size(), 5);
        compare(queue.isEmpty() + "", "false");
        compare(queue.dequeue(0, true), 1);
        compare(queue.dequeue(0, true), 2);
        compare(queue.dequeue(0, true), 3);
        compare(queue.dequeue(0, true), 4);
        compare(queue.dequeue(0, true), 5);
        compare(queue.size(), 0);
        compare(queue.isEmpty() + "", "true");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        compare(queue.size(), 5);
        compare(queue.isEmpty() + "", "false");        
        compare(queue.dequeue(4, true), 5);
        compare(queue.dequeue(3, true), 4);
        compare(queue.dequeue(2, true), 3);
        compare(queue.dequeue(1, true), 2);
        compare(queue.dequeue(0, true), 1);
        compare(queue.size(), 0);
        compare(queue.isEmpty() + "", "true");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        compare(queue.dequeue(1, true), 2);
        compare(queue.dequeue(0, true), 1);
        compare(queue.dequeue(0, true), 3);
        compare(queue.dequeue(0, true), 4);
        compare(queue.dequeue(0, true), 5);
        compare(queue.isEmpty() + "", "true");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        compare(queue.dequeue(3, true), 4);
        compare(queue.dequeue(0, true), 1);
        compare(queue.dequeue(0, true), 2);
        compare(queue.dequeue(0, true), 3);
        compare(queue.dequeue(0, true), 5);
        compare(queue.isEmpty() + "", "true");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        compare(queue.dequeue(0, false), 1);
        compare(queue.dequeue(1, false), 2);
        compare(queue.dequeue(2, false), 3);
        compare(queue.dequeue(3, false), 4);
        compare(queue.dequeue(4, false), 5);
        compare(queue.size(), 5);
        compare(queue.isEmpty() + "", "false");
        
        endTest(new Object(){}.getClass().getEnclosingMethod().getName()); 
    }

}