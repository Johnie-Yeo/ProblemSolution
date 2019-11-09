package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class DragonCurve {
	public class Point{
		public int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point addPoint(Point p) {
			return new Point(x + p.x, y + p.y);
		}
	}
	Point dir[] = {new Point(1,0), new Point(0,-1), new Point(-1,0), new Point(0,1)}; 
	ArrayList<Integer> direction = new ArrayList<>(); 
	public class Dragon{
		public Point location;
		int generation;
		public Dragon(Point p, int g) {
			location = p;
			generation = g;
		}
	}
	public void countDragon() {
		int [][]maze = new int[101][101];
		Scanner kb = new Scanner(System.in);
		int numDragon = kb.nextInt();
		for(int i = 0; i < numDragon; i++) {
			ArrayList<Dragon> arr = new ArrayList<>();
			ArrayList<Integer> direction = new ArrayList<>(); 
			int x = kb.nextInt();
			int y = kb.nextInt();
			int d = kb.nextInt();
			int g = kb.nextInt();
			Point p = new Point(x, y);
			maze[x][y] = 1;
			arr.add(new Dragon(p, 0));
			direction.add(d);
			Point nextP = p.addPoint(dir[d]);
			arr.add(new Dragon(nextP, 0));
			maze[nextP.x][nextP.y] = 1;
			buildDragonCurve(arr, g, maze, direction);
		}
		kb.close();
		int count = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(maze[i][j] == 1) {
					if(isSquare(maze, i, j))
						count++;
				}
			}
		}
		System.out.println(count);
		drawMaze(maze);
	}
	private void drawMaze(int[][] maze) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				System.out.print(maze[j][i] + " ");
			}
			System.out.println();
	}
		
	}
	private boolean isSquare(int[][] maze, int i, int j) {
		if(i == 0 || j ==0)
			return false;
		if(maze[i-1][j] == 1 && maze[i][j-1] == 1 && maze[i-1][j-1] == 1)
			return true;
		return false;
	}
	
	private void buildDragonCurve(ArrayList<Dragon> arr, int gen, int[][] maze, ArrayList<Integer> direct) {
		for(int i = 1; i <=gen; i++) {
			for(int j = arr.size()-1; j > 0; j--) {
				Dragon prev = arr.get(arr.size()-1);
				int nextD = (direct.get(direct.size() + (j - arr.size())) + 1) % 4;
				Point pt = prev.location.addPoint(dir[nextD]);
				direct.add(nextD);
				int g = i;
				arr.add(new Dragon(pt, g));
				maze[pt.x][pt.y] = 1;
			}
		}
	}
	public static void main(String[] args) {
		DragonCurve app = new DragonCurve();
		app.countDragon();
	}

}
