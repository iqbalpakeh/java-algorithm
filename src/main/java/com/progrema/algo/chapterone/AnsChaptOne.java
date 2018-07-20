package com.progrema.algo.chapterone;

import com.progrema.algo.Logger;

public class AnsChaptOne {

    public static class Node {
        // why have to use static?
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void test_node() {

        Node head = new Node(1); 
        Node node_2 = new Node(2);
        Node node_3 = new Node(3);
        Node node_4 = new Node(4);

        head.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;

        Logger.print("\nOriginal linkedlist:");
        iterateNode(head);

        Logger.print("\nAdd new node at start:");
        head = addHeadNode(head, new Node(5));
        iterateNode(head);

        Logger.print("\nAdd new node at tail:");
        head = addTailNode(head, new Node(6));
        iterateNode(head);

        Logger.print("\nRemove node at tail:");
        head = removeTailNode(head);
        iterateNode(head);

        Logger.print("\nRemove node at head:");
        head = removeHeadNode(head);
        iterateNode(head);
    }

    public static Node deleteNodeAtPos(Node head, int position) {
        
        return head;
    }

    public static Node removeTailNode(Node head) {
        Node currentNode = head;        
        Node lastNode = head;
        while(currentNode.next != null) {
            lastNode = currentNode;
            currentNode = currentNode.next;
        }
        lastNode.next = null;
        return head;
    }

    public static Node addTailNode(Node head, Node newNode) {
        Node currentNode = head;        
        while(currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        return head;
    }

    public static Node removeHeadNode(Node head) {
        return head.next;
    }

    public static Node addHeadNode(Node head, Node newNode) {
        Node oldHead = head;
        head = newNode;
        head.next = oldHead;
        return head;
    }

    public static void iterateNode(Node node) {
        while(node != null) {
            Logger.print("" + node.data);
            node = node.next;
        }
    }

    /**
     * Write a version of BinarySearch that uses the recursive rank() given on page
     * 25 and traces the method calls. Each time the recursive method is called, print the argument
     * values lo and hi, indented by the depth of the recursion. Hint: Add an argument
     * to the recursive method that keeps track of the depth.
     */
    public static void test_ans_1_1_22() {
        int[] a = {1, 3, 5, 7, 9, 10, 11, 12, 13, 66, 100};
        Logger.print("index 66 in a[] is " + rank(66, a));
    }

    private static int rank(int key, int[] a) { 
        showArray(a);
        return rank(key, a, 0, a.length - 1, 0); 
    }

    private static int rank(int key, int[] a, int lo, int hi, int depth) { 
        
        // Index of key in a[], if present, is not smaller than lo
        // and not larger than hi.

        Logger.print("\ndepth: " + depth);
        Logger.print("lo: " + lo);
        Logger.print("hi: " + hi);

        if (lo > hi) 
            return -1;

        int mid = lo + (hi - lo) / 2;
        Logger.print("mid: " + mid);

        if (key < a[mid]) 
            return rank(key, a, lo, mid - 1, ++depth);
        else if (key > a[mid]) 
            return rank(key, a, mid + 1, hi, ++depth);
        else 
            return mid;
    }

    public static void test_ans_1_1_20() {
        Logger.print("factorial of 5 is " + factorial(5));
    }

    private static int ln(int n) {
        //???
        return 0;
    }

    private static int factorial(int n) {
        if (n==1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }

    /**
     * Print the value of exR1(6)
     */
    public static void test_ans_1_1_16() {
        Logger.print(exR1(6));
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }

    /**
     * Write a static method histogram() that takes an array a[] of int values and
     * an integer M as arguments and returns an array of length M whose ith entry is the number
     * of times the integer i appeared in the argument array. If the values in a[] are all
     * between 0 and M–1, the sum of the values in the returned array should be equal to a.length.
     */    
    public static void test_ans_1_1_15() {

        int[] arr = {1, 2, 3, 4, 5};
        Logger.print("\n Data: ");
        showArray(arr);
        Logger.print("\n Result: ");
        showArray(histogram(arr, 10));

        int[] arr2 = {0, 2, 3, 4, 1};
        Logger.print("\n Data: ");
        showArray(arr2);
        Logger.print("\n Result: ");
        showArray(histogram(arr2, 10));
    }

    public static int[] histogram(int[] a, int M) {
        int[] arr = new int[M];
        for (int i=0; i<M; i++) {
            arr[i] = calculateAppeared(a, i);
        }
        return arr;
    }

    private static int calculateAppeared(int[] a, int i) {
        int count=0;
        for (int j=0; j<a.length; j++) {
            if(a[j]==i) count++;
        }
        return count;
    }

    /**
     * Write a code fragment that prints the contents of a two-dimensional boolean
     * array, using * to represent true and a space to represent false. Include row and column numbers. 
     */    
    public static void test_ans_1_1_11() {
        
        boolean a[][] = {{false, false}, {false, true}, {true, false}, {true, true}};
        ans_1_1_11(a);

        boolean b[][] = {{false, false, false}, {false, false, true}, {false, true, false}, {false, true, true},
                         {true, false, false}, {true, false, true}, {true, true, false}, {true, true, true}};
        ans_1_1_11(b);
    }
    
    public static void ans_1_1_11(boolean[][] a) {

        String s = "";

        int colLen = a.length; 
        int rowLen = a[0].length;

        for (int i=0; i<colLen; i++) {
            for (int j=0; j<rowLen; j++){
                s = "col = " + i + ", row = " + j + ", val = ";
                if (a[i][j] == true) {
                    s += "*";
                } else {
                    s += "_";
                }
                Logger.print(s);
            }
        }
    }

    public static void test_ans_1_1_9() {

        Logger.print("N = 5, bin = " + ans_1_1_9(5));
        Logger.print("N = 10, bin = " + ans_1_1_9(10));
        Logger.print("N = 15, bin = " + ans_1_1_9(15));
    }

    public static String ans_1_1_9(int N) {

        String s = "";
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        return s;
    }

    public static void showArray(int[] a) {
        for (int row=0; row<a.length; row++) {
                Logger.print("" + a[row]);
        }
    }

}