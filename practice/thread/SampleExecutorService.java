import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Map;

public class SampleExecutorService {

    public void print(String word){
        System.out.println(word);
    }

    /*
    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(10);
        SampleExecutorService samExe = new SampleExecutorService();
        int i = 0;
        while(i != 100){
            int task = i;
            executor.submit(() -> samExe.print("task " + task +" " + Thread.currentThread().getName()));
            i++;
        }
        System.exit(0);


    }*/

    /*
    public static void main(String[] args) {

        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();

        for (Thread t : threads.keySet()) {
            System.out.println(
                    "Name: " + t.getName() +
                            ", ID: " + t.threadId() +
                            ", State: " + t.getState()
            );
        }
    } */

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            int task = i;
            executor.submit(() -> {
                System.out.println("Task " + task + " : " +
                        Thread.currentThread().getName());

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1000);

        System.out.println("\nAll JVM Threads:");

        for (Thread t : Thread.getAllStackTraces().keySet()) {
            System.out.println(t.getName() + " : " + t.getState());
        }

        executor.shutdown();
    }
}