package BOJ.Samsung.Test;

import Test.*;

import java.util.Scanner;
import java.util.Stack;

public class DragonCurve {
	public static void main(String[] args) {
//		new Main().solve();
		new DragonCurve().test();
	}

	private void test() {
		OldTest test = new OldTest();

		String input;
		int expect;

		input = "3\n" +
				"3 3 0 1\n" +
				"4 2 1 3\n" +
				"4 2 2 1";
		expect = 4;
		testCase(test, input, expect);

		input  = "4\n" +
				"3 3 0 1\n" +
				"4 2 1 3\n" +
				"4 2 2 1\n" +
				"2 7 3 4";
		expect = 11;
		testCase(test, input, expect);

		input  = "10\n" +
				"5 5 0 0\n" +
				"5 6 0 0\n" +
				"5 7 0 0\n" +
				"5 8 0 0\n" +
				"5 9 0 0\n" +
				"6 5 0 0\n" +
				"6 6 0 0\n" +
				"6 7 0 0\n" +
				"6 8 0 0\n" +
				"6 9 0 0";
		expect = 8;
		testCase(test, input, expect);

		input  = "4\n" +
				"50 50 0 10\n" +
				"50 50 1 10\n" +
				"50 50 2 10\n" +
				"50 50 3 10";
		expect = 1992;
		testCase(test, input, expect);

		input = "1\n" +
				"3 3 0 3";
		expect = 3;
		testCase(test, input, expect);
	}

	private void testCase(OldTest test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0]);
		int[][] curveInfo = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getAllPointMarkedSquare(N, curveInfo);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[][] curveInfo = new int[N][4];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 4; j++){
				curveInfo[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getAllPointMarkedSquare(N, curveInfo);
		System.out.println(result);
	}

	private class Point{
		int x, y, dir;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	private final int[] dirX = {0, -1, 0, 1};
	private final int[] dirY = {1, 0, -1, 0};
	
	private int getAllPointMarkedSquare(int n, int[][] curveInfo) {
		int[][] map = new int[101][101];

		for(int[] curve : curveInfo){
			markCurve(map, curve);
		}
		int result = getMarkedSquare(map);
		return result;
	}

	private void markCurve(int[][] map, int[] curve) {
		int y = curve[0];
		int x = curve[1];
		int dir = curve[2];
		int generation = curve[3];
		
		Point start = new Point(x, y, dir);
		Stack<Point> stack = getDragonStack(start, generation);
		
		markDragonOnMap(stack, map);
	}

	private void markDragonOnMap(Stack<Point> stack, int[][] map) {
		markLastPoint(stack.peek(), map);
		for(Point p : stack){
			int x = p.x;
			int y = p.y;
			map[x][y] = 1;
		}
	}

	private void markLastPoint(Point point, int[][] map) {
		int dir = point.dir;
		int x = point.x + dirX[dir];
		int y = point.y + dirY[dir];
		map[x][y] = 1;
	}

	private Stack<Point> getDragonStack(Point start, int generation) {
		Stack<Point> stack = new Stack<>();
		stack.add(start);

		int x = start.x;
		int y = start.y;
		int dir = start.dir;

		for(int i = 1; i <= generation; i++){
			Stack<Point> clone = clone(stack);

			while(!clone.isEmpty()){
				Point cur = clone.pop();
				x += dirX[dir];
				y += dirY[dir];
				dir = (cur.dir + 1) % 4;
				Point next = new Point(x, y, dir);
				stack.add(next);
			}
		}

		return stack;
	}

	private Stack<Point> clone(Stack<Point> stack) {
		Stack<Point> clone = new Stack<>();
		clone.addAll(stack);
		return clone;
	}


	private int getMarkedSquare(int[][] map) {
		int count = 0;
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(isMarked(map, i, j)){
					count++;
				}
			}
		}
		return count;
	}

	private boolean isMarked(int[][] map, int i, int j) {
		return (map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1&& map[i+1][j+1] == 1);
	}
}
