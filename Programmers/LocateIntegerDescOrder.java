package Programmers;

import java.util.Arrays;

public class LocateIntegerDescOrder {
	public static void main(String[] args) {
		new LocateIntegerDescOrder().solve();
	}
	public void solve() {
		long input = 118372;
		long result = 873211;
		test(input, result);
	}
	public void test(long input, long result) {
		if(solution(input) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public long solution(long input) {
		char[] list = toArray(input);
		
		Arrays.sort(list);
		
		return Long.parseLong(new StringBuilder().append(list).reverse().toString());
	}
	public char[] toArray(long input) {
		String tmp = Long.toString(input);
		char[] list = tmp.toCharArray();
		return list;
	}
}
