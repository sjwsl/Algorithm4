import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class node{
        Item item;
        node last, next;

        public node(Item item, node last, node next) {
            this.item = item;
            this.last = last;
            this.next = next;
        }
    }

    private node head , tail;

    private int size = 0;

    // construct an empty deque
    public Deque() {
        head=tail=null;
        size=0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;
        if (size == 1) {
            head = tail = new node(item, null, null);
            return;
        }
        head.last = new node(item, null, head);
        head = head.last;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        size++;
        if (size == 1) {
            addFirst(item);
            return;
        }
        tail.next = new node(item, tail, null);
        tail = tail.next;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();
        Item returnValue = head.item;
        if (--size == 0) {
            head = tail = null;
            return returnValue;
        }
        head = head.next;
        head.last = null;
        return returnValue;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();
        Item returnValue = tail.item;
        if (--size == 0) {
            head = tail = null;
            return returnValue;
        }
        tail = tail.last;
        tail.next = null;
        return returnValue;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Item> {

        private node now=head;

        public boolean hasNext() {
            return now!=null;
        }

        @Override
        public Item next() {
            if(!hasNext())throw new java.util.NoSuchElementException();
            Item returnValue =now.item;
            now=now.next;
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();
        q.addFirst(1);
        q.addLast(2);
    }

}