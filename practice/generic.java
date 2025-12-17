class Box<T> {
    T id;
    T name;

    public void set(T id, T name) {
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.println(id + " " + name);
    }
}

public class generic{
    public static void main(String[] args){
        Box<Integer> b = new Box<>();
        b.set(100,100);
        b.print();
    }
}
