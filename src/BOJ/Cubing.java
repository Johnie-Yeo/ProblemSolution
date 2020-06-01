package BOJ;

import Test.Test;

import java.util.Scanner;
public class Cubing {
	public static void main(String[] args) {
//		new Main().solve();
		new Cubing().test();
	}

	private void test() {
		Test test = new Test();

		String input, expect;

		input = "1\n" +
				"L-";
		expect = "rww\n" +
				 "rww\n" +
				 "rww";
		testCase(test, input, expect);

		input = "2\n" +
				"F+ B+";
		expect = "bbb\n" +
				 "www\n" +
				 "ggg";
		testCase(test, input, expect);

		input = "4\n" +
				"U- D- L+ R+";
		expect = "gwg\n" +
				"owr\n" +
				"bwb";
		testCase(test, input, expect);

		input = "10\n" +
				"L- U- L+ U- L- U- U- L+ U+ U+";
		expect = "gwo\n" +
				"www\n" +
				"rww";
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, String expect) {
		String[] parsed = input.split("\n");
		int N = Integer.parseInt(parsed[0]);
		String[] moves = parsed[1].split(" ");
		String result = getTopAfterMove(N, moves);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int T = kb.nextInt();

		for(int i = 0; i < T; i++){
			int N = kb.nextInt();
			String[] moves = new String[N];
			for(int j = 0; j < N; j++){
				moves[j] = kb.next();
			}
			String result = getTopAfterMove(N, moves);
			System.out.println(result);
		}
		kb.close();
	}

	private final char WHITE  = 'w';
	private final char YELLOW = 'y';
	private final char RED    = 'r';
	private final char ORANGE = 'o';
	private final char GREEN  = 'g';
	private final char BLUE   = 'b';

	private final char UP    = 'U';
	private final char DOWN  = 'D';
	private final char FRONT = 'F';
	private final char BACK  = 'B';
	private final char LEFT  = 'L';
	private final char RIGHT = 'R';

	private final int UP_INDEX = 0;
	private final int DOWN_INDEX = 1;
	private final int FRONT_INDEX = 2;
	private final int BACK_INDEX = 3;
	private final int LEFT_INDEX = 4;
	private final int RIGHT_INDEX = 5;

	private String getTopAfterMove(int n, String[] moves) {
		char[][][] cube = initCube();

		for(String move : moves){
			char dimension = move.charAt(0);
			char dir = move.charAt(1);
			if(dir == '-'){
				for(int i = 0; i < 2; i++){
					cube = moveCube(cube, dimension);
				}
			}
			cube = moveCube(cube, dimension);
		}

		String result = getTop(cube);
		return result;
	}

	private char[][][] moveCube(char[][][] cube, char dimension) {
		switch (dimension){
			case UP:
				cube = moveUP(cube);
				break;
			case DOWN:
				cube = moveDown(cube);
				break;
			case FRONT:
				cube = moveFront(cube);
				break;
			case BACK:
				cube = moveBack(cube);
				break;
			case LEFT:
				cube = moveLeft(cube);
				break;
			case RIGHT:
				cube = moveRight(cube);
				break;
		}
		return cube;
	}

	private char[][][] moveRight(char[][][] cube) {
		char[][] right = cube[RIGHT_INDEX];
		turn(right);

		char tmp1 = cube[UP_INDEX][0][2];
		char tmp2 = cube[UP_INDEX][1][2];
		char tmp3 = cube[UP_INDEX][2][2];

		cube[UP_INDEX][0][2] = cube[FRONT_INDEX][0][2];
		cube[UP_INDEX][1][2] = cube[FRONT_INDEX][1][2];
		cube[UP_INDEX][2][2] = cube[FRONT_INDEX][2][2];

		cube[FRONT_INDEX][0][2] = cube[DOWN_INDEX][2][2];
		cube[FRONT_INDEX][1][2] = cube[DOWN_INDEX][1][2];
		cube[FRONT_INDEX][2][2] = cube[DOWN_INDEX][0][2];

		cube[DOWN_INDEX][0][2] = cube[BACK_INDEX][0][2];
		cube[DOWN_INDEX][1][2] = cube[BACK_INDEX][1][2];
		cube[DOWN_INDEX][2][2] = cube[BACK_INDEX][2][2];

		cube[BACK_INDEX][0][2] = tmp3;
		cube[BACK_INDEX][1][2] = tmp2;
		cube[BACK_INDEX][2][2] = tmp1;

		return cube;
	}

	private char[][][] moveLeft(char[][][] cube) {
		char[][] left = cube[LEFT_INDEX];
		turn(left);

		char tmp1 = cube[UP_INDEX][0][0];
		char tmp2 = cube[UP_INDEX][1][0];
		char tmp3 = cube[UP_INDEX][2][0];

		cube[UP_INDEX][0][0] = cube[BACK_INDEX][2][0];
		cube[UP_INDEX][1][0] = cube[BACK_INDEX][1][0];
		cube[UP_INDEX][2][0] = cube[BACK_INDEX][0][0];

		cube[BACK_INDEX][0][0] = cube[DOWN_INDEX][0][0];
		cube[BACK_INDEX][1][0] = cube[DOWN_INDEX][1][0];
		cube[BACK_INDEX][2][0] = cube[DOWN_INDEX][2][0];

		cube[DOWN_INDEX][0][0] = cube[FRONT_INDEX][2][0];
		cube[DOWN_INDEX][1][0] = cube[FRONT_INDEX][1][0];
		cube[DOWN_INDEX][2][0] = cube[FRONT_INDEX][0][0];

		cube[FRONT_INDEX][0][0] = tmp1;
		cube[FRONT_INDEX][1][0] = tmp2;
		cube[FRONT_INDEX][2][0] = tmp3;

		return cube;
	}

	private char[][][] moveBack(char[][][] cube) {
		char[][] back = cube[BACK_INDEX];
		counterTurn(back);

		char tmp1 = cube[UP_INDEX][0][0];
		char tmp2 = cube[UP_INDEX][0][1];
		char tmp3 = cube[UP_INDEX][0][2];

		cube[UP_INDEX][0][0] = cube[RIGHT_INDEX][0][2];
		cube[UP_INDEX][0][1] = cube[RIGHT_INDEX][1][2];
		cube[UP_INDEX][0][2] = cube[RIGHT_INDEX][2][2];

		cube[RIGHT_INDEX][0][2] = cube[DOWN_INDEX][0][2];
		cube[RIGHT_INDEX][1][2] = cube[DOWN_INDEX][0][1];
		cube[RIGHT_INDEX][2][2] = cube[DOWN_INDEX][0][0];

		cube[DOWN_INDEX][0][0] = cube[LEFT_INDEX][0][0];
		cube[DOWN_INDEX][0][1] = cube[LEFT_INDEX][1][0];
		cube[DOWN_INDEX][0][2] = cube[LEFT_INDEX][2][0];

		cube[LEFT_INDEX][0][0] = tmp3;
		cube[LEFT_INDEX][1][0] = tmp2;
		cube[LEFT_INDEX][2][0] = tmp1;

		return cube;
	}

	private char[][][] moveFront(char[][][] cube) {
		char[][] front = cube[FRONT_INDEX];
		turn(front);

		char tmp1 = cube[UP_INDEX][2][0];
		char tmp2 = cube[UP_INDEX][2][1];
		char tmp3 = cube[UP_INDEX][2][2];

		cube[UP_INDEX][2][0] =  cube[LEFT_INDEX][2][2];
		cube[UP_INDEX][2][1] =  cube[LEFT_INDEX][1][2];
		cube[UP_INDEX][2][2] =  cube[LEFT_INDEX][0][2];

		cube[LEFT_INDEX][0][2] = cube[DOWN_INDEX][2][0];
		cube[LEFT_INDEX][1][2] = cube[DOWN_INDEX][2][1];
		cube[LEFT_INDEX][2][2] = cube[DOWN_INDEX][2][2];

		cube[DOWN_INDEX][2][0] = cube[RIGHT_INDEX][2][0];
		cube[DOWN_INDEX][2][1] = cube[RIGHT_INDEX][1][0];
		cube[DOWN_INDEX][2][2] = cube[RIGHT_INDEX][0][0];

		cube[RIGHT_INDEX][0][0] = tmp1;
		cube[RIGHT_INDEX][1][0] = tmp2;
		cube[RIGHT_INDEX][2][0] = tmp3;

		return cube;
	}

	private char[][][] moveDown(char[][][] cube) {
		char[][] down = cube[DOWN_INDEX];
		counterTurn(down);

		char tmp1 = cube[FRONT_INDEX][2][0];
		char tmp2 = cube[FRONT_INDEX][2][1];
		char tmp3 = cube[FRONT_INDEX][2][2];

		cube[FRONT_INDEX][2][0] = cube[LEFT_INDEX][2][0];
		cube[FRONT_INDEX][2][1] = cube[LEFT_INDEX][2][1];
		cube[FRONT_INDEX][2][2] = cube[LEFT_INDEX][2][2];

		cube[LEFT_INDEX][2][0] = cube[BACK_INDEX][2][2];
		cube[LEFT_INDEX][2][1] = cube[BACK_INDEX][2][1];
		cube[LEFT_INDEX][2][2] = cube[BACK_INDEX][2][0];

		cube[BACK_INDEX][2][0] = cube[RIGHT_INDEX][2][2];
		cube[BACK_INDEX][2][1] = cube[RIGHT_INDEX][2][1];
		cube[BACK_INDEX][2][2] = cube[RIGHT_INDEX][2][0];

		cube[RIGHT_INDEX][2][0] = tmp1;
		cube[RIGHT_INDEX][2][1] = tmp2;
		cube[RIGHT_INDEX][2][2] = tmp3;

		return cube;
	}

	private char[][][] moveUP(char[][][] cube) {
		char[][] top = cube[UP_INDEX];
		turn(top);

		char tmp1 = cube[FRONT_INDEX][0][0];
		char tmp2 = cube[FRONT_INDEX][0][1];
		char tmp3 = cube[FRONT_INDEX][0][2];

		cube[FRONT_INDEX][0][0] = cube[RIGHT_INDEX][0][0];
		cube[FRONT_INDEX][0][1] = cube[RIGHT_INDEX][0][1];
		cube[FRONT_INDEX][0][2] = cube[RIGHT_INDEX][0][2];

		cube[RIGHT_INDEX][0][0] = cube[BACK_INDEX][0][2];
		cube[RIGHT_INDEX][0][1] = cube[BACK_INDEX][0][1];
		cube[RIGHT_INDEX][0][2] = cube[BACK_INDEX][0][0];

		cube[BACK_INDEX][0][0] = cube[LEFT_INDEX][0][2];
		cube[BACK_INDEX][0][1] = cube[LEFT_INDEX][0][1];
		cube[BACK_INDEX][0][2] = cube[LEFT_INDEX][0][0];

		cube[LEFT_INDEX][0][0] = tmp1;
		cube[LEFT_INDEX][0][1] = tmp2;
		cube[LEFT_INDEX][0][2] = tmp3;

		return cube;
	}

	private void turn(char[][] dimension){
		char[][] clone = clone(dimension);

		dimension[0][0] = clone[2][0];
		dimension[0][1] = clone[1][0];
		dimension[0][2] = clone[0][0];
		dimension[1][0] = clone[2][1];
		dimension[2][0] = clone[2][2];
		dimension[2][1] = clone[1][2];
		dimension[2][2] = clone[0][2];
		dimension[1][2] = clone[0][1];
	}

	private void counterTurn(char[][] dimension){
		char[][] clone = clone(dimension);

		dimension[0][0] = clone[0][2];
		dimension[0][1] = clone[1][2];
		dimension[0][2] = clone[2][2];
		dimension[1][0] = clone[0][1];
		dimension[1][2] = clone[2][1];
		dimension[2][0] = clone[0][0];
		dimension[2][1] = clone[1][0];
		dimension[2][2] = clone[2][0];
	}

	private char[][] clone(char[][] arr) {
		char[][] clone = new char[3][3];
		for(int i = 0; i < 3; i++){
			clone[i] = arr[i].clone();
		}
		return clone;
	}

	private String getTop(char[][][] cube) {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				sb.append(cube[0][i][j]);
			}
			if(i < 2){
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private char[][][] initCube() {
		char[][][] cube = new char[6][][];

		cube[0] = fillDimensionWith(WHITE); // top
		cube[1] = fillDimensionWith(YELLOW); //bottom
		cube[2] = fillDimensionWith(RED); // front
		cube[3] = fillDimensionWith(ORANGE);// back
		cube[4] = fillDimensionWith(GREEN);// left
		cube[5] = fillDimensionWith(BLUE);// right

		return cube;
	}

	private char[][] fillDimensionWith(char color) {
		char[][] dimension = new char[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				dimension[i][j] = color;
			}
		}
		return dimension;
	}
}
