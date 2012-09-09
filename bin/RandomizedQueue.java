/*----------------------------------p-------------------------------------------
 * Author:        Mark Johnson
 * Written:       9/4/2012
 * Last Updated:  9/5/2012
 *
 * Compilation:   javac RandomizedQueue.java
 * Execution:     java RandomizedQueue
 *
 * Defines a RandomizedQueue type, with specified operations.
 *---------------------------------------------------------------------------*/

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int size;
    private int first;
    private int last;

    /**************************************************************************
     * Method for default RandomizedQueue construction.
     * Constructs an empty randomized queue
     * Casts from array of Object to array of Item.
     * Causes warning when compiling with -Xlint:unchecked.
     *************************************************************************/
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[2];
        size = 0;
        first = 0;
        last = 0;
    }

    /**************************************************************************
     * Method that says whether the collection is empty.
     * Is the queue empty?
     *************************************************************************/
    public boolean isEmpty() {
        return size == 0;
    }

    /**************************************************************************
     * Method for returning size of the collection.
     * Return the number of items on the queue.
     *************************************************************************/
    public int size() {
        return size;
    }

    /**************************************************************************
     * Method which adds an item to the collection.
     * Complexity: constant amortized time required
     * Must use space proportional to current number of items.
     * Add the item.
     * Throw a NullPointerException if the client attempts to add a null item.
     *************************************************************************/
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        // TODO : unimplemented
        randomQueue[last] = item;
        last++;
        size++;
    }

    /**************************************************************************
     * Method which returns and removes a random item from the collection.
     * Item must be chosen uniformly random from all items in the collection.
     * Complexity: constant amortized time required
     * Must use space proportional to current number of items.
     * Delete and return a random item.
     * Throw a NoSuchElementExcpetion if the client attempts to dequeue an
     * item from an empty randomized queue.
     *************************************************************************/
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // TODO : unimplemented
	//        Item removedItem = randomQueue[last-1];
	//        randomQueue[last-1] = null;
	//        last--;
	//        size--;
	//        return removedItem;
        return null;
        
    }

    /**************************************************************************
     * Method which returns a random item.
     * Complexity: constant amortized time required
     * Must use space proportional to current number of items.
     * Return (but do not delete) a random item.
     * Throw a NoSuchElementException if the client attempts to sample an
     * item from an empty randomized queue.
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
     * Return an independent iterator over items in random order
     *************************************************************************/
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**************************************************************************
     * Inner Iterator class.
     * Iterator construction in time linear in the number of items.
     * Operations next() and hasNext() in constant worst-case time.
     * May use a linear amount of extra space per iterator.
     * Each iterator must maintain its own random order.
     *************************************************************************/
    private class RandomizedQueueIterator implements Iterator<Item> {

        public RandomizedQueueIterator() {
        }
        /*********************************************************************
         * Throw an NoSuchElementException if the client calls the next
         * method in the iterator and there are no more items to return.
         ********************************************************************/
        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            return null;
        }

        public boolean hasNext() {
            return false;
        }
        /**********************************************************************
         * Throw an UnsupportedOperationException if the client calls the
         * remove method in the iterator.
         *********************************************************************/
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

        // Test enqueue
        StdOut.print("Test enqueue method: ");
        RandomizedQueue<String> testRQueue5 = new RandomizedQueue<String>();
        testRQueue5.enqueue("Test enqueue method");
        if (testRQueue5.size() == 1) {
            StdOut.println("passed");
        } else {
            StdOut.println("FAILED");
        }

        // Test dequeue
        StdOut.print("Test dequeue method: ");
        RandomizedQueue<String> testRQueue6 = new RandomizedQueue<String>();
        testRQueue6.enqueue("Test dequeue method");
        String testDeque = testRQueue6.dequeue();
        if (testDeque.equals("Test dequeue method") && testRQueue6.size == 0) {
            StdOut.println("passed");
        } else {
            StdOut.println("FAILED");
        }
    }
}