package Programmers;

import Test.Test;

import java.util.*;

public class Scale {
    public static void main(String[] args) {
        new Scale().test();
    }

    private void test() {
        Test test = new Test();

        int[] weight;
        int result, expect;

        weight = new int[]{3, 1, 6, 2, 7, 30, 1};
        expect = 21;
        result = solution(weight);
        test.test(result, expect).printResult();

        weight = new int[10000];
        for(int i = 0; i < 10000; i++){
            weight[i] = 1;
        }
        expect = 10001;
        result = solution(weight);
        test.test(result, expect).printResult();
    }
    public int solution(int[] weight) {
        Arrays.sort(weight);
        int result = 1;
        for(int elem : weight){
            if(result >= elem){
                result += elem;
            }
        }
        return result;
    }
}
