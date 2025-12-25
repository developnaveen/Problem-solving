import java.util.HashMap;
import java.util.Iterator;

public class hashmap  {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }
}