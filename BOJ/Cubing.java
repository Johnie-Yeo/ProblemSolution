package BOJ;

import java.util.Scanner;
public class Cubing {
	final int U = 0;
	final int D = 1;
	final int F = 2;
	final int B = 3;
	final int L = 4;
	final int R = 5;
	
	public static void main(String[] args) {
		Cubing app = new Cubing();
		app.getInput();
	}

	private void getInput() {
		StringBuilder sb = new StringBuilder();
		Scanner kb = new Scanner(System.in);
		int testCase = kb.nextInt();
		int n;
		char [][]cube = new char[6][9];
		for(int i = 0; i < testCase; i++) {
			cube = getNewCube(cube);
			n = kb.nextInt();
			for(int j = 0; j < n; j++) {
				String dir = kb.next();
				turnCube(dir, cube);
			}
			printUpperPartOfCube(cube, sb);
		}
		kb.close();
		System.out.println(sb);
	}

	private void printUpperPartOfCube(char[][] cube, StringBuilder sb) {
		for(int i = 0; i < 9; i++) {
			sb.append(cube[0][i]);
			if(i % 3 == 2)
				sb.append("\n");
		}	
	}

	private void turnCube(String dir, char[][] cube) {
		if(dir.charAt(0) == 'L') {
			if(dir.charAt(1) == '+') {
				turnLeft(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnLeft(cube);
			}
		}
		else if(dir.charAt(0) == 'R') {
			if(dir.charAt(1) == '+') {
				turnRight(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnRight(cube);
			}
		}
		else if(dir.charAt(0) == 'F') {
			if(dir.charAt(1) == '+') {
				turnFront(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnFront(cube);
			}
		}
		else if(dir.charAt(0) == 'B') {
			if(dir.charAt(1) == '+') {
				turnBack(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnBack(cube);
			}
		}
		else if(dir.charAt(0) == 'U') {
			if(dir.charAt(1) == '+') {
				turnUpper(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnUpper(cube);
			}
		}
		else if(dir.charAt(0) == 'D') {
			if(dir.charAt(1) == '+') {
				turnDown(cube);
			}
			else {
				for(int i = 0; i < 3; i++)
					turnDown(cube);
			}
		}
	}

	
	private void turnDown(char[][] cube) {
		char a = cube[L][6], b = cube[L][7], c = cube[L][8];
		char d = cube[R][8], e = cube[R][7], f = cube[R][6];
		char g = cube[B][8], h = cube[B][7], i = cube[B][6];
		char j = cube[F][6], k = cube[F][7], l = cube[F][8];
		cube[F][6] = a; cube[F][7] = b; cube[F][8] = c;
		cube[B][6] = d; cube[B][7] = e; cube[B][8] = f;
		cube[L][6] = g; cube[L][7] = h; cube[L][8] = i;
		cube[R][6] = j; cube[R][7] = k; cube[R][8] = l;
		
		rotateReverse(cube, D);
	}

	private void rotateReverse(char[][] cube, int dir) {
		char a = cube[dir][2], b = cube[dir][1], c = cube[dir][0];
		char d = cube[dir][5], 					 f = cube[dir][3];
		char g = cube[dir][8], h = cube[dir][7], i = cube[dir][6];
		cube[dir][2] = g; cube[dir][1] = d; cube[dir][0] = a;
		cube[dir][5] = h; 					cube[dir][3] = b;
		cube[dir][8] = i; cube[dir][7] = f; cube[dir][6] = c;
	}

	private void rotate(char[][] cube, int dir) {
		char a = cube[dir][0], b = cube[dir][1], c = cube[dir][2];
		char d = cube[dir][3], 					 f = cube[dir][5];
		char g = cube[dir][6], h = cube[dir][7], i = cube[dir][8];
		cube[dir][0] = g; cube[dir][1] = d; cube[dir][2] = a;
		cube[dir][3] = h; 					cube[dir][5] = b;
		cube[dir][6] = i; cube[dir][7] = f; cube[dir][8] = c;
	}

	private void turnUpper(char[][] cube) {
		char a = cube[R][0], b = cube[R][1], c = cube[R][2];
		char d = cube[L][2], e = cube[L][1], f = cube[L][0];
		char g = cube[F][0], h = cube[F][1], i = cube[F][2];
		char j = cube[B][2], k = cube[B][1], l = cube[B][0];
		cube[F][0] = a; cube[F][1] = b; cube[F][2] = c;
		cube[B][0] = d; cube[B][1] = e; cube[B][2] = f;
		cube[L][0] = g; cube[L][1] = h; cube[L][2] = i;
		cube[R][0] = j; cube[R][1] = k; cube[R][2] = l;
		
		rotate(cube, U);
	}

	private void turnBack(char[][] cube) {
		char a = cube[R][2], b = cube[R][5], c = cube[R][8];
		char d = cube[L][0], e = cube[L][3], f = cube[L][6];
		char g = cube[U][2], h = cube[U][1], i = cube[U][0];
		char j = cube[D][2], k = cube[D][1], l = cube[D][0];
		cube[U][0] = a; cube[U][1] = b; cube[U][2] = c;
		cube[D][0] = d; cube[D][1] = e; cube[D][2] = f;
		cube[L][0] = g; cube[L][3] = h; cube[L][6] = i;
		cube[R][2] = j; cube[R][5] = k; cube[R][8] = l;
		
		rotateReverse(cube, B);
	}

	private void turnFront(char[][] cube) {
		char a = cube[L][8], b = cube[L][5], c = cube[L][2];
		char d = cube[R][6], e = cube[R][3], f = cube[R][0];
		char g = cube[D][6], h = cube[D][7], i = cube[D][8];
		char j = cube[U][6], k = cube[U][7], l = cube[U][8];
		cube[U][6] = a; cube[U][7] = b; cube[U][8] = c;
		cube[D][6] = d; cube[D][7] = e; cube[D][8] = f;
		cube[L][2] = g; cube[L][5] = h; cube[L][8] = i;
		cube[R][0] = j; cube[R][3] = k; cube[R][6] = l;
		
		rotate(cube, F);
	}

	private void turnRight(char[][] cube) {
		char a = cube[F][2], b = cube[F][5], c = cube[F][8];
		char d = cube[B][2], e = cube[B][5], f = cube[B][8];
		char g = cube[D][8], h = cube[D][5], i = cube[D][2];
		char j = cube[U][8], k = cube[U][5], l = cube[U][2];
		cube[U][2] = a; cube[U][5] = b; cube[U][8] = c;
		cube[D][2] = d; cube[D][5] = e; cube[D][8] = f;
		cube[F][2] = g; cube[F][5] = h; cube[F][8] = i;
		cube[B][2] = j; cube[B][5] = k; cube[B][8] = l;
		
		rotate(cube, R);
	}

	private void turnLeft(char[][] cube) {
		char a = cube[B][6], b = cube[B][3], c = cube[B][0];
		char d = cube[F][6], e = cube[F][3], f = cube[F][0];
		char g = cube[U][0], h = cube[U][3], i = cube[U][6];
		char j = cube[D][0], k = cube[D][3], l = cube[D][6];
		cube[U][0] = a; cube[U][3] = b; cube[U][6] = c;
		cube[D][0] = d; cube[D][3] = e; cube[D][6] = f;
		cube[F][0] = g; cube[F][3] = h; cube[F][6] = i;
		cube[B][0] = j; cube[B][3] = k; cube[B][6] = l;
		
		rotate(cube, L);
	}

	private char[][] getNewCube(char[][] cube) {
		for(int i = 0; i < 9; i++) {
			cube[0][i] = 'w';
		}
		for(int i = 0; i < 9; i++) {
			cube[1][i] = 'y';
		}
		for(int i = 0; i < 9; i++) {
			cube[2][i] = 'r';
		}
		for(int i = 0; i < 9; i++) {
			cube[3][i] = 'o';
		}
		for(int i = 0; i < 9; i++) {
			cube[4][i] = 'g';
		}
		for(int i = 0; i < 9; i++) {
			cube[5][i] = 'b';
		}
		return cube;
	}
}
