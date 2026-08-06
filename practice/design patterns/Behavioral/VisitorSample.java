interface Place{
    void entry(Visitor visitor);
}

class Home implements Place{
    private String Place;
    public String whereAreWe(){
        return "you are at home";
    }

    @Override
    public void entry(Visitor visitor){
        visitor.visit(this);
    }
}
class Office implements Place{
    private String Place;
    public String whereAreWe(){
        return "you are at office";
    }

    @Override
    public void entry(Visitor visitor){
        visitor.visit(this);
    }
}

interface Visitor{
    void visit(Home home);
    void visit(Office office);
}

class Son implements Visitor{
    @Override
    public void visit(Home home){
        System.out.println("Son " +home.whereAreWe());
    }

    @Override
    public void visit(Office office){
        System.out.println("Son " +office.whereAreWe());
    }
}

class Dad implements Visitor{
    @Override
    public void visit(Home home){
        System.out.println("Dad " +home.whereAreWe());
    }

    @Override
    public void visit(Office office){
        System.out.println("Dad " +office.whereAreWe());
    }
}

public class VisitorSample {
    public static void main(String[] args){
        Place home = new Home();
        Place office = new Office();

        Visitor son = new Son();
        Visitor dad = new Dad();

        home.entry(son);
        home.entry(dad);
        office.entry(dad);
    }
}