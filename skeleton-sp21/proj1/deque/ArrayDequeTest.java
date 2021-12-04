package deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", ad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<Double> ad2 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        assertEquals(s,"string");
        double d = ad2.removeFirst();
        assertEquals(d,3.14159,0.0);
        boolean b = ad3.removeFirst();
        assertEquals(b,true);
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

    }

    @Test
    /* Simple resize check. */
    public void resizeTest(){

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 1024; i ++) {
            ad1.addFirst(i);
        }
        for (int i = 0; i < 1008; i ++) {
            ad1.removeFirst();
        }

        assertEquals("The length of item should be 16", 64, ad1.length());
        ad1.printDeque();

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

       ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }
        for (double i = 0; i < 500000; i++) {
            int res = ad1.removeFirst();

            assertEquals("Should have the same value", i, res, 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    /** Compare results from ArrayDeque and LLDeque after random test */
    public void randomizedTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        int N = 10000000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0,6);
            if (operationNumber == 0){
                int randVal = StdRandom.uniform(0,100);
                ad1.addFirst(randVal);
                lld1.addFirst(randVal);
            }
            else if (operationNumber == 1) {
                ad1.removeFirst();
                lld1.removeFirst();
            }
            else if (operationNumber == 2) {
                int randVal = StdRandom.uniform(0,100);
                ad1.addLast(randVal);
                lld1.addLast(randVal);
            }
            else if (operationNumber == 3) {
                assertEquals("The items at the last index does not match.",ad1.removeLast(),lld1.removeLast());
            }
            else if (operationNumber == 4) {
                int randIndex = StdRandom.uniform(0,5000);
                assertEquals("The items at same index of two types of arrays does not match.",ad1.get(randIndex), lld1.get(randIndex));
            }
            else if (operationNumber == 5) {
                int sizeA = ad1.size();
                int sizeL = lld1.size();
                assertEquals("Size of two types of array does not match.",sizeL, sizeA);
            }
        }
        assertEquals(ad1.size(),lld1.size());
        assertEquals(ad1.removeLast(), lld1.removeLast());
        assertEquals(ad1.removeFirst(), lld1.removeFirst());
        int randIndex = StdRandom.uniform(0, ad1.size());
        assertEquals(ad1.get(randIndex), lld1.get(randIndex));
    }


    @Test
    public void IteratorTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque();

        for (int i = 0; i < 100; i++) {
            ad1.addLast(i);
        }

        int i = 0;
        for(int r : ad1){
            int rGet = ad1.get(i);
            assertEquals("The returned value from get should be same as value from iterator", rGet,r);
            i+=1;
        }
    }

    @Test
    public void emptyArrayIteratorTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        for(String r:ad1){
            assertEquals("Empty array should return null",null,r);
        }
    }

    @Test
    public void equalTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ArrayDeque<String> ad3 = new ArrayDeque<>();
        ArrayDeque<String> ad4 = new ArrayDeque<>();
        ArrayDeque<String> ad5 = new ArrayDeque<>();

        ad1.addFirst("Hello");
        ad2.addLast("Hello");
        ad3.addFirst("Hello");

        ad1.addFirst("this");
        ad2.addLast("this");
        ad3.addFirst("this");

        ad1.addFirst("is");
        ad2.addLast("is");
        ad3.addFirst("is");

        ad1.addFirst("program");
        ad3.addFirst("program");

        assertEquals("Same array should be equal", true, ad1.equals(ad3));
        assertEquals("ad1.equal should be same as ad3.equal", ad3.equals(ad1), ad1.equals(ad3));
        assertEquals("Array with different sequence should be different", false, ad1.equals(ad2));
        assertEquals("Eqauls should be able to handle null", false, ad1.equals(null));
        assertEquals("Empty array should be the same", true, ad4.equals(ad5));
    }

    public class IntComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }

    public class StringComparator implements  Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void maxArrayDequeIntTest(){

        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(new IntComparator());
        MaxArrayDeque<Integer> ad2 = new MaxArrayDeque<>(new IntComparator());

        for (int i = 0; i < 10; i++){
            ad1.addFirst(i);
        }
        int m = ad1.max();
        assertEquals("maximum should be 9", 9, m);
        assertEquals("Empty array should return null", null, ad2.max());
    }

    @Test
    public void stringMaxArrayDequeTest(){
        MaxArrayDeque<String> ad1 = new MaxArrayDeque<>(new StringComparator());

        ad1.addFirst("hello");
        ad1.addFirst("Hi");
        ad1.addFirst("you");
        ad1.addFirst("");
        ad1.addFirst("[");

        assertEquals("you",ad1.max());
    }

}
