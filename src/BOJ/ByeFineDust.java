package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class Cleaner {
	static int []dirX = {-1,0,1,0};
	static int []dirY = {0,-1,0,1};
	static public class Point{
		int x, y;
		int dir = 0;
		
		public Point(int x, int y) {
			this.x= x;
			this.y= y;
		}
		public void setDir(int d) {
			dir = d;
		}
	}
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int R = kb.nextInt();
		int C = kb.nextInt();
		int T = kb.nextInt();
		int [][]map = new int[R][C];
		ArrayList<Point> cleaner = new ArrayList<>();
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++) {
				int tmp = kb.nextInt();
				map[i][j]=tmp;
				if(tmp < 0)
					cleaner.add(new Point(i, j));
			}
				
		kb.close();
		
		for(int i = 0; i < T; i++) {
			spreadDust(map);
			cleanDust(map, cleaner);
		}
		int result = calc(map, R, C);
		System.out.println(result);
	}
	private static int calc(int[][] map, int r, int c) {
		int result = 0;
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
				if(map[i][j] > 0)
					result += map[i][j];
		return result;
	}
	private static void cleanDust(int[][] map, ArrayList<Point> cleaner) {
		Point cleaner1 = cleaner.get(0);
		cleaner1.setDir(0);
		Point cleaner2 = cleaner.get(1);
		cleaner2.setDir(2);
		cleanUpper(cleaner1, map);
		cleanDown(cleaner2, map, 2);
	}
	private static void cleanDown(Point point, int[][] map, int i) {
		int x = point.x + dirX[point.dir];
		int y = point.y + dirY[point.dir];
		int d = point.dir;
		while(true) {
			Point next = moveDown(x, y, d, map, point.x);
			if(next.x == point.x && next.y == point.y) {
				map[x][y] = 0;
				break;
			}
			map[x][y] = map[next.x][next.y];
			x = next.x;
			y = next.y;
			d = next.dir;
		}
	}
	private static Point moveDown(int px, int py, int d, int[][] map, int X) {
		int x = px + dirX[d];
		int y = py + dirY[d];
		if(x < X || y < 0 || x >= map.length || y >= map[0].length) {
			d = (d + 1) % 4;
			x = px + dirX[d];
			y = py + dirY[d];
		}
		Point p = new Point(x, y);
		p.setDir(d);
		return p;
	}
	private static void cleanUpper(Point point, int[][] map) {
		int x = point.x + dirX[point.dir];
		int y = point.y + dirY[point.dir];
		int d = point.dir;
		while(true) {
			Point next = moveUpper(x, y, d, map, point.x);
			if(next.x == point.x && next.y == point.y) {
				map[x][y] = 0;
				break;
			}
			map[x][y] = map[next.x][next.y];
			x = next.x;
			y = next.y;
			d = next.dir;
		}
	}
	private static Point moveUpper(int px, int py, int d, int[][] map, int X) {
		int x = px + dirX[d];
		int y = py + dirY[d];
		if(x < 0 || y < 0 || x > X || y >= map[0].length) {
			d = (d + 4 -1) % 4;
			x = px + dirX[d];
			y = py + dirY[d];
		}
		Point p = new Point(x, y);
		p.setDir(d);
		return p;
	}
	private static void spreadDust(int[][] map) {
		int R = map.length;
		int C = map[0].length;
		int [][]tmpMap = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) {
					int count = 0;
					for(int d = 0; d < 4; d++) {
						int x = i + dirX[d];
						int y = j + dirY[d];
						if(x<0 || y< 0 || x>=R || y>=C || map[x][y] < 0)
							continue;
						count++;
						tmpMap[x][y] += map[i][j] / 5;
					}
					tmpMap[i][j] += (map[i][j] - ((map[i][j]/5) * count));
				}
			}
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] >= 0)
					map[i][j] = tmpMap[i][j];
			}
		}
	}
}
