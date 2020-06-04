package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TwoDArrayNOperation {
	public static void main(String[] args) {
//		new Main().solve();
		new TwoDArrayNOperation().test();
	}

	private void test() {
		String input;
		int expect;

		input = "1 2 2\n" +
				"1 2 1\n" +
				"2 1 3\n" +
				"3 3 3";
		expect = 0;
		testCase(input, expect);

		input = "1 2 1\n" +
				"1 2 1\n" +
				"2 1 3\n" +
				"3 3 3";
		expect = 1;
		testCase(input, expect);

		input = "1 2 3\n" +
				"1 2 1\n" +
				"2 1 3\n" +
				"3 3 3";
		expect = 2;
		testCase(input, expect);

		input = "1 2 4\n" +
				"1 2 1\n" +
				"2 1 3\n" +
				"3 3 3";
		expect = 52;
		testCase(input, expect);

		input = "1 2 5\n" +
				"1 2 1\n" +
				"2 1 3\n" +
				"3 3 3";
		expect = -1;
		testCase(input, expect);

		input = "3 3 3\n" +
				"1 1 1\n" +
				"1 1 1\n" +
				"1 1 1";
		expect = 2;
		testCase(input, expect);
	}

	private void testCase(String input, int expect) {
		Test test = new Test();

		String[] parsed = input.split("\n", 2);
		int r = Integer.parseInt(parsed[0].split(" ")[0]);
		int c = Integer.parseInt(parsed[0].split(" ")[1]);
		int k = Integer.parseInt(parsed[0].split(" ")[2]);
		int[][] arr = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getTime(r, c, k, arr);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		int r = kb.nextInt();
		int c = kb.nextInt();
		int k = kb.nextInt();
		int [][]arr = new int[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				arr[i][j] = kb.nextInt();
		kb.close();

		int result = getTime(r, c, k, arr);
		System.out.println(result);
	}

	private final int SIZE = 100;
	private int getTime(int r, int c, int k, int[][] arr) {
		int[][] map = initMap(arr);
		for(int time = 0; time <= 100; time++){
			if(map[r-1][c-1] == k){
				return time;
			}
			if(time < 100){
				map = operate(map);
			}
		}
		return -1;
	}

	private class Count implements Comparable<Count>{
		int number;
		int count;
		public Count(int number, int count){
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Count o) {
			if(this.count - o.count != 0){
				return this.count - o.count;
			}else{
				return this.number - o.number;
			}
		}
	}
	private int[][] operate(int[][] map) {
		int rowSize = getRowSize(map);
		int colSize = getColumnSize(map);

		if(rowSize >= colSize){
			return R_operate(map, rowSize, colSize);
		}else{
			return C_operate(map, rowSize, colSize);
		}
	}

	private int[][] C_operate(int[][] map, int rowSize, int colSize) {
		int[][] result = new int[SIZE][SIZE];
		for(int i = 0; i < colSize; i++){
			ArrayList<Integer> col = getCol(map, i, rowSize);
			result = replaceColumn(col, i, result);
		}
		return result;
	}

	private int[][] replaceColumn(ArrayList<Integer> col, int index, int[][] result) {
		int size = col.size() > 100 ? 100 : col.size();
		for(int i = 0; i < size; i++){
			result[i][index] = col.get(i);
		}
		return result;
	}

	private ArrayList<Integer> getCol(int[][] map, int index, int size) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for(int i = 0; i < size; i++){
			int cur = map[i][index];
			if(cur > 0){
				int count = hashMap.get(cur) == null ? 0 : hashMap.get(cur);
				hashMap.put(cur, count+1);
			}
		}
		ArrayList<Integer> list = parseMapToList(hashMap);
		return list;
	}

	private int[][] R_operate(int[][] map, int rowSize, int colSize) {
		int[][] result = new int[SIZE][SIZE];
		for(int i = 0; i < rowSize; i++){
			ArrayList<Integer> row = getRow(map[i], colSize);
			result = replaceRow(row, i, result);
		}
		return result;
	}

	private int[][] replaceRow(ArrayList<Integer> row, int index, int[][] arr) {
		int size = row.size() > SIZE ? SIZE : row.size();
		for(int i = 0; i < size; i++){
			arr[index][i] = row.get(i);
		}
		return arr;
	}

	private ArrayList<Integer> getRow(int[] arr, int size) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < size; i++){
			if(arr[i] > 0){
				int count = map.get(arr[i]) == null ? 0 : map.get(arr[i]);
				map.put(arr[i], count+1);
			}
		}
		ArrayList<Integer> list = parseMapToList(map);
		return list;
	}

	private ArrayList<Integer> parseMapToList(HashMap<Integer, Integer> map) {
		ArrayList<Count> list = new ArrayList<>();
		for(int number : map.keySet()){
			int count = map.get(number);
			Count cur = new Count(number, count);
			list.add(cur);
		}
		Collections.sort(list);
		ArrayList<Integer> result = parseIntArrayList(list);
		return result;
	}

	private ArrayList<Integer> parseIntArrayList(ArrayList<Count> list) {
		ArrayList<Integer> result = new ArrayList<>();
		for(Count count : list){
			result.add(count.number);
			result.add(count.count);
		}
		return result;
	}

	private int getColumnSize(int[][] map) {
		for(int i = 0; i < SIZE; i++){
			boolean hasValue = false;
			for(int j = 0; j < SIZE; j++){
				if(map[j][i] > 0){
					hasValue = true;
					break;
				}
			}
			if(!hasValue){
				return i;
			}
		}
		return SIZE;
	}

	private int getRowSize(int[][] map) {
		for(int i = 0; i < SIZE; i++){
			boolean hasValue = false;
			for(int j = 0; j < SIZE; j++){
				if(map[i][j] > 0){
					hasValue = true;
					break;
				}
			}
			if(!hasValue){
				return i;
			}
		}
		return SIZE;
	}

	private int[][] initMap(int[][] arr) {
		int[][] map = new int[SIZE][SIZE];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				map[i][j] = arr[i][j];
			}
		}
		return map;
	}

}
