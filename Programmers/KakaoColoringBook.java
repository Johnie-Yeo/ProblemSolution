package Programmers;

import java.util.ArrayDeque;
import java.util.Deque;

//���ǻ��� �������� ����ġ�� �׿����� �÷����Ͽ� �� ��ȭ�� �׷��޶�� ��Ź�Ͽ� ���� ���� �׸��� �޾Ҵ�. ���� ���� �׸��� ���̵� ������ �÷����Ͽ� �ְ� �;��� ����ġ�� ������ ������ ��ĥ�ϱⰡ ��ٷο� ��������ٴ� ����� �߰��ϰ� �׸��� ���̵��� ������ ���� �����Ͽ���. (�����̶� �����¿�� ����� ���� ������ ������ �ǹ��Ѵ�.)
//
//�׸��� �� ���� ������ �ִ����� ���� ū ������ ���̴� ������ ����ϴ� ���α׷��� �ۼ��غ���.

//���� �ܼ��� BFS
public class KakaoColoringBook {
	public static void main(String[] args) {
		new KakaoColoringBook().solve();
	}
	public void solve() {
		int m = 13;
		int n = 16;
		int [][]picture = {
				{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
				{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{0,1,1,1,1,2,1,1,1,1,2,1,1,1,1,0},
				{0,1,1,1,2,1,2,1,1,2,1,2,1,1,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,1,3,3,3,1,1,1,1,1,1,3,3,3,1,0},
				{0,1,1,1,1,1,2,1,1,2,1,1,1,1,1,0},
				{0,0,1,1,1,1,1,2,2,1,1,1,1,1,0,0},
				{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0}
		};
		int[] result = solution(m,n,picture);
		for(int val : result)
			System.out.print(val+" ");
		System.out.println();
	}
	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		boolean[][] visit = new boolean[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(picture[i][j] > 0 && !visit[i][j]) {
					int size = bfs(visit, picture, i, j);
					numberOfArea++;
					if(size > maxSizeOfOneArea)
						maxSizeOfOneArea = size;
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;

		return answer;
	}
	private int bfs(boolean[][] visit, int[][] picture, int i, int j) {
		class Point{
			int x;
			int y;

			public Point(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
		int[] dirX = {-1, 0, 1, 0};
		int[] dirY = {0, -1, 0, 1};
		Deque<Point> queue = new ArrayDeque<>();
		int area = 0;
		int color = picture[i][j];
		int X = picture.length;
		int Y = picture[0].length;
		
		queue.push(new Point(i, j));
		visit[i][j] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.pop();
			area++;
			
			for(int d = 0; d < 4; d++) {
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				
				if(x < 0 || y < 0 || x >= X || y >= Y)
					continue;
				if(picture[x][y] == color && !visit[x][y]) {
					queue.addLast(new Point(x, y));
					visit[x][y] = true;
				}
			}
		}
		return area;
	}
}
