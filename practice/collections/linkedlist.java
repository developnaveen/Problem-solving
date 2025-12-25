import java.util.LinkedList;
import java.util.Iterator;
public class linkedlist{
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.addFirst("d");
        list.addLast("e");

        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        list.removeFirst();
        list.removeLast();

        list.set(0,"n");


        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }

        list.offer("g");
        list.poll();
        list.peek();

        list.push("l");
        list.pop();

    }
}