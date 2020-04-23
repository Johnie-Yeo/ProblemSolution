package Programmers;

import Test.OldTest;

public class NumberListWithXGap {
	public static void main(String[] args) {
		new NumberListWithXGap().solve();
	}
	public void solve() {
		OldTest test = new OldTest();
		int x = 2;
		int n = 5;
		long[] result = {2,4,6,8,10};
		test.test(solution(x, n), result);

		x = 4;
		n = 3;
		result = new long[]{4,8,12};
		test.test(solution(x, n), result);

		x = -4;
		n = 2;
		result = new long[] {-4, -8};
		test.test(solution(x, n), result);
	}
	public long[] solution(int x, int n) {
		long[] answer = new long[n];
		for(int i = 1; i <= n; i++) {
			answer[i-1] = (long)x*i;
			
		}
		return answer;
	}
}