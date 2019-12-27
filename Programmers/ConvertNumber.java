package Programmers;

public class ConvertNumber {
	public static void main(String[] args) {
		new ConvertNumber().solve();
	}
	public void solve() {
		String str = "1234";
		int result = 1234;
		test(str, result);
		
		str = "-1234";
		result = -1234;
		test(str, result);
	}
	public void test(String str, int result) {
		if(solution(str) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public int solution(String str) {
		return Integer.parseInt(str);
	}
}
