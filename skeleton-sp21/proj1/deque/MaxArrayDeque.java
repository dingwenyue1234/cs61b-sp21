package deque;

import java.util.Collections;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c){
        super();
        comp = c;
    }

    public T max(){
        return max(comp);
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        else{
            T currentMax = get(0);
            for (int i = 0; i < size(); i++){
                int res = c.compare(currentMax,get(i));
                if (res < 0){
                    currentMax = get(i);
                }
            }
            return currentMax;
        }
    }
}
