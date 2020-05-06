package Programmers;

import Test.OldestTest;

public class MatrixAdd {
	public static void main(String[] args) {
		new MatrixAdd().solve();
	}
	public void solve() {
		OldestTest test = new OldestTest();

		int[][] arr1 = {{1,2},{2,3}};
		int[][] arr2 = {{3,4},{5,6}};
		int[][] result = {{4,6},{7,9}};
		test.test(solution(arr1, arr2), result);
		
		arr1 = new int[][]{{1},{2}};
		arr2 = new int[][]{{3},{4}};
		result = new int[][]{{4},{6}};
		test.test(solution(arr1, arr2), result);
	}
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int row = arr1.length;
		int col = arr1[0].length;
		
		int[][] answer = new int[row][col];
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				answer[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
		return answer;
	}
}	
