package Leetcode.medium;

import Test.Test;

public class StringToInteger {
    public static void main(String[] args) {
        new StringToInteger().test();
    }

    private void test() {
        String input;
        int result, expect;

        input = "42";
        expect = 42;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "   -42";
        expect = -42;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "4193 with words";
        expect = 4193;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "words and 987";
        expect = 0;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "-91283472332";
        expect = -2147483648;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "+-12";
        expect = 0;
        result = myAtoi(input);
        Test.test(result, expect).printResult();

        input = "";
        expect = 0;
        result = myAtoi(input);
        Test.test(result, expect).printResult();
    }

    public int myAtoi(String s) {
        int start = 0;
        int length = s.length();

        start = trimHead(s, length);
        int end = start;

        if(end < length && (s.charAt(end) == '+' || s.charAt(end) == '-')) {
            end++;
        }

        while(end < length && isDigit(s.charAt(end))) {
            end++;
        }

        if(start == end || !isDigit(s.charAt(end-1))) {
            return 0;
        }

        String result = s.substring(start, end);

        try {
            return Integer.parseInt(result);
        } catch (Exception e) {
            if(result.charAt(0) == '-') {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private int trimHead(String s, int length) {
        int index = 0;
        while(index < length && s.charAt(index) == ' ') {
            index++;
        }
        return index;
    }
}
