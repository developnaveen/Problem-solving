import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    private int balance = 100;
    private ReentrantLock lock = new ReentrantLock();

    public void deposit(int amount) {

        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + " Depositing " + amount);

            balance += amount;

            System.out.println(Thread.currentThread().getName() + " Balance = " + balance);

        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        Lock account = new Lock();

        System.out.println("Initial Balance : " + account.getBalance());

        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> account.deposit(500));

        CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> account.deposit(1000));

        CompletableFuture<Void> t3 = CompletableFuture.runAsync(() -> account.deposit(200));

        CompletableFuture.allOf(t1, t2, t3).join();

        System.out.println("Final Balance : " + account.getBalance());
    }
}