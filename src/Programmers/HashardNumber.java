package Programmers;

import Test.OldTest;

public class HashardNumber {
	public static void main(String[] args) {
		new HashardNumber().solve();
	}
	public void solve() {
		OldTest test = new OldTest();
		
		int input = 10;
		boolean result = true;
		test.test(solution(input), result);
		
		input = 12;
		result = true;
		test.test(solution(input), result);
		
		input = 11;
		result = false;
		test.test(solution(input), result);
		
		input = 13;
		result = false;
		test.test(solution(input), result);
	}
	public boolean solution(int input) {
		int sum = getDigitSum(input);
		if(input % sum == 0) {
			return true;
		}else {
			return false;
		}
	}
	public int getDigitSum(int input) {
		int sum = 0;
		
		while(input > 0) {
			sum += input%10;
			input /= 10;
		}
		
		return sum;
	}
}
