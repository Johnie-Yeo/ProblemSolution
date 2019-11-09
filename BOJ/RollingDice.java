package BOJ;

import java.util.Scanner;

public class RollingDice {
	public final int []dirX = {0,0,-1,1};
	public final int []dirY = {1,-1,0,0};
	public final int EAST = 0;
	public final int WEST = 1;
	public final int NORTH = 2;
	public final int SOUTH = 3;
	
	public class Dice{
		int up, down, left, right, front, back;
		public Dice() {
			this.up = 0;
			this.down = 0;
			this.left = 0;
			this.right = 0;
			this.front = 0;
			this.back = 0;
		}
		public void roll(int dir) {
			if(dir == EAST) {
				this.rollEast();
			}else if(dir == WEST) {
				this.rollWest();
			}else if(dir == SOUTH) {
				this.rollSouth();
			}else if(dir == NORTH) {
				this.rollNorth();
			}
		}
		public void rollNorth() {
			int tmp = front;
			front = down;
			down = back;
			back = up;
			up = tmp;
		}
		public void rollSouth() {
			int tmp = front;
			front = up;
			up = back;
			back = down;
			down = tmp;
		}
		public void rollEast() {
			int tmp = left;
			left = down;
			down = right;
			right = up;
			up = tmp;
		}
		public void rollWest() {
			int tmp = left;
			left = up;
			up = right;
			right = down;
			down = tmp;
		}
	}
	public static void main(String[] args) {
		new RollingDice().solve();
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
		
		play(N, M, map, x, y, num, order);
	}
	private void play(int N, int M, int[][] map, int x, int y, int num, int[] order) {
		Dice dice = new Dice();
		for(int i = 0; i < num; i++) {
			int dir = order[i]-1;
			int tmpX = x + dirX[dir];
			int tmpY = y + dirY[dir];
			
			if(tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= M) {
				continue;
			}
			
			x = tmpX;
			y = tmpY;
			dice.roll(dir);
			if(map[x][y] == 0) {//�̵��� ĭ�� �����ִ� ���� 0�̸�
				map[x][y] = dice.down;//�ֻ��� �ٴڸ��� ����
			}else {
				dice.down = map[x][y];
				map[x][y] = 0;
			}
			System.out.println(dice.up);
		}
	}
}	
