package lec10_inheritance3;

public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};

        Dog d = (Dog) Maximizer.max(dogs);
        d.bark();

        Dog.NameComparator nc= new Dog.NameComparator();
        if (nc.compare(d1,d3) > 0) {
            d1.bark();
        } else {
            d3.bark();
        }
    }
}
