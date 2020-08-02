package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab {
	public static void main(String[] args) {
		new Lab().test();
//		new Main().solve();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "7 7\n" +
				"2 0 0 0 1 1 0\n" +
				"0 0 1 0 1 2 0\n" +
				"0 1 1 0 1 0 0\n" +
				"0 1 0 0 0 0 0\n" +
				"0 0 0 0 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"0 1 0 0 0 0 0";
		expect = 27;
		testCase(test, input, expect);

		input = "4 6\n" +
				"0 0 0 0 0 0\n" +
				"1 0 0 0 0 2\n" +
				"1 1 1 0 0 2\n" +
				"0 0 0 0 0 2";
		expect = 9;
		testCase(test, input, expect);

		input = "8 8\n" +
				"2 0 0 0 0 0 0 2\n" +
				"2 0 0 0 0 0 0 2\n" +
				"2 0 0 0 0 0 0 2\n" +
				"2 0 0 0 0 0 0 2\n" +
				"2 0 0 0 0 0 0 2\n" +
				"0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0";
		expect = 3;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		final String NEW_LINE = "\n";
		final String SPACE = " ";
		String head = input.substring(0, 3);
		String body = input.substring(4);
		int N = Integer.parseInt(head.split(SPACE)[0]);
		int M = Integer.parseInt(head.split(SPACE)[1]);
		int[][] map = InputParser.parseStringTo2DIntArray(body);
		int result = getMaxSafetyArea(N, M, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getMaxSafetyArea(N, M, map);
		System.out.println(result);
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Point next(int n, int m){
			int x = this.x;
			int y = this.y + 1;
			if(y >= m){
				x++;
				y = 0;
			}
			if(x >= n){
				return null;
			}
			return new Point(x, y);
		}
		public Point clone(){
			return new Point(this.x, this.y);
		}
	}
	private final int WALL = 1;
	private final int VIRUS = 2;
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, -1, 0, 1};

	private int getMaxSafetyArea(int n, int m, int[][] map) {
		ArrayList<Point[]> wallComb = getWallCombination(n, m, map);

		int max = 0;
		for(Point[] walls : wallComb){
			int[][] clone = getClone(map, n);
			setWall(walls, clone);
			spreadVirus(clone, n, m);
			int safetyArea = getSafetyArea(clone);
			max = Math.max(max, safetyArea);
		}

		return max;
	}

	private ArrayList<Point[]> getWallCombination(int n, int m, int[][] map) {
		Point start = new Point(0, 0);
		int index = 0;
		Point[] walls = new Point[3];
		ArrayList<Point[]> result = dfs(start, index, walls, n, m, map);
		return result;
	}

	private ArrayList<Point[]> dfs(Point start, int index, Point[] walls, int n, int m, int[][] map) {
		ArrayList<Point[]> result = new ArrayList<>();

		if(index >= 3){
			Point[] clone = clone(walls);
			result.add(clone);
			return result;
		}else if(start == null){
			return result;
		}

		Point wall = getWall(start, map, n, m);
		if(wall == null){
			return result;
		}
		walls[index] = wall;
		Point next = wall.next(n, m);
		ArrayList<Point[]> tmp1 = dfs(next, index+1, walls, n, m, map);
		result.addAll(tmp1);

		ArrayList<Point[]> tmp2 = dfs(next, index, walls, n, m, map);
		result.addAll(tmp2);

		return result;
	}

	private Point[] clone(Point[] points) {
		Point[] clone = new Point[3];
		for(int i = 0; i < 3; i++){
			clone[i] = points[i].clone();
		}
		return clone;
	}

	private Point getWall(Point start, int[][] map, int n, int m) {
		while(start != null && !isPossibleToSetWall(start, map)){
			start = start.next(n, m);
		}
		return start;
	}

	private boolean isPossibleToSetWall(Point start, int[][] map) {
		int x = start.x;
		int y = start.y;
		return (map[x][y] == 0);
	}

	private int getSafetyArea(int[][] map) {
		int count = 0;
		for(int[] row : map){
			for(int elem : row){
				if(elem == 0){
					count++;
				}
			}
		}
		return count;
	}

	private void spreadVirus(int[][] map, int n, int m) {
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] == VIRUS){
					spread(map, i, j, n, m);
				}
			}
		}
	}

	private void spread(int[][] map, int i, int j, int n, int m) {
		Point start = new Point(i, j);
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);

		while(!queue.isEmpty()){
			Point cur = queue.poll();
			markVirus(cur, map);

			for(int d = 0; d < 4; d++){
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];

				if(isOutOfRange(x, y, n, m)){
					continue;
				}
				if(map[x][y] == 0){
					Point next = new Point(x, y);
					queue.add(next);
				}
			}
		}
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

	private void markVirus(Point cur, int[][] map) {
		int x = cur.x;
		int y = cur.y;
		map[x][y] = -2;
	}

	private void setWall(Point[] walls, int[][] map) {
		for(Point wall : walls){
			int x = wall.x;
			int y = wall.y;
			map[x][y] = WALL;
		}
	}

	private int[][] getClone(int[][] map, int n) {
		int[][] clone = new int[n][];
		for(int i = 0; i < n; i++){
			clone[i] = map[i].clone();
 		}
		return clone;
	}

}
