package BOJ;

import java.util.Scanner;

public class Make1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Make1().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		kb.close();
		
		int result = countCalculatingToMake1(N);
		System.out.println(result);
	}
	public int countCalculatingToMake1(int n) {
		int []count = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			if(i == 1) {
				count[i] = 0;
			}else {
				int result = Integer.MAX_VALUE;
				int tmp = 0;
				if(i % 3 == 0) {
					tmp = count[i/3]+1;
					if(tmp < result) {
						result = tmp;
					}
				}
				if(i % 2 == 0) {
					tmp = count[i/2]+1;
					if(tmp < result) {
						result = tmp;
					}
				}
				tmp = count[i-1]+1;
				if(tmp < result) {
					result = tmp;
				}
				
				count[i] = result;
			}
		}
		
		return count[n];
	}
}
