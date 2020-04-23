package Programmers;

import Test.OldTest;

public class HidePhoneNumber {
	public static void main(String[] args) {
		new HidePhoneNumber().solve();
	}
	public void solve() {
		OldTest test = new OldTest();
		String input  = "01033334444";	
		String result = "*******4444";
		test.test(solution(input), result);
		
		input = "027778888";
		result = "*****8888";
		test.test(solution(input), result);
	}
	public String solution(String input) {
		int length = input.length();
		int start = length - 4;
		String result = makeStar(start);
		result += input.substring(start);
		return result;
	}
	public String makeStar(int num) {
		String result = "";
		for(int i = 0; i < num; i++) {
			result += "*";
		}
		return result;
	}
}
