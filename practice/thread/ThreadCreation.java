import java.util.concurrent.locks.LockSupport;

class ThreadObj extends Thread{
    @Override
    public void run(){
        System.out.println("thread is extended");
    }
}

class ThreadCreate implements Runnable{
    @Override
    public void run(){
        System.out.println("thread is implemented");
        //raceCondition();
        sleepCondition();
    }

    public static synchronized void raceCondition(){
        while(true){

        }
    }

    public static void sleepCondition(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadCreation {

    /*
    public static void main (String[] args){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread is running");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    } */

    /*
    public static void main (String[] args){
        ThreadObj t1 = new ThreadObj();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());

        Runnable runnable = new ThreadCreate();
        Thread t2 = new Thread(runnable);
        System.out.println(t2.getState());
        t2.start();
        System.out.println(t2.getState());
    } */

    /* Blocked
    public static void main (String[] args) {
        Runnable runnable = new ThreadCreate();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        System.exit(0);
    }*/

    /* waste just to see the state of thread
    public static void main(String[] args) {

        Runnable runnable = new ThreadCreate();

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print all possible thread states
        for (Thread.State state : Thread.State.values()) {
            System.out.println(state);
        }

        // Print current state of threads
        System.out.println("t1 : " + t1.getState());
        System.out.println("t2 : " + t2.getState());

        System.exit(0);
    } */

    /*
    public static Thread t1;
    public static void main (String[] args) {
        t1 = new Thread(new ThreadCreation());
        t1.start();
    }
    public void run(){
        Thread t2 = new Thread(new ThreadCreate());
        t2.start();
        try{
            t2.join();
            System.out.println("t1 : " + t1.getState());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    } */

    /*
    public static void main (String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("started");
            LockSupport.park();
            System.out.println("after unlock");
        });
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("now unlock");
        LockSupport.unpark(t1);
    } */
    /*
    public static void main (String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("started");
            long wakeUpTime = System.currentTimeMillis() + 5000;
            LockSupport.parkUntil(wakeUpTime);
            System.out.println("after unlock");
        });
        t1.start();
    } */

    public static void main (String[] args) {
        Runnable runnable = new ThreadCreate();
        Thread t1 = new Thread(runnable);
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(t1.getState());
    }

    }