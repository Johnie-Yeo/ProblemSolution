package Leetcode.easy;

import Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum2 {
    public static void main(String[] args) {
        new TwoSum2().test();
    }

    private void test() {
        int[] numbers;
        int target;
        int[] result, expect;

        numbers = new int[]{2, 7, 11, 15};
        target = 9;
        expect = new int[]{1, 2};
        result = twoSum(numbers, target);
        Test.test(result, expect).printResult();

        numbers = new int[]{2,3,4};
        target = 6;
        expect = new int[]{1, 3};
        result = twoSum(numbers, target);
        Test.test(result, expect).printResult();

        numbers = new int[]{-1, 0};
        target = -1;
        expect = new int[]{1, 2};
        result = twoSum(numbers, target);
        Test.test(result, expect).printResult();
    }

    public int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;

        for(int i = 0; i < length - 1; i++) {
            for(int j = i+1; j < length; j++) {
                int cur = numbers[i] + numbers[j];
                if(cur == target) {
                    return new int[]{i + 1, j + 1};
                } else if(cur > target) {
                    break;
                }
            }
        }

        return null;
    }
}
