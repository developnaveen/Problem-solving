import java.io.*;

// Class must be public or static if inside another class
class User implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int age;
}

public class SerializeDemo {

    public static void main(String[] args) {

        User user = new User();
        user.name = "naveen";
        user.age = 23;

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("user.ser"))) {

            out.writeObject(user);
            System.out.println("Object serialized successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
