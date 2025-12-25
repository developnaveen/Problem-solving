import java.util.ArrayList;
import java.util.Iterator;

class arraylist{
    public static void main(String[] args){
        ArrayList<String> list = new  ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        list.forEach(System.out::println);
        list.forEach(n -> System.out.println(n));

        System.out.println(list.get(0));
        list.set(0,"grapes");
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        list.remove("banana");
        System.out.println(list.get(0));
        list.size();
        System.out.println(list.size());
        list.contains("orange");
        System.out.println(list.contains("orange"));
        list.isEmpty();
        System.out.println(list.isEmpty());
        list.clear();
        System.out.println(list.isEmpty());

        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            it.remove();
        }

    }
}