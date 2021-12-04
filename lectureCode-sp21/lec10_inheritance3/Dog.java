package lec10_inheritance3;

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public int compareTo(Dog uddaDog) {
        //assume nobody is messing up and giving us
        //something that isn't a dog.
        return size - uddaDog.size;
    }

    public static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b){
            return a.name.compareTo(b.name);
        }
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
}