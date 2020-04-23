package Programmers;

import Test.OldTest;

public class BracketConvert {
	public static void main(String[] args) {
		new BracketConvert().solve();
	}
	public void solve() {
		OldTest test = new OldTest();
		
		String input = "(()())()";
		String expect = "(()())()";
		String result = solution(input);
		test.test(result, expect);
		
		input = ")(";
		expect = "()";
		result = solution(input);
		test.test(result, expect);
		
		input = "()))((()";
		expect = "()(())()";
		result = solution(input);
		test.test(result, expect);
	}
	public String solution(String bracket) {
        String answer = getCorrectBracket(bracket, "");
        return answer;
    }
	public String getCorrectBracket(String bracket, String result) {
		if(isCorrect(bracket)) {
			return result + bracket;
		}
		 
		int index = getPivotIndexToMakeTwoBalancedBracket(bracket);
		String u = bracket.substring(0, index+1);
		String v = bracket.substring(index+1);


		if(isCorrect(u)) {
			return getCorrectBracket(v, result+u);
		}else {
			String tmp = "(";
			tmp += getCorrectBracket(v, "");
			tmp += ")";
			tmp += getReverseSurroundedBracket(u);
			return result + tmp;
		}		
	}
	public String getReverseSurroundedBracket(String bracket) {
		char[] list = bracket.toCharArray();
		int length = list.length;
		String result = "";
		for(int i = 1; i < length-1; i++) {
			if(list[i] == '(') {
				result += ')';
			}else if(list[i] == ')') {
				result += '(';
			}
		}
		return result;
	}
	public int getPivotIndexToMakeTwoBalancedBracket(String bracket) {
		char[] list = bracket.toCharArray();
		int length = list.length;
		int count = 0;
		for(int i = 0; i < length; i++) {
			if(list[i] == '(') {
				count++;
			}else if(list[i] == ')') {
				count--;
			}
			if(count == 0) {
				return i;
			}
		}
		return -1;
	}
	public boolean isCorrect(String bracket) {
		char[] list = bracket.toCharArray();
		int result = 0;
		for(char element : list) {
			if(element == '(') {
				result++;
			}else if(element == ')') {
				result--;
			}
			if(result < 0) {
				return false;
			}
		}
		if(result == 0) {
			return true;
		}else {
			return false;
		}
	}
}	
