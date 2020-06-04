package BOJ;

import Test.*;

import java.util.*;

public class Lab3 {
	public static void main(String[] args) {
//		new Main().solve();
		new Lab3().test();
	}

	private void test() {
		String input;
		int expect;

		input = "7 3\n" +
				"2 0 0 0 1 1 0\n" +
				"0 0 1 0 1 2 0\n" +
				"0 1 1 0 1 0 0\n" +
				"0 1 0 0 0 0 0\n" +
				"0 0 0 2 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 0 0 2";
		expect = 4;
		testCase(input, expect);

		input = "7 3\n" +
				"2 0 2 0 1 1 0\n" +
				"0 0 1 0 1 2 0\n" +
				"0 1 1 2 1 0 0\n" +
				"2 1 0 0 0 0 2\n" +
				"0 0 0 2 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 2 0 2";
		expect = 4;
		testCase(input, expect);

		input = "7 4\n" +
				"2 0 2 0 1 1 0\n" +
				"0 0 1 0 1 2 0\n" +
				"0 1 1 2 1 0 0\n" +
				"2 1 0 0 0 0 2\n" +
				"0 0 0 2 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 2 0 2";
		expect = 4;
		testCase(input, expect);

		input = "7 5\n" +
				"2 0 2 0 1 1 0\n" +
				"0 0 1 0 1 2 0\n" +
				"0 1 1 2 1 0 0\n" +
				"2 1 0 0 0 0 2\n" +
				"0 0 0 2 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 2 0 2";
		expect = 3;
		testCase(input, expect);

		input = "7 3\n" +
				"2 0 2 0 1 1 0\n" +
				"0 0 1 0 1 0 0\n" +
				"0 1 1 1 1 0 0\n" +
				"2 1 0 0 0 0 2\n" +
				"1 0 0 0 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 2 0 2";
		expect = 7;
		testCase(input, expect);

		input = "7 2\n" +
				"2 0 2 0 1 1 0\n" +
				"0 0 1 0 1 0 0\n" +
				"0 1 1 1 1 0 0\n" +
				"2 1 0 0 0 0 2\n" +
				"1 0 0 0 0 1 1\n" +
				"0 1 0 0 0 0 0\n" +
				"2 1 0 0 2 0 2";
		expect = -1;
		testCase(input, expect);

		input = "5 1\n" +
				"2 2 2 1 1\n" +
				"2 1 1 1 1\n" +
				"2 1 1 1 1\n" +
				"2 1 1 1 1\n" +
				"2 2 2 1 1";
		expect = 0;
		testCase(input, expect);

		input = "4 2\n" +
				"0 1 1 0\n" +
				"2 1 1 2\n" +
				"2 1 1 2\n" +
				"0 1 1 0";
		expect = 2;
		testCase(input, expect);

		input = "5 3\n" +
				"2 2 2 0 0\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1";
		expect = 2;
		testCase(input, expect);

		input = "5 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"2 0 0 2 0\n" +
				"1 1 1 1 1";
		expect = 2;
		testCase(input, expect);

		input = "5 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"0 2 0 2 0\n" +
				"1 1 1 1 1";
		expect = 3;
		testCase(input, expect);

		input = "5 1\n" +
				"2 2 2 1 1\n" +
				"2 1 1 1 1\n" +
				"2 1 1 1 1\n" +
				"2 1 1 1 1\n" +
				"2 2 2 2 0";
		expect = 1;
		testCase(input, expect);

		input = "4 1\n" +
				"2 1 1 1\n" +
				"1 1 1 1\n" +
				"1 1 1 1\n" +
				"1 1 1 1";
		expect = 0;
		testCase(input, expect);
	}

	private void testCase(String input, int expect) {
		Test test = new Test();
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int M = Integer.parseInt(parsed[0].split(" ")[1]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getMinTimeToSpreadVirusWholeArea(N, M, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int M = kb.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i< N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getMinTimeToSpreadVirusWholeArea(N, M, map);
		System.out.println(result);
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Point clone(){
			return new Point(this.x, this.y);
		}
	}
	private int getMinTimeToSpreadVirusWholeArea(int n, int m, int[][] map) {
		if(!containEmptyArea(map)){
			return 0;
		}
		ArrayList<Point> virusable = getVirusablePoint(n, map);
		ArrayList<ArrayList<Point>> virusComb = getVirusComb(virusable, m);

		int min = -1;
		for(ArrayList<Point> virus : virusComb){
			int[][] clone = clone(map);
			int cur = getTimeToSpreadVirus(n, clone, virus);
			if(containEmptyArea(clone)){
				continue;
			}
			min = min < 0 ? cur : Math.min(cur, min);
		}
		return min;
	}

	private int[][] clone(int[][] map) {
		return Arrays.stream(map).map(row -> Arrays.stream(row).toArray()).toArray(int[][]::new);
	}

	private boolean containEmptyArea(int[][] map) {
		for(int[] row : map){
			for(int elem : row){
				if(elem == 0){
					return true;
				}
			}
		}
		return false;
	}

	private int getTimeToSpreadVirus(int n, int[][] map, ArrayList<Point> virus) {
		final int[] dirX = {-1, 0, 1, 0};
		final int[] dirY = {0, -1, 0, 1};
		final int WALL = 1;

		int value = -1;
		setMap(virus, map, value);
		Queue<Point> queue = new LinkedList<>();
		queue.addAll(virus);
		int result = -1;

		while(!queue.isEmpty()){
			Point cur = queue.poll();

			for(int d = 0; d < 4; d++){
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];

				if(isOutOfRange(x, y, n) || map[x][y] == WALL || map[x][y] < 0){
					continue;
				}
				if(map[x][y] == 2){
					value = map[cur.x][cur.y];
					map[x][y] = value - 1;
				}else{
					value = map[cur.x][cur.y] - 1;
					map[x][y] = value;
					result = Math.min(value, result);
				}

				queue.add(new Point(x, y));
			}
		}

		return (result * -1 - 1);
	}

	private boolean isOutOfRange(int x, int y, int n) {
		return (x < 0 || y < 0 || x >= n || y >= n);
	}

	private void setMap(ArrayList<Point> virus, int[][] map, int cur) {
		for(Point point : virus){
			map[point.x][point.y] = cur;
		}
	}

	private ArrayList<ArrayList<Point>> getVirusComb(ArrayList<Point> virusable, int m) {
		int index = 0;
		ArrayList<Point> cur = new ArrayList<>();
		ArrayList<ArrayList<Point>> list = dfs(virusable, index, m, cur);
		return list;
	}

	private ArrayList<ArrayList<Point>> dfs(ArrayList<Point> virusable, int index, int limit, ArrayList<Point> cur) {
		ArrayList<ArrayList<Point>> result = new ArrayList<>();

		if(index >= limit){
			result.add(clone(cur));
			return result;
		}

		ArrayList<Point> list = new ArrayList<>(virusable);
		for(Point point : virusable){
			cur.add(point);
			list.remove(0);
			ArrayList<ArrayList<Point>> tmp = dfs(list, index+1, limit, cur);
			result.addAll(tmp);
			cur.remove(index);
		}
		return result;
	}

	private ArrayList<Point> clone(ArrayList<Point> list){
		ArrayList<Point> result = new ArrayList<>();
		for(Point point : list){
			result.add(point.clone());
		}
		return result;
	}

	private ArrayList<Point> getVirusablePoint(int n, int[][] map) {
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(map[i][j] == 2){
					Point point = new Point(i, j);
					list.add(point);
				}
			}
		}
		return list;
	}
}
