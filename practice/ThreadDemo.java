class Work implements Runnable{
    @Override
    public void run() {
        System.out.println("thread is running " + Thread.currentThread().getName());
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
       Work t = new Work();
       Thread t1 = new Thread(t);
       Thread t2 = new Thread(t);

       t1.start();
       t2.start();
    }
}
