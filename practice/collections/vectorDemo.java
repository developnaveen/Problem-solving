import java.util.Vector;
import java.util.Iterator;

public class vectorDemo{
    public static void main(String args[]){
        Vector<String> v = new Vector<>();

        v.add("naveen");
        v.add("kumar");
        v.add("kannan");

        System.out.println(v);
        Iterator<String> it = v.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
        System.out.println(v);
    }
}