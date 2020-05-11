package BOJ;

import Test.Test;

import java.util.Scanner;

public class ClimbingStairs{
    public static void main(String[] args) {
        new ClimbingStairs().test();
//        new Main().solve();
    }
    private void test(){
        Test test = new Test();
        
        int n;
        int[] stairs;
        int result, expect;
        
        n = 6;
        stairs = new int[]{10, 20, 15, 25, 10, 20};
        result = getMaxScore(n, stairs);
        expect = 75;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);
        
        int N = kb.nextInt();
        int[] stairs = new int[N];
        for(int i = 0; i < N; i++){
            stairs[i] = kb.nextInt();
        }
        int result = getMaxScore(N, stairs);
        System.out.println(result);
        kb.close();
    }

    private int getMaxScore(int n, int[] stairs) {
        if(n <= 2){
            return sum(stairs);
        }
        int[] dp = new int[n];
        dp[0] = stairs[0];
        dp[1] = max(stairs[0]+stairs[1], stairs[1]);
        dp[2] = max(stairs[0]+stairs[2], stairs[1]+stairs[2]);
        for(int i = 3; i < n; i++){
            int a = dp[i-2] + stairs[i];
            int b = dp[i-3] + stairs[i-1] + stairs[i];
            dp[i] = max(a, b);
        }
        return dp[n-1];
    }

    private int sum(int[] arr){
        int sum = 0;
        for(int elem : arr){
            sum += elem;
        }
        return sum;
    }
    private int max(int a, int b){
        return Math.max(a, b);
    }
}