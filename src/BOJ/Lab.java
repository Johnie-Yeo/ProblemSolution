package BOJ;

import java.util.Scanner;

public class Lab {
	int [][]lab;
	final int PATH = 0;
	final int WALL = 1;
	final int VIRUS = 2;
	int X, Y;
	
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point goNext() {
			if(x < X && y + 1 < Y)
				return new Point(x, y+1);
			else if(x + 1 < X && y + 1 == Y)
				return new Point(x + 1, 0);
			else
				return new Point(X, Y);
		}
		public boolean equals(Point p) {
			if(x == p.x && y == p.y)
				return true;
			return false;
		}
	}
	public void readMap() {
		Scanner kb = new Scanner(System.in);
		X = kb.nextInt();
		Y = kb.nextInt();
		lab = new int[X][Y];
		for(int i = 0; i< X ; i++) {
			for(int j = 0; j < Y; j++) {
				lab[i][j] = kb.nextInt();
			}
		}
		kb.close();
	}
	public int getMaximumSafeArea() {
		Point p1 = new Point(0, 0);
		Point p2 = p1.goNext();
		Point p3 = p2.goNext();
		Point last = new Point(X, Y);
		int max = 0;
		while(!p1.equals(last)) {
			if(p2.equals(last)) {
				p1 = p1.goNext();
				p2 = p1.goNext();
				p3 = p2.goNext();
				continue;
			}
			if(p3.equals(last)) {
				p2 = p2.goNext();
				p3 = p2.goNext();
				continue;
			}
			if(lab[p1.x][p1.y] != PATH) {
				p1 = p2;
				p2 = p3;
				p3 = p3.goNext();
				continue;
			}
			if(lab[p2.x][p2.y] != PATH) {
				p2 = p3;
				p3 = p3.goNext();
				continue;
			}
			if(lab[p3.x][p3.y] != PATH) {
				p3 = p3.goNext();
				continue;
			}
			lab[p1.x][p1.y] = -1;
			lab[p2.x][p2.y] = -1;
			lab[p3.x][p3.y] = -1;
			spreadingVirus();
			int result = calSafeArea();
			if(result > max)
				max = result;
			p3 = p3.goNext();
			getBackLab();
		}
		
		
		return max;
	}
	public void spreadingVirus() {
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(lab[i][j] == VIRUS) {
					DFS(i - 1, j);
					DFS(i, j + 1);
					DFS(i + 1, j);
					DFS(i, j - 1);
				}
			}
		}
	}
	public void DFS(int i, int j) {
		if(i < 0 || j < 0 || i >= X || j >= Y 
				|| lab[i][j] != PATH)
			return;
		else {
			lab[i][j] = -2;
			DFS(i - 1, j);
			DFS(i, j + 1);
			DFS(i + 1, j);
			DFS(i, j - 1);
		}
	}
	public int calSafeArea() {
		int count = 0;
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(lab[i][j] == PATH)
					count++;
			}
		}
		return count;
	}
	public void getBackLab() {
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(lab[i][j] < 0)
					lab[i][j] = PATH;
			}
		}
	}
	public static void main(String[] args) {
		Lab app = new Lab();
		app.readMap();
		System.out.println(app.getMaximumSafeArea());
	}
}
