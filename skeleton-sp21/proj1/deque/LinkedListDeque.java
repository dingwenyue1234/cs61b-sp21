package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class Deque {
        public T item;
        public Deque next;
        public Deque prev;

        public Deque(T i, Deque n, Deque p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Deque sentinal;
    private int size;

    /** Create an empty LLDeque */
    public LinkedListDeque() {
        sentinal = new Deque(null, null, null);
        size = 0;
    }

    /** Adds an item to the front of the deque.*/
    @Override
    public void addFirst(T item){

        if (size == 0) {
            sentinal.item = item;
            sentinal.next = sentinal;
            sentinal.prev = sentinal;
        }
        sentinal.next = new Deque(item, sentinal.next, sentinal);
        sentinal.next.next.prev = sentinal.next;
        size += 1;
    }

    /** Adds an item to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (size == 0) {
            sentinal.item = item;
            sentinal.next = sentinal;
            sentinal.prev = sentinal;
        }
        sentinal.prev = new Deque(item,sentinal,sentinal.prev);
        sentinal.prev.prev.next = sentinal.prev;
        size += 1;
    }

    /** Return the number of item in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. Once all the items
     * have been printed, print out new line.
     */
    @Override
    public void printDeque() {
        Deque L = sentinal.next;
        for (int i = 0; i < size; i += 1) {
            System.out.println(L.item);
            L = L.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null.*/
    @Override
    public T removeFirst() {
        if (size > 0) {
            Deque temp = sentinal.next;
            temp.prev = null;
            sentinal.next = sentinal.next.next;
            temp.next = null;
            sentinal.next.prev = sentinal;
            size -= 1;
            return temp.item;
        }
        else {
            return null;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, return null.*/
    @Override
    public T removeLast() {
        if (size >0) {
            Deque temp = sentinal.prev;
            temp.next = null;
            sentinal.prev = sentinal.prev.prev;
            temp.prev = null;
            sentinal.prev.next = sentinal;
            size -= 1;
            return temp.item;
        }
       else {
           return null;
        }
    }

    /** Gets the item at the given index using iteration.*/
    @Override
    public T get(int index) {
        if (index < size) {
            Deque L = sentinal.next;
            for (int i = 0; i < index; i += 1) {
                L = L.next;
            }
            return L.item;
        }
        return null;
    }

    /** Helper function to make get method recursive */
    private T getRecursiveHelper(int index, Deque q) {
        if (index < size) {
            Deque L = q.next;

            if (index == 0){
                return L.item;
            }
            else {
                L = L.next;
                return getRecursiveHelper(index-1, L);
            }
        }
        return null;
    }

    /** Gets the item at the given index using recursion.*/
    public T getRecursive(int index) {
        return getRecursiveHelper(index,sentinal);
    }



    /**Iterator for deque objects */
    public Iterator<T> iterator(){
        return new LLDequeIterator();
    }
    
    private class LLDequeIterator implements Iterator<T> {
        private int wizPos;
        
        public LLDequeIterator(){
            wizPos = 0;
        }
        
        public boolean hasNext(){
            return wizPos < size;
        }
        public T next(){
            T returnItem = get(wizPos);
            wizPos+=1;
            return returnItem;
        }
    }

    /** Return whether or not the parameter o is equal to the Deque */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        LinkedListDeque<T> oLLD = (LinkedListDeque<T>) o;
        if (oLLD.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++){
            if(oLLD.get(i) != this.get(i)){
                return false;
            }
        }
        return true;
    }

}
