package Programmers;

public class OddNumberEvenNumber {
	public static void main(String[] args) {
		new OddNumberEvenNumber().solve();
	}
	public void solve() {
		int num = -2;
		System.out.println(num%2);
		System.out.println(solution(num));
	}
	public String solution(int num) {
		if(num % 2 == 0) {
			return "Even";
		}else {
			return "Odd";
		}
	}
}
