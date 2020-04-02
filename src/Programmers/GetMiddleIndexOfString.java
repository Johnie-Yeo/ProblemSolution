package Programmers;


public class GetMiddleIndexOfString {

	public static void main(String[] args) {
		new GetMiddleIndexOfString().solve();
	}
	public void solve() {
		String s= "abcded";
		String result = solution(s);
		System.out.println(result);
	}
	public String solution(String s) {
		int len = s.length();
		if(len % 2 == 0) {
			return s.substring(len/2-1, len/2+1);
		}else {
			return s.substring(len/2, len/2+1);
		}
	}
}
