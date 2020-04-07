package Programmers;

import Test.Test;

public class DrawRectangleStar {
	public static void main(String[] args) {
		new DrawRectangleStar().solve();
	}
	public void solve() {
		Test test = new Test();
		
		int m = 5;
		int n = 3;
		String result = ""+
				"*****" +"\n"+ 
				"*****" +"\n"+
				"*****";
		test.test(solution(m,n), result);
	}
	public String solution(int m, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append("*");
			}
			if(i < n-1) {
				sb.append("\n");	
			}
		}
		return sb.toString();
	}
}
