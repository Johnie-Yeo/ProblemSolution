package Programmers;

import Test.Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class CrossSteppingStone{
    public static void main(String[] args) {
        new CrossSteppingStone().test();
    }
    public void test(){
        Test test = new Test();

        int[] stones;
        int k, result, expect;

        stones = new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        k = 3;
        result = solution(stones, k);
        expect = 3;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = 200000-i;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 100000;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = i+1;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 100000;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = 1;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 1;
        test.test(result, expect).printResult();
    }
    public int solution(int[] stones, int k) {
        int size = stones.length;
        int min = getMin(stones);
        int max = getMax(stones);
        int result = -1;

        while(min <= max){
            int middle = (min+max)/2;
            if(isCrossable(stones, middle, k)){
                result = middle;
                min = middle+1;
            }else{
                max = middle-1;
            }
        }
        return result;
    }

    private boolean isCrossable(int[] stones, int friend, int limit) {
        int start = -1;
        for(int stone : stones){
            stone = stone - friend + 1;
            if(stone <= 0){
                if(start < 0){
                    start = 1;
                }else{
                    start++;
                }
            }else{
                if(start >= limit){
                    return false;
                }else{
                    start = -1;
                }
            }
        }
        if(start >= limit){
            return false;
        }
        return true;
    }

    private int getMin(int[] arr){
        int min = Integer.MAX_VALUE;
        for(int elem : arr){
            min = Math.min(min, elem);
        }
        return min;
    }
    private int getMax(int[] arr) {
        int max = 0;
        for(int elem : arr){
            max = Math.max(max, elem);
        }
        return max;
    }
}