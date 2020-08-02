package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class RollingDice {
	public static void main(String[] args) {
//		new Main().solve();
		new RollingDice().test();
	}

	private void test() {
		Test test = new Test();

		int N, M, x, y, numberOfOrders;
		String input;
		int[][] map;
		int[] orders;
		int[] result, expect;

		N = 4; M = 2; x = 0; y = 0; numberOfOrders = 8;
		input = "0 2\n" +
				"3 4\n" +
				"5 6\n" +
				"7 8";
		map = InputParser.parseStringTo2DIntArray(input);
		input = "4 4 4 1 3 3 3 2";
		orders = InputParser.parseStringToIntArray(input);
		result = getTopOfDiceNumbers(N, M, x, y, numberOfOrders, map, orders);
		expect = InputParser.parseStringToIntArray("0 0 3 0 0 8 6 3");
		test.test(result, expect).printResult();

		N = 3; M = 3; x = 1; y = 1; numberOfOrders = 9;
		input = "1 2 3\n" +
				"4 0 5\n" +
				"6 7 8";
		map = InputParser.parseStringTo2DIntArray(input);
		input = "1 3 2 2 4 4 1 1 3";
		orders = InputParser.parseStringToIntArray(input);
		result = getTopOfDiceNumbers(N, M, x, y, numberOfOrders, map, orders);
		expect = InputParser.parseStringToIntArray("0 0 0 3 0 1 0 6 0");
		test.test(result, expect).printResult();

		N = 2; M = 2; x = 0; y = 0; numberOfOrders = 16;
		input = "0 2\n" +
				"3 4";
		map = InputParser.parseStringTo2DIntArray(input);
		input = "4 4 4 4 1 1 1 1 3 3 3 3 2 2 2 2";
		orders = InputParser.parseStringToIntArray(input);
		result = getTopOfDiceNumbers(N, M, x, y, numberOfOrders, map, orders);
		expect = InputParser.parseStringToIntArray("0 0 0 0");
		test.test(result, expect).printResult();

		N = 3; M = 3; x = 0; y = 0; numberOfOrders = 16;
		input = "0 1 2\n" +
				"3 4 5\n" +
				"6 7 8";
		map = InputParser.parseStringTo2DIntArray(input);
		input = "4 4 1 1 3 3 2 2 4 4 1 1 3 3 2 2";
		orders = InputParser.parseStringToIntArray(input);
		result = getTopOfDiceNumbers(N, M, x, y, numberOfOrders, map, orders);
		expect = InputParser.parseStringToIntArray("0 0 0 6 0 8 0 2 0 8 0 2 0 8 0 2");
		test.test(result, expect).printResult();
	}

	public void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int M = kb.nextInt();
		int [][]map = new int[N][M];
		int x = kb.nextInt();
		int y = kb.nextInt();
		int num = kb.nextInt();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		int []order = new int[num];
		for(int n = 0; n < num; n++) {
			order[n] = kb.nextInt();
		}
		kb.close();

		int[] result = getTopOfDiceNumbers(N, M, x, y, num, map, order);
		for(int elem : result){
			System.out.println(elem);
		}
	}

	private class Dice{
		int top, bottom, front, back, left, right;
		public Dice(){
			this.top = 0;
			this.bottom = 0;
			this.front = 0;
			this.back = 0;
			this.left = 0;
			this.right = 0;
		}
		public void roll(int dir){
			switch (dir){
				case NORTH:
					this.rollNorth();
					break;
				case SOUTH:
					this.rollSouth();
					break;
				case EAST:
					this.rollEast();
					break;
				case WEST:
					this.rollWest();
					break;
			}
		}
		private void rollNorth(){
			int tmp = this.top;
			this.top = this.front;
			this.front = this.bottom;
			this.bottom = this.back;
			this.back = tmp;
		}
		private void rollSouth(){
			int tmp = this.top;
			this.top = this.back;
			this.back = this.bottom;
			this.bottom = this.front;
			this.front = tmp;
		}
		private void rollEast(){
			int tmp = this.top;
			this.top = this.left;
			this.left = this.bottom;
			this.bottom = this.right;
			this.right = tmp;
		}
		private void rollWest(){
			int tmp = this.top;
			this.top = this.right;
			this.right = this.bottom;
			this.bottom = this.left;
			this.left = tmp;
		}
	}
	private final int EAST = 1;
	private final int WEST = 2;
	private final int NORTH = 3;
	private final int SOUTH = 4;
	private final int[] dirX = {0, 0, -1, 1};
	private final int[] dirY = {1, -1, 0, 0};
	private int[] getTopOfDiceNumbers(int n, int m, int x, int y, int numberOfOrders, int[][] map, int[] orders) {
		ArrayList<Integer> result = new ArrayList<>();
		Dice dice = new Dice();

		for(int order : orders){
			int dir = order - 1;
			int newX = x + dirX[dir];
			int newY = y + dirY[dir];
			if(isOutOfRange(newX, newY, n, m)){
				continue;
			}else{
				x = newX;
				y = newY;
			}
			dice.roll(order);
			if(map[x][y] == 0){
				map[x][y] = dice.bottom;
			}else{
				dice.bottom = map[x][y];
				map[x][y] = 0;
			}
			result.add(dice.top);
		}

		return result.stream().mapToInt(e -> e).toArray();
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

}	
