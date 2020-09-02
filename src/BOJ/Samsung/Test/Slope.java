package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Slope {
	public static void main(String[] args) {
//		new Main().solve();
		new Slope().test();
	}

	private void test() {
		OldTest test = new OldTest();

		String input;
		int expect;

		input = "6 2\n" +
				"3 3 3 3 3 3\n" +
				"2 3 3 3 3 3\n" +
				"2 2 2 3 2 3\n" +
				"1 1 1 2 2 2\n" +
				"1 1 1 3 3 1\n" +
				"1 1 2 3 3 2";
		expect = 3;
		testCase(test, input, expect);

		input = "6 2\n" +
				"3 2 1 1 2 3\n" +
				"3 2 2 1 2 3\n" +
				"3 2 2 2 3 3\n" +
				"3 3 3 3 3 3\n" +
				"3 3 3 3 2 2\n" +
				"3 3 3 3 2 2";
		expect = 7;
		testCase(test, input, expect);

		input = "6 3\n" +
				"3 2 1 1 2 3\n" +
				"3 2 2 1 2 3\n" +
				"3 2 2 2 3 3\n" +
				"3 3 3 3 3 3\n" +
				"3 3 3 3 2 2\n" +
				"3 3 3 3 2 2";
		expect = 3;
		testCase(test, input, expect);

		input = "6 1\n" +
				"3 2 1 1 2 3\n" +
				"3 2 2 1 2 3\n" +
				"3 2 2 2 3 3\n" +
				"3 3 3 3 3 3\n" +
				"3 3 3 3 2 2\n" +
				"3 3 3 3 2 2";
		expect = 11;
		testCase(test, input, expect);
	}

	private void testCase(OldTest test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int L = Integer.parseInt(parsed[0].split(" ")[1]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getNumberOfPath(N, L, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int L = kb.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int result = getNumberOfPath(N, L, map);
		System.out.println(result);
	}

	private int getNumberOfPath(int n, int l, int[][] map) {
		ArrayList<int[]> paths = getAllPath(n, map);
		int count = 0;

		for(int[] path : paths){
			if(isCrossable(path, n, l)){
				count++;
			}
		}
		return count;
	}

	private boolean isCrossable(int[] path, int n, int l) {
		boolean allSame = true;
		for(int i = 1; i < n; i++){
			int diff = Math.abs(path[i] - path[i-1]);
			if(diff > 1){
				return false;
			}
			if(diff != 0){
				allSame = false;
			}
		}
		if(allSame){
			return true;
		}

		for(int i = 1; i <= 10; i++){
			ArrayList<Integer> list = getList(path, n, i);
			if(list.isEmpty()){
				continue;
			}
			if(containInfeasibleCase(list, path, n, l)){
				return false;
			}
		}
		return true;
	}

	private class Path{
		int depart;
		int destination;
		public Path(int depart, int destination){
			this.depart = depart;
			this.destination = destination;
		}
		public int getDist(){
			return this.destination - this.depart + 1;
		}
	}
	private boolean containInfeasibleCase(ArrayList<Integer> list, int[] path, int n, int l) {
		ArrayList<Path> paths = getPathList(list);
		for(Path cur : paths){
			int dist   = cur.getDist();
			int target = path[cur.depart];
			int left   = getValue(path, cur.depart - 1, n);
			int right  = getValue(path, cur.destination + 1, n);
			if(left < 0){
				if(right > target && dist < l){
					return true;
				}
			}else if(right < 0){
				if(left > target && dist < l){
					return true;
				}
			}else if(left > target && right > target){
				if(dist < 2 * l){
					return true;
				}
			}else if(left > target || right > target){
				if(dist < l){
					return true;
				}
			}
		}
		return false;
	}

	private ArrayList<Path> getPathList(ArrayList<Integer> list) {
		int depart = list.get(0);
		int size = list.size();
		ArrayList<Path> result = new ArrayList<>();

		for(int i = 1; i < size; i++){
			int prev = list.get(i-1);
			int cur = list.get(i);
			if(cur - prev != 1){
				int destination = prev;
				Path path = new Path(depart, destination);
				result.add(path);
				depart = cur;
			}
		}
		int destination = list.get(size-1);
		Path path = new Path(depart, destination);
		result.add(path);

		return result;
	}

	private int getValue(int[] path, int i, int n) {
		if(i < 0 || i >= n){
			return -1;
		}
		return path[i];
	}

	private ArrayList<Integer> getList(int[] path, int n, int height) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++){
			if(path[i] == height){
				list.add(i);
			}
		}
		return list;
	}

	private ArrayList<int[]> getAllPath(int n, int[][] map) {
		ArrayList<int[]> result = new ArrayList<>();

		for(int i = 0; i < n; i++){
			int[] row = new int[n];
			int[] column = new int[n];
			for(int j = 0; j < n; j++){
				row[j] 	  = map[i][j];
				column[j] = map[j][i];
			}
			result.add(row);
			result.add(column);
		}

		return result;
	}
}
