import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    /*public Deque() {
        // construct an empty Deque, constructor is provided by the compiler
        first = null;
	}*/

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        // returns void, should compile
        Node newNode = new Node();
        newNode = first;
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public void addLast(Item item) {
        // returns void, should compile
    }

    public Item removeFirst() {
        Item item = first.item;
        return item;
    }

    public Item removeLast() {
        Item item = first.item;
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

        // Test Deque constructor, test one thing, is the object not null
        StdOut.print("Test Deque constructor: ");
        Deque<String> test1Deque = new Deque<String>();
        if (test1Deque != null) {
            StdOut.println("passed");
        }

        // Test addFirst, test one thing
        StdOut.print("Test Deque addFirst method: ");
        Deque<String> test2Deque = new Deque<String>();
        test2Deque.addFirst("Hello");
        if ((test2Deque.size() == 1) && (!test2Deque.isEmpty())) {
            StdOut.println("passed");
        }
    }
}