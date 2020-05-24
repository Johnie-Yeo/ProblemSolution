package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TileWithConfetti {
	public static void main(String[] args) {
//		new Main().solve();
		new TileWithConfetti().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int[][] map;
		int result, expect;

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 0;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 1 0 0 0 0 0\n" +
				"0 0 0 0 0 1 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 4;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 0 0 0 0 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 1 1 0 0 0 0\n" +
				"0 0 0 0 1 1 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 5;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 0 0 0 0 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 1 1 0 0 0 0\n" +
				"0 0 0 0 0 1 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = -1;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 0 0 0 0 0 0 0\n" +
				"0 1 1 1 0 0 0 0 0 0\n" +
				"0 0 1 1 1 1 1 0 0 0\n" +
				"0 0 0 1 1 1 1 0 0 0\n" +
				"0 0 0 0 1 1 1 0 0 0\n" +
				"0 0 1 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 7;
		test.test(result, expect).printResult();

		input = "1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 4;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 1 1 1 1 1 1 1 1 0\n" +
				"0 0 0 0 0 0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 4;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 1 1 1 0 0 0 0\n" +
				"0 1 1 1 1 1 0 0 0 0\n" +
				"0 0 1 1 1 1 0 0 0 0\n" +
				"0 0 1 1 1 1 0 0 0 0\n" +
				"0 1 1 1 1 1 1 1 1 1\n" +
				"0 1 1 1 0 1 1 1 1 1\n" +
				"0 1 1 1 0 1 1 1 1 1\n" +
				"0 0 0 0 0 1 1 1 1 1\n" +
				"0 0 0 0 0 1 1 1 1 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 6;
		test.test(result, expect).printResult();

		input = "0 0 0 0 0 0 0 0 0 0\n" +
				"1 1 1 1 1 0 0 0 0 0\n" +
				"1 1 1 1 1 0 1 1 1 1\n" +
				"1 1 1 1 1 0 1 1 1 1\n" +
				"1 1 1 1 1 0 1 1 1 1\n" +
				"1 1 1 1 1 0 1 1 1 1\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 1 0 1 1 0 0 0\n" +
				"0 1 1 1 0 1 1 0 0 0\n" +
				"0 1 1 1 0 0 0 0 0 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMinimumConfettiToCover(map);
		expect = 5;
		test.test(result, expect).printResult();
	}

	public void solve() {
		Scanner kb = new Scanner(System.in);

		int[][] map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j  = 0; j < 10; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int result = getMinimumConfettiToCover(map);
		System.out.println(result);
	}

	private class Point{
		int x;
		int y;
		boolean flag;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
			this.flag = false;
		}

		@Override
		public boolean equals(Object obj) {
			Point o = (Point) obj;
			return (this.x == o.x && this.y == o.y);
		}

		@Override
		public int hashCode() {
			String toString = this.x + "," + this.y;
			return toString.hashCode();
		}

		public void checkFlag(boolean flag){
			this.flag = flag;
		}
	}
	private int getMinimumConfettiToCover(int[][] map) {
		ArrayList<Point> list = new ArrayList<>();
		HashMap<Point, Integer> indices = new HashMap<>();
		setPointListAndIndex(map, list, indices);
		HashMap<Integer, Integer> confetti = initConfetti();
		int count = 0;
		int index = 0;
		int size = list.size();
		int min = dfs(map, list, indices, confetti, count, index, size);
		return min;
	}

	private int dfs(int[][] map, ArrayList<Point> list, HashMap<Point, Integer> indices, HashMap<Integer, Integer> confetti, int count, int index, int size) {
		if(index >= size){
			return count;
		}
		int min = -1;
		Point cur = list.get(index);
		if(cur.flag){
			return dfs(map, list, indices, confetti, count, index+1, size);
		}
		for(int i = 1; i <= 5; i++){
			if(confetti.get(i) > 0){
				if(isAvailableToCover(map, cur, i)){
					confetti.put(i, confetti.get(i)-1);
					cover(map, cur, list, indices, i);
					int tmp = dfs(map, list, indices, confetti, count+1, index+1, size);
					recover(map, cur, list, indices, i);
					if(tmp >= 0){
						if(min < 0){
							min = tmp;
						}else{
							min = Math.min(min, tmp);
						}
					}
					confetti.put(i, confetti.get(i)+1);
				}else{
					break;
				}
			}
		}
		return min;
	}

	private void recover(int[][] map, Point cur, ArrayList<Point> list, HashMap<Point, Integer> indices, int size) {
		int x = cur.x;
		int y = cur.y;
		for(int i = x; i < x + size; i++){
			for(int j = y; j < y + size; j++){
				map[i][j] = 1;
				int index = indices.get(new Point(i, j));
				list.get(index).flag = false;
			}
		}
	}

	private void cover(int[][] map, Point cur, ArrayList<Point> list, HashMap<Point, Integer> indices, int size) {
		int x = cur.x;
		int y = cur.y;
		for(int i = x; i < x + size; i++){
			for(int j = y; j < y + size; j++){
				map[i][j] = 0;
				int index = indices.get(new Point(i, j));
				list.get(index).flag = true;
			}
		}
	}

	private boolean isOutOfRange(int x, int y){
		return (x < 0 || y < 0 || x >= 10 || y >= 10);
	}

	private boolean isAvailableToCover(int[][] map, Point cur, int size) {
		int x = cur.x;
		int y = cur.y;
		for(int i = x; i < x + size; i++){
			for(int j = y; j < y + size; j++){
				if(isOutOfRange(i, j)){
					return false;
				}
				if(map[i][j] != 1){
					return false;
				}
			}
		}
		return true;
	}

	private HashMap<Integer, Integer> initConfetti() {
		HashMap<Integer, Integer> map = new HashMap<>();

		for(int i = 1; i <= 5; i++){
			map.put(i, 5);
		}

		return map;
	}

	private void setPointListAndIndex(int[][] map, ArrayList<Point> list, HashMap<Point, Integer> indices) {
		int index = 0;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(map[i][j] == 1){
					Point point = new Point(i, j);
					list.add(point);
					indices.put(point, index++);
				}
			}
		}
	}
}
