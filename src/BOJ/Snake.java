package BOJ;


import Test.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {
	public static void main(String[] args) {
//		new Main().solve();
		new Snake().test();
	}
	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private class Move{
		int x;
		char c;
		public Move(int x, char c){
			this.x = x;
			this.c = c;
		}
	}
	private void test() {
		Test test = new Test();

		int result, expect;
		String input;

		input= "6\n" +
				"3\n" +
				"3 4\n" +
				"2 5\n" +
				"5 3\n" +
				"3\n" +
				"3 D\n" +
				"15 L\n" +
				"17 D";
		result = buildResult(input);
		expect = 9;
		test.test(result, expect).printResult();

		input= "10\n" +
				"4\n" +
				"1 2\n" +
				"1 3\n" +
				"1 4\n" +
				"1 5\n" +
				"4\n" +
				"8 D\n" +
				"10 D\n" +
				"11 D\n" +
				"13 L";
		result = buildResult(input);
		expect = 21;
		test.test(result, expect).printResult();

		input = "10\n" +
				"5\n" +
				"1 5\n" +
				"1 3\n" +
				"1 2\n" +
				"1 6\n" +
				"1 7\n" +
				"4\n" +
				"8 D\n" +
				"10 D\n" +
				"11 D\n" +
				"13 L";
		result = buildResult(input);
		expect = 13;
		test.test(result, expect).printResult();
	}

	private int buildResult(String input){
		int index = 0;
		final String ENTER = "\n";
		final String SPACE = " ";

		String[] parsed = input.split(ENTER);
		int N = toInt(parsed[index++]);
		int K = toInt(parsed[index++]);
		Point[] apples = new Point[K];
		for(int i = 0; i < K; i++){
			String[] tmp = parsed[index++].split(SPACE);
			int x = toInt(tmp[0]);
			int y = toInt(tmp[1]);
			apples[i] = new Point(x, y);
		}
		int L = toInt(parsed[index++]);
		Queue<Move> moves = new LinkedList<>();
		for(int i = 0; i < L; i++){
			String[] tmp = parsed[index++].split(SPACE);
			int x = toInt(tmp[0]);
			char c = tmp[1].charAt(0);
			moves.add(new Move(x, c));
		}
		return getGameEndPoint(N, K, apples, L, moves);
	}
	private int toInt(String s){
		return Integer.parseInt(s);
	}
	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int K = kb.nextInt();
		Point[] apples = new Point[K];
		for(int i = 0; i < K; i++){
			int x = kb.nextInt();
			int y = kb.nextInt();
			apples[i] = new Point(x, y);
		}
		int L = kb.nextInt();
		Queue<Move> moves = new LinkedList<>();
		for(int i = 0; i < L; i++){
			int x = kb.nextInt();
			char c = kb.next().charAt(0);
			moves.add(new Move(x, c));
		}
		int result = getGameEndPoint(N, K, apples, L, moves);
		System.out.println(result);

		kb.close();
	}
	private final int SNAKE = 1;
	private final int APPLE = 2;
	private final int[] dirX = {0, 1, 0, -1};
	private final int[] dirY = {1, 0, -1, 0};
	private final char LEFT = 'L';
	private final char RIGHT = 'D';

	private int getGameEndPoint(int n, int k, Point[] apples, int l, Queue<Move> moves) {
		int[][] map = setMap(n, apples);
		int x = 0;
		int y = 0;
		map[x][y] = SNAKE;
		Queue<Point> snakeBody = new LinkedList<>();
		snakeBody.add(new Point(x, y));
		int dir = 0;
		Move move = moves.poll();
		int time = 0;

		while(true){
			if(move != null && time == move.x){
				if(move.c == LEFT){
					dir = (dir+3)%4;
				}else if(move.c == RIGHT){
					dir = (dir+1)%4;
				}
				move = moves.poll();
			}
			x = x + dirX[dir];
			y = y + dirY[dir];
			if(isOutOfRange(x, y, n) || collideWithBody(x, y, map)){
				time++;
				break;
			}

			if(map[x][y] != APPLE){
				Point tmp = snakeBody.poll();
				map[tmp.x][tmp.y] = 0;
			}
			map[x][y] = SNAKE;
			snakeBody.add(new Point(x, y));

			time++;
		}

		return time;
	}

	private boolean collideWithBody(int x, int y, int[][] map){
		return (map[x][y] == SNAKE);
	}
	private boolean isOutOfRange(int x, int y, int n){
		return (x < 0 || y < 0 || x >= n || y >= n);
	}

	private int[][] setMap(int n, Point[] apples) {
		int[][] map = new int[n][n];
		for(Point apple : apples){
			map[apple.x-1][apple.y-1] = APPLE;
		}
		return map;
	}
}
