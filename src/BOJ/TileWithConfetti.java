package BOJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TileWithConfetti {
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;		
		}
		public boolean equals(Object o) {
			if(((Point)o).x == this.x && ((Point)o).y == this.y) {
				return true;
			}
			return false;
		}
	}
	public static void main(String[] args) {
		new TileWithConfetti().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int[][] map = new int[10][10];
		ArrayList<Point> coveringNeededList = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			for(int j  = 0; j < 10; j++) {
				map[i][j] = kb.nextInt();
				if(map[i][j] == 1) {
					coveringNeededList.add(new Point(i, j));
				}
			}
		}
		
		kb.close();
		
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for(int i = 1; i <= 5; i++) {			
			hashMap.put(i, 5);
		}
		int result = getMinimumConfettiToCover(map, coveringNeededList, 0, -1, hashMap);
		System.out.println(result);
	}
	public int getMinimumConfettiToCover(int[][] map, ArrayList<Point> list, int numOfConfetti, int min, HashMap<Integer, Integer> hashMap) {
		if(list.size() <= 0) {
			if(min < 0) {
				return numOfConfetti;
			}
			return Math.min(numOfConfetti, min);
		}
		
		Point cur = list.get(0);
		for(int size = 1; size <= 5; size++) {
			if(makableSquare(cur, map, size, hashMap)) {
				hashMap.put(size, hashMap.get(size)-1);
				
				int[][] clone = cloneMap(map);
				ArrayList<Point> cloneList = new ArrayList<>();
				
				cloneList.addAll(list);
				buildSquare(cloneList, cur, clone, size);
				min = getMinimumConfettiToCover(clone, cloneList, numOfConfetti+1, min, hashMap);
				
				hashMap.put(size, hashMap.get(size)+1);
			}
		}
		return min;
	}
	private int[][] cloneMap(int[][] map) {
		int[][] clone = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			clone[i] = map[i].clone();
		}
		
		return clone;
	}
	public void buildSquare(ArrayList<Point> list, Point cur, int[][] map, int size) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int x = cur.x+i;
				int y = cur.y+j;
				Point p = new Point(x, y);
				map[x][y] = 0;
				
				removeBy(list, p);
			}
		}
	}
	public boolean makableSquare(Point cur, int[][] map, int size, HashMap<Integer, Integer> hashMap) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int x = cur.x+i;
				int y = cur.y+j;
				if(x >= 10 || y >= 10 || map[x][y] == 0 || hashMap.get(size) <= 0) {
					return false;
				}
			}
		}
		return true;
	}
	public Point removeBy(ArrayList<Point> list, Point p) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(p)) {
				Point removed = list.remove(i);
				return removed;
			}
		}
		return null;
	}
}
