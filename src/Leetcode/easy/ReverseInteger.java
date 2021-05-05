package Leetcode.easy;

import Test.Test;

public class ReverseInteger {
    public static void main(String[] args) {
        new ReverseInteger().test();
    }

    private void test() {
        int input;
        int result, expect;

        input = 123;
        expect = 321;
        result = reverse(input);
        Test.test(result, expect).printResult();

        input = -123;
        expect = -321;
        result = reverse(input);
        Test.test(result, expect).printResult();

        input = 120;
        expect = 21;
        result = reverse(input);
        Test.test(result, expect).printResult();

        input = 0;
        expect = 0;
        result = reverse(input);
        Test.test(result, expect).printResult();

        input = 1534236469;
        expect = 0;
        result = reverse(input);
        Test.test(result, expect).printResult();
    }

    public int reverse(int x) {
        int result = 0;
        boolean isMinus = x < 0 ? true : false;

        if(isMinus) {
            x *= -1;
        }

        while(x > 0) {
            if(result != 0 && (result * 10 / result != 10) && (result * 10 % 10 != 0)) {
                return 0;
            }
            result *= 10;
            if((result + x % 10) < 0) {
                return 0;
            }
            result += x % 10;
            x /= 10;
        }

        if(isMinus) {
            result *= -1;
        }

        return result;
    }
}
