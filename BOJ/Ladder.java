package BOJ;

import java.util.Scanner;

public class Ladder {
	public static void main(String[] args) {
		new Ladder().solve();
	}
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void down() {
			this.x++;
		}
		public void right() {
			this.y++;
		}
		public void left() {
			this.y--;
		}
		public void next(int x, int y) {
			this.right();
			if(this.y >= y) {
				this.down();
				this.y = 0;
			}
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		int H = kb.nextInt();
		
		int [][]map = new int[N+2][H+1];
		
		for(int i = 0; i < M; i++) {
			int a = kb.nextInt();
			int b = kb.nextInt();
			map[a][b] = 1;
		}
		
		kb.close();
		
		int result = getMinimumAdditionalLadder(N, M, H, map, 0);
		System.out.println(result);
	}
	public int getMinimumAdditionalLadder(int n, int m, int h, int[][] map, int result) {
		if(!isCycle(n, h, map)) {
			result++;
			
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j < h-1; j++) {
					if(map[i][j] == 0) {
						map[i][j] = 1;
						if(isCycle(n, h, map)) {
							return result;
						}
						map[i][j] = 0;
					}
				}
			}
			
			
		}else {
			return result;
		}
		
		return 0;
	}
	public boolean isCycle(int n, int h, int[][] map) {
		
		for(int i = 1; i <= n; i++) {
			Point cur = new Point(0, i);
			
			cur.down();
			while(cur.x < n+1) {
				if(map[cur.x][cur.y] == 1) {
					cur.right();
					cur.down();
				}
				else {
					if(map[cur.x][cur.y-1] == 1) {
						cur.left();
						cur.down();
					}else {
						cur.down();
					}
				}
			}
			
			if(cur.y != i) {
				return false;
			}
		}
		
		return true;
	}
}
