/*-----------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       9/4/2012
 * Last Updated:  9/4/2012
 *
 * Compilation:   javac RandomizedQueue.java
 * Execution:     java RandomizedQueue
 *
 * Defines a Deque type, with specified operations.
 *---------------------------------------------------------------------------*/

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;

    /**************************************************************************
     * Method for default RandomizedQueue construction.
     *************************************************************************/
    public RandomizedQueue() {
        size = 0;
    }

    /**************************************************************************
     * Method that says whether the collection is empty.
     *************************************************************************/
    public boolean isEmpty() {
        return true;
    }

    /**************************************************************************
     * Method for returning size of the collection.
     *************************************************************************/
    public int size() {
        return size;
    }

    /**************************************************************************
     * Method which adds an item to the collection.
     * Complexity: constant amortized time required
     *************************************************************************/
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        // TODO : unimplemented
    }

    /**************************************************************************
     * Method which returns and removes a random item from the collection.
     * Item must be chosen uniformly random from all items in the collection.
     * Complexity: constant amortized time required
     *************************************************************************/
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // TODO : unimplemented
        return null;
    }

    /**************************************************************************
     * Method which returns a random item.
     * Complexity: constant amortized time required
     *************************************************************************/
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // TODO : unimplemented
        return null;
    }

    /**************************************************************************
     * Method for creating an iterator on the collection.
     *************************************************************************/
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        public Item next() {
            return null;
        }

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        StdOut.println("Hello, World!");

        // Test RandomizedQueue constructor
        StdOut.print("Test RandomizedQueue constructor: ");
        RandomizedQueue<String> testRQueue1 = new RandomizedQueue<String>();
        if (!testRQueue1.equals(null)) {
            StdOut.println("passed");
	} else {
            StdOut.println("FAILED");
        }

        // Test exceptions 1
        StdOut.print("Test exception 1: ");
        RandomizedQueue<String> testRQueue2 = new RandomizedQueue<String>();
        String test = null;
        try {
            testRQueue2.enqueue(test);
        } catch (java.lang.NullPointerException e) {
            StdOut.println("passed");
        }

        // Test exceptions 2
        StdOut.print("Test exception 2: ");
        RandomizedQueue<String> testRQueue3 = new RandomizedQueue<String>();
        try {
            testRQueue3.dequeue();
        } catch (java.util.NoSuchElementException e) {
            StdOut.println("passed");
        }

        // Test exceptions 3
        StdOut.print("Test exception 3: ");
        RandomizedQueue<String> testRQueue4 = new RandomizedQueue<String>();
        try {
            testRQueue4.sample();
        } catch (java.util.NoSuchElementException e) {
            StdOut.println("passed");
        }
    }
}