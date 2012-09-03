/*-----------------------------------------------------------------------------
 * Author:        Mark Johnson
 * Written:       8/30/2012
 * Last Updated:  9/3/2012
 *
 * Compilation:   javac Deque.java
 * Execution:     java Deque
 *
 * Defines a Deque type, with specified operations.
 *---------------------------------------------------------------------------*/

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

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

    public void addLast(Item item) {
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

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size --;
        return item;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        /*public DequeIterator() {
            // needs to construct an object, constructor provided by the compiler
	    }*/

        public Item next() {
            // needs to return a reference to an item
            return current.item;
        }

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            // returns void, should compile
            // not supported, throw UnsupportedOperationException
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
        StdOut.print("Test removeFirst, try remove from empty Deque: ");
        Deque<String> test9Deque = new Deque<String>();
        try {
            test9Deque.removeFirst();
	} catch (java.util.NoSuchElementException e) {
            StdOut.println("passed: exception caught");
        }

        // Test removeLast, can't remove from an empty Deque
        StdOut.print("Test removeLast, try remove from empty Deque: ");
        Deque<String> test10Deque = new Deque<String>();
        try {
            test10Deque.removeLast();
        } catch (java.util.NoSuchElementException e) {
            StdOut.println("passed: exception caught");
        }

        // Test removeLast, test item returned
        StdOut.print("Test reomveLast, item returned: ");
        Deque<String> test11Deque = new Deque<String>();
        test11Deque.addFirst("add at front");
        if (test11Deque.removeLast().equals("add at front")) {
            StdOut.println("passed");
	}
        StdOut.print("Test removeLast, item returned: ");
        Deque<String> test12Deque = new Deque<String>();
        test12Deque.addLast("add at back");
        if (test12Deque.removeLast().equals("add at back")) {
            StdOut.println("passed");
        }
        

        // Test removeLast, test size after removal
    }
}