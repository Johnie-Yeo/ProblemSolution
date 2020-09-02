package BOJ.Samsung.Test;

import Test.*;

import java.util.Scanner;

public class RobotCleaner {
	public static void main(String[] args) {
//		new Main().solve();
		new RobotCleaner().test();
	}

	private void test() {
		OldTest test = new OldTest();

		String input;
		int expect;

		input = "3 3\n" +
				"1 1 0\n" +
				"1 1 1\n" +
				"1 0 1\n" +
				"1 1 1";
		expect = 1;
		testCase(test, input, expect);

		input = "11 10\n" +
				"7 4 0\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 0 0 0 0 0 0 0 0 1\n" +
				"1 0 0 0 1 1 1 1 0 1\n" +
				"1 0 0 1 1 0 0 0 0 1\n" +
				"1 0 1 1 0 0 0 0 0 1\n" +
				"1 0 0 0 0 0 0 0 0 1\n" +
				"1 0 0 0 0 0 0 1 0 1\n" +
				"1 0 0 0 0 0 1 1 0 1\n" +
				"1 0 0 0 0 0 1 1 0 1\n" +
				"1 0 0 0 0 0 0 0 0 1\n" +
				"1 1 1 1 1 1 1 1 1 1";
		expect = 57;
		testCase(test, input, expect);
	}

	private void testCase(OldTest test, String input, int expect) {
		final String NEW_LINE = "\n";
		final String SPACE = " ";
		String[] parsed = input.split(NEW_LINE);
		int N = Integer.parseInt(parsed[0].split(SPACE)[0]);
		int M = Integer.parseInt(parsed[0].split(SPACE)[1]);
		int r = Integer.parseInt(parsed[1].split(SPACE)[0]);
		int c = Integer.parseInt(parsed[1].split(SPACE)[1]);
		int d = Integer.parseInt(parsed[1].split(SPACE)[2]);
		int[][] map = new int[N][];
		for(int i = 0; i < N; i++){
			String row = parsed[i+2];
			map[i] = InputParser.parseStringToIntArray(row);
		}
		int result = getNumberOfCleanedArea(N, M, r, c, d, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		int r = kb.nextInt();
		int c = kb.nextInt();
		int d = kb.nextInt();
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getNumberOfCleanedArea(N, M, r, c, d, map);
		System.out.println(result);
	}

	private final int NORTH = 0;
	private final int EAST  = 1;
	private final int SOUTH = 2;
	private final int WEST  = 3;
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, 1, 0, -1};

	private int getNumberOfCleanedArea(int n, int m, int r, int c, int d, int[][] map) {
		int count = 0;

		while(true){
			if(map[r][c] == 0){
				count++;
			}
			map[r][c] = -1;
			boolean next = false;
			for(int turn = 0; turn < 4; turn++){
				d = turnLeft(d);
				if(isCleanable(n, m, r, c, d, map)){
					next = true;
					r = r + dirX[d];
					c = c + dirY[d];
					break;
				}
			}
			if(!next){
				int back = (d + 2) % 4;
				r += dirX[back];
				c += dirY[back];
				if(map[r][c] == 1){
					break;
				}
			}
		}

		return count;
	}

	private boolean isCleanable(int n, int m, int r, int c, int d, int[][] map) {
		int x = r + dirX[d];
		int y = c + dirY[d];
		if(isOutOfRange(x, y, n, m)){
			return false;
		}
		return map[x][y] == 0;
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

	private int turnLeft(int dir) {
		return (dir+3)%4;
	}
}
