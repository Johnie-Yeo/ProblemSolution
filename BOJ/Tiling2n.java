//BOJ 11726
package BOJ;

import java.util.Scanner;

public class Tiling2n {
	public static void main(String[] args) {
		new Tiling2n().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int result = getNumberOfWaysToFillTile(N);
		System.out.println(result);
		
		kb.close();
	}
	public int getNumberOfWaysToFillTile(int N) {
        if(N <= 2) {
			return N;
		}
		int []arr = new int[N+1];
		arr[1] = 1;
		arr[2] = 2;
		
		for(int i = 3; i <= N; i++) {
			arr[i] = (arr[i-1] + arr[i-2])%10007;
			
		}
		return arr[N];
	}
}
