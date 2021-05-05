package Leetcode.easy;

import Test.Test;

public class PalindromeNumber {
    public static void main(String[] args) {
        new PalindromeNumber().test();
    }

    private void test() {
        int input;
        boolean result, expect;


        input = 12221;
        expect = true;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = -121;
        expect = false;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = 10;
        expect = false;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = -101;
        expect = false;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = 1221;
        expect = true;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = 1231;
        expect = false;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();

        input = 1000021;
        expect = false;
        result = isPalindrome(input);
        Test.test(result, expect).printResult();
    }

    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }

        String str = Integer.toString(x);
        int start = 0;
        int end = str.length()-1;

        while(start < end) {
            if(str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
