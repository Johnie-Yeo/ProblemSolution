package Programmers;

public class Soobak {
	public static void main(String[] args) {
		new Soobak().solve();
	}
	public void solve() {
		int n = 3;
		String result = "수박수";
		
		test(n, result);
		
		n = 4;
		result = "수박수박";
		
		test(n, result);
	}
	public void test(int n, String result) {
		if(solution(n).equals(result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public String solution(int n) {
		StringBuilder sb = new StringBuilder();
		int count = n / 2;
		
		for(int i = 0; i < count; i++) {
			sb.append("수박");
		}
		if(n % 2 == 1) {
			sb.append("수");
		}
		
		return sb.toString();
	}
}
