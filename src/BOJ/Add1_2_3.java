package BOJ;

import java.util.Scanner;

public class Add1_2_3 {
	public static void main(String[] args) {
		new Add1_2_3().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int []arr = new int[12];
			arr[1] = 1;
			arr[2] = 2;
			arr[3] = 4;
			int lastIndex = 3;
			int result = getNumberOfWays(arr, lastIndex, N);
			lastIndex = result;
			System.out.println(arr[lastIndex]);
		}
		
		kb.close();
	}
	public int getNumberOfWays(int []arr, int lastIndex, int N) {
		
		for(int i = lastIndex+1; i <= N; i++) {
			arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
		}
		
		return N;
	}
}
