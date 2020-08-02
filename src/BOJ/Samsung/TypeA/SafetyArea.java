package BOJ.Samsung.TypeA;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class SafetyArea {
	int[] dirX = {-1,0,1,0};
	int[] dirY = {0,-1,0,1};
	
	public static void main(String[] args) {
		new SafetyArea().solve();
	}
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		
		int [][]map = new int[N][N];
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int height = kb.nextInt();
				map[i][j] = height;
				set.add(height-1);
			}
		}
		
		kb.close();
		
		Iterator<Integer> iter = set.iterator();
		int maxNumberOfSatefyArea = 0;
		while(iter.hasNext()) {
			int rain = iter.next();
			int result = numberOfSafetyArea(N, map, rain);
			maxNumberOfSatefyArea = Math.max(maxNumberOfSatefyArea, result);
		}
		System.out.println(maxNumberOfSatefyArea);
	}
	private int numberOfSafetyArea(int n, int[][] map, int rain) {
		int [][]clone = cloneMap(map);
		int area = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(clone[i][j] <= rain) {
					if(clone[i][j] > 0) {						
						clone[i][j] *= -1;
					}
				}else {
					getSafetyArea(n, clone, i, j, rain);
					area++;
				}
			}
		}
		
		return area;
	}
	private void getSafetyArea(int N, int[][] map, int X, int Y, int rain) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		
		queue.add(new Point(X, Y));
		map[X][Y] *= -1;
		
		while(!queue.isEmpty()) {
			Point cur = queue.pop();
			
			for(int d = 0; d < 4; d++) {
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				
				if(x < 0 || y < 0 || x >= N || y >= N) {
					continue;
				}
				if(map[x][y] > rain) {
					queue.add(new Point(x, y));
				}
				if(map[x][y] > 0) {					
					map[x][y] *= -1;
				}
			}
		}
	}
	int[][] cloneMap(int [][]map) {
		int length = map.length;
		int[][] clone = new int[length][length];
		
		for(int i = 0; i < length; i++) {
			clone[i] = map[i].clone();
		}
		
		return clone;
	}
}
