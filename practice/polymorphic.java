class comman{
    int num1;
    int num2;
    int num3;

    public int add(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
        return this.num1 + this.num2;
    }

    public int add(int num1, int num2, int num3){
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        return this.num1+ this.num2+ this.num3;
    }
}


public class polymorphic{
    public static void main(String[] args){
        comman c = new comman();
       System.out.println(c.add(12,13));
    }
}