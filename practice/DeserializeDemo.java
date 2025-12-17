import java.io.*;

public class DeserializeDemo {
    public static void main(String[] args) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.ser"))) {

            User user = (User) in.readObject();

            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
