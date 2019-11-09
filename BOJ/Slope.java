package BOJ;

import java.util.Scanner;

public class Slope {
	static int N, L;
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		L = kb.nextInt();
		int [][]map = new int[N][N];
		for(int  i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				map[i][j] = kb.nextInt();
		kb.close();
		
		int []rows = new int[N];
		int []cols = new int[N];
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int a = map[i][j];
				int b = map[j][i];
				rows[j] = a;
				cols[j] = b;
			}
			result += isRoute(rows);
			result += isRoute(cols);
		}
		System.out.println(result);
	}
	private static int isRoute(int[] rows) {
		int start = 0;
		boolean []chk = new boolean[N];
		boolean up = true;
		
		for(int i = 1; i < N; i++) {
			if(Math.abs(rows[start] - rows[i]) > 1)
				return 0;
			
			if(rows[start] < rows[i]) {
				up = true;
				if(!isAvailable(rows, chk, start, up))
					return 0;
			}
			else if(rows[start] > rows[i]) {
				up = false;
				if(!isAvailable(rows, chk, start, up))
					return 0;
			}
			start++;
		}
		return 1;
	}
	private static boolean isAvailable(int[] rows, boolean[] chk, int start, boolean up) {
		int idx;
		if(!up)
			start++;
		if(chk[start])
			return false;
		if(up) {
			idx = start - L + 1;
			if(idx < 0)
				return false;
			else {
				for(int i = idx; i < start; i++) {
					if(chk[i] || rows[i] != rows[start]) {
						return false;
					}
					chk[i] = true;
				}
			}
			return true;
		}
		else {
			idx = start + L -1;
			if(idx >= N)
				return false;
			else {
				for(int i = start; i <= idx; i++) {
					if(chk[i] || rows[i] != rows[start])
						return false;
					chk[i] = true;
				}
			}
			return true;
		}
	}
}
