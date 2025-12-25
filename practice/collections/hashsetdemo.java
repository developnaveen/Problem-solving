import java.util.HashSet;
import java.util.Iterator;

public class hashsetdemo{
    public static void main(String[] args){
        HashSet<String> set = new HashSet<String>();

        set.add("naveen");
        set.add("sathiya");
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