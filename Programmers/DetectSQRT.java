package Programmers;

public class DetectSQRT {
	public static void main(String[] args) {
		new DetectSQRT().solve();
	}
	public void solve() {
		long input = 121;
		long result = 144;
		test(input, result);

		input = 3;
		result = -1;
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
		double root = Math.sqrt((double)input);
		double floor = Math.floor(root);
		if(root == floor) {
			root++;
			return (long) (root*root);
		}else {
			return -1;
		}
	}
}
