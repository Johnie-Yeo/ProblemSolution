package BOJ;

import Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class KnapsackProblem {
    public static void main(String[] args) {
        new KnapsackProblem().test();
//        new Main().solve();
    }
    private void test(){
        Test test = new Test();

        int N, C;
        int[] stuff;
        int result, expect;

        N = 2;
        C = 1;
        stuff = new int[]{1, 1};
        result = getNumberOfMethods(N,C, stuff);
        expect = 3;
        test.test(result, expect).printResult();

        N = 5;
        C = 5;
        stuff = new int[]{1, 2, 3, 4, 5};
        result = getNumberOfMethods(N,C, stuff);
        expect = 10;
        test.test(result, expect).printResult();

        N = 6;
        C = 10;
        stuff = new int[]{5, 7, 12, 5, 3, 1};
        result = getNumberOfMethods(N,C, stuff);
        expect = 16;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int C = kb.nextInt();
        int[] stuff = new int[N];
        for(int i = 0; i < N; i++){
            stuff[i] = kb.nextInt();
        }
        int result = getNumberOfMethods(N, C, stuff);
        System.out.println(result);

        kb.close();
    }

    private int getNumberOfMethods(int n, int c, int[] stuff) {
        int half = n/2;
        ArrayList<Integer> headList = getCombSumList(half, c, stuff, 0, 0);
        ArrayList<Integer> tailList = getCombSumList(n, c, stuff, half, 0);
        int count = headList.size() + tailList.size();
        int[] tail = tailList.stream().mapToInt(e->e).toArray();
        Arrays.sort(tail);
        for(int elem : headList){
            for(int target : tail){
                if(elem + target <= c){
                    count++;
                }else{
                    break;
                }
            }
        }

        return count+1;
    }

    private ArrayList<Integer> getCombSumList(int n, int max, int[] stuff, int index, int weight) {
        ArrayList<Integer> list = new ArrayList<>();
        if(index == n){
            if(weight > 0)
                list.add(weight);
            return list;
        }

        ArrayList<Integer> cur = getCombSumList(n, max, stuff, index+1, weight);
        list.addAll(cur);
        if(weight + stuff[index] <= max){
            cur = getCombSumList(n, max, stuff, index+1, weight+stuff[index]);
            list.addAll(cur);
        }
        return list;
    }
}