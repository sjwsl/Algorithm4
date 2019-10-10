import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue

    private Item[] items;
    private int size;

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(size * 2);
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(size);
        Item returnValue = items[randomIndex];
        items[randomIndex] = items[--size];
        items[size] = null;
        if (size == items.length / 4) resize(items.length / 2);
        return returnValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(size);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] copyItems;

        private int copySize;

        public RandomizedQueueIterator() {
            copyItems = items;
            copySize = size;
        }

        @Override
        public boolean hasNext() {
            return copySize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int randomIndex = StdRandom.uniform(copySize);
            Item returnValue = copyItems[randomIndex];
            copyItems[randomIndex] = copyItems[--size];
            copyItems[size] = null;
            //if (size == items.length / 4) resize(size / 2);
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q=new RandomizedQueue<>();
        for(int i=1;i<=10;i++){
            q.enqueue(i);
        }
        for(int i=1;i<=10;i++){
            StdOut.println(q.sample());
        }
    }

}