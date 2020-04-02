package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class RotateArray4 {
	public static void main(String[] args) {
		new RotateArray4().solve();
	}
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Point o) {
			if(this.x-o.x == 0 && this.y-o.y == 0) {
				return true;
			}
			return false;
		}
		public Point right() {
			return new Point(this.x, this.y+1);
		}
		public Point down() {
			return new Point(this.x+1, this.y);
		}
		public Point left() {
			return new Point(this.x, this.y-1);
		}
		public Point up() {
			return new Point(this.x-1, this.y);
		}
		public Point clone() {
			return new Point(this.x, this.y);
		}
	}
	public class Rotate{
		Point point;
		int size;
		public Rotate(int x, int y, int size) {
			this.point = new Point(x, y);
			this.size = size;
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		int K = kb.nextInt();
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		ArrayList<Rotate> rotate = new ArrayList<>();
		for(int i = 0; i < K; i++) {
			int r = kb.nextInt();
			int c = kb.nextInt();
			int s = kb.nextInt();
			rotate.add(new Rotate(r, c, s));
		}
		
		kb.close();
		
		int result = getMinimumValueOf(map, rotate, Integer.MAX_VALUE);
		System.out.println(result);
	}
	private int getMinimumValueOf(int[][] original, ArrayList<Rotate> rotate, int min) {
		if(rotate.size() == 0) {
			return getValue(original);
		}
		for(int i = 0; i < rotate.size(); i++) {
			ArrayList<Rotate> cur = cloneList(rotate);
			Rotate curRotation = cur.remove(i);
			int[][] map = cloneMap(original);
			rotate(map, curRotation);
			int result = getMinimumValueOf(map, cur, min);
			if(min > result)
				min = result;
		}
		
		return min;
	}
	public ArrayList<Rotate> cloneList(ArrayList<Rotate> rotate){
		ArrayList<Rotate> clone = new ArrayList<>();
		
		for(Rotate cur : rotate) {
			clone.add(cur);
		}
		
		return clone;
	}
	public int getValue(int[][] map) {
		int N = map.length;
		int M = map[0].length;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			min = Math.min(sum, min);
		}
		return min;
	}
	public int[][] cloneMap(int [][]map){
		int N = map.length;
		int M = map[0].length;
		
		int [][]clone = new int[N][M];
		for(int i = 0; i < N; i++) {
			clone[i] = map[i].clone();
		}
			
		return clone;
	}
	public void rotate(int[][] map, Rotate rotate) {
		for(int i = 1; i <= rotate.size; i++) {
			ArrayList<Point> cycle = getCycle(rotate.point.x, rotate.point.y, i);
			
			Point last = cycle.get(cycle.size()-1);
			int prev = map[last.x-1][last.y-1];
			for(Point cur : cycle) {
				int tmp = map[cur.x-1][cur.y-1];
				map[cur.x-1][cur.y-1] = prev;
				prev = tmp;
			}
		}
	}
	public ArrayList<Point> getCycle(int x, int y, int dist){
		ArrayList<Point> result = new ArrayList<>();
		
		Point start = new Point(x - dist, y - dist);
		result.add(start);
		
		Point next = start.right();
		while(!next.equals(start)) {
			result.add(next);
			if(next.x == x - dist && next.y < y + dist) {
				next = next.right();
			}else if(next.x < x + dist && next.y == y + dist) {
				next = next.down();
			}else if(next.x == x + dist && next.y > y - dist) {
				next = next.left();
			}else if(next.x > x - dist && next.y == y - dist) {
				next = next.up();
			}
		}
		
		return result;
	}
}
