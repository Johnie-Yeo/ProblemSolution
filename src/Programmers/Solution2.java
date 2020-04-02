package Programmers;

import java.util.Scanner;

public class Solution2 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int x = kb.nextInt();
		int n = kb.nextInt();
		kb.close();
		long []result = solution(x,n);
		for(int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}
	private static long[] solution(int x, int n) {
		long[] answer = new long[n];
		long init = 0;
		for(int i = 0; i < n; i++) {
			answer[i] = init + x;
			init = answer[i];
		}
	    return answer;
	}
}
