import java.util.Deque;
import java.util.ArrayDeque;

public class deque {
    public static void main(String[] args) {

        Deque<String> dq = new ArrayDeque<>();

        dq.add("A");
        dq.addFirst("B");
        dq.addLast("C");

        System.out.println(dq);
    }
}
