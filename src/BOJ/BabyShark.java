package BOJ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BabyShark {
	public static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point addPoint(Point o) {
			return new Point(x + o.x, y + o.y);
		}
	}
	static Point []dir = { new Point(-1, 0), new Point(0, -1), new Point(0, 1), new Point(1, 0) };
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int [][]map = new int[N][N];
		int curX = 0, curY = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++) {
				map[i][j] = kb.nextInt();
				if(map[i][j] == 9) {
					curX = i;
					curY = j;
					map[i][j] = 0;
				}	
			}
		kb.close();
		System.out.println(getTimeOfGettingFish(map, curX, curY, N));		
	}
	private static int getTimeOfGettingFish(int[][] map, int curX, int curY, int N) {
		int sharkSize = 2;
		int fish = 0;
		int step = 0;
		int [][]check = new int [N][N];
		Queue<Point> cur = new ArrayDeque<Point>();
		Point p = new Point(curX, curY);
		Point removeP = null;
		cur.add(p);
		check[p.x][p.y] = 1;
		while(true) {
			if(!cur.isEmpty()) {
				p = cur.remove();
				for(int i = 0; i < 4; i++) {
					Point tmp = p.addPoint(dir[i]);
					if(tmp.x < 0 || tmp.y < 0 || tmp.x >= N || tmp.y >= N)
						continue;
					if(check[tmp.x][tmp.y] == 0 && map[tmp.x][tmp.y] <= sharkSize) {
						cur.add(tmp);
						check[tmp.x][tmp.y] = check[p.x][p.y] + 1;
						if(map[tmp.x][tmp.y] < sharkSize && map[tmp.x][tmp.y] > 0) {
							if(removeP == null)
								removeP = tmp;
							else if(check[removeP.x][removeP.y] == check[tmp.x][tmp.y]) {
								if(removeP.x > tmp.x || (removeP.x == tmp.x && removeP.y > tmp.y))
									removeP = tmp;
							}
						}
					}
				}
			}
			else {
				if(removeP == null)
					break;
				else {
					map[removeP.x][removeP.y] = 0;
					fish++;
					step += (check[removeP.x][removeP.y] - 1);
					check = new int[N][N];
					cur.removeAll(cur);
					cur.add(removeP);
					check[removeP.x][removeP.y] = 1;
					if(fish == sharkSize) {
						sharkSize++;
						fish = 0;
					}
					removeP = null;
				}
			}
		}
		return step;
	}
}
