package Leetcode.easy;

import Test.Test;

public class TwoSum {
    public static void main(String[] args) {
        new TwoSum().test();
    }

    private void test() {
        int[] nums;
        int target;
        int[] result, expect;

        nums = new int[]{2, 7, 11, 15};
        target = 9;
        expect = new int[]{0, 1};
        result = twoSum(nums, target);
        Test.test(result, expect).printResult();

        nums = new int[]{3, 2, 4};
        target = 6;
        expect = new int[]{1, 2};
        result = twoSum(nums, target);
        Test.test(result, expect).printResult();

        nums = new int[]{3, 3};
        target = 6;
        expect = new int[]{0, 1};
        result = twoSum(nums, target);
        Test.test(result, expect).printResult();
    }

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;

        for(int i = 0; i < length-1; i++) {
            for(int j = i+1; j < length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }
}
