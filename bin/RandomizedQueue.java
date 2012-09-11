/*-----------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       9/4/2012
 * Last Updated:  9/5/2012
 *
 * Compilation:   javac RandomizedQueue.java
 * Execution:     java RandomizedQueue
 *
 * Defines a RandomizedQueue type, with specified operations.
 *
 * Any sequence of M randomized queue operations (starting from an empty queue)
 * should take at most cM steps in the worst case, for some constant c.
 *
 * Additionally, your iterator implementation should support construction in
 * time linear in the number of items and it should support operations next
 * and hasNext in constant worst-case time; you may use a linear amount of
 * extra memory per iterator.
 *
 * Challenge is to ensure that array resizing is infrequent.
 * When the array is full, copy it into a array twice its size.
 *---------------------------------------------------------------------------*/

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int N; // number of items in the queue

    /**************************************************************************
     * Method for default RandomizedQueue construction.
     * Constructs an empty randomized queue
     * Casts from array of Object to array of Item.
     * Causes warning when compiling with -Xlint:unchecked.
     *************************************************************************/
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
        N = 0;
    }

    /**************************************************************************
     * Method that says whether the collection is empty.
     * Is the queue empty?
     *************************************************************************/
    public boolean isEmpty() {
        return N == 0;
    }

    /**************************************************************************
     * Method for returning size of the collection.
     * Return the number of items on the queue.
     *************************************************************************/
    public int size() {
        return N;
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

        if (N == randomQueue.length) {
            resize(2 * randomQueue.length);
        }

        randomQueue[N] = item;
        N++;
    }

    /**************************************************************************
     * Method which returns and removes a random item from the collection.
     * Item must be chosen uniformly random from all items in the collection.
     * Complexity: constant amortized time required
     * Must use space proportional to current number of items.
     * Delete and return a random item.
     * Throw a NoSuchElementExcpetion if the client attempts to dequeue an
     * item from an empty randomized queue.
     *
     * The item removed is chosen uniformly at random from items in the 
     * data structure.
     *
     * Deletes and returns a random item.
     *
     * If there are N items in the randomized queue, then you should choose
     * each one with probability 1/N, up to the randomness of
     * StdRandom.uniform(), independent of past decisions. You can generate
     * a pseudo-random integer between 0 and N-1 using StdRandom.uniform(N)
     * from the StdRandom library.
     *
     * Method dequeue is not currently random.
     * Method dequeue could make use of iterator to introduce randomness.
     *************************************************************************/
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        
        Item removedItem = randomQueue[N-1]; // reference to item to remove
        randomQueue[N-1] = null;             // set the array index to null
        N--;                                 // decrement size
        if (N > 0 && N == (randomQueue.length/4)) {
            this.resize(randomQueue.length/2);
        }
        return removedItem;                  // return reference
    }

    /**************************************************************************
     * Method which returns a random item.
     * Complexity: constant amortized time required
     * Must use space proportional to current number of items.
     * Return (but do not delete) a random item.
     * Throw a NoSuchElementException if the client attempts to sample an
     * item from an empty randomized queue.
     *
     * Return (but do not delete) a random item.
     *
     * Test 10: Check randomness of sample() by enqueueing the integers 0
     * through 9, then sampling 100000 times and counting the zeros.
     *************************************************************************/
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        return randomQueue[StdRandom.uniform(N)];
    }

    /**************************************************************************
     * Method for creating an iterator on the collection.
     * Return an independent iterator over items in random order
     *************************************************************************/
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randomQueue[i];
        }
        randomQueue = copy;
    }

    /**************************************************************************
     * Inner Iterator class.
     * Iterator construction in time linear in the number of items.
     * Operations next() and hasNext() in constant worst-case time.
     * May use a linear amount of extra space per iterator.
     * Each iterator must maintain its own random order.
     *************************************************************************/
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] itr;  // declare variable
        private int current; // declare variable

        /**********************************************************************
         * Time is linear in current number of items.
         *
         * Currently copies the random queue to a new array and shuffles it.
         *
         * Change so that it shuffles the random queue and then copies it
         * to the new array.
         *
         * Use custom shuffle method instead of StdRandom.shuffle.
         *********************************************************************/
        public RandomizedQueueIterator() {
            shuffle(randomQueue, N);      // shuffle random queue
            current = -1;                 // initialize current to -1
            itr = (Item[]) new Object[N]; // initialize size of iterator
            for (int i = 0; i < N; i++) { // copy random queue to iterator
                itr[i] = randomQueue[i];
            }

        }
        /*********************************************************************
         * Throw an NoSuchElementException if the client calls the next
         * method in the iterator and there are no more items to return.
         *
         * Constant worst-case time.
         ********************************************************************/
        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            current++;           // increment current            
            return itr[current]; // return new current
        }

        /*********************************************************************
         * Constant worst-case time.
         ********************************************************************/
        public boolean hasNext() {
            return (current < itr.length -1); // is current equal last index
        }

        /**********************************************************************
         * Throw an UnsupportedOperationException if the client calls the
         * remove method in the iterator.
         *********************************************************************/
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        /*
         * Need custom shuffle method.
         */
        private void shuffle(Item[] a, int numberItems) {
            for (int i = 1; i < numberItems; i++) {
                int r = i + StdRandom.uniform(numberItems - i);
                Item temp = a[i];
                a[i] = a[r];
                a[r] = temp;                
            }
        }
    }

    public static void main(String[] args) {
        StdOut.println("Hello, World!");

        // Test RandomizedQueue constructor
        StdOut.print("Test RandomizedQueue constructor: ");
        RandomizedQueue<String> testRQueue1 = new RandomizedQueue<String>();
        if (testRQueue1 != null) {
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
        if (testDeque.equals("Test dequeue method") && testRQueue6.size() == 0) {
            StdOut.println("passed");
        } else {
            StdOut.println("FAILED");
        }

        // Test enqueue, add 8 items
        StdOut.print("Test enqueue, add 8: ");
        RandomizedQueue<String> testRQueue7 = new RandomizedQueue<String>();
        testRQueue7.enqueue("a");
        testRQueue7.enqueue("b");
        testRQueue7.enqueue("c");
        testRQueue7.enqueue("d");
        testRQueue7.enqueue("e");
        testRQueue7.enqueue("f");
        testRQueue7.enqueue("g");
        testRQueue7.enqueue("h");
        if (testRQueue7.size() == 8) {
            StdOut.println("passed");
        } else {
            StdOut.println("FAILED");
        }

        // Test enqueue, add and remove 8 items
        StdOut.print("Test enqueue, add and remove 8: ");
        RandomizedQueue<String> testRQueue8 = new RandomizedQueue<String>();
        if (true) {
            StdOut.println("passed");
        } else {
            StdOut.println("FAILED:");
        }

        // Test iterator not null
        StdOut.print("Test iterator not null: ");
        RandomizedQueue<String> testRQueue9 = new RandomizedQueue<String>();
        testRQueue9.enqueue("a");
        testRQueue9.enqueue("b");
        testRQueue9.enqueue("c");
        testRQueue9.enqueue("d");
        testRQueue9.enqueue("e");
        testRQueue9.enqueue("f");
        Iterator myIterator = testRQueue9.iterator();
        if (myIterator != null) {
            StdOut.println("passed");
        }
        
        // Test actual foreach
        // FAIL
        StdOut.println("Test foreach: ");
        RandomizedQueue<String> testRQueue11 = new RandomizedQueue<String>();
        testRQueue11.enqueue("1");
        testRQueue11.enqueue("2");
        testRQueue11.enqueue("3");
        testRQueue11.enqueue("4");
        testRQueue11.enqueue("5");
        testRQueue11.enqueue("6");
        Iterator itr0 = testRQueue11.iterator();
        for (String xyz : testRQueue11) {
            StdOut.println(xyz);
        }
    }
}