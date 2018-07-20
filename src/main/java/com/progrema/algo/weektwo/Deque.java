package com.progrema.algo.weektwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    /**
     * Point to the first Node
     */
    private Node mFirst; 
    
    /**
     * Point to the last Node
     */
    private Node mLast;

    /**
     * Size of the object in Deque
     */
    private int mSize;

    /**
     * Node class contain of data and pointer
     */
    private class Node {
        Item item;
        Node next;
    }

    /**
     * Iterator class for implementing Iterable
     */
    private class DequeIterator implements Iterator<Item> {

        private Node mCurrent = mFirst;

        public boolean hasNext() {            
            return mCurrent != null;
        }

        public Item next() {
            if (mCurrent != null) {
                Item item = mCurrent.item;
                mCurrent = mCurrent.next;
                return item;            
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * construct an empty deque
     */
    public Deque() {
        mFirst = null;
        mLast = null;
        mSize = 0;
    }
    
    /**
     * is the deque empty
     */
    public boolean isEmpty() {
        return mSize == 0;
    }               
    
    /**
     * Number of items on deque
     */
    public int size() {
        return mSize;
    }                        

    /**
     * add the item to the front
     * 
     * @param item to add at first pointer
     */
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = mFirst;
        mFirst = new Node();
        mFirst.item = item;
        mFirst.next = oldFirst;
        
        if (isEmpty()) {
            mLast = mFirst;
        } 

        debug_showlist();

        mSize++;
    }

    /**
     * add the item to the end
     * 
     * @param item to add at first pointer 
     */
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            mLast = new Node();
            mLast.item = item;
            mLast.next = null;
            mFirst = mLast;
        } else {
            Node oldLast = mLast;
            mLast = new Node();
            mLast.item = item;
            mLast.next = null;
            oldLast.next = mLast;
        }

        debug_showlist();

        mSize++;
    }
    
    /**
     * remove and return the item from the front
     */
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = mFirst.item;
        mFirst = mFirst.next;
        mSize--;

        if (isEmpty()) { 
            mLast = null;
        }

        debug_showlist();

        return item;
    }

    /**
     * remove and return the item from the end
     */
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = mLast.item;
        mLast = mFirst;
        
        if (mSize == 1) {
            mLast = null;
            mFirst = null;
        } else {
            Node beforeLast = mLast;
            while (mLast.next != null) {
                beforeLast = mLast;
                mLast = mLast.next;
            }
            mLast = beforeLast;
            mLast.next = null;
        }

        debug_showlist();

        mSize--;
        return item;
    }
    
    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Debugging code showing list
     */
    private void debug_showlist() {
        Node current = mFirst;        
        while (current != null) {
            // System.out.print(current.item + " - ");
            current = current.next;
        }
        // System.out.print("null\n\n");
    }
    
}

