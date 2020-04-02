package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class CCTV {
	int N = 0, M = 0;
	int [][]map;
	int[] dirX = { -1, 0, 1, 0 };
	int[] dirY = { 0, -1, 0, 1 };
	int MAX = 0;
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		CCTV app = new CCTV();
		app.solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		M = kb.nextInt();
		map = new int[N][M];
		ArrayList<Point> cctv = new ArrayList<>();
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++) {
				int tmp = kb.nextInt();
				map[i][j] = tmp;
				if(tmp > 0 && tmp < 6)
					cctv.add(new Point(i, j));
				else if(tmp == 6)
					cnt++;
			}
		kb.close();
		
		selectCCTV(cctv, 0);
		int result = N * M - (cnt + MAX + cctv.size());
		System.out.println(result);
	}
	private void selectCCTV(ArrayList<Point> cctv, int idx) {
		if(idx == cctv.size()) {
			int max = getArea();
			if(max > MAX)
				MAX = max;
			return;
		}
		Point tmp = cctv.get(idx);
		for(int i = 0; i < 4; i++) {
			setType(map[tmp.x][tmp.y], i, tmp.x, tmp.y, idx+1);
			selectCCTV(cctv, idx+1);
			getBackMap(idx + 1);
		}
	}
	private int getArea() {
		int count = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] < 0)
					count++;
		return count;
	}
	private void getBackMap(int idx) {
		idx *= -1;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] == idx)
					map[i][j] = 0;
	}
	public void setType(int type, int dir, int i, int j, int idx) {
		if(type == 1)
			typeOne(dir, i, j, idx);
		else if(type == 2)
			typeTwo(dir, i, j, idx);
		else if(type == 3)
			typeThree(dir, i, j, idx);
		else if(type == 4)
			typeFour(dir, i, j, idx);
		else if(type == 5)
			typeFive(dir, i, j, idx);
	}
	public void typeOne(int dir, int i, int j, int idx) {
		i += dirX[dir];
		j += dirY[dir];
		while(i >= 0 && j >= 0 && i < map.length
				&& j < map[0].length && map[i][j] != 6) {
			if(map[i][j] == 0)
				map[i][j] = idx * -1;
			i += dirX[dir];
			j += dirY[dir];
		}
	}
	public void typeTwo(int dir, int i, int j, int idx) {
		typeOne(dir, i, j, idx);
		typeOne((dir+2)%4, i, j, idx);
	}
	public void typeThree(int dir, int i, int j, int idx) {
		typeOne(dir, i, j, idx);
		typeOne((dir+1)%4, i, j, idx);
	}
	public void typeFour(int dir, int i, int j, int idx) {
		typeOne(dir, i, j, idx);
		typeThree((dir+1)%4, i, j, idx);
	}
	public void typeFive(int dir, int i, int j, int idx) {
		typeThree(dir, i, j, idx);
		typeThree((dir+2)%4, i, j, idx);
	}
}
