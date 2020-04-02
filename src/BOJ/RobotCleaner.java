package BOJ;

import java.util.Scanner;

public class RobotCleaner {
	//���� 14503
	//�׳� ��¥ ��Ű�´�θ� �ϸ� �ȴ�
	int N, M;
	int [][]map;
	final int []dirX = {-1,0,1,0};
	final int []dirY = {0,1,0,-1};
	final int DIRTY = 0, WALL = 1, CLEAN = 2;
	
	public static void main(String[] args) {
		RobotCleaner app = new RobotCleaner();
		app.solve();
	}
	private void solve() {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		M = kb.nextInt();
		map = new int[N][M];
		
		int r = kb.nextInt();
		int c = kb.nextInt();
		int d = kb.nextInt();
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				map[i][j] = kb.nextInt();
		kb.close();
		int result = clean(r,c,d);
		System.out.println(result);
	}
	public int clean(int r, int c, int d) {
		if(map[r][c] == DIRTY)
			map[r][c] = CLEAN;
		int dir = d;
		for(int i = 0; i < 4; i++) {
			dir = (dir+3)%4;
			int x = r + dirX[dir];
			int y = c + dirY[dir];
			if(x < 0 || y < 0 || x >= N || y >= M)
				continue;
			if(map[x][y] == DIRTY)
				return 1+clean(x,y,dir);
			if(i == 3) {
				int tmpdir = (dir+2)%4;
				x = r + dirX[tmpdir];
				y = c + dirY[tmpdir];
				if(map[x][y] != WALL) {
					i = -1;
					r = x;
					c = y;
				}
			}
		}
		return 1;
	}
}
