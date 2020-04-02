package Programmers;

public class CeasarEncryption {
	public static void main(String[] args) {
		new CeasarEncryption().solve();
	}
	public void solve() {
		String str = "AB";
		int push = 1;
		String result = "BC";
		
		test(str, push, result);
		str = "z";
		push = 1;
		result ="a";
		
		test(str, push, result);
		str = "a B z";
		push = 4;
		result = "e F d";
		test(str, push, result);
	}
	public void test(String str, int push, String result) {
		if(solution(str, push).equals(result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public String solution(String str, int push) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cur == ' ') {
				result += cur;
				continue;
			}
			char moved = (char) (cur + push);
			String upperRegex = "[A-Z]";
			String lowerRegex = "[a-z]";
			
			if((""+cur).matches(upperRegex)) {
				if(!(""+moved).matches(upperRegex)) {
					moved -= 26;
				}
			}
			if((""+cur).matches(lowerRegex)) {
				if(!(""+moved).matches(lowerRegex)) {
					moved -= 26;
				}
			}
			result += moved;
		}
		return result;
	}
}
