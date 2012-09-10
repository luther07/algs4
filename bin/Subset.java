import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int k; // command line integer

        k = Integer.parseInt(args[0]);

        RandomizedQueue<String> sample = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            sample.enqueue(StdIn.readString());
        }

        Iterator randomIterator = sample.iterator();

        for (int i = 0; i < k; i++) {
            StdOut.println(sample.dequeue());
        }

        // use one object of type Deque or RandomizedQueue
        // use generics properly to avoid casting and compiler warnings

        // reads in a sequence of N strings from standard input,
        // using StdIn.readString(),
        // and prints out exactly k of them,
        // uniformly at random

        // Each item from the sequence can be printed out at most once.
        // You may assume that k>=0 and no greater than the number
        // of strings on standard input.
    }
}