package BOJ;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

public class CastleDefence {
	public static void main(String[] args) {
		new CastleDefence().solve();
	}
	public final int ENEMY = 1;
	public final int[] dirX = {0, -1, 0};
	public final int[] dirY = {-1, 0, 1};
	public class Point{
		int x, y;
		int dist;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public boolean equals(Object p) {
			if(this.x == ((Point)p).x && this.y == ((Point)p).y) {
				return true;
			}
			return false;
		}
		public int hashCode() {
			return (this.x+"/"+this.y).hashCode();
		}
	}
	public class Archer{
		int y;
		int distLimit;
		public Archer(int y, int distLimit) {
			this.y = y;
			this.distLimit = distLimit;
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		int distLimit = kb.nextInt();
		
		int [][]map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		
		kb.close();
		
		int result = getMaximumKill(map, distLimit);
		System.out.println(result);
	}
	public int getMaximumKill(int[][] map, int distLimit) {
		int numberOfArcher = 3;
		Archer[] archers = new Archer[numberOfArcher];
		for(int i = 0; i < numberOfArcher; i++) {
			archers[i] = new Archer(i, distLimit);
		}
		archers[2].y--;
		int result = DFS(map, archers, 0);
		
		return result;
	}
	public int DFS(int[][] originalMap, Archer[] archers, int max) {
		int M = originalMap[0].length;
		int[][] map = cloneMap(originalMap);
		archers = getNext(M, archers);
		
		if(archers == null) {
			return max;
		}
		
		int result = numberOfKillingEnemy(map, archers);
		if(result > max) {
			max = result;
		}
		
		return  DFS(originalMap, archers, max);
	}
	public Archer[] getNext(int M, Archer[] archers) {
		archers[2].y++;
		if(archers[2].y >= M) {
			archers[1].y++;
			archers[2].y = archers[1].y+1;
		}
		if(archers[1].y >= M-1) {
			archers[0].y++;
			archers[1].y = archers[0].y+1;
			archers[2].y = archers[1].y+1;
		}
		if(archers[0].y >= M-2) {
			// �� �̻� ���� �� �� ������
			return null;
		}
		
		return archers;
	}
	public int numberOfKillingEnemy(int [][]map, Archer[] archerList) {
		int N = map.length;
		
		int kill = 0;
		for(int i = 0; i < N; i++) {
			kill += killEnemys(map, archerList);
			map = moveEnemy(map);
		}
		return kill;
	}
	public int[][] moveEnemy(int[][] map) {
		int N = map.length;
		int M = map[0].length;
		int [][]clone = new int[N-1][M];
		
		for(int i = 0; i < N-1; i++) {
			clone[i] = map[i].clone();
		}
		
		return clone;
	}
	public int killEnemys(int [][]map, Archer[] archerList) {
		int kill = 0;
		HashSet<Point> targetList = new HashSet<>();
		
		for(Archer archer : archerList) {
			Point target = targetEnemyByArcher(map, archer);
			if(target != null) {
				if(!targetList.contains(target) && map[target.x][target.y] == ENEMY) {
					targetList.add(target);
				}else {
					//already killed
				}
			}
		}
		for(Point target : targetList) {
			map[target.x][target.y] = 0;
			kill++;
		}
		return kill;
	}
	public Point targetEnemyByArcher(int[][] map, Archer archer) {
		for(int i = 1; i <= archer.distLimit; i++) {
			Point target = getClosestEnemy(map, archer, i);
			if(target != null) {
				return target;
			}
		}
		return null;
	}
	public Point getClosestEnemy(int[][] map, Archer archer, int distance) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		HashSet<Point> hash = new HashSet<>();
		
		int N = map.length;
		int M = map[0].length;
		
		queue.add(new Point(N, archer.y, 0));
		hash.add(new Point(N, archer.y));
		
		while(!queue.isEmpty()) {
			Point cur = queue.pop();
			
			for(int d = 0; d < 3; d++) {
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				int curDist = cur.dist + 1;
				
				if(x < 0 || y < 0 || x >= N || y >= M) {
					continue;
				}
				
				if(curDist > distance) {
					return null;
				}
				if(map[x][y] == ENEMY && curDist <= distance) {
					return new Point(x, y);
				}
				
				Point hashPoint = new Point(x, y);
				if(!hash.contains(hashPoint)) {
					hash.add(hashPoint);
					queue.add(new Point(x, y, curDist));
				}
				
			}
		}
		return null;
	}
	public int[][] cloneMap(int [][]map){
		int N = map.length;
		int M = map[0].length;
		
		int[][] clone = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			clone[i] = map[i].clone();
		}
		return clone;
	}
}
