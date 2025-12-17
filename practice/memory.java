public class memory {

    static class Person {
        String name;
        Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        int x = 10;                 // Stored in Stack
        String title = "Engineer";  // Reference in Stack, object in Heap

        Person p = new Person("Naveen");  // Object in Heap, reference in Stack

        System.out.println("x = " + x);
        System.out.println("Title = " + title);
        System.out.println("Person Name = " + p.name);
    }
}
