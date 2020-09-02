package BOJ;

import Test.OlderTest;

import java.util.Scanner;

public class NumberOf0InCombination{
    private class MyNumber{
        int count2, count5;
        public MyNumber(int count2, int count5){
            this.count2 = count2;
            this.count5 = count5;
        }
    }
    public static void main(String[] args) {
//        new Main().solve();
        new NumberOf0InCombination().test();
    }
    public void test(){
        OlderTest<Integer> test = new OlderTest<Integer>();
        int n, m, result, expect;

        n = 40;
        m = 20;
        result = getNumberOf0InCombination(n, m);
        expect = 1;
        test.test(result, expect).printResult();

        n = 25;
        m = 12;
        result = getNumberOf0InCombination(n, m);
        expect = 2;
        test.test(result, expect).printResult();

        n = 2000;
        m = 1000;
        result = getNumberOf0InCombination(n, m);
        expect = 1;
        test.test(result, expect).printResult();

        n = 2000000000;
        m = 1000000000;
        result = getNumberOf0InCombination(n, m);
        expect = 1;
        test.test(result, expect).printResult();

        n = 125;
        m = 31;
        result = getNumberOf0InCombination(n, m);
        expect = 3;
        test.test(result, expect).printResult();

        n = 202;
        m = 82;
        result = getNumberOf0InCombination(n, m);
        expect = 2;
        test.test(result, expect).printResult();

        n = 20;
        m = 16;
        result = getNumberOf0InCombination(n, m);
        expect = 0;
        test.test(result, expect).printResult();

        n = 20;
        m = 0;
        result = getNumberOf0InCombination(n, m);
        expect = 0;
        test.test(result, expect).printResult();

        n = 100;
        m = 1;
        result = getNumberOf0InCombination(n, m);
        expect = 2;
        test.test(result, expect).printResult();

        n = 65;
        m = 40;
        result = getNumberOf0InCombination(n, m);
        expect = 0;
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        int n = kb.nextInt();
        int m = kb.nextInt();
        int result = getNumberOf0InCombination(n, m);
        System.out.println(result);
        kb.close();
    }

    private int getNumberOf0InCombination(int n, int m) {
        int numberOf2 = getDenominator2(n) - getNumerator2(n, m);
        int a = getDenominator5(n);
        int b = getNumerator5(n, m);
        int numberOf5 = getDenominator5(n) - getNumerator5(n, m);
        int min = Math.min(numberOf2, numberOf5);
        return Math.max(0, min);
    }

    private int getNumerator2(int n, int m) {
        return getNumberOf(2, m) + getNumberOf(2, n - m);
    }

    private int getNumerator5(int n, int m) {
        int a = getNumberOf(5, m);
        int b = getNumberOf(5, n - m);
        return getNumberOf(5, m) + getNumberOf(5, n - m);
    }

    private int getDenominator2(int n) {
        return getNumberOf(2, n);
    }

    private int getDenominator5(int n) {
        return getNumberOf(5, n);
    }

    private int getNumberOf(int N, int end) {
        int count = 0;
        long number = N;

        while(number <= Integer.MAX_VALUE && number <= end){
            count += end/number;
            number *= N;
        }

        return count;
    }
}