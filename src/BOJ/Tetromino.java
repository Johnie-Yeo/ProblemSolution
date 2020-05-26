package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Tetromino {
	public static void main(String[] args) {
//		new Main().solve();
		new Tetromino().test();
	}

	private void test() {
		Test test = new Test();

		int N, M;
		int[][] map;
		int result, expect;
		String input;

		N = 5; M = 5;
		input = "1 2 3 4 5\n" +
				"5 4 3 2 1\n" +
				"2 3 4 5 6\n" +
				"6 5 4 3 2\n" +
				"1 2 1 2 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxPoliominoWeight(N, M, map);
		expect = 19;
		test.test(result, expect).printResult();

		N = 4; M = 5;
		input = "1 2 3 4 5\n" +
				"1 2 3 4 5\n" +
				"1 2 3 4 5\n" +
				"1 2 3 4 5";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxPoliominoWeight(N, M, map);
		expect = 20;
		test.test(result, expect).printResult();

		N = 4; M = 10;
		input = "1 2 1 2 1 2 1 2 1 2\n" +
				"2 1 2 1 2 1 2 1 2 1\n" +
				"1 2 1 2 1 2 1 2 1 2\n" +
				"2 1 2 1 2 1 2 1 2 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxPoliominoWeight(N, M, map);
		expect = 7;
		test.test(result, expect).printResult();
	}

	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		
		int [][]map = new int[N][M];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int result = getMaxPoliominoWeight(N, M, map);
		System.out.println(result);
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private int getMaxPoliominoWeight(int n, int m, int[][] map) {
		ArrayList<ArrayList<Point>> poliominoList = getPoliominoList();
		int max = 0;
		for(ArrayList<Point> poliomino : poliominoList){
			int cur = getPoliominoWeight(n, m, map, poliomino);
			max = Math.max(cur, max);
		}
		return max;
	}

	private ArrayList<ArrayList<Point>> getPoliominoList() {
		ArrayList<Point> horizontal 		= getHorizontal();
		ArrayList<Point> vertical 			= getVertical();
		ArrayList<Point> square             = getSquare();
		ArrayList<Point> L_Shape            = getLShape();
		ArrayList<Point> L_Shape_90         = getLShape90();
		ArrayList<Point> L_Shape_180        = getLShape180();
		ArrayList<Point> L_Shape_270        = getLShape270();
		ArrayList<Point> reverse_L          = getReversedL();
		ArrayList<Point> reverse_L_90       = getReversedL_90();
		ArrayList<Point> reverse_L_180      = getReversedL_180();
		ArrayList<Point> reverse_L_270      = getReversedL_270();
		ArrayList<Point> thunder            = getThunder();
		ArrayList<Point> thunder_90         = getThunder_90();
		ArrayList<Point> reversedThunder    = getReversedThunder();
		ArrayList<Point> reversedThunder90  = getReversedThunder90();
		ArrayList<Point> fuckShape          = getFuckShape();
		ArrayList<Point> fuckShape90        = getFuckShape90();
		ArrayList<Point> fuckShape180       = getFuckShape180();
		ArrayList<Point> fuckShape270       = getFuckShape270();

		ArrayList<ArrayList<Point>> list = new ArrayList<ArrayList<Point>>(){{
			add(horizontal);
			add(vertical);
			add(square);
			add(L_Shape);
			add(L_Shape_90);
			add(L_Shape_180);
			add(L_Shape_270);
			add(reverse_L);
			add(reverse_L_90);
			add(reverse_L_180);
			add(reverse_L_270);
			add(thunder);
			add(thunder_90);
			add(reversedThunder);
			add(reversedThunder90);
			add(fuckShape);
			add(fuckShape90);
			add(fuckShape180);
			add(fuckShape270);
		}};
		return list;
	}

	private ArrayList<Point> getFuckShape270() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getFuckShape180() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(0, 2);
		Point p4 = new Point(1, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getFuckShape90() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 0);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getFuckShape() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedThunder90() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedThunder() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(1, 0);
		Point p4 = new Point(2, 0);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getThunder_90() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(0, 2);
		Point p3 = new Point(1, 0);
		Point p4 = new Point(1, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getThunder() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedL_270() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(0, 2);
		Point p4 = new Point(1, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedL_180() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 0);
		Point p4 = new Point(2, 0);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedL_90() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(1, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getReversedL() {
		Point p1 = new Point(0, 1);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(2, 1);
		Point p4 = new Point(2, 0);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getLShape270() {
		Point p1 = new Point(1, 0);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(1, 2);
		Point p4 = new Point(0, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getLShape180() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getLShape90() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(0, 1);
		Point p4 = new Point(0, 2);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getLShape() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(2, 0);
		Point p4 = new Point(2, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getSquare() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(0, 1);
		Point p4 = new Point(1, 1);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getVertical() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point p3 = new Point(2, 0);
		Point p4 = new Point(3, 0);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private ArrayList<Point> getHorizontal() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(0, 2);
		Point p4 = new Point(0, 3);
		ArrayList<Point> list = new ArrayList<Point>(){{ add(p1); add(p2); add(p3); add(p4); }};
		return list;
	}

	private int getPoliominoWeight(int n, int m, int[][] map, ArrayList<Point> poliomino) {
		int max = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				int cur = getWeight(i, j, poliomino, map, n, m);
				max = Math.max(cur, max);
			}
		}
		return max;
	}

	private int getWeight(int i, int j, ArrayList<Point> poliomino, int[][] map, int n, int m) {
		int value = 0;

		for(Point point : poliomino){
			int x = point.x + i;
			int y = point.y + j;
			if(isOutOfRange(x, y, n, m)){
				return -1;
			}
			value += map[x][y];
		}

		return value;
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

}
