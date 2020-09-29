package Programmers;

import Test.Test;

public class Thievery {
    public static void main(String[] args) {
        new Thievery().test();
    }

    private void test() {
        int[] money = {1, 2, 3, 1};
        int expect = 4;
        int result = solution(money);
        Test.test(result, expect).printResult();

        money = new int[]{1, 200000, 3};
        expect = 200000;
        result = solution(money);
        Test.test(result, expect).printResult();

        money = new int[]{1, 200000, 300000000};
        expect = 300000000;
        result = solution(money);
        Test.test(result, expect).printResult();

        money = new int[]{1, 2, 300000000, 300000000};
        expect = 300000002;
        result = solution(money);
        Test.test(result, expect).printResult();

        money = new int[]{1, 2, 300000000, 300000000, 8};
        expect = 300000008;
        result = solution(money);
        Test.test(result, expect).printResult();
    }

    public int solution(int[] money) {
        int length = money.length;

        int[] dp;
        int max = 0, tmpMax;

        if(length > 3) {
            dp = new int[length];
            dp[0] = money[0];
            dp[1] = money[1];
            dp[2] = money[0] + money[2];

            tmpMax = Math.max(dp[1], dp[2]);

            for(int i = 3; i < length - 1; i++){
                dp[i] = money[i] + Math.max(dp[i-2], dp[i-3]);
                tmpMax = Math.max(tmpMax, dp[i]);
            }

            max = Math.max(max, tmpMax);
        }

        dp = new int[length];
        dp[0] = 0;
        dp[1] = money[1];
        dp[2] = money[2];

        tmpMax = Math.max(dp[1], dp[2]);

        for(int i = 3; i < length; i++){
            dp[i] = money[i] + Math.max(dp[i-2], dp[i-3]);
            tmpMax = Math.max(tmpMax, dp[i]);
        }

        max = Math.max(max, tmpMax);

        dp = new int[length];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = money[2];

        tmpMax = dp[2];

        for(int i = 3; i < length; i++){
            dp[i] = money[i] + Math.max(dp[i-2], dp[i-3]);
            tmpMax = Math.max(tmpMax, dp[i]);
        }

        max = Math.max(max, tmpMax);

        return max;
    }
}
