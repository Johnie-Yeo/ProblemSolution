package Programmers;

public class AddEachDigit {
	public static void main(String[] args) {
		new AddEachDigit().solve();
	}
	public void solve() {
		int input = 123;
		int result =6;
		
		test(input, result);
		
		input = 987;
		result = 24;
		
		test(input, result);
	}
	public void test(int input, int result) {
		if(solution(input) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public int solution(int input) {
		int sum = 0;
		while(input > 0) {
			sum += input % 10;
			input /= 10;
		}
		return sum;
	}
}
