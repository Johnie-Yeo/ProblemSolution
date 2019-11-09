package Programmers;

public class TheYear2016 {
	public static void main(String[] args) {
		new TheYear2016().solve();
	}
	public void solve() {
		int a = 12;
		int b = 1;
		String result = solution(a, b);
		System.out.println(result);
	}
	public String solution(int a, int b) {
		String answer = "";
		String[] week = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		int[] days = {31,29,31,30,31,30,31,31,30,31,30};
		int day = 5;
		for(int i = 0; i < a-1; i++)
			day += days[i];
		for(int i = 0; i < b - 1;i++)
			day++;
		day %= 7;
		answer += week[day];
		return answer;
	}
}
