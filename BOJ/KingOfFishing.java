package BOJ;

import java.util.Scanner;

public class KingOfFishing {
	//���� 17143
	//������ �ùķ��̼� ����
	//��´� -> ����Ⱑ �̵��Ѵ��� �ݺ�
	public int []dirX = {-1,1,0,0};
	public int []dirY = {0,0,1,-1};
	public class Shark{
		int dir;
		int speed;
		int size;
		public Shark(int dir, int speed, int size) {
			this.dir = dir;
			this.speed = speed;
			this.size = size;
		}
	}
	public static void main(String[] args) {
		KingOfFishing app = new KingOfFishing();
		app.solve();
	}
	public void solve(){
		Scanner kb = new Scanner(System.in);
		int R = kb.nextInt();
		int C = kb.nextInt();
		int M = kb.nextInt(); // number of shark
		Shark [][]map = new Shark[R+1][C+1];
		for(int i = 0; i < M; i++) {
			int r = kb.nextInt();
			int c = kb.nextInt();
			int s = kb.nextInt(); //speed
			int d = kb.nextInt();//direction
			int z = kb.nextInt(); //size
			map[r][c] = new Shark(d-1,s,z);
		}
		kb.close();
		int count = 0;
		for(int i = 1; i <= C; i++) {
			count += getShark(i, R, map);
			moveShark(R,C,map);
		}
		System.out.println(count);
	}
	private void moveShark(int row, int col, Shark[][] map) {
		Shark [][]tmpMap = new Shark[row+1][col+1];
		for(int i = 1; i <= row; i++)
			for(int j = 1; j <= col; j++)
				if(map[i][j] != null) {
					Shark shark = map[i][j];
					//move
					int r = i + dirX[shark.dir] * shark.speed;
					int c = j + dirY[shark.dir] * shark.speed;
					
					r = handleOutOfRangeCase(r,row, shark);
					c = handleOutOfRangeCase(c, col, shark);
					
					if(tmpMap[r][c] == null || (tmpMap[r][c].size < shark.size)) 
						tmpMap[r][c] = shark;
				}
		for(int i = 1; i <= row; i++)
			for(int j = 1; j <= col; j++)
				map[i][j] = tmpMap[i][j];
	}
	private int handleOutOfRangeCase(int x, int X, Shark shark) {
		int change = 0;
		while(x > X || x < 1) {
			if(x > X)
				x = X + (X - x);
			else if(x < 1)
				x = 1 + (1 -x);
			change = (change+1)%2;
		}
		if(change == 1) {
			if(shark.dir == 0)
				shark.dir = 1;
			else if(shark.dir == 1)
				shark.dir = 0;
			else if(shark.dir == 2)
				shark.dir = 3;
			else if(shark.dir == 3)
				shark.dir = 2;
		}
		return x;
	}
	private int getShark(int location, int row, Shark[][] map) {
		for(int i = 1; i <= row; i++) {
			Shark shark = map[i][location];
			if(shark != null) {
				map[i][location] = null;
				return shark.size;
			}
		}
		return 0;
	}
}
