public class MemoryTest {
    public static void main(String[] args) {
        while (true) {
            byte[] data = new byte[5 * 1024 * 1024]; // 5 MB
            System.out.println("Allocated 5 MB");
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}
