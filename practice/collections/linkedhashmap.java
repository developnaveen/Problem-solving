import java.util.LinkedHashMap;
import java.util.Iterator;

public class linkedhashmap {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        System.out.println(map);

        Iterator itr = map.entrySet().iterator();
        while (itr.hasNext()){
            itr.next();
            itr.remove();
        }
        System.out.println(map);
    }
}