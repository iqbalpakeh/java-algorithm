package com.progrema.algo.weektwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue <Item> implements Iterable<Item> {

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
    private class RandomizedQueueIterator implements Iterator<Item> {

        public boolean hasNext() {            
            return isEmpty() == false;
        }

        public Item next() {
            if (isEmpty() == false) {
                return dequeue();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }        
    }

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        mFirst = null;
        mLast = null;
        mSize = 0;
    }                  
    
    /**
     * is the randomized queue empty?
     */
    public boolean isEmpty() {
        return mSize == 0;
    }
    
    /**
     * return the number of items on the randomized queue
     */
    public int size() {
        return mSize;
    }                       
    
    /**
     * add the item
     */
    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldlast = mLast;
        mLast = new Node();
        mLast.item = item;
        mLast.next = null;
        if (isEmpty()) mFirst = mLast;
        else oldlast.next = mLast;        
        mSize++;
        debug_showlist();
    }        
    
    /**
     * Remove and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return dequeue(StdRandom.uniform(size()), true);
    } 

    /**
     * Remove/show item from queue based on position.
     * 
     * @param removePosition is position of node to remove.
     * @param isRemove true to remove node from queue. Otherwise, false.
     */
    public Item dequeue(int removePosition, boolean isRemove) {

        if (removePosition >= size()) {
            throw new NoSuchElementException();
        }

        if (removePosition == 0) {
            
            Item item = mFirst.item;
            if (isRemove == true) {
                mFirst = mFirst.next;            
                mSize--;
                if (isEmpty()) { 
                    mLast = null;
                }
            }
            debug_showlist();
            return item;

        } else {

            int currentPosition = 0;
            removePosition--;
            Node node = mFirst;
            while (currentPosition != removePosition) {                
                node = node.next;            
                currentPosition++;
            }

            // now, currentPosition is pointing to one node before removedNode
            Node removedNode = node.next;
            Node beforeLast = node;

            // System.out.println("node = " + node.item);
            // System.out.println("removedNode = " + removedNode.item);
            // System.out.println("");

            if (isRemove == true) {   
                if (removedNode.next != null) {
                    node.next = removedNode.next;
                    removedNode.next = null;                                 
                } else {
                    mLast = beforeLast;
                    mLast.next = null;
                }
                mSize--;
            }
            debug_showlist();
            return removedNode.item;            

        }
    }
    
    /**
     * return a random item (but do not remove it)
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }        
        return dequeue(StdRandom.uniform(size()), false);
    }
    
    /**
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
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
