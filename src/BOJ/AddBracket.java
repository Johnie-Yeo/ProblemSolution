package BOJ;

import Test.Test;

import java.util.Scanner;

public class AddBracket{
    private final char PLUS = '+';
    private final char MINUS = '-';
    private final char MULTIPLE = '*';

    public static void main(String[] args) {
//        new AddBracket().solve();
        new AddBracket().test();
    }

    private void test() {
        Test<Integer> test = new Test<Integer>();

        int N;
        char[] formula;
        int result, expect;

        N = 9;
        formula = new char[]{'3', '+', '8', '*', '7', '-', '9', '*', '2'};
        result = maxResult(N, formula);
        expect = 136;
        test.test(result, expect).printResult();

        N = 5;
        formula = new char[]{'8', '*', '3', '+', '5'};
        result = maxResult(N, formula);
        expect = 64;
        test.test(result, expect).printResult();

        N = 7;
        formula = new char[]{'8', '*', '3', '+', '5', '+', '2'};
        result = maxResult(N, formula);
        expect = 66;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1*2+3*4*5-6*7*8*9*0".toCharArray();
        result = maxResult(N, formula);
        expect = 0;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1*2+3*4*5-6*7*8*9*9".toCharArray();
        result = maxResult(N, formula);
        expect = 426384;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1-9-1-9-1-9-1-9-1-9".toCharArray();
        result = maxResult(N, formula);
        expect = 24;
        test.test(result, expect).printResult();

        N = 9;
        formula = "1+2*3+4*5".toCharArray();
        result = maxResult(N, formula);
        expect = 105;
        test.test(result, expect).printResult();
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        String formula = kb.next();
        int result = maxResult(N, formula.toCharArray());
        System.out.println(result);
    }

    private int maxResult(int n, char[] formula) {
        int firstNumber = toInt(formula[0]);
        if(n == 1){
            return firstNumber;
        }
        int index = 1;
        int max = dfs(firstNumber, index, n, formula);
        return max;
    }

    private int dfs(int cur, int index, int size, char[] formula){
        if(index >= size){
            return cur;
        }
        char operator = formula[index];
        int number = toInt(formula[index+1]);
        int result = operate(cur, operator, number);
        int max = Integer.MIN_VALUE;

        int tmpResult = dfs(result, index+2, size, formula);
        max = Math.max(max, tmpResult);

        if(index+2 >= size){
            return max;
        }
        char nextOperator = formula[index+2];
        int nextNumber = toInt(formula[index+3]);
        int tmp = operate(number, nextOperator, nextNumber);
        result = operate(cur, operator, tmp);
        tmpResult = dfs(result, index+4, size, formula);
        max = Math.max(max, tmpResult);

        return max;
    }

    private int operate(int num1, char operator, int num2) {
        switch (operator){
            case PLUS:
                return num1 + num2;
            case MINUS:
                return num1 - num2;
            case MULTIPLE:
                return num1 * num2;
            default:
                return 0;
        }
    }

    private int toInt(char number){
        return number - '0';
    }
}