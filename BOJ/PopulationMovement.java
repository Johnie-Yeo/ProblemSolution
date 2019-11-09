package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class PopulationMovement {
	public static void main(String[] args) {
		PopulationMovement app = new PopulationMovement();
		app.ps();
	}
	public class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public void ps() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int L = kb.nextInt();
		int R = kb.nextInt();
		int [][]A = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				A[i][j] = kb.nextInt();
		kb.close();
		
		int count = 0;
		boolean curMovement = true;
		while(curMovement) {
			if(count > 2000)
				break;
			boolean [][]check = new boolean[N][N];
			curMovement = false;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++) {
					if(check[i][j])
						continue;
					else {
						int sum = 0;
						ArrayList<Point> list = new ArrayList<>();
						if((sum = openBorder(A, i, j, check, N, L, R, list)) > A[i][j]) {
							popMove(A, list, sum);
							curMovement = true;
						}
					}
				}
			if(curMovement)
				count++;
		}
		System.out.println(count);
	}
	
	private void popMove(int[][] pop, ArrayList<Point> list, int sum) {
		for(int i = 0; i < list.size(); i++) {
			int aver = sum / list.size();
			Point curP = list.get(i);
			pop[curP.x][curP.y] = aver;
		}
	}

	private int openBorder(int[][] pop, int curX, int curY, boolean[][] check, int n, int l, int r, ArrayList<Point> list) {
		int sum = 0;
		int []dirX = { -1, 0, 1, 0 };
		int []dirY = { 0, -1, 0, 1 };
		if(check[curX][curY])
			return sum;
		else {
			check[curX][curY] = true;
			sum += pop[curX][curY];
			list.add(new Point(curX, curY));
			
			for(int i = 0; i < 4; i++) {
				int x = curX + dirX[i];
				int y = curY + dirY[i];
				if(x < 0 || y < 0 || x >= n || y >= n)
					continue;
				int diff = Math.abs(pop[curX][curY] - pop[x][y]);
				if(diff >= l && diff <= r) {
					sum += openBorder(pop, x, y, check, n, l, r, list);
				}
			}
		}
		return sum;
	}
}
