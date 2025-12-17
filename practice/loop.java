public class loop{
    public static void main(String[] args){
        int n=5;
        for(int i=0;i<n;i++){
            System.out.print(i);
        }
        System.out.println();
        int i = 0;
        while(i < n){
            System.out.print(i);
            i++;
        }
        System.out.println();
        i = 0;
        do{
            i++;
            System.out.print(i);
        }while(i < n);
    }
}