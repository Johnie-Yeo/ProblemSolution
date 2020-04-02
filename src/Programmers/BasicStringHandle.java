package Programmers;

public class BasicStringHandle {
	public static void main(String[] args) {
		new BasicStringHandle().solve();
	}
	public void solve() {
		String str = "a234";
		boolean result = false;

		test(str, result);

		str = "1234";
		result = true;

		test(str, result);
	}
	public void test(String str, boolean result) {
		if(solution(str) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public boolean solution(String str) {
		if(isNumber(str) && rightLength(str)) {
			return true;
		}
		return false;
	}
	public boolean isNumber(String str) {
		String regex = "\\d+";
		return str.matches(regex);		
	}
	public boolean rightLength(String str) {
		int length = str.length();
		if(length == 4 || length == 6) {
			return true;
		}
		return false;
	}
}
