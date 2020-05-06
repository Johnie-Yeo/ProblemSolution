// https://programmers.co.kr/learn/courses/30/lessons/43104

package Programmers;

import Test.OldestTest;

public class TileDecoration {
    public static void main(String[] args) {
        new TileDecoration().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        int N = 5;
        long expect = 26;
        long result = solution(N);
        test.test(result, expect);

        N = 6;
        expect = 42;
        result = solution(N);
        test.test(result, expect);
    }
    public long solution(int n){
        if(n == 1){
            return 4;
        }
        long[] finbonacci = getFibonacci(n);

        long longer = (finbonacci[n-2] + finbonacci[n-1]);
        long shorter = finbonacci[n-1];

        return 2 * (longer + shorter);
    }

    private long[] getFibonacci(int n) {
        long[] fibonacci = new long[n];
        fibonacci[0] = 1;
        if(n <= 1){
            return fibonacci;
        }
        fibonacci[1] = 1;
        for(int i = 2; i < n; i++){
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }
}
