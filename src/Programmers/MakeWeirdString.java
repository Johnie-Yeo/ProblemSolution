package Programmers;

public class MakeWeirdString {
	public static void main(String[] args) {
		new MakeWeirdString().solve();
	}
	public void solve() {
		String input = "try hello world";
		String result = "TrY HeLlO WoRlD";
		
		test(input, result);
	}
	public void test(String input, String result) {
		if(solution(input).equals(result)) {
			System.out.println("Pass");
		}else {
			System.out.println("expect "+result);
			System.err.println("however "+solution(input)+" returned");
			System.err.println("Fail");
		}
	}
	public String solution(String input) {
		// 절대 좋은 알고리즘이 아니다.
		// 단지 편하게 Java 표준 method를 쓰고 싶다.
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(int i = 0; i < input.length(); i++) {
			String cur = input.substring(i, i+1);
			
			if(cur.equals(" ")) {
				sb.append(cur);
				count = 0;
				continue;
			} 
			
			if(count % 2 == 0) {
				sb.append(cur.toUpperCase());
			}else {
				sb.append(cur.toLowerCase());
			}
			count++;
		}
		return sb.toString();
	}
}
