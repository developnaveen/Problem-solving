public class conditional{
    public static void main(String[] args){
        boolean age = true;
        if(age){
            System.out.println("success");
        } else if(age) {
            System.out.println("not success");
        }else{
            System.out.println("NO activites");
        }

        int vote = 18;
        switch (vote){
            case 18:
                System.out.println("eligible to vote");
                break;
            case 10:
                System.out.println("not eligible to vote");
                break;
        }

        int count = 5;
        while(count > 0){
            System.out.println(count);
            count--;
        }

    }
}