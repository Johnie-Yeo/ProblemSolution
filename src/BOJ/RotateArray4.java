package BOJ;


import Test.Test;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RotateArray4 {
	public static void main(String[] args) {
//		new Main().solve();
		new RotateArray4().test();
	}

	private void test() {
		Test test = new Test();


	}

	public void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		int K = kb.nextInt();

		int[][] A = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				A[i][j] = kb.nextInt();
			}
		}
		kb.nextLine();
		String[] rotate = new String[K];
		for(int i = 0; i < K; i++){
			rotate[i] = kb.nextLine();
		}
		kb.close();
		int result = getMaxArrayValue(N, M, K, A, rotate);
		System.out.println(result);
	}

	private class Rotate{
		int r, c, s;
		public Rotate(int r, int c, int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	private int getMaxArrayValue(int n, int m, int k, int[][] a, String[] rotate) {
		Rotate[] rotateInfo = getRotateInfo(rotate, k);
		boolean[] check = new boolean[k];
		int index = 0;
		int result = dfs(n, m, k, a, rotateInfo, check, index);
		return result;
	}

	private int dfs(int n, int m, int k, int[][] map, Rotate[] rotateInfo, boolean[] check, int index) {
		if(index >= k){
			int value = getValue(map);
			return value;
		}
		int max = 0;
		for(int i = 0; i < k; i++){
			if(!check[i]){
				check[i] = true;
				int[][] rotated = rotate(n, m, map, rotateInfo[i]);
				int cur = dfs(n, m, k, rotated, rotateInfo, check, index+1);
				max = Math.max(cur, max);
				check[i] = false;
			}
		}
		return max;
	}

	private int[][] rotate(int n, int m, int[][] map, Rotate rotate){
		int[][] clone = clone(map, n, m);
		for(int i = 1; i <= rotate.s; i++){
			clone = rotateRound(clone, rotate.r, rotate.c, i);
		}
		return clone;
	}

	private int[][] clone(int[][] arr, int n, int m){
		int[][] clone = new int[n][];
		for(int i = 0; i < n; i++){
			clone[i] = arr[i].clone();
		}
		return clone;
	}

	private int[][] rotateRound(int[][] map, int r, int c, int dist) {
		int x = r - dist - 1;
		int y = c - dist - 1;
		int[] dirX = {0, 1, 0, -1};
		int[] dirY = {1, 0, -1, 0};

		for(int d = 0; d < 4; d++){
			int tmp = 0;
			x += dirX[d];
			y += dirY[d];
			while(!isCorner(x, y, r, c, dist)){

			}
		}
		return map;
	}

	private boolean isCorner(int x, int y, int r, int c, int dist) {
		boolean isTop 			= (x == r - dist - 1);
		boolean isBottom		= (x == r + dist - 1);
		boolean isLeft 			= (y == c - dist - 1);
		boolean isRight 		= (y == c + dist - 1);
		boolean isLeftTop  		= (isLeft  && isTop);
		boolean isRightTop		= (isRight && isTop);
		boolean isLeftBottom 	= (isLeft  && isBottom);
		boolean isRightBottom	= (isRight && isBottom);
		return (isLeftTop || isRightTop || isLeftBottom || isRightBottom);
	}

	private int getValue(int[][] arr){
		int min = Integer.MAX_VALUE;
		for(int[] row : arr){
			int sum = getSum(row);
			min = Math.min(sum, min);
		}
		return min;
	}

	private int getSum(int[] row) {
		int sum = 0;
		for(int elem : row){
			sum += elem;
		}
		return sum;
	}

	private Rotate[] getRotateInfo(String[] rotate, int k) {
		Rotate[] result = new Rotate[k];

		for(int i = 0; i < k; i++){
			String[] parsed = rotate[i].split(" ");
			int r = toInt(parsed[0]);
			int c = toInt(parsed[1]);
			int s = toInt(parsed[2]);
			Rotate cur = new Rotate(r, c, s);
			result[i] = cur;
		}

		return result;
	}
	private int toInt(String s){
		return Integer.parseInt(s);
	}
}
