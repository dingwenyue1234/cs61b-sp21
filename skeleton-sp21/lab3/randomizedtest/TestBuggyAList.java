package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> buggyAList = new BuggyAList();
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing();

        for (int i = 0; i < 3; i+=1){
            buggyAList.addLast(i+4);
            aListNoResizing.addLast(i+4);
        }

        assertEquals(buggyAList.size(),aListNoResizing.size());

        for (int i = 0; i < 3; i += 1) {
            assertEquals(buggyAList.removeLast(),aListNoResizing.removeLast());
        }
    }

    @Test
    public void ramdomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);

                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                if (L.size() > 0){
                    L.removeLast();
                }
                if(B.size() > 0){
                    B.removeLast();
                }
            } else if(operationNumber == 2){
                if (L.size() > 0){
                    L.getLast();
                }
                if(B.size() > 0){
                    B.getLast();
                }
            } else if (operationNumber == 3){
                int sizeL = L.size();
                int sizeB = B.size();
                System.out.println("L size: "+ sizeL);
                System.out.println("B size: "+ sizeB);
            }

        }

        assertEquals(B.size(),L.size());
        assertEquals(B.getLast(),L.getLast());

        int randomIdx = StdRandom.uniform(0,B.size());
        assertEquals(B.get(randomIdx),L.get(randomIdx));
        assertEquals(B.removeLast(),L.removeLast());
    }

}
