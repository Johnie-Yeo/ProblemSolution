package Programmers;

import Test.OldestTest;
// https://programmers.co.kr/learn/courses/30/lessons/43165
public class TargetNumber{
    public static void main(String[] args) {
        new TargetNumber().test();
    }
    public void test(){
        OldestTest test = new OldestTest();

        int[]numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int expect = 5;
        int result = solution(numbers, target);
        test.test(result, expect);
    }
    public int solution(int[] numbers, int target) {
        int index = -1;
        int answer = 0;
        int size = numbers.length;

        answer = getNumberOfMethodsToMakeTargetNumber(numbers, target, index, size);

        return answer;
    }

    private int getNumberOfMethodsToMakeTargetNumber(int[] numbers, int target, int index, int size) {
        if(index >= size-1){
            if(getSum(numbers) == target){
                return 1;
            }
            return 0;
        }
        index++;
        int positive = getNumberOfMethodsToMakeTargetNumber(numbers, target, index, size);
        numbers[index] *= -1;
        int negative = getNumberOfMethodsToMakeTargetNumber(numbers, target, index, size);
        return positive + negative;
    }

    private int getSum(int[] numbers) {
        int result = 0;
        for(int number : numbers){
            result += number;
        }
        return result;
    }
}