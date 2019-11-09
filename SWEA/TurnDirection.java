package SWEA;

import java.util.Scanner;

public class TurnDirection {
//	public final int[] dirX = {1,0,-1,0};
//	public final int[] dirY = {0,1,0,-1};
//	public class Point {
//		int x, y;
//		public Point(int x, int y) {
//			this.x = x;
//			this.y = y;
//		}
//		public Point move(int dir) {
//			int x = this.x + dirX[dir];
//			int y = this.y + dirY[dir];
//			
//			return new Point(x, y);
//		}
//		public boolean equals(Object o) {
//			if(this.x == ((Point)o).x && this.y == ((Point)o).y) {
//				return true;
//			}
//			return false;
//		}
//		public int hashCode() {
//			int hash = 7;
//			hash = 71*hash+this.x;
//			hash = 71*hash+this.y;
//			return hash;
//		}
//	}
//	public class Pair{
//		Point point;
//		int move;
//		int dir;
//		
//		public Pair(Point p, int move, int dir) {
//			this.point = p;
//			this.move = move;
//			this.dir = dir;
//		}
//	}
	public static void main(String[] args) {
		new TurnDirection().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int x1 = kb.nextInt();
			int y1 = kb.nextInt();
			int x2 = kb.nextInt();
			int y2 = kb.nextInt();
			
			int result = getMinimumMove(x1, y1, x2, y2);
			System.out.println("#"+t+ " "+result);
		}
		
		kb.close();
	}
	public int getMinimumMove(int x1, int y1, int x2, int y2) {
		int diffX = Math.abs(x2-x1);
		int diffY = Math.abs(y2-y1);
		
		int min = Math.min(diffX, diffY);
		int max = Math.max(diffX, diffY);
		
		int result = 0;
		result += (min*2);
		for(int i = min+1; i <= max; i++) {
			if((i-min) % 2 == 1) {
				result++;
			}else {
				result += 3;
			}
		}
		return result;
//		//BFS
//		Point cur = new Point(x1, y1);
//		Point destination = new Point(x2, y2);
//		ArrayDeque<Pair> queue = new ArrayDeque<>();
//		HashSet<Point> list = new HashSet<Point>();
//		
//		int move = 0;
//		
//		queue.add(new Pair(cur, 0, -1));
//		list.add(cur);
//		
//		while(!queue.isEmpty()) {
//			Pair tmp = queue.pop();
//			cur = tmp.point;
//			int dir = tmp.dir;
//			move = tmp.move;
//			
//			if(cur.equals(destination)) {
//				break;
//			}
//			
//			
//			for(int d = 0; d < 4; d++) {
//				if((dir >= 0) && ((dir % 2 == 0 && d % 2 == 0) || (dir % 2 == 1 && d % 2 == 1))) {
//					continue;
//				}
//				Point next = cur.move(d);
//				if(next.x < -100 || next.y < -100 || next.x > 100 || next.y > 100 ||
//						list.contains(next)) {
//					continue;
//				}else {
//					list.add(next);
//					queue.add(new Pair(next, move+1, d));
//				}
//			}
//		}
//		
//		return move;
	}
}
