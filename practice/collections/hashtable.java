import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class hashtable {
    public static void main(String[] args) {

        Hashtable<Integer, String> table = new Hashtable<>();

        table.put(1, "naveen");
        table.put(2, "kumar");
        table.put(3, "jana");

        System.out.println(table);
        Iterator<Map.Entry<Integer, String>> it = table.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, String> e = it.next();
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }
}
