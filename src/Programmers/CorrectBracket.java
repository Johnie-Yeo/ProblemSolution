package Programmers;

import Test.Test;

public class CorrectBracket {
	public static void main(String[] args) {
		new CorrectBracket().solve();
	}
	public void solve() {
		Test<Boolean> test = new Test<>();

		String input = "()()";
		boolean expect = true;
		boolean result = solution(input);
		test.test(result, expect);

		input = "(())()";
		expect = true;
		result = solution(input);
		test.test(result, expect);

		input = ")()(";
		expect = false;
		result = solution(input);
		test.test(result, expect);

		input = "(()(";
		expect = false;
		result = solution(input);
		test.test(result, expect);
	}
	boolean solution(String s) {
		int count = 0;
		
		char[] list = s.toCharArray();
		if(list.length % 2 == 1) {
			return false;
		}
		for(char bracket : list) {
			if(bracket == '(') {
				count++;
			}else if(bracket == ')') {
				count--;
			}
			
			if(count < 0) {
				return false;
			}
		}
		if(count == 0) {			
			return true;
		}
		return false;
	}
}
