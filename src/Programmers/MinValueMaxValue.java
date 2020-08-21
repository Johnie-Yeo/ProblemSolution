package Programmers;

import Test.Test;

import java.util.*;

public class MinValueMaxValue {
    public static void main(String[] args) {
        new MinValueMaxValue().test();
    }

    private void test() {
        Test test = new Test();

        String input, result, expect;

        input = "1 2 3 4";
        result = solution(input);
        expect = "1 4";
        test.test(result, expect).printResult();

        input = "-1 -2 -3 -4";
        result = solution(input);
        expect = "-4 -1";
        test.test(result, expect).printResult();

        input = "-1 -1";
        result = solution(input);
        expect = "-1 -1";
        test.test(result, expect).printResult();
    }
    public String solution(String s) {
        int[] arr = parseStringToIntegerArray(s);
        int min = getMin(arr);
        int max = getMax(arr);
        String result = min + " " + max;
        return result;
    }

    private int getMax(int[] list) {
        int max = Integer.MIN_VALUE;
        for(int elem : list){
            max = Math.max(elem, max);
        }
        return max;
    }

    private int getMin(int[] list) {
        int min = Integer.MAX_VALUE;
        for(int elem : list) {
            min = Math.min(elem, min);
        }
        return min;
    }

    private int[] parseStringToIntegerArray(String s) {
        String[] parsed = s.split(" ");
        int[] arr = Arrays.stream(parsed)
                .mapToInt(e -> Integer.parseInt(e))
                .toArray();
        return arr;
    }
}
