package BOJ;

import Test.OlderTest;

import java.util.ArrayList;
import java.util.Scanner;

public class StringSquared{
    public static void main(String[] args) {
//        new Main().solve();
        new StringSquared().test();
    }
    public void test(){
        OlderTest<Integer> test = new OlderTest<Integer>();
        String word;
        int result, expect;

        word = "abcd";
        result = getMaxN(word);
        expect = 1;
        test.test(result, expect).printResult();

        word = "aaaa";
        result = getMaxN(word);
        expect = 4;
        test.test(result, expect).printResult();

        word = "ababab";
        result = getMaxN(word);
        expect = 3;
        test.test(result, expect).printResult();

        word = "abcabc";
        result = getMaxN(word);
        expect = 2;
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        while(true){
            String word = kb.next();
            if(word.equals(".")){
                break;
            }
            int result = getMaxN(word);
            System.out.println(result);
        }
        kb.close();
    }

    private int getMaxN(String word) {
        int length = word.length();

        for(int i = 1; i <= length; i++){
            if(length % i != 0){
                continue;
            }
            String key = word.substring(0, i);
            boolean dividable = isDividable(word, key, length, i);
            if(dividable){
                return length / i;
            }
        }
        return 0;
    }

    private boolean isDividable(String word, String key, int wordLength, int keyLength) {
        ArrayList<String> slicedWord = sliceWord(word, wordLength, keyLength);
        for(String sliced : slicedWord){
            if(!sliced.equals(key)){
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> sliceWord(String word, int wordLength, int keyLength) {
        ArrayList<String> list = new ArrayList<>();
        int start = 0;
        int end = keyLength;
        while(end <= wordLength){
            list.add(word.substring(start, end));
            start += keyLength;
            end += keyLength;
        }
        return list;
    }
}