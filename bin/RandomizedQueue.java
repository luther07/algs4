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
     *************************************************************************/
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        // TODO : unimplemented
    }

    /**************************************************************************
     * Method which returns and removes a random item from the collection.
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
}