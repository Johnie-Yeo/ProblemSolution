package BOJ;

import SWEA.SangwonContinuosSum;
import Test.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class NormalBackPack {
    public static void main(String[] args) {
        new NormalBackPack().test();
//        new NormalBackPack().solve();
    }

    private void test() {
        Test test = new Test();

        int N, K;
        int[][] stuffs;
        int result, expect;

        N = 4;
        K = 7;
        stuffs = new int[][]{
            {6, 13},
            {4, 8},
            {3, 6},
            {5, 12}
        };
        result = getMaxValue(N, K, stuffs);
        expect = 14;
        test.test(result, expect).printResult();
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int K = kb.nextInt();

        int[][] stuffs = new int[N][2];

        for(int i = 0; i < N; i++){
            stuffs[i][0] = kb.nextInt();
            stuffs[i][1] = kb.nextInt();
        }

        int result = getMaxValue(N, K, stuffs);
        System.out.println(result);
    }

    private class Stuff{
        int weight;
        int value;
        public Stuff(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
    private int getMaxValue(int n, int k, int[][] stuffs) {
        ArrayList<Stuff> stuffList = initStuffList(stuffs);
        int row = n+1;
        int col = k+1;
        int[][] dp = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(i == 0 || j == 0){
                    continue;
                }
                if(stuffList.get(i-1).weight <= j){
                    int tmp1 = stuffList.get(i-1).value + dp[i-1][j-stuffList.get(i-1).weight];
                    int tmp2 = dp[i-1][j];
                    dp[i][j] = Math.max(tmp1, tmp2);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][k];
    }

    private ArrayList<Stuff> initStuffList(int[][] stuffs) {
        ArrayList<Stuff> list = new ArrayList<>();

        for(int[] stuff : stuffs){
            int weight = stuff[0];
            int value = stuff[1];
            Stuff cur = new Stuff(weight, value);
            list.add(cur);
        }
        return list;
    }

}
