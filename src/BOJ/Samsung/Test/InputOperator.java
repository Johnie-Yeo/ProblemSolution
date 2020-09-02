package BOJ.Samsung.Test;

import Test.*;

import java.util.Scanner;

public class InputOperator {
	public static void main(String[] args) {
//		new Main().solve();
		new InputOperator().test();
	}

	private void test() {
		OldTest test = new OldTest();

		String input;
		String expect;

		input = "2\n" +
				"5 6\n" +
				"0 0 1 0";
		expect = "30\n" +
				 "30";
		testCase(test, input, expect);

		input = "3\n" +
				"3 4 5\n" +
				"1 0 1 0";
		expect = "35\n" +
				 "17";
		testCase(test, input, expect);

		input = "6\n" +
				"1 2 3 4 5 6\n" +
				"2 1 1 1";
		expect = "54\n" +
				 "-24";
		testCase(test, input, expect);
	}

	private void testCase(OldTest test, String input, String expect) {
		String[] parsed = input.split("\n");
		int N = Integer.parseInt(parsed[0]);
		int[] A = InputParser.parseStringToIntArray(parsed[1]);
		int[] operators = InputParser.parseStringToIntArray(parsed[2]);
		String result= getMinAndMaxValue(N, A, operators);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[] A = new int[N];
		for(int i = 0; i < N; i++){
			A[i] = kb.nextInt();
		}
		int[] operators = new int[4];
		for(int i = 0; i < 4; i++) {
			operators[i] = kb.nextInt();
		}
		kb.close();
		String result = getMinAndMaxValue(N, A, operators);
		System.out.println(result);
	}

	private final int PLUS     = 0;
	private final int MINUS    = 1;
	private final int MULTIPLY = 2;
	private final int DIVIDE   = 3;

	private String getMinAndMaxValue(int n, int[] a, int[] operators) {
		int cur = a[0];
		int index = 1;
		int max = getMaxValue(n, a, operators, cur, index);
		int min = getMinValue(n, a, operators, cur, index);
		String result = max + "\n" + min;
		return result;
	}

	private int getMinValue(int n, int[] a, int[] operators, int cur, int index) {
		if(index >= n){
			return cur;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++){
			if(operators[i] > 0){
				operators[i]--;
				int calculated = calculate(cur, a[index], i);
				int temp = getMinValue(n, a, operators, calculated, index+1);
				min = Math.min(temp, min);
				operators[i]++;
			}
		}
		return min;
	}

	private int getMaxValue(int n, int[] a, int[] operators, int cur, int index) {
		if(index >= n){
			return cur;
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < 4; i++){
			if(operators[i] > 0){
				operators[i]--;
				int calculated = calculate(cur, a[index], i);
				int temp = getMaxValue(n, a, operators, calculated, index+1);
				max = Math.max(temp, max);
				operators[i]++;
			}
		}
		return max;
	}

	private int calculate(int a, int b, int operator) {
		switch (operator){
			case PLUS:
				return a + b;
			case MINUS:
				return a - b;
			case MULTIPLY:
				return a * b;
			case DIVIDE:
				return a / b;
		}
		return -1;
	}
}
