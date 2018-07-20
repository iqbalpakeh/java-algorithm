package com.progrema.algo.weektwo;

import com.progrema.algo.Logger;
import com.progrema.algo.UnitTest;

public class DequeTest extends UnitTest {

    public static void main(String[] args) {

        test_addFirst();
        test_addLast();
        test_removeLast();

        debug_1();
        debug_2();
        debug_3();
        debug_4();
    }

    public static void test_removeLast() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();  
        deque.addFirst(1);
        compare(deque.removeLast(), 1);
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);
        compare(deque.removeLast(), 0);
        compare(deque.removeLast(), 1);
        compare(deque.removeLast(), 2);

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());        
    }

    public static void test_addLast() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();   
        compare("" + deque.isEmpty(), "true");

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        compare(deque.removeFirst(), 1); 
        compare(deque.removeFirst(), 2); 
        compare(deque.removeFirst(), 3); 
        compare(deque.removeFirst(), 4); 
        compare(deque.removeFirst(), 5);  
        compare("" + deque.isEmpty(), "true");     

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        compare(deque.removeLast(), 5);
        compare(deque.removeLast(), 4);
        compare(deque.removeLast(), 3);
        compare(deque.removeLast(), 2); 
        compare(deque.removeLast(), 1);
        compare("" + deque.isEmpty(), "true");

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());        
    }

    public static void test_addFirst() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();   
        compare("" + deque.isEmpty(), "true");

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        compare(deque.removeFirst(), 5); 
        compare(deque.removeFirst(), 4); 
        compare(deque.removeFirst(), 3); 
        compare(deque.removeFirst(), 2); 
        compare(deque.removeFirst(), 1);  
        compare("" + deque.isEmpty(), "true");     

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        compare(deque.removeLast(), 1);
        compare(deque.removeLast(), 2);
        compare(deque.removeLast(), 3);
        compare(deque.removeLast(), 4); 
        compare(deque.removeLast(), 5);
        compare("" + deque.isEmpty(), "true");

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public static void debug_4() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();        
        deque.isEmpty();   
        deque.addLast(0);  
        deque.addLast(1);
        deque.addLast(2);
        Logger.print("last = " + deque.removeLast() + "\n");
        Logger.print("isEmpty = " + deque.isEmpty() + "\n"); 
        Logger.print("last = " + deque.removeLast() + "\n");

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public static void debug_3() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();        
        deque.isEmpty();     
        deque.addFirst(1);
        deque.addFirst(2);
        Logger.print("last = " + deque.removeLast() + "\n"); 
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);
        Logger.print("last = " + deque.removeLast() + "\n"); 

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public static void debug_2() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        Logger.print("val = " + deque.removeLast() + "\n");
        deque.addFirst(0);
        Logger.print("val = " + deque.removeLast() + "\n");
        deque.addFirst(2);
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(3);
        Logger.print("val = " + deque.removeLast() + "\n");

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public static void debug_1() {

        startTest(new Object(){}.getClass().getEnclosingMethod().getName());

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);

        for (int i : deque) {
            Logger.print("val = " + i + "\n");    
        }

        Logger.print("val = " + deque.removeLast());

        endTest(new Object(){}.getClass().getEnclosingMethod().getName());
    }        

}