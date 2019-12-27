package Programmers;

public class NumberOfPY {
	public static void main(String[] args) {
		new NumberOfPY().solve();
	}
	public void solve() {
		String str = "pPoooyY";
		boolean result = true;
		test(str, result);
		
		str = "Pyy";
		result = false;
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
		int count = 0;
		
		for(int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cur == 'p' || cur == 'P') {
				count++;
			}else if(cur == 'y' || cur == 'Y') {
				count--;
			}
		}
		
		if(count == 0) {
			return true;
		}else {
			return false;
		}
	}
}
