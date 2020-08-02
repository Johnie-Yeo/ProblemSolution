package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CCTV {
	public static void main(String[] args) {
//		new Main().solve();
		new CCTV().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "4 6\n" +
				"0 0 0 0 0 0\n" +
				"0 0 0 0 0 0\n" +
				"0 0 1 0 6 0\n" +
				"0 0 0 0 0 0";
		expect = 20;
		testCase(test, input, expect);

		input = "6 6\n" +
				"0 0 0 0 0 0\n" +
				"0 2 0 0 0 0\n" +
				"0 0 0 0 6 0\n" +
				"0 6 0 0 2 0\n" +
				"0 0 0 0 0 0\n" +
				"0 0 0 0 0 5";
		expect = 15;
		testCase(test, input, expect);

		input = "6 6\n" +
				"1 0 0 0 0 0\n" +
				"0 1 0 0 0 0\n" +
				"0 0 1 0 0 0\n" +
				"0 0 0 1 0 0\n" +
				"0 0 0 0 1 0\n" +
				"0 0 0 0 0 1";
		expect = 6;
		testCase(test, input, expect);

		input = "6 6\n" +
				"1 0 0 0 0 0\n" +
				"0 1 0 0 0 0\n" +
				"0 0 1 5 0 0\n" +
				"0 0 5 1 0 0\n" +
				"0 0 0 0 1 0\n" +
				"0 0 0 0 0 1";
		expect = 2;
		testCase(test, input, expect);

		input = "1 7\n" +
				"0 1 2 3 4 5 6";
		expect = 0;
		testCase(test, input, expect);

		input = "3 7\n" +
				"4 0 0 0 0 0 0\n" +
				"0 0 0 2 0 0 0\n" +
				"0 0 0 0 0 0 4";
		expect = 0;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0].split(" ")[0]);
		int M = Integer.parseInt(parsed[0].split(" ")[1]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getMinBlindSpot(N, M, map);
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
		int result = getMinBlindSpot(N, M, map);
		System.out.println(result);
		kb.close();
	}

	private final int WALL = 6;
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, -1, 0, 1};

	private class Camera{
		int number;
		int x, y;
		ArrayList<Integer> dir;
		public Camera(int number, int x, int y){
			this.number = number;
			this.x = x;
			this.y = y;
			this.dir = new ArrayList<>();
		}
		public void setDir(int dir){
			this.dir.add(dir);
		}
		public void setDirs(ArrayList<Integer> dirs){
			ArrayList<Integer> dir = dirs.stream().collect(Collectors.toCollection(ArrayList::new));
			this.dir = dir;
		}
		public Camera clone(){
			Camera camera = new Camera(this.number, this.x, this.y);
			camera.setDirs(this.dir);
			return camera;
		}
	}

	private int getMinBlindSpot(int n, int m, int[][] map) {
		ArrayList<ArrayList<Camera>> cameraComb = getCameraComb(n, m, map);
		int min = Integer.MAX_VALUE;

		for(ArrayList<Camera> cameras: cameraComb){
			int[][] clone = getClone(map);
			checkAllBlindSpot(n, m, clone, cameras);
			int cur = getBlindSpot(clone);
			min = Math.min(cur, min);
		}
		return min;
	}

	private void checkAllBlindSpot(int n, int m, int[][] map, ArrayList<Camera> cameras) {
		for(Camera camera : cameras){
			checkBlindSpot(camera, n, m, map);
		}
	}

	private void checkBlindSpot(Camera camera, int n, int m, int[][] map) {
		ArrayList<Integer> dirs = camera.dir;

		for(int dir : dirs){
			int x = camera.x;
			int y = camera.y;
			while(true){
				x = x + dirX[dir];
				y = y + dirY[dir];
				if(isOutOfRange(x, y, n, m) || map[x][y] == WALL){
					break;
				}
				map[x][y] = -1;
			}
		}
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

	private ArrayList<ArrayList<Camera>> getCameraComb(int n, int m, int[][] map) {
		ArrayList<Camera> cameraList = getCameraList(n, m, map);
		int size = cameraList.size();
		int index = 0;
		ArrayList<ArrayList<Camera>> result = dfs(cameraList, index, size);
		return result;
	}

	private ArrayList<ArrayList<Camera>> dfs(ArrayList<Camera> cameraList, int index, int size) {
		ArrayList<ArrayList<Camera>> result = new ArrayList<>();
		if(index >= size){
			ArrayList<Camera> cur = clone(cameraList);
			result.add(cur);
			return result;
		}
		Camera cur = cameraList.get(index);
		ArrayList<ArrayList<Integer>> dirs = getPossibleDirList(cur.number);
		for(ArrayList<Integer> dir : dirs){
			cur.setDirs(dir);
			ArrayList<ArrayList<Camera>> tmp = dfs(cameraList, index+1, size);
			result.addAll(tmp);
		}
		return result;
	}

	private ArrayList<ArrayList<Integer>> getPossibleDirList(int number) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		ArrayList<Integer> tmp;
		switch (number){
			case 1:
				for(int i = 0; i < 4; i++){
					tmp = new ArrayList<>();
					tmp.add(i);
					list.add(tmp);
				}
				break;
			case 2:
				tmp = new ArrayList<>();
				tmp.add(0); tmp.add(2);
				list.add(tmp);

				tmp = new ArrayList<>();
				tmp.add(1); tmp.add(3);
				list.add(tmp);
				break;
			case 3:
				tmp = new ArrayList<>();
				tmp.add(0); tmp.add(1);
				list.add(tmp);

				tmp = new ArrayList<>();
				tmp.add(1); tmp.add(2);
				list.add(tmp);
				tmp = new ArrayList<>();
				tmp.add(2); tmp.add(3);
				list.add(tmp);

				tmp = new ArrayList<>();
				tmp.add(3); tmp.add(0);
				list.add(tmp);
				break;
			case 4:
				tmp = new ArrayList<>();
				tmp.add(0); tmp.add(1); tmp.add(2);
				list.add(tmp);

				tmp = new ArrayList<>();
				tmp.add(1); tmp.add(2); tmp.add(3);
				list.add(tmp);
				tmp = new ArrayList<>();
				tmp.add(2); tmp.add(3); tmp.add(0);
				list.add(tmp);

				tmp = new ArrayList<>();
				tmp.add(3); tmp.add(0); tmp.add(1);
				list.add(tmp);
				break;
			case 5:
				tmp = new ArrayList<>();
				tmp.add(0); tmp.add(1); tmp.add(2); tmp.add(3);
				list.add(tmp);
				break;
		}
		return list;
	}

	private ArrayList<Camera> clone(ArrayList<Camera> cameras){
		ArrayList<Camera> clone = cameras.stream()
										.map(e -> e.clone())
										.collect(Collectors.toCollection(ArrayList::new));
		return clone;
	}

	private ArrayList<Camera> getCameraList(int n, int m, int[][] map) {
		ArrayList<Camera> list = new ArrayList<>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				int number = map[i][j];
				if(number > 0 && number < 6){
					Camera camera = new Camera(number, i, j);
					list.add(camera);
				}
			}
		}
		return list;
	}

	private int[][] getClone(int[][] map) {
		int[][] clone = Arrays.stream(map).map(e -> e.clone()).toArray(int[][]::new);
		return clone;
	}

	private int getBlindSpot(int[][] map) {
		int count = 0;
		for(int[] row : map){
			for(int spot : row){
				if(spot == 0){
					count++;
				}
			}
		}
		return count;
	}

}
