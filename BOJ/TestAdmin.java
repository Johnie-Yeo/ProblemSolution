package BOJ;

import java.util.Scanner;

public class TestAdmin {
	public static void main(String[] args) {
		new TestAdmin().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int []A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = kb.nextInt();
		}
		int B = kb.nextInt();
		int C = kb.nextInt();
		kb.close();
		
		long result = getMinimumNumberOfAdmin(N, A, B, C);
		System.out.println(result);
	}
	private long getMinimumNumberOfAdmin(int n, int[] a, int b, int c) {
		long answer = 0;
		for(int i = 0; i < n; i++) {
			int tmp = a[i];
			tmp -= b;
			answer++;
			if(tmp > 0) {
				answer += (tmp/c);
				if(tmp % c > 0) {
					answer++;
				}
			}
		}
		return answer;
	}
}
