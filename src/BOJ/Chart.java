package BOJ;

import Test.OldTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Chart {
    public static void main(String[] args) {
//        new Main().solve();
        new Chart().test();
    }
    private void test(){
        OldTest test = new OldTest();

        int N;
        int[] data;
        String input;
        int result, expect;

        N = 4;
        input = "10 40 10 40";
        data = Arrays.stream(input.split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = getMaxDivideLine(N, data);
        expect = 2;
        test.test(result, expect).printResult();

        N = 4;
        input = "10 10 40 40";
        data = Arrays.stream(input.split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = getMaxDivideLine(N, data);
        expect = 2;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        String input;
        input = kb.nextLine();
        int N = Integer.parseInt(input);
        input = kb.nextLine();
        int[] data = Arrays.stream(input.split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int result = getMaxDivideLine(N, data);
        System.out.println(result);

        kb.close();
    }

    private int getMaxDivideLine(int n, int[] data) {
        int max = 0;
        boolean[] check = new boolean[n];
        int[] comb = new int[n];
        ArrayList<int[]> rateCombs = getRateCombs(data, n, 0, check, comb);

        for(int[] rates : rateCombs){
            int count = 0;
            for(int i = 0; i < n; i++){
                if(isFeasible(i, rates, n)){
                    count++;
                }
            }
            max = Math.max(count, max);
        }

        return max / 2;
    }

    private ArrayList<int[]> getRateCombs(int[] data, int n, int index, boolean[] check, int[] comb) {
        ArrayList<int[]> result = new ArrayList<>();

        if(index >= n){
            result.add(comb.clone());
            return result;
        }

        for(int i = 0; i < n; i++){
            if(!check[i]){
                check[i] = true;
                comb[index] = data[i];
                ArrayList<int[]> tmp = getRateCombs(data, n, index+1, check, comb);
                result.addAll(tmp);
                check[i] = false;
            }
        }
        return result;
    }

    private boolean isFeasible(int index, int[] data, int n) {
        int cur = 0;
        while(cur < 50){
            cur += data[index % n];
            index++;
        }
        if(cur == 50){
            return true;
        }
        return false;
    }
}