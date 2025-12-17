class parent{
    int id;
    String name;

    parent(int id,String name){
        this.id = id;
        this.name = name;
    }

    public parent getuser(){
        return this;
    }
}
public class inheritance extends parent{

    inheritance(int id, String name) {
        super(id, name);
    }

    @Override
    public parent getuser(){
        return this;
    }

    public static void main(String[] args){
        parent p = new parent(1,"naveen");
        System.out.println(p.getuser().name);
        inheritance obj = new inheritance(2, "kumar");
        System.out.println(obj.getuser().name);
    }
}