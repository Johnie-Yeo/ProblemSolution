package Programmers;

import Test.Test;

public class Fibonacci {
    public static void main(String[] args) {
        new Fibonacci().test();
    }

    private void test() {
        Test test = new Test();

        int input, result, expect;

        input = 3;
        result = solution(input);
        expect = 2;
        test.test(result, expect).printResult();

        input = 5;
        result = solution(input);
        expect = 5;
        test.test(result, expect).printResult();
    }

    public int solution(int n) {
        if(n == 0) {
            return 0;
        }else if(n == 1) {
            return 1;
        }

        int prev = 0;
        int cur = 1;
        for(int i = 2; i <= n; i++) {
            int tmp = cur;
            cur = (cur % 1234567 + prev % 1234567) % 1234567;
            prev = tmp;
        }
        return cur;
    }
}
