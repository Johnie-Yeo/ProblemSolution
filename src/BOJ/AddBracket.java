package BOJ;


import Test.Test;

import java.util.Scanner;

public class AddBracket{
    private final char PLUS = '+';
    private final char MINUS = '-';
    private final char MULTIPLY = '*';

    public static void main(String[] args) {
//        new Main().solve();
        new AddBracket().test();
    }
    private void test(){
        Test test = new Test();

        int N;
        String formula;
        int result, expect;

//        예제 입력 1
        N = 9;
        formula = "3+8*7-9*2";
        result = getMaxResult(N, formula);
        expect = 136;
        test.test(result, expect).printResult();

//        예제 입력 2
        N = 5;
        formula = "8*3+5";
        result = getMaxResult(N, formula);
        expect = 64;
        test.test(result, expect).printResult();

        N = 7;
        formula = "8*3+5+2";
        result = getMaxResult(N, formula);
        expect = 66;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1*2+3*4*5-6*7*8*9*0";
        result = getMaxResult(N, formula);
        expect = 0;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1*2+3*4*5-6*7*8*9*9";
        result = getMaxResult(N, formula);
        expect = 426384;
        test.test(result, expect).printResult();

        N = 19;
        formula = "1-9-1-9-1-9-1-9-1-9";
        result = getMaxResult(N, formula);
        expect = 24;
        test.test(result, expect).printResult();

        N = 3;
        formula = "1-9";
        result = getMaxResult(N, formula);
        expect = -8;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        String formula = kb.next();
        int result = getMaxResult(N, formula);
        System.out.println(result);

        kb.close();
    }

    private int getMaxResult(int size, String formula) {
        int start = 2;
        int result = toInt(formula.charAt(0));
        int max = dfs(start, size, formula.toCharArray(), result);
        return max;
    }

    private int dfs(int start, int size, char[] formula, int result) {
        int max = Integer.MIN_VALUE;

        if(start -1 >= size){
            return result;
        }

        char prevOperator = getOperator(formula, start-1);
        int number1 = toInt(formula[start]);

        int cur = calculate(result, prevOperator, number1);
        int unBind = dfs(start+2, size, formula, cur);//뒤에꺼랑 bind 유무
        max = Math.max(unBind, max);

        if(start + 1 >= size){
            return max;
        }

        int number2 = toInt(formula[start+2]);
        char operator = getOperator(formula, start+1);
        cur = calculate(number1, operator, number2);
        cur = calculate(result, prevOperator, cur);
        int bind = dfs(start+4, size, formula, cur);
        max = Math.max(bind, max);

        return max;
    }

    private char getOperator(char[] formula, int index) {
        return formula[index];
    }

    private int calculate(int num1, char operator, int num2){
        switch (operator){
            case PLUS:
                return num1 + num2;
            case MINUS:
                return num1 - num2;
            case MULTIPLY:
                return num1 * num2;
            default:
                System.out.println("Wrong operator!!!!!!!!");
                System.exit(-1);
                return -1;
        }
    }

    private int toInt(char c){
        return c - '0';
    }
}