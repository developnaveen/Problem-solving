import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class TreeMapDemo{
    public static void main(String[] args){
        TreeMap<String, String> mapTree = new TreeMap<>();

        mapTree.put("A","B");
        mapTree.put("B","C");
        mapTree.put("C","D");

        Iterator<Map.Entry<String, String>> it = mapTree.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, String> e = it.next();
            System.out.println(e.getKey() +" = " + e.getValue());
        }
    }
}