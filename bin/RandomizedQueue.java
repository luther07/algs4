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