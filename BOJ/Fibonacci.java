//BOJ 1003
package BOJ;

import java.util.Scanner;

public class Fibonacci {
	public static void main(String[] args) {
		new Fibonacci().solve();
	}
	public class Pair{
		int zero;
		int one;
		public Pair(int zero, int one) {
			this.zero = zero;
			this.one = one;
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		Pair []arr = new Pair[41];
		arr[0] = new Pair(1,0);
		arr[1] = new Pair(0,1);
		int lastIndex = 1;
		
		for(int t = 1; t <= T; t ++) {
			int N = kb.nextInt();
			
			if(N > lastIndex) {
				lastIndex = getPair(lastIndex, arr, N);
			}
			System.out.println(arr[N].zero + " " + arr[N].one);
		}
		
		kb.close();
	}
	public int getPair(int lastIndex, Pair[] arr, int n) {
		for(int i = lastIndex+1; i <= n; i++) {
			int zero = arr[i-1].zero + arr[i-2].zero;
			int one = arr[i-1].one + arr[i-2].one;
			arr[i] = new Pair(zero, one);
		}
		return n;
	}
}
