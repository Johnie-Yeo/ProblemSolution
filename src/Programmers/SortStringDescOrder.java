package Programmers;

import java.util.Arrays;

public class SortStringDescOrder {
	public static void main(String[] args) {
		new SortStringDescOrder().solve();
	}
	public void solve() {
		String input = "Zbcdefg";
		String result = "gfedcbZ";
		test(input, result);
	}
	public void test(String input, String result) {
		if(solution(input).equals(result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public String solution(String input) {
		char[] str = input.toCharArray();
		Arrays.sort(str);
		String result = new StringBuilder(new String(str)).reverse().toString();
		return result;
	}
}
