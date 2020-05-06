package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class CubeEditor{
    public static void main(String[] args) {
//        new CubeEditor().solve();
        new CubeEditor().test();
    }
    private void test(){
        OldTest<Integer> test = new OldTest<Integer>();

        String input = "abcdabcabb";
        int result = getLongestSearchableString(input);
        int expect = 3;
        test.test(result, expect).printResult();

        input = "abcdefghijklmnopqrstuvwxyzzyxalsdjflkqjwehrkqnmqwoieruqoipxlkcjvzlxkcvjoiqwerkqweiuhkhcvzxhkqwne";
        result = getLongestSearchableString(input);
        expect = 3;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        String input = kb.next();
        int result = getLongestSearchableString(input);
        System.out.println(result);
    }

    private int getLongestSearchableString(String input) {
        int max = 0;
        int length = input.length();

        for(int i = 0; i < length; i++){
            if(length - i < max){
                break;
            }
            String context = input.substring(i);
            int[] pi = getPi(context.toCharArray());
            int piMax = getMax(pi);
            max = Math.max(piMax, max);
        }

        return max;
    }

    private int getMax(int[] arr){
        int max = 0;
        for(int element : arr){
            max = Math.max(element, max);
        }
        return max;
    }

    private int getCountOfSearch(char[] context, char[] keyword){
        int[] pi = getPi(keyword);
        int count = 0;
        int keywordIndex = 0;
        int keywordLength = keyword.length;
        int contextLength = context.length;

        for(int contextIndex = 0; contextIndex < contextLength; contextIndex++){
            while(keywordIndex > 0 && context[contextIndex] != keyword[keywordIndex]){
                keywordIndex = pi[keywordIndex-1];
            }
            if(context[contextIndex] == keyword[keywordIndex]){
                if(keywordIndex == keywordLength-1){
                    count++;
                    keywordIndex = pi[keywordIndex];
                }else{
                    keywordIndex++;
                }
            }
        }

        return count;
    }
    private int[] getPi(char[] keyword){
        int compareIndex = 0;
        int length = keyword.length;
        int[] pi = new int[length];

        for(int index = 1; index < length; index++){
            while(compareIndex > 0 && keyword[compareIndex] != keyword[index]){
                compareIndex = pi[compareIndex-1];
            }
            if(keyword[index] == keyword[compareIndex]){
                pi[index] = ++compareIndex;
            }
        }
        return pi;
    }
}