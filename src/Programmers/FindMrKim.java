package Programmers;

public class FindMrKim {
	public static void main(String[] args) {
		new FindMrKim().solve();
	}
	public void solve() {
		String[] seoul = {"Jane", "Kim"};
		String result = "김서방은 1에 있다";
		test(seoul, result);
	}
	public void test(String[] seoul, String result) {
		if(solution(seoul).equals(result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public String solution(String[] seoul) {
		String answer = "김서방은 %s에 있다";
		
		int index = 0;
		for(String cur: seoul) {
			if(cur.equals("Kim")) {
				break;
			}
			index++;
		}
		
		String result = String.format(answer, index);
		return result;
	}
}
