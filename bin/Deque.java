/*-----------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/30/2012
 * Last Updated:  9/4/2012
 *
 * Compilation:   javac Deque.java
 * Execution:     java Deque
 *
 * Defines a Deque type, with specified operations.
 *---------------------------------------------------------------------------*/

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    /**************************************************************************
     * Method for default Deque construction.
     *************************************************************************/
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**************************************************************************
     * Method that says if the Deque is empty.
     *************************************************************************/
    public boolean isEmpty() {
        return (size == 0);
    }

    /**************************************************************************
     * Method that returns size.
     *************************************************************************/
    public int size() {
        return size;
    }

    /**************************************************************************
     * Method that adds an item to the front of the Deque.
     * Each deque operation must work in constant worst-case time.
     * Each deque should use space proportional to number of current items.
     *************************************************************************/
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        newNode.previous = null;
        if (this.size() > 0) {
            first.previous = newNode;
        }
        first = newNode;
        if (this.size() == 0) {
            last = newNode;
        }
        size++;
    }

    /**************************************************************************
     * Method that adds an item to the back of the Deque.
     * Each deque operation must work in constant worst-case time.
     * Each deque should use space proportional to number of current items.
     *************************************************************************/
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = last;
        if (this.size() > 0) {
            last.next = newNode;
        }
        last = newNode;
        if (this.size() == 0) {
            first = newNode;
        }
        size++;
    }

    /**************************************************************************
     * Method that removes the item at the front of the Deque.
     * Each deque operation must work in constant worst-case time.
     * Each deque should use space proportional to number of current items.
     ********p*****************************************************************/
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size --;
        return item;
    }

    /**************************************************************************
     * Method that removes the item at the back of the Deque.
     * Each deque operation must work in constant worst-case time.
     * Each deque should use space proportional to number of current items.
     *************************************************************************/
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        size--;
        return item;
    }

    /**************************************************************************
     * Method that returns an iterator on the Deque.
     *************************************************************************/
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**************************************************************************
     * Inner class for iterating over the Deque.
     *************************************************************************/
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        /********************************************************************** 
         * Method that returns the next item and increments the pointer.
         * Must work in constant worst-case time.
         * Must use a constant amount of extra space per iterator.
         *********************************************************************/
        public Item next() {
            if (current == null) {
                return null;
            } else if (current.next == null) {
                return null;
            } else {
                current = current.next;
                return current.item;
            }
        }

        /*********************************************************************
         * Method that says whether or not there exists a next item.
         * Must work in constant worst-case time.
         * Must use a constant amount of extra space per iterator.
         *********************************************************************/
        public boolean hasNext() {
            if (current == null) {
                return false;
            } else {
                return (current.next != null);
            }
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        StdOut.println("Hello, world!");

        // Constructor: test Deque default constructor
        StdOut.print("Deque constructed object not null: ");
        Deque<String> test1Deque = new Deque<String>();
        if (test1Deque != null) {
            StdOut.println("\t\tpassed");
        }
        // Constructor: test Deque default constructor, object has size 0
        StdOut.print("Deque constructed object is size 0: ");
        Deque<String> test2Deque = new Deque<String>();
        if (test2Deque.size() == 0) {
            StdOut.println("\t\tpassed");
        }

        // Test addFirst, test one thing
        StdOut.print("Test addFirst method: ");
        Deque<String> test3Deque = new Deque<String>();
        test3Deque.addFirst("add at the start");
        if (test3Deque.size() == 1) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test addFirst, try to add a null item
        StdOut.print("Test addFirst, try to add null item: ");
        Deque<String> test8Deque = new Deque<String>();
        String test = null;
        try { 
            test8Deque.addFirst(test);
        } catch (java.lang.NullPointerException e) {
            StdOut.println("\t\tpassed: exception caught");
        }

        // Test isEmpty, test if isEmpty is true on default object
        StdOut.print("Test isEmpty method: ");
        Deque<String> test4Deque = new Deque<String>();
        if (test4Deque.isEmpty() == true) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test addLast, test size after method call
        StdOut.print("Test addLast method: ");
        Deque<String> test5Deque = new Deque<String>();
        test5Deque.addLast("add at the end");
        if (test5Deque.size() == 1) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test addLast, try to add a null item
        StdOut.print("Test addLast, try to add null item: ");
        Deque<String> test15Deque = new Deque<String>();
        test = null;
        try {
            test15Deque.addLast(test);
        } catch (java.lang.NullPointerException e) {
            StdOut.println("\t\tpassed: exception caught");
        }

        // Test removeFirst, test item returned
        StdOut.print("Test removeFirst method, value returned: ");
        Deque<String> test6Deque = new Deque<String>();
        test6Deque.addFirst("add one item");
        if (test6Deque.removeFirst().equals("add one item")) {
            StdOut.println("\tpassed");
	}

        // Test removeFirst, test size after removal
        StdOut.print("Test removeFirst method, size after: ");
        Deque<String> test7Deque = new Deque<String>();
        test7Deque.addFirst("add one item");
        test7Deque.removeFirst();
        if (test7Deque.size() == 0) {
            StdOut.println("\t\tpassed");
        }

        // Test removeFirst, can't remove from an empty Deque
        StdOut.print("Test removeFirst, remove from empty Deque: ");
        Deque<String> test9Deque = new Deque<String>();
        try {
            test9Deque.removeFirst();
	} catch (java.util.NoSuchElementException e) {
            StdOut.println("\tpassed: exception caught");
        }

        // Test removeLast, can't remove from an empty Deque
        StdOut.print("Test removeLast, remove from empty Deque: ");
        Deque<String> test10Deque = new Deque<String>();
        try {
            test10Deque.removeLast();
        } catch (java.util.NoSuchElementException e) {
            StdOut.println("\tpassed: exception caught");
        }

        // Test removeLast, test item returned
        StdOut.print("Test reomveLast, item returned: ");
        Deque<String> test11Deque = new Deque<String>();
        test11Deque.addFirst("add at front");
        if (test11Deque.removeLast().equals("add at front")) {
            StdOut.println("\t\tpassed");
	}
        StdOut.print("Test removeLast, item returned: ");
        Deque<String> test12Deque = new Deque<String>();
        test12Deque.addLast("add at back");
        if (test12Deque.removeLast().equals("add at back")) {
            StdOut.println("\t\tpassed");
        }

        // Test removeLast, test size after removal
        StdOut.print("Test removeLast, size: ");
        Deque<String> test13Deque = new Deque<String>();
        test13Deque.addFirst("add 1");
        test13Deque.addFirst("add 2");
        test13Deque.addFirst("add 3");
        test13Deque.removeLast();
        if (test13Deque.size() == 2) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test addFirst with removeLast
        StdOut.print("Test addFirst with removeLast: ");
        Deque<String> test23Deque = new Deque<String>();
        test23Deque.addFirst("add 1");
        test23Deque.addFirst("add 2");
        test23Deque.addFirst("add 3");
        if (test23Deque.removeLast().equals("add 1")) {
	    StdOut.println("\t\t\tpassed");
	} else {
            StdOut.println("FAILED");
        }

        // Test addLast with removeFirst
        StdOut.print("Test addLast with removeFirst: ");
        Deque<String> test24Deque = new Deque<String>();
        test24Deque.addLast("add 1");
        test24Deque.addLast("add 2");
        test24Deque.addLast("add 3");
        if (test24Deque.removeFirst().equals("add 1")) {
            StdOut.println("\t\t\tpassed");
	} else {
            StdOut.println("failed");
        }

        // Test removeLast, test size after removal
        StdOut.print("Test removeLast, size: ");
        Deque<String> test14Deque = new Deque<String>();
        test14Deque.addLast("add 1");
        test14Deque.addLast("add 2");
        test14Deque.addLast("add 3");
        test14Deque.removeLast();
        if (test14Deque.size() == 2) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test Iterator construction
        StdOut.print("Test iterator(): ");
        Deque<String> test16Deque = new Deque<String>();
        Iterator itr = test16Deque.iterator();
        if (itr != null) {
            StdOut.println("\t\t\t\tpassed");
        }

        // Test Iterator, hasNext(), passing on Deque with 1 item, but FAILING empty Deque
        StdOut.print("Test hasNext(): ");
        Deque<String> test17Deque = new Deque<String>();
        test17Deque.addFirst("add 1");
        test17Deque.addFirst("add 2");
        test17Deque.addFirst("add 3");
        Iterator itr2 = test17Deque.iterator();
        if (itr2.hasNext()) {
            StdOut.println("\t\t\t\tpassed");
        } else {
            StdOut.println("\t\t\t\tFAILED");
        }

        // Test Iterator, next()
        StdOut.print("Test next(): ");
        Deque<String> test18Deque = new Deque<String>();
        test18Deque.addFirst("add 1");
        test18Deque.addFirst("add 2");
        test18Deque.addFirst("add 3");
        Iterator itr3 = test17Deque.iterator();
        if (itr3.next().equals("add 2")) {
            StdOut.println("\t\t\t\t\tpassed");
        }

        // Test Iterator, test if remove throws exception
        StdOut.print("Test Iterator .remove(): ");
        Deque<String> test20Deque = new Deque<String>();
        test20Deque.addFirst("add 1");
        Iterator itr4 = test20Deque.iterator();
        try {
            itr4.remove();
        } catch (java.lang.UnsupportedOperationException e) {
            StdOut.println("\t\t\tpassed: exception caught");
        }

        // Test Iterator, foreach(), FAILING
        StdOut.print("Test foreach(): ");
        Deque<String> test19Deque = new Deque<String>();
        test19Deque.addFirst("1");
        test19Deque.addFirst("2");
        test19Deque.addFirst("3");
        test19Deque.addFirst("4");
        test19Deque.addFirst("5");
        test19Deque.addFirst("6");
        Iterator itr5 = test19Deque.iterator();
        while (itr5.hasNext()) {
            StdOut.println(itr5.next());
        }

        // Test Iterator on empty collection
        StdOut.print("Test Iterator basic operations: ");
        Deque<String> test21Deque = new Deque<String>();
        Iterator itr6 = test21Deque.iterator();
        if (itr6.hasNext() == false) {
            StdOut.print("\t\tPass1");
	}
        if (itr6.next() == null) {
            StdOut.print("Pass2\n");
        }

        // Test Iterator on collection with 1 item
        StdOut.print("Test Iterator basic operations: ");
        Deque<String> test22Deque = new Deque<String>();
        test22Deque.addFirst("1");
        test22Deque.addFirst("2");
        Iterator itr7 = test22Deque.iterator();
        if (itr7.hasNext() == true) {
            StdOut.print("\t\tPass1");
        }
        // this equals 1 because each item is inserted in front
        if (itr7.next() == "1") {
            StdOut.print("Pass2\n");
        }

        // Test Iterator on collection with 6 items
        StdOut.println("Test iterator with 6 items:");
        Deque<String> test25Deque = new Deque<String>();
        test25Deque.addFirst("1");
        test25Deque.addFirst("2");
        test25Deque.addFirst("3");
        test25Deque.addFirst("4");
        test25Deque.addFirst("5");
        test25Deque.addFirst("6");
        Iterator itr8 = test25Deque.iterator();
        while (itr8.hasNext()) {
            StdOut.println(itr8.next());
        }

        // Actual foreach
        StdOut.println("Test foreach: ");
        Deque<String> test26Deque = new Deque<String>();
        test26Deque.addFirst("1");
        test26Deque.addFirst("2");
        test26Deque.addFirst("3");
        test26Deque.addFirst("4");
        test26Deque.addFirst("5");
        test26Deque.addFirst("6");
        Iterator itr9 = test26Deque.iterator();
        for (String xyz : test26Deque) {
            StdOut.println(xyz);
        }
    }
}