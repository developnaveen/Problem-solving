public class problem04{
    public static void main(String[] args){
        int num = 1234;
        System.out.println(karprekar(num));
    }

    public static int karprekar(int num){
        if( num == 6174) return 0;

        String s = String.format("%04d", num);

        char[] desc = s.toCharArray();
        java.util.Arrays.sort(desc);
        String ascStr = new String(desc);
        String decStr = new StringBuilder(ascStr).reverse().toString();

        int big = Integer.parseInt(decStr);
        int small = Integer.parseInt(ascStr);
        int result = big - small;

        return 1 + karprekar(result);
    }
}