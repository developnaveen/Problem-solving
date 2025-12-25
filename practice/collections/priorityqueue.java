import java.util.PriorityQueue;

public class priorityqueue {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(30);
        pq.add(10);
        pq.add(20);

        System.out.println(pq);      // Heap view
        System.out.println(pq.poll()); // 10
        System.out.println(pq.poll()); // 20
        System.out.println(pq.poll()); // 30
    }
}
