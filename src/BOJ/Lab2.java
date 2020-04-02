package BOJ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab2 {
//	0�� �� ĭ, 1�� ��, 2�� ���̷����� ���� �� �ִ� ĭ�̴�.
	public final int WALL = 1;
	public final int VIRUS = 2;
	public final int[] dirX = {-1,0,1,0};
	public final int[] dirY = {0,-1,0,1};
	
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point next(int N) {
			int x = this.x;
			int y = this.y+1;
			
			if(y >= N) {
				x++;
				y = 0;
			}
			if(x >= N) {
				return null;
			}
			return new Point(x, y);
		}
		public boolean equals(Object o) {
			if(this.x == ((Point)o).x && ((Point)o).y == this.y) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		new Lab2().solve();
	}
	public void solve() {		
		Scanner kb = new Scanner(System.in);
		
//		ù° �ٿ� �������� ũ�� N(5 �� N �� 50), ���� �� �ִ� ���̷����� ���� M(1 �� M �� 10)�� �־�����.
		int N = kb.nextInt();
		int M = kb.nextInt();
		
//		�����Ҵ� ũ�Ⱑ N��N�� ���簢������ ��Ÿ�� �� ������, ���簢���� 1��1 ũ���� ���簢������ �������� �ִ�. 		
		int[][] map = new int[N][N];
		
		ArrayList<Point> virusPuttable = new ArrayList<>();
//		�����Ҵ� �� ĭ, ������ �̷���� ������, ���� ĭ �ϳ��� ���� �����Ѵ�.
//		��° �ٺ��� N���� �ٿ� �������� ���°� �־�����. 0�� �� ĭ, 1�� ��, 2�� ���̷����� ���� �� �ִ� ĭ�̴�. 2�� ������ M���� ũ�ų� ����, 10���� �۰ų� ���� �ڿ����̴�.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = kb.nextInt();
				if(map[i][j] == VIRUS) {
					virusPuttable.add(new Point(i,j));
					map[i][j] = 0;
				}
			}
		}		
		
		kb.close();
		
//		�������� ��� �� ĭ�� ���̷����� �ְ� �Ǵ� �ּ� �ð��� ����Ѵ�. ���̷����� ��� ���Ƶ� ��� �� ĭ�� ���̷����� �۶߸� �� ���� ��쿡�� -1�� ����Ѵ�.
		int result  = getMinimumTimeVirusToFill(map, M, virusPuttable);
		
		System.out.println(result);
	}
	public int getMinimumTimeVirusToFill(int[][] map, int M, ArrayList<Point> virusPuttable) {
//		�¿��̴� �������� Ư�� ��ġ�� ���̷��� M���� ���� ���̰�, �¿����� ��ȣ�� ���ÿ� ���̷����� ������ �ȴ�.
		Point[] virus = new Point[M];
		
		for(int i = 0; i < M; i++) {
			virus[i] = virusPuttable.get(i);
		}
		
		int min = -1;
		
		while(virus != null) {
			int time = getTimeVirusToSpread(map, virus);
			if(min < 0) {
				min = time;
			}else if(time > 0){
				min = Math.min(min,  time);
			}
			int indexOfVirus = M-1;
			int numberOfVirus = M-1;
			virus = moveVirus(virus, indexOfVirus, numberOfVirus, virusPuttable);
		}
		
		return min;
	}
	public int getTimeVirusToSpread(int[][] originalMap, Point[] virusList) {
//		�Ϻ� �� ĭ�� ���̷����� ���� �� �ִ� ĭ�̴�. ���̷����� �����¿�� ������ ��� �� ĭ���� ���ÿ� �����Ǹ�, 1�ʰ� �ɸ���.
//		�������� ��� �� ĭ�� ���̷����� �ְ� �Ǵ� �ּ� �ð��� ����Ѵ�. ���̷����� ��� ���Ƶ� ��� �� ĭ�� ���̷����� �۶߸� �� ���� ��쿡�� -1�� ����Ѵ�.
		int[][] map = cloneMap(originalMap);
		int N = map.length;
		ArrayDeque<Point> queue = new ArrayDeque<>();
		
		for(Point virus : virusList) {
			queue.add(virus);
			map[virus.x][virus.y] = -1;
		}
		
		int min = -1;
		while(!queue.isEmpty()) {
			Point cur = queue.pop();
			
			for(int d = 0; d < 4; d++) {
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				int time = map[cur.x][cur.y]-1;
				
				if(x < 0 || y < 0 || x >= N || y >= N || map[x][y] != 0) {
					continue;
				}
				
				min = time;
				map[x][y] = time;
				queue.add(new Point(x,y));
			}
		}
		
		if(isFullWithVirus(map)) {			
			min *= -1;
			min--;
			return min;
		}else {
			return -1;
		}
	}
	private boolean isFullWithVirus(int[][] map) {
		int N = map.length;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	private int[][] cloneMap(int[][] originalMap) {
		int N = originalMap.length;
		int [][]map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = originalMap[i].clone();
		}
		return map;
	}
	public Point[] moveVirus(Point[] virus, int indexOfVirus, int numberOfVirus, ArrayList<Point> virusPuttable) {
		if(indexOfVirus < 0) {
			return null;
		}
		
		int index = virusPuttableIndex(virusPuttable, virus[indexOfVirus]);
		
		if(++index < virusPuttable.size() - numberOfVirus + indexOfVirus) {
			for(int i = 0; i <= numberOfVirus - indexOfVirus; i++) {				
				virus[indexOfVirus+i] = virusPuttable.get(index+i);
			}
			
			return virus;
		}else {
			return moveVirus(virus, indexOfVirus-1, numberOfVirus, virusPuttable);
		}
	}
	public int virusPuttableIndex(ArrayList<Point> list, Point p) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(p)) {
				return i;
			}
		}
		return -1;
	}
}
