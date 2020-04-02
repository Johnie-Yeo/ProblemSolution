package Programmers;

public class SumBetweenTwoInteger {
	public static void main(String[] args) {
		new SumBetweenTwoInteger().solve();
	}
	public void test(int a, int b, long result) {
		if(this.solution(a, b) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public void solve() {
		int a = 3, b = 5;
		long result = 12;
		test(a, b, result);

		a = 3; b =3;
		result = 3;
		test(a, b, result);

		a = 5; b = 3;
		result = 12;
		test(a, b, result);
	}
	public long solution(int a, int b) {
		long answer = 0;
		int min = Math.min(a, b);
		int max = Math.max(a, b);

		for(int i = min; i <= max; i++) {
			answer += i;
		}
		return answer;
	}
}
