package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PopulationMovement {
	public static void main(String[] args) {
//		new Main().solve();
		new PopulationMovement().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "2 20 50\n" +
				"50 30\n" +
				"20 40";
		expect = 1;
		testCase(test, input, expect);

		input = "2 40 50\n" +
				"50 30\n" +
				"20 40";
		expect = 0;
		testCase(test, input, expect);

		input = "2 20 50\n" +
				"50 30\n" +
				"30 40";
		expect = 1;
		testCase(test, input, expect);

		input = "3 5 10\n" +
				"10 15 20\n" +
				"20 30 25\n" +
				"40 22 10";
		expect = 2;
		testCase(test, input, expect);

		input = "4 10 50\n" +
				"10 100 20 90\n" +
				"80 100 60 70\n" +
				"70 20 30 40\n" +
				"50 20 100 10";
		expect = 3;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int L = Integer.parseInt(parsed[0].split(" ")[1]);
		int R = Integer.parseInt(parsed[0].split(" ")[2]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getNumberOfPopulationMovement(N, L, R, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int L = kb.nextInt();
		int R = kb.nextInt();

		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map[i][j] = kb.nextInt();
			}
		}

		int result = getNumberOfPopulationMovement(N, L, R, map);
		System.out.println(result);
		kb.close();
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, -1, 0, 1};

	private int getNumberOfPopulationMovement(int n, int l, int r, int[][] map) {
		int result = 0;
		while(isMovable(n, l, r, map)){
			result++;
		}
		return result;
	}

	private boolean isMovable(int n, int l, int r, int[][] map) {
		boolean[][] check = new boolean[n][n];
		boolean result = false;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(!check[i][j]){
					ArrayList<Point> open = getOpenArea(map, i, j, n, l, r, check);
					move(open, map);
					if(open.size() > 1){
						result = true;
					}
				}
			}
		}

		return result;
	}

	private ArrayList<Point> getOpenArea(int[][] map, int x, int y, int n, int l, int r, boolean[][] check) {
		ArrayList<Point> result = new ArrayList<>();

		int cur = map[x][y];
		result.add(new Point(x, y));
		check[x][y] = true;

		for(int d = 0; d < 4; d++){
			int i = x + dirX[d];
			int j = y + dirY[d];
			if(isOutOfRange(i, j, n) || check[i][j]){
				continue;
			}
			int diff = Math.abs(cur - map[i][j]);
			if(diff >= l && diff <= r){
				ArrayList<Point> tmp = getOpenArea(map, i, j, n, l, r, check);
				result.addAll(tmp);
			}
		}

		return result;
	}

	private boolean isOutOfRange(int x, int y, int n) {
		return (x < 0 || y < 0 || x >= n || y >= n);
	}

	private void move(ArrayList<Point> list, int[][] map) {
		int aver = getAverage(list, map);
		for(Point point : list){
			int x = point.x;
			int y = point.y;
			map[x][y] = aver;
		}
	}

	private int getAverage(ArrayList<Point> list, int[][] map) {
		int sum = getSum(list, map);
		int size = list.size();
		int result = sum / size;
		return result;
	}

	private int getSum(ArrayList<Point> list, int[][] map) {
		int sum = 0;
		for(Point point : list){
			int x = point.x;
			int y = point.y;
			sum += map[x][y];
		}
		return sum;
	}
}
