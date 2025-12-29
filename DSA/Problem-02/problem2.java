import java.util.*;
public class problem2{
    public static void main(String[] args){
        String[] word = {"chair", "height", "racket", "touch", "tunic"};
        System.out.println(iscircle(word));
    }

    public static boolean iscircle(String[] words){
        List<String[]> Result = new ArrayList<>();

        permutate(words, 0, Result);
        for(String[] r:Result){
            if(formscircle(r)){
                return true;
            }
        }
        return false;

    }

    public static boolean formscircle(String[] words){
        for (int i = 0; i < words.length - 1; i++) {
            char last = words[i].charAt(words[i].length() - 1);
            char first = words[i + 1].charAt(0);

            if (last != first) {
                return false;
            }
        }
        char lastLast = words[words.length - 1].charAt(words[words.length - 1].length() - 1);
        char firstFirst = words[0].charAt(0);

        return lastLast == firstFirst;
    }
    public static void permutate(String[] words,int index, List<String[]> result){
        if(index == words.length){
            result.add(words.clone());
            return;
        }

        for(int i=index; i<words.length;i++){
            swap(words,i,index);
            permutate(words,index+1,result);
            swap(words,i,index);
        }
    }

    public static void swap(String[] word,int i,int j){
        String temp = word[i];
        word[i] = word[j];
        word[j] = temp;
    }
}