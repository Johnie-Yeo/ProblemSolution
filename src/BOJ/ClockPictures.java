package BOJ;

import Test.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ClockPictures {
    private final int CYCLE = 360000;
    private final String POSSIBLE = "possible";
    private final String IMPOSSIBLE = "impossible";
    public static void main(String[] args) {
//        new ClockPictures().solve();
        new ClockPictures().test();
    }
    private void test(){
        Test<String> test = new Test<String>();

        int n;
        int[] clock1, clock2;
        String result, expect;

        n = 6;
        clock1 = new int[]{1, 2, 3, 4, 5, 6};
        clock2 = new int[]{7, 6, 5, 4, 3, 1};
        result = detectPossibility(n, clock1, clock2);
        expect = "impossible";
        test.test(result, expect).printResult();

        n = 2;
        clock1 = new int[]{0, 270000};
        clock2 = new int[]{180000, 270000};
        result = detectPossibility(n, clock1, clock2);
        expect = "possible";
        test.test(result, expect).printResult();

        n = 7;
        clock1 = new int[]{140, 130, 110, 120, 125, 100, 105};
        clock2 = new int[]{235, 205, 215, 220, 225, 200, 240};
        result = detectPossibility(n, clock1, clock2);
        expect = "impossible";
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int n = kb.nextInt();
        int[] clock1 = new int[n];
        int[] clock2 = new int[n];
        for(int i = 0; i < n; i++){
            clock1[i] = kb.nextInt();
        }
        for(int i = 0; i < n; i++){
            clock2[i] = kb.nextInt();
        }
        String result = detectPossibility(n, clock1, clock2);
        System.out.println(result);
        kb.close();
    }

    private String detectPossibility(int n, int[] clock1, int[] clock2) {
        Arrays.sort(clock1);
        Arrays.sort(clock2);
        clock1 = extendClock(n, clock1);
        int[] clock1Interval = getInterval(clock1, n*2);
        int[] clock2Interval = getInterval(clock2, n);
        if(search(clock1Interval, clock2Interval, n)){
            return POSSIBLE;
        }else{
            return IMPOSSIBLE;
        }
    }

    private boolean search(int[] context, int[] key, int n) {
        int[] pi = getPi(key, n-1);

        int keyIndex = 0;

        for(int index = 0; index < 2*n-1; index++){
            while(keyIndex > 0 && context[index] != key[keyIndex]){
                keyIndex = pi[keyIndex-1];
            }
            if(context[index] == key[keyIndex]){
                if(keyIndex == n-2){
                    return true;
                }else{
                    keyIndex++;
                }
            }
        }
        return false;
    }

    private int[] getPi(int[] key, int length){
        int compareIndex = 0;
        int[] pi = new int[length];

        for(int index = 1; index < length; index++){
            while(compareIndex > 0 && key[index] != key[compareIndex]){
                compareIndex = pi[compareIndex - 1];
            }
            if(key[index] == key[compareIndex]){
                pi[index] = ++compareIndex;
            }
        }

        return pi;
    }

    private int[] getInterval(int[] clock, int n) {
        int[] interval = new int[n-1];
        for(int i = 0; i < n-1; i++){
            interval[i] = clock[i+1] - clock[i];
        }
        return interval;
    }

    private int[] extendClock(int n, int[] clock1) {
        int[] clock = new int[2*n];
        for(int i = 0; i < 2*n; i++){
            if(i < n){
                clock[i] = clock1[i];
            }else{
                clock[i] = clock1[i-n]+CYCLE;
            }
        }
        return clock;
    }
}