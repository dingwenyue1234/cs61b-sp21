package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>,Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Constructor to initiate empty ArrayDeque. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /** Helper function to resize an Array */
    private void resize(int capacity){
        T[] itemsResize = (T[]) new Object[capacity];
        int first = (nextFirst + 1) % items.length;
        int newFirst = first + capacity - items.length;
        while (newFirst < 0) {
            newFirst += capacity;
        }
        for (int i = 0; i < size; i ++) {
            itemsResize[(newFirst+i)%capacity] = get(i);
        }
        nextFirst = newFirst - 1;
        nextLast = (newFirst + size) % capacity;

        items = itemsResize;
    }

    /** Adds an item to the front of the deque.*/
    @Override
    public void addFirst(T item){
        if(size >= items.length-1) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    /** Adds an item to the back of the deque. */
    @Override
    public void addLast(T item) {
        if(size >= items.length-1) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }


    /** Return the number of item in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Return the size of item. */
    public int length(){
        return items.length;
    }

    /** Prints the items in the deque from first to last, separated by a space. Once all the items
     * have been printed, print out new line.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++){
            int index = (i + nextFirst + 1) % items.length;
            System.out.println(items[index]);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null.*/
    @Override
    public T removeFirst() {
        if (size < Math.floorDiv(items.length, 4) && size >= 16) {
            resize(Math.floorDiv(items.length, 2));
        }
        if (size == 0){
            return null;
        }
        T temp = items[(nextFirst + 1) % items.length];
        items[(nextFirst + 1) % items.length] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size -= 1;
        return temp;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, return null.*/
    @Override
    public T removeLast() {
        if (size < Math.floor(items.length/4) && size >= 16) {
            resize(Math.floorDiv(items.length, 2));
        }
        if (size == 0){
            return null;
        }
        T temp = items[ (nextLast - 1 + items.length) % items.length];
        items[(nextLast - 1 + items.length) % items.length] = null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        size -= 1;
        return temp;
    }

    /** Gets the item at the given index using iteration.*/
    @Override
    public T get(int index) {
        if (index < size){
            return items[(index + nextFirst + 1) % items.length];
        }
        return null;
    }


    /**Iterator for deque objects */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int wizPos;

        public ArrayDequeIterator(){
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
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
        ArrayDeque<T> oAD = (ArrayDeque<T>) o;
        if (oAD.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++){
            if(oAD.get(i) != this.get(i)){
                return false;
            }
        }
        return true;
    }

}
