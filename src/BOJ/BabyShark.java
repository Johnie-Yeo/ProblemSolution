package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BabyShark {
	public static void main(String[] args) {
//		new Main().solve();
		new BabyShark().test();
	}

	private void test() {
		String input;
		int expect;

		input = "3\n" +
				"0 0 0\n" +
				"0 0 0\n" +
				"0 9 0";
		expect = 0;
		testCase(input, expect);

		input = "3\n" +
				"0 0 1\n" +
				"0 0 0\n" +
				"0 9 0";
		expect = 3;
		testCase(input, expect);

		input = "4\n" +
				"4 3 2 1\n" +
				"0 0 0 0\n" +
				"0 0 9 0\n" +
				"1 2 3 4";
		expect = 14;
		testCase(input, expect);

		input = "6\n" +
				"5 4 3 2 3 4\n" +
				"4 3 2 3 4 5\n" +
				"3 2 9 5 6 6\n" +
				"2 1 2 3 4 5\n" +
				"3 2 1 6 5 4\n" +
				"6 6 6 6 6 6";
		expect = 60;
		testCase(input, expect);

		input = "6\n" +
				"6 0 6 0 6 1\n" +
				"0 0 0 0 0 2\n" +
				"2 3 4 5 6 6\n" +
				"0 0 0 0 0 2\n" +
				"0 2 0 0 0 0\n" +
				"3 9 3 0 0 1";
		expect = 48;
		testCase(input, expect);

		input = "6\n" +
				"1 1 1 1 1 1\n" +
				"2 2 6 2 2 3\n" +
				"2 2 5 2 2 3\n" +
				"2 2 2 4 6 3\n" +
				"0 0 0 0 0 6\n" +
				"0 0 0 0 0 9";
		expect = 39;
		testCase(input, expect);
	}

	private void testCase(String input, int expect) {
		Test test = new Test();
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getTimeToFishing(N, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getTimeToFishing(N, map);
		System.out.println(result);
	}

	private class Point{
		int x, y;
		int dist;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public void setDist(int dist){
			this.dist = dist;
		}

		public void move(Point point) {
			this.x = point.x;
			this.y = point.y;
		}

		public Point clone(){
			return new Point(this.x, this.y);
		}
	}

	private int getTimeToFishing(int n, int[][] map) {
		int sharkSize = 2;
		Point sharkPoint = getSharkPoint(map, n);

		Point point = getHuntableFishPoint(map, n, sharkPoint, sharkSize);

		int time = 0;
		int eat = 0;
		while(point != null){
			time += point.dist;
			sharkPoint.move(point);
			eat++;
			if(eat == sharkSize){
				eat = 0;
				sharkSize++;
			}
			map[point.x][point.y] = 0;
			point = getHuntableFishPoint(map, n, sharkPoint, sharkSize);
		}

		return time;
	}

	private Point getHuntableFishPoint(int[][] map, int n, Point sharkPoint, int sharkSize) {
		final int[] dirX = {-1, 0, 0, 1};
		final int[] dirY = {0, -1, 1, 0};

		Queue<Point> queue = new LinkedList<>();
		Point start = sharkPoint.clone();
		start.setDist(0);
		queue.add(start);

		boolean[][] check = new boolean[n][n];
		check[start.x][start.y] = true;
		Point result = null;

		while(!queue.isEmpty()){
			Point cur = queue.poll();
			if(result != null && cur.dist >= result.dist){
				break;
			}

			for(int d = 0; d < 4; d++){
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				int dist = cur.dist;

				if(isOutOfRange(x, y, n) || check[x][y]){
					continue;
				}

				check[x][y] = true;
				Point next = new Point(x, y, dist+1);

				if(map[x][y] > 0 && map[x][y] < sharkSize){
					if(result == null){
						result = next;
					}else{
						result = getResult(next, result);
					}
				}else if(map[x][y] == 0 || map[x][y] == sharkSize){
					queue.add(next);
				}
			}
		}
		return result;
	}

	private Point getResult(Point a, Point b) {
		if(a.x != b.x){
			if(a.x < b.x){
				return a;
			}else{
				return b;
			}
		}else{
			if(a.y < b.y){
				return a;
			}else{
				return b;
			}
		}
	}

	private boolean isOutOfRange(int x, int y, int n) {
		return (x < 0 || y < 0 || x >= n || y >= n);
	}

	private Point getSharkPoint(int[][] map, int n) {
		final int SHARK = 9;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(map[i][j] == SHARK){
					map[i][j] = 0;
					return new Point(i, j);
				}
			}
		}
		return null;
	}
}
