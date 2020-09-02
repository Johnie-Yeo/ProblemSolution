package Programmers;

import Test.OldTest;

public class MakePrimeNumber {
    public static void main(String[] args) {
        new MakePrimeNumber().test();
    }

    private void test() {
        OldTest test = new OldTest();

        int[] nums;
        int result, expect;

        nums = new int[]{1, 2, 3, 4};
        result = solution(nums);
        expect = 1;
        test.test(result, expect).printResult();

        nums = new int[]{1, 2, 7, 6, 4};
        result = solution(nums);
        expect = 4;
        test.test(result, expect).printResult();
    }

    public int solution(int[] nums) {
        int result = getNumberOfCombinationToMakePrimeNumber(nums, 0, 0, 0);
        return result;
    }

    private int getNumberOfCombinationToMakePrimeNumber(int[] nums, int count, int index, int sum) {
        if(count == 3) {
            if(isPrime(sum)) {
                return 1;
            }else{
                return 0;
            }
        }
        if(index >= nums.length) {
            return 0;
        }
        int result = 0;
        int tmp = getNumberOfCombinationToMakePrimeNumber(
                nums, count+1, index+1, sum+nums[index]
        );
        result += tmp;
        tmp = getNumberOfCombinationToMakePrimeNumber(
                nums, count, index+1, sum
        );
        result += tmp;

        return result;
    }

    private boolean isPrime(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
