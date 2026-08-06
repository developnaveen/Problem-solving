import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {

    public static void main(String[] args) {

        List<String> channels = new ArrayList<>();

        channels.add("Sun TV");
        channels.add("K TV");
        channels.add("Vijay TV");

        Iterator<String> iterator = channels.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}