package BOJ;


import java.util.Arrays;
import java.util.Scanner;

public class Gear {
	public static void main(String[] args) {
		int [][]gear = new int[4][8];
		Scanner kb = new Scanner(System.in);
		for(int i = 0; i < 4; i++) {
			int tmp = kb.nextInt();
			int div = 10000000;
			for(int j = 0; j < 8; j++) {
				gear[i][j] = tmp / div;
				tmp %= div;
				div /= 10;
			}
		}
		int K = kb.nextInt();
		int idx, dir;
		for(int i = 0; i < K; i++) {
			idx = kb.nextInt();
			dir = kb.nextInt();
			boolean[] check = new boolean[4];
			turnGear(idx-1, dir, gear, check);
		}
		kb.close();
		int result = getScore(gear);
		System.out.println(result);
	}
	private static int getScore(int[][] gear) {
		int score = 0;
		for(int i = 0; i < 4; i++) {
			score += Math.pow(2, i) * gear[i][0];
		}
		return score;
	}
	private static void turnGear(int idx, int dir, int[][] gear, boolean[] check) {
		check[idx] = true;
		int []copy = Arrays.copyOf(gear[idx], 8);
		for(int i = 0; i < 8; i++)
			copy[i] = gear[idx][(i-dir+8)%8];
		if(idx+1 < 4 && !check[idx+1] && gear[idx][2] != gear[idx+1][6])
			turnGear(idx+1, dir*(-1), gear, check);
		if(idx-1 >= 0 && !check[idx-1] && gear[idx][6] != gear[idx-1][2])
			turnGear(idx-1, dir*(-1), gear, check);		
		gear[idx] = Arrays.copyOf(copy, 8);
	}
}

