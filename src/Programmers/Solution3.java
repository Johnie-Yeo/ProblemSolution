package Programmers;

import java.util.Scanner;

public class Solution3 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int [][]maze = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				maze[i][j] = kb.nextInt();
		}
		kb.close();
		int re = solution(maze);
		System.out.println(re);
	}

	public static int solution(int[][] board) {
		int answer = 0;
		int a = board.length;
		int b = board[0].length;
		int len = Math.min(a, b);
		for(int i = len; i > 0; i--) {
			// i = ���簢���� �Ѻ� ����
			int tmpX = a - i + 1;
			int tmpY = b - i + 1;
			int num = tmpX * tmpY;
			int x = 0, y = -1;
			for(int n = 0; n < num; n++) {
				y++;
				if(y > b - i) {
					x++;
					y = 0;
				}
				if(isFeasible(x, y, i, board)) {
					answer = i * i;
					return answer;
				}
			}
		}
		return answer;
	}

	public static boolean isFeasible(int x, int y, int len, int[][] board) {
		for(int i = x; i < x+len; i++)
			for(int j = y; j < y+len; j++)
				if(board[i][j] == 0)
					return false;
		return true;
	}
}
