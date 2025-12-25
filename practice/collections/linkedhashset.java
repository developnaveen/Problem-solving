import java.util.LinkedHashSet;
import java.util.Iterator;

public class linkedhashset{
    public static void main(String[] args){
        LinkedHashSet<String> set = new LinkedHashSet<>();

        set.add("naveen");
        set.add("jana");
        set.add("naveen");

        System.out.println(set);
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        System.out.println(set);
    }
}
