package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ChickenDeliver {
	public static void main(String[] args) {
//		new Main().solve();
		new ChickenDeliver().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;;

		input = "5 3\n" +
				"0 0 1 0 0\n" +
				"0 0 2 0 1\n" +
				"0 1 2 0 0\n" +
				"0 0 1 0 0\n" +
				"0 0 0 0 2";
		expect = 5;
		testCase(test, input, expect);

		input = "5 2\n" +
				"0 2 0 1 0\n" +
				"1 0 1 0 0\n" +
				"0 0 0 0 0\n" +
				"2 0 0 1 1\n" +
				"2 2 0 1 2";
		expect = 10;
		testCase(test, input, expect);

		input = "5 1\n" +
				"1 2 0 0 0\n" +
				"1 2 0 0 0\n" +
				"1 2 0 0 0\n" +
				"1 2 0 0 0\n" +
				"1 2 0 0 0";
		expect = 11;
		testCase(test, input, expect);

		input = "5 1\n" +
				"1 2 0 2 1\n" +
				"1 2 0 2 1\n" +
				"1 2 0 2 1\n" +
				"1 2 0 2 1\n" +
				"1 2 0 2 1";
		expect = 32;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int M = Integer.parseInt(parsed[0].split(" ")[1]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getMinChickenDistance(N, M, map);
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map[i][j] = kb.nextInt();
			}
		}
		int result = getMinChickenDistance(N, M, map);
		System.out.println(result);
		kb.close();
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
		public int getDist(Point point){
			int x = Math.abs(point.x - this.x);
			int y = Math.abs(point.y - this.y);
			return x + y;
		}
	}
	private final int HOME = 1;
	private final int CHICKEN = 2;
	private int getMinChickenDistance(int n, int m, int[][] map) {
		ArrayList<Point> chickenList = getList(CHICKEN, n,  map);
		ArrayList<Point> homeList = getList(HOME, n, map);

		ArrayList<ArrayList<Point>> chickenComb = getCombination(chickenList, m);

		int min = Integer.MAX_VALUE;

		for(ArrayList<Point> chicken : chickenComb){
			int cur = getAllChickenDistance(chicken, homeList);
			min = Math.min(cur, min);
		}

		return min;
	}

	private int getAllChickenDistance(ArrayList<Point> chickenList, ArrayList<Point> homeList) {
		int result = 0;

		for(Point home : homeList){
			int dist = getClosestChicken(home, chickenList);
			result += dist;
		}

		return result;
	}

	private int getClosestChicken(Point home, ArrayList<Point> chickenList) {
		int min = Integer.MAX_VALUE;

		for(Point chicken : chickenList){
			int dist = home.getDist(chicken);
			min = Math.min(dist, min);
		}

		return min;
	}

	private ArrayList<ArrayList<Point>> getCombination(ArrayList<Point> list, int m) {
		int size = list.size();
		int index = 0;
		ArrayList<Point> comb = new ArrayList<>();
		ArrayList<ArrayList<Point>> result = dfs(list, m, index, size, comb);
		return result;
	}

	private ArrayList<ArrayList<Point>> dfs(ArrayList<Point> list, int m, int index, int size, ArrayList<Point> comb) {
		ArrayList<ArrayList<Point>> result = new ArrayList<>();
		if(index >= m){
			ArrayList<Point> clone = new ArrayList<>();
			clone.addAll(comb);
			result.add(clone);
			return result;
		}

		ArrayList<Point> clone = new ArrayList<>();
		clone.addAll(list);
		for(Point cur : list){
			comb.add(cur.clone());
			clone.remove(0);
			ArrayList<ArrayList<Point>> tmp = dfs(clone, m, index+1, size-1, comb);
			if(!tmp.isEmpty()){
				result.addAll(tmp);
			}
			comb.remove(index);
		}
		return result;
	}

	private ArrayList<Point> getList(int target, int n, int[][] map) {
		ArrayList<Point> result = new ArrayList<>();

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(map[i][j] == target){
					Point p = new Point(i, j);
					result.add(p);
				}
			}
		}

		return result;
	}
}
