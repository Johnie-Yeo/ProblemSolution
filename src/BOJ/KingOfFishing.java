package BOJ;

import Test.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class KingOfFishing {
	public static void main(String[] args) {
//		new Main().solve();
		new KingOfFishing().test();
	}

	private void test() {
		String input;
		int expect;

		input = "4 6 8\n" +
				"4 1 3 3 8\n" +
				"1 3 5 2 9\n" +
				"2 4 8 4 1\n" +
				"4 5 0 1 4\n" +
				"3 3 1 2 7\n" +
				"1 5 8 4 3\n" +
				"3 6 2 1 2\n" +
				"2 2 2 3 5";
		expect = 22;
		testCase(input, expect);

		input = "100 100 0";
		expect = 0;
		testCase(input, expect);

		input = "4 5 4\n" +
				"4 1 3 3 8\n" +
				"1 3 5 2 9\n" +
				"2 4 8 4 1\n" +
				"4 5 0 1 4";
		expect = 22;
		testCase(input, expect);

		input = "2 2 4\n" +
				"1 1 1 1 1\n" +
				"2 2 2 2 2\n" +
				"1 2 1 2 3\n" +
				"2 1 2 1 4";
		expect = 4;
		testCase(input, expect);
	}

	private void testCase(String input, int expect) {
		Test test = new Test();
		String[] parsed = input.split("\n", 2);

		int R = Integer.parseInt(parsed[0].split(" ")[0]);
		int C = Integer.parseInt(parsed[0].split(" ")[1]);
		int M = Integer.parseInt(parsed[0].split(" ")[2]);
		int[][] sharks;
		if(M > 0){
			sharks = InputParser.parseStringTo2DIntArray(parsed[1]);
		}else{
			sharks = new int[0][5];
		}
		int result = getSumOfSizeOfFishedShark(R, C, M, sharks);
		test.test(result, expect).printResult();
	}

	public void solve(){
		Scanner kb = new Scanner(System.in);
		int R = kb.nextInt();
		int C = kb.nextInt();
		int M = kb.nextInt();

		int[][] sharks = new int[M][5];
		for(int i = 0; i < M; i++){
			for(int j = 0; j < 5; j++){
				sharks[i][j] = kb.nextInt();
			}
		}
		int result = getSumOfSizeOfFishedShark(R, C, M, sharks);
		System.out.println(result);
	}

	private final int[] dirX = {-1, 1, 0, 0};
	private final int[] dirY = {0, 0, 1, -1};
	private class Info{
		int speed, dir, size;
		public Info(int speed, int dir, int size){
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	public class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(new Object[]{this.x, this.y});
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			return (this.x == p.x && this.y == p.y);
		}
	}
	private int getSumOfSizeOfFishedShark(int r, int c, int m, int[][] sharksInfo) {
		HashMap<Point, Info> sharks = initSharkInfo(sharksInfo);

		int result = 0;
		for(int i = 0; i < c; i++){
			int fishedSize = getShark(sharks, r, i);
			sharks = move(sharks, r, c);
			result += fishedSize;
		}
		return result;
	}

	private HashMap<Point, Info> move(HashMap<Point, Info> sharks, int r, int c) {
		HashMap<Point, Info> moved = new HashMap<>();

		for(Point point : sharks.keySet()){
			Info curInfo = sharks.get(point);
			Point movedPoint = getMovedPoint(point, curInfo, r, c);
			if(moved.containsKey(movedPoint)){
				if(moved.get(movedPoint).size > curInfo.size){
					continue;
				}
			}
			moved.put(movedPoint, curInfo);
		}

		return moved;
	}

	private Point getMovedPoint(Point point, Info info, int r, int c) {
		int x = point.x + dirX[info.dir] * info.speed;
		int y = point.y + dirY[info.dir] * info.speed;

		while(isOutOfRange(x, y, r, c)){
			if(x < 0){
				x *= -1;
			}else if(x >= r){
				x = (r-1) - (x - (r-1));
			}else if(y < 0){
				y *= -1;
			}else if(y >= c){
				y = (c-1) - (y - (c-1));
			}else{
				return null;
			}
			info.dir = reverseDir(info.dir);
		}

		return new Point(x, y);
	}

	private int reverseDir(int dir) {
		if(dir % 2 == 0){
			return dir+1;
		}else{
			return dir-1;
		}
	}

	private boolean isOutOfRange(int x, int y, int r, int c) {
		return (x < 0 || y < 0 || x >= r || y >= c);
	}

	private int getShark(HashMap<Point, Info> sharks, int row, int col) {
		for(int i = 0; i < row; i++){
			Point cur = new Point(i, col);
			if(sharks.containsKey(cur)){
				Info info = sharks.remove(cur);
				return info.size;
			}
		}
		return 0;
	}

	private HashMap<Point, Info> initSharkInfo(int[][] sharksInfo) {
		HashMap<Point, Info> map = new HashMap<>();

		for(int[] shark : sharksInfo){
			int x = shark[0] - 1;
			int y = shark[1] - 1;
			Point point = new Point(x, y);

			int speed = shark[2];
			int dir = shark[3] - 1;
			int size = shark[4];
			Info info = new Info(speed, dir, size);

			map.put(point, info);
		}

		return map;
	}
}
