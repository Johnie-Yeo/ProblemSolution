package BOJ;

import Test.Test;

import java.util.Scanner;

public class IrritatingDinnerMenu{
    public static void main(String[] args) {
//        new Main().solve();
        new IrritatingDinnerMenu().solve();
    }
    public void test(){
        Test<String> test = new Test<String>();

        int N;
        String target;
        String roulette;
        String result, expect;

        N = 9;
        target = "I W A N T M E A T";
        roulette = "E A T I W A N T M";
        result = getProbability(N, target, roulette);
        expect = "1/9";
        test.test(result, expect).printResult();

        N = 6;
        target = "A B A B A B A B A B";
        roulette = "A B A B A B A B A B";
        result = getProbability(N, target, roulette);
        expect = "1/2";
        test.test(result, expect).printResult();

        N = 6;
        target = "A B C D E";
        roulette = "A B C D E";
        result = getProbability(N, target, roulette);
        expect = "1/5";
        test.test(result, expect).printResult();

        N = 6;
        target = "A A A A A A";
        roulette = "A A A A A A";
        result = getProbability(N, target, roulette);
        expect = "1/1";
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = Integer.parseInt(kb.nextLine());
        String target = kb.nextLine();
        String roulette = kb.nextLine();
        String result = getProbability(N, target, roulette);
        System.out.println(result);

        kb.close();
    }

    private String getProbability(int n, String targetStr, String rouletteStr) {
        char[] target = toCharArr(targetStr);
        char[] roulette = toCharArr(rouletteStr);
        roulette = expandTwice(roulette);

        int searchCount = kmp(roulette, target);
        String result = getFractionalFraction(target.length, searchCount);
        return result;
    }

    private String getFractionalFraction(int denominator, int numerator) {
        int gcd = getGcd(denominator, numerator);
        denominator = denominator / gcd;
        numerator = numerator / gcd;
        return numerator+"/"+denominator;
    }

    private int getGcd(int a, int b) {
        if(b == 0){
            return a;
        }else{
            return getGcd(b, a%b);
        }
    }

    private int kmp(char[] context, char[] key) {
        int[] pi = getPi(key);
        int keyLength = key.length;
        int contextLength = context.length;
        int keyIndex = 0;
        int count = 0;
        
        for(int i = 0; i < contextLength; i++){
            while(keyIndex > 0 && context[i] != key[keyIndex]){
                keyIndex = pi[keyIndex-1];
            }
            if(context[i] == key[keyIndex]){
                if(keyIndex == keyLength - 1){
                    count++;
                    keyIndex = pi[keyIndex];
                }else{
                    keyIndex++;
                }
            }
        }
        return count;
    }

    private int[] getPi(char[] key) {
        int compare = 0;
        int length = key.length;
        int[] pi = new int[length];

        for(int i = 1; i < length; i++){
            while(compare > 0 && key[i] != key[compare]){
                compare = pi[compare-1];
            }
            if(key[i] == key[compare]){
                pi[i] = ++compare;
            }
        }

        return pi;
    }

    private char[] expandTwice(char[] str) {
        int length = str.length;
        char[] result = new char[length*2 - 1];
        for(int i = 0; i < length; i++){
            result[i] = str[i];
            if(i < length-1){
                result[i+length] = str[i];
            }
        }
        return result;
    }

    private char[] toCharArr(String str) {
        String[] strArray = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String elem : strArray){
            sb.append(elem);
        }
        return sb.toString().toCharArray();
    }
}