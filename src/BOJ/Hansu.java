package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class Hansu{
    public static void main(String[] args) {
//        new Main().solve();
        new Hansu().test();
    }
    public void test(){
        OldTest<Integer> test = new OldTest<Integer>();

        int N;
        int result, expect;

        N = 110;
        result = getNumberOfHansu(N);
        expect = 99;
        test.test(result, expect).printResult();

        N = 1;
        result = getNumberOfHansu(N);
        expect = 1;
        test.test(result, expect).printResult();

        N = 210;
        result = getNumberOfHansu(N);
        expect = 105;
        test.test(result, expect).printResult();

        N = 1000;
        result = getNumberOfHansu(N);
        expect = 144;
        test.test(result, expect).printResult();
    }

    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int result = getNumberOfHansu(N);
        System.out.println(result);

        kb.close();
    }

    private int getNumberOfHansu(int n) {
        int count = 0;

        for(int i = 1; i <= n; i++){
            if(isHansu(i)){
                count++;
            }
        }

        return count;
    }

    private boolean isHansu(int n){
        if(n < 100){
            return true;
        }else if(n >= 1000){
            return false;
        }
        int[] digits = getDigit(n);
        int diff = digits[0] - digits[1];
        int length = digits.length;

        for(int i = 1; i < length-1; i++){
            if(digits[i] - digits[i+1] != diff){
                return false;
            }
        }
        return true;
    }

    private int[] getDigit(int n) {
        int[] digit = new int[3];
        int i = 2;
        while(n > 0){
            digit[i--] = n % 10;
            n /= 10;
        }
        return digit;
    }
}