package BOJ;

import Test.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeInvestment {
	public static void main(String[] args) {
//		new Main().solve();
		new TreeInvestment().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "1 1 1\n" +
				"1\n" +
				"1 1 1";
		expect = 1;
		testCase(test, input, expect);

		input = "1 1 4\n" +
				"1\n" +
				"1 1 1";
		expect = 0;
		testCase(test, input, expect);

		input = "5 2 1\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 2;
		testCase(test, input, expect);

		input = "5 2 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 15;
		testCase(test, input, expect);

		input = "5 2 3\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 13;
		testCase(test, input, expect);

		input = "5 2 4\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 13;
		testCase(test, input, expect);

		input = "5 2 5\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 13;
		testCase(test, input, expect);

		input = "5 2 6\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 85;
		testCase(test, input, expect);

		input = "2 2 5\n" +
				"1 2\n" +
				"2 1\n" +
				"1 1 4\n" +
				"2 2 4";
		expect = 8;
		testCase(test, input, expect);

		input = "2 2 2\n" +
				"1 2\n" +
				"2 1\n" +
				"1 1 1\n" +
				"1 1 1\n" +
				"1 1 1";
		expect = 1;
		testCase(test, input, expect);

		input = "10 1 1000\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"100 100 100 100 100 100 100 100 100 100\n" +
				"1 1 1";
		expect = 5443;
		testCase(test, input, expect);

		input = "2 1 2\n" +
				"0 100\n" +
				"0 0\n" +
				"1 2 5";
		expect = 1;
		testCase(test, input, expect);

		input = "2 1 2\n" +
				"0 100\n" +
				"0 0\n" +
				"1 2 5";
		expect = 1;
		testCase(test, input, expect);

		input = "5 2 7\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 3 2 3 2\n" +
				"2 1 3\n" +
				"3 2 3";
		expect = 71;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int M = Integer.parseInt(parsed[0].split(" ")[1]);
		int K = Integer.parseInt(parsed[0].split(" ")[2]);
		parsed = parsed[1].split("\n", N+1);
		int[][] A = new int[N][];
		for(int i = 0; i < N; i++){
			A[i] = InputParser.parseStringToIntArray(parsed[i]);
		}
		int[][] tree = InputParser.parseStringTo2DIntArray(parsed[N]);
		int result = getNumberOfLiveTreeAfterKYears(N, M, K, A, tree);
		test.test(result, expect).printResult();
	}

	private void solve() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try{
			String line = br.readLine();
			int N = Integer.parseInt(line.split(" ")[0]);
			int M = Integer.parseInt(line.split(" ")[1]);
			int K = Integer.parseInt(line.split(" ")[2]);

			int[][] A = new int[N][N];

			for(int i = 0; i < N; i++){
				line = br.readLine();
				String[] tmp = line.split(" ");
				A[i] = Arrays.stream(tmp).mapToInt(e -> Integer.parseInt(e)).toArray();
			}
			int[][] tree = new int[M][3];
			for(int i = 0; i < M; i++){
				line = br.readLine();
				String[] tmp = line.split(" ");
				tree[i] = Arrays.stream(tmp).mapToInt(e -> Integer.parseInt(e)).toArray();
			}
			br.close();
			int result = getNumberOfLiveTreeAfterKYears(N, M, K, A, tree);
			System.out.println(result);
		}catch (IOException e){}
	}


	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(new Object[]{ this.x, this.y });
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			return (this.x == p.x && this.y == p.y);
		}
	}
	private int getNumberOfLiveTreeAfterKYears(int n, int m, int k, int[][] a, int[][] trees) {
		HashMap<Point, ArrayList<Integer>> treeMap = initTreeMap(trees);
		int[][] nourishment = initNourishment(n);

		for(int year = 0; year < k; year++){
			HashMap<Point, Integer> nourishmentFromDeadTrees = spring(treeMap, nourishment, n);
			summer(nourishmentFromDeadTrees, nourishment);
			autumn(treeMap, n);
			winter(nourishment, a, n);
		}

		int result = getNumberOfLiveTree(treeMap, n);
		return result;
	}

	private HashMap<Point, Integer> spring(HashMap<Point, ArrayList<Integer>> treeMap, int[][] nourishment, int n) {
		HashMap<Point, Integer> nourishmentFromDeadTrees = new HashMap<>();

		for(Point point : treeMap.keySet()){
			ArrayList<Integer> ageList = treeMap.get(point);
			ArrayList<Integer> grownList = new ArrayList<>();
			int add = 0;

			for(int age : ageList){
				if(nourishment[point.x][point.y] >= age){
					nourishment[point.x][point.y] -= age;
					grownList.add(age+1);
				}else{
					add += (age/2);
				}
			}

			treeMap.put(point, grownList);
			nourishmentFromDeadTrees.put(point, add);
		}

		return nourishmentFromDeadTrees;
	}

	private void summer(HashMap<Point, Integer> nourishmentFromDeadTrees, int[][] nourishment) {
		for(Point point : nourishmentFromDeadTrees.keySet()){
			int x = point.x;
			int y = point.y;
			int add = nourishmentFromDeadTrees.get(point);
			nourishment[x][y] += add;
		}
	}

	private void autumn(HashMap<Point, ArrayList<Integer>> treeMap, int n) {
		ArrayList<Point> pointList = new ArrayList<>(treeMap.keySet());
		final int[]dirX = {-1, -1, -1, 0, 0, 1, 1, 1};
		final int[]dirY = {-1, 0, 1, -1, 1, -1, 0, 1};
		for(Point point : pointList){
			ArrayList<Integer> ageList = treeMap.get(point);
			int numberOfParent = getNumberOfParent(ageList);

			for(int d = 0; d < 8; d++){
				int x = point.x + dirX[d];
				int y = point.y + dirY[d];
				if(isOutOfRange(x, y, n)){
					continue;
				}
				addTree(treeMap, x, y, numberOfParent);
			}
		}
	}

	private int getNumberOfParent(ArrayList<Integer> ageList) {
		int count = 0;
		for(int age : ageList){
			if(age % 5 == 0){
				count++;
			}
		}
		return count;
	}

	private boolean isOutOfRange(int x, int y, int n) {
		return (x < 0 || y < 0 || x >= n || y >= n);
	}

	private void addTree(HashMap<Point, ArrayList<Integer>> treeMap, int x, int y, int numberOfParent) {
		Point point = new Point(x, y);
		ArrayList<Integer> ageList = treeMap.get(point) == null ? new ArrayList<>() : treeMap.get(point);
		for(int i = 0; i < numberOfParent; i++){
			ageList.add(0, 1);
		}
		treeMap.put(point, ageList);
	}

	private void winter(int[][] nourishment, int[][] a, int n) {
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				nourishment[i][j] += a[i][j];
			}
		}
	}

	private int[][] initNourishment(int n) {
		int[][] map = new int[n][n];
		Arrays.stream(map).forEach(a -> Arrays.fill(a, 5));
		return map;
	}

	private HashMap<Point, ArrayList<Integer>> initTreeMap(int[][] trees) {
		HashMap<Point, ArrayList<Integer>> map = new HashMap<>();

		for(int[] tree : trees){
			int x = tree[0] - 1;
			int y = tree[1] - 1;
			int age = tree[2];
			Point p = new Point(x, y);
			ArrayList<Integer> list = new ArrayList<>();
			list.add(age);
			map.put(p, list);
		}
		return map;
	}

	private int getNumberOfLiveTree(HashMap<Point, ArrayList<Integer>> treeMap, int n) {
		int result = 0;
		for(Point p : treeMap.keySet()){
			int size = treeMap.get(p).size();
			result += size;
		}
		return result;
	}
}
