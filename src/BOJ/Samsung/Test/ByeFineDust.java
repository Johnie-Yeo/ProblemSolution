package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ByeFineDust {
	public static void main(String[] args) {
//		new Main().solve();
		new ByeFineDust().test();
	}

	private void test() {
		String input;
		int expect;

		input = "7 8 1\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 188;
		testCase(input, expect);

		input = "7 8 2\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 188;
		testCase(input, expect);

		input = "7 8 3\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 186;
		testCase(input, expect);

		input = "7 8 4\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 178;
		testCase(input, expect);

		input = "7 8 5\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 172;
		testCase(input, expect);

		input = "7 8 20\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 71;
		testCase(input, expect);

		input = "7 8 30\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 52;
		testCase(input, expect);

		input = "7 8 50\n" +
				"0 0 0 0 0 0 0 9\n" +
				"0 0 0 0 3 0 0 8\n" +
				"-1 0 5 0 0 0 22 0\n" +
				"-1 8 0 0 0 0 0 0\n" +
				"0 0 0 0 0 10 43 0\n" +
				"0 0 5 0 15 0 0 0\n" +
				"0 0 40 0 0 0 20 0";
		expect = 46;
		testCase(input, expect);
	}

	private void testCase(String input, int expect) {
		Test test = new Test();

		String[] parsed = input.split("\n", 2);
		int R = Integer.parseInt(parsed[0].split(" ")[0]);
		int C = Integer.parseInt(parsed[0].split(" ")[1]);
		int T = Integer.parseInt(parsed[0].split(" ")[2]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getRemainedFineDust(R, C, T, map);
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);
		int R = kb.nextInt();
		int C = kb.nextInt();
		int T = kb.nextInt();
		int [][]map = new int[R][C];
		for(int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int result = getRemainedFineDust(R, C, T, map);
		System.out.println(result);
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			return (this.x == p.x && this.y == p.y);
		}

		public Point nextUp(int r, int c, Point limit) {
			Point next = new Point(this.x, this.y);
			if(next.y == 0){
				if(next.x > 0){
					next.x--;
				}else{
					next.y++;
				}
			}else if(next.x == 0){
				if(next.y < c-1){
					next.y++;
				}else{
					next.x++;
				}
			}else if(next.y == c-1){
				if(next.x < limit.x){
					next.x++;
				}else{
					next.y--;
				}
			}else if(next.x == limit.x){
				if(next.y > 0){
					next.y--;
				}else{
					return null;
				}
			}else{
				return null;
			}
			return next;
		}

		public Point nextDown(int r, int c, Point limit) {
			Point next = new Point(this.x, this.y);
			if(next.y == 0){
				if(next.x < r-1){
					next.x++;
				}else{
					next.y++;
				}
			}else if(next.x == r-1){
				if(next.y < c-1){
					next.y++;
				}else{
					next.x--;
				}
			}else if(next.y == c-1){
				if(next.x > limit.x){
					next.x--;
				}else{
					next.y--;
				}
			}else if(next.x == limit.x){
				if(next.y > 0){
					next.y--;
				}else{
					return null;
				}
			}else{
				return null;
			}
			return next;
		}
	}
	private final int CLEANER = -1;
	private int getRemainedFineDust(int R, int C, int T, int[][] map) {
		Point[] cleaner = getCleaner(R, map);
		for(int t = 0; t < T; t++){
			map = spread(cleaner, R, C, map);
			clean(cleaner, R, C, map);
		}
		int result = getRemained(map);
		return result;
	}

	private int getRemained(int[][] map) {
		int result = 0;
		for(int[] row : map){
			for(int elem : row){
				if(elem > 0){
					result += elem;
				}
			}
		}
		return result;
	}

	private void clean(Point[] cleaner, int r, int c, int[][] map) {
		cleanUp(cleaner[0], r, c, map);
		cleanDown(cleaner[1], r, c, map);
	}

	private void cleanDown(Point cleaner, int r, int c, int[][] map) {
		Point start = new Point(cleaner.x+1, 0);
		while(!start.equals(cleaner)){
			Point next = start.nextDown(r, c, cleaner);
			map[start.x][start.y] = map[next.x][next.y] == CLEANER ? 0 : map[next.x][next.y];
			start = next;
		}
	}

	private void cleanUp(Point cleaner, int r, int c, int[][] map) {
		Point start = new Point(cleaner.x-1, 0);
		while(!start.equals(cleaner)){
			Point next = start.nextUp(r, c, cleaner);
			map[start.x][start.y] = map[next.x][next.y] == CLEANER ? 0 : map[next.x][next.y];
			start = next;
		}
	}

	private int[][] spread(Point[] cleaner, int r, int c, int[][] map) {
		int[][] spread = new int[r][c];

		for(Point point : cleaner){
			spread[point.x][point.y] = CLEANER;
		}

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(map[i][j] > 0){
					ArrayList<Point> spreadablePoints = getSpreadablePoint(map, i, j, r, c);
					spreadToPoint(spread, map[i][j], i, j, spreadablePoints);
				}
			}
		}

		return spread;
	}

	private void spreadToPoint(int[][] spread, int cur, int x, int y, ArrayList<Point> spreadablePoints) {
		int size = spreadablePoints.size();
		int spreadAmount = cur / 5;
		spread[x][y] += (cur - spreadAmount*size);
		for(Point point : spreadablePoints){
			spread[point.x][point.y] += spreadAmount;
		}
	}

	private ArrayList<Point> getSpreadablePoint(int[][] map, int i, int j, int r, int c) {
		ArrayList<Point> list = new ArrayList<>();
		final int[] dirX = {-1, 0, 1, 0};
		final int[] dirY = {0, -1, 0, 1};
		for(int d = 0; d < 4; d++){
			int x = i + dirX[d];
			int y = j + dirY[d];
			if(isOutOfRange(x, y, r, c) || map[x][y] == CLEANER){
				continue;
			}
			Point point = new Point(x, y);
			list.add(point);
		}
		return list;
	}

	private boolean isOutOfRange(int x, int y, int r, int c) {
		return (x < 0 || y < 0 || x >= r || y >= c);
	}

	private Point[] getCleaner(int r, int[][] map) {
		Point[] cleaners = new Point[2];
		int index = 0;
		for(int i = 0; i < r; i++){
			if(map[i][0] == CLEANER){
				Point cur = new Point(i, 0);
				cleaners[index++] = cur;
			}
		}
		return cleaners;
	}

}
