package BOJ;

import Test.OldTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CaesarCipher{
    private class Caesar{
        String caesar;
        int length;
        HashMap<Character, Integer> map;
        public Caesar(String caesar){
            this.caesar = caesar;
            this.length = caesar.length();
            this.map = this.setMap(caesar);
        }

        private HashMap<Character, Integer> setMap(String caesar) {
            HashMap<Character, Integer> map = new HashMap<>();
            int length = caesar.length();
            for(int i = 0; i < length; i++){
                map.put(caesar.charAt(i), i);
            }
            return map;
        }

        public String shift(String str, int shift){
            StringBuilder sb = new StringBuilder();
            int length = str.length();

            for(int i = 0; i < length; i++){
                char shifted = this.shift(str.charAt(i), shift);
                sb.append(shifted);
            }

            return sb.toString();
        }

        private char shift(char c, int shift){
            int index = map.get(c);
            index = (index+shift) % this.length;
            return this.caesar.charAt(index);
        }
    }
    public static void main(String[] args) {
//        new Main().solve();
        new CaesarCipher().test();
    }
    public void test(){
        OldTest<String> test = new OldTest<String>();

        String A, W, S;
        String result, expect;

        A = "ABC";
        W = "ABC";
        S = "ABCBBBABC";
        result = getSolution(A, W, S);
        expect = "no solution";
        test.test(result, expect).printResult();

        A = "ABC";
        W = "ABC";
        S = "ABCBCAABC";
        result = getSolution(A, W, S);
        expect = "unique: 1";
        test.test(result, expect).printResult();

        A = "D7a";
        W = "D7a";
        S = "D7aaD77aDD7a";
        result = getSolution(A, W, S);
        expect = "ambiguous: 1 2";
        test.test(result, expect).printResult();

        A = "ABC";
        W = "ABC";
        S = "ABC";
        result = getSolution(A, W, S);
        expect = "unique: 0";
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        
        for(int i = 0; i < N; i++){
            String A = kb.next();
            String W = kb.next();
            String S = kb.next();
            String result = getSolution(A, W, S);
            System.out.println(result);
        }

        kb.close();
    }

    private String getSolution(String a, String w, String s) {
        int maxShift = a.length();
        ArrayList<Integer> list = new ArrayList<>();
        Caesar caesar = new Caesar(a);

        for(int shift = 0; shift < maxShift; shift++){
            String cipher = caesar.shift(w, shift);
            int search = getSearchCount(s.toCharArray(), cipher.toCharArray());
            if(search == 1){
                list.add(shift);
            }
        }

        String result = getResult(list);
        return result;
    }

    private int getSearchCount(char[] context, char[] keyword) {
        int[] pi = getPi(keyword);
        int count = 0;
        int contextLength = context.length;
        int keywordLength = keyword.length;
        int keyIndex = 0;

        for(int i = 0; i < contextLength; i++){
            while(keyIndex > 0 && context[i] != keyword[keyIndex]){
                keyIndex = pi[keyIndex - 1];
            }
            if(context[i] == keyword[keyIndex]){
                if(keyIndex == keywordLength -1){
                    count++;
                    keyIndex = pi[keyIndex];
                }else{
                    keyIndex++;
                }
            }
        }
        return count;
    }

    private int[] getPi(char[] word){
        int compareIndex = 0;
        int length = word.length;
        int[] pi = new int[length];

        for(int i = 1; i < length; i++){
            while(compareIndex > 0 && word[compareIndex] != word[i]){
                compareIndex = pi[compareIndex - 1];
            }
            if(word[compareIndex] == word[i]){
                pi[i] = ++compareIndex;
            }
        }

        return pi;
    }

    private String getResult(ArrayList<Integer> list) {
        if(list.size() == 0){
            return "no solution";
        }else if(list.size() == 1){
            return "unique: "+list.get(0);
        }else{
            return "ambiguous: "+toString(list);
        }
    }

    private String toString(ArrayList<Integer> list){
        StringBuilder sb = new StringBuilder();
        for(int element : list){
            sb.append(element);
            sb.append(" ");
        }
        return sb.toString().substring(0, sb.length()-1);
    }
}