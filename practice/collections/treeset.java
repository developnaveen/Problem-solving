import java.util.TreeSet;
import java.util.Iterator;

public class treeset{
    public static void main(String args[]) {
        TreeSet<String> tree = new TreeSet<>(
                (a,b) -> a.compareTo(b)
        );

        tree.add("naveen");
        tree.add("hello");

        System.out.println(tree);

        Iterator<String> it = tree.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            it.next();
            it.remove();
        }
    }
}
