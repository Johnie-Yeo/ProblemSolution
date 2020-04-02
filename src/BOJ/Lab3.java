package BOJ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Lab3 {
	//���� 17142
	//������ BFS
	//��Ȱ��ȭ������ ���̷����� �����Ǿ����� ��Ȳ�� ��Ȯ�� ��Ÿ�� ���� �ʾ� ���� ��̴�
	//�׳� ��Ȱ��ȭ ������ ���̷����� �����Ǿ����� �ʻ󿡴� �ð��� �帥�ɷ� ǥ�������� ��ü�ð��� ���ؼ��� �ð��� �帥�� ǥ�� �����ָ� ��
	//�������� ���طδ� �ϼ����� �������� ����
	final int PATH = 0;
	final int WALL = 1;
	final int VIRUS = 2;
	int [][]MAP;
	int N, M;
	public class Point{
		int idx;
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setIdx(int idx) {
			this.idx = idx;
		}
	}
	public static void main(String[] args) {
		Lab3 app = new Lab3();
		app.solve();
	}
	public void solve() {
		ArrayList<Point> list = processCommand();
		Point []virus = new Point[M];
		for(int i = 0; i < M; i++)
			virus[i] = list.get(i);
		int min = 2100000000;
		boolean getResult = false;

		while(true) {
			int result = spreadVirus(virus);
			if(result >= 0 && min > result) {
				min = result;
				getResult = true;
			}
			if(!setNextVirus(virus, M-1, list, 0) || min == 0)
				break;
		}
		if(!getResult)
			min = -1;

		System.out.println(min);
	}
	private int spreadVirus(Point[] virus) {
		final int []dirX = {-1,0,1,0};
		final int []dirY = {0,-1,0,1};
		int time = -1;
		int [][]map = new int[N][N]; // �����Ѽ� ����
		Deque<Point> list = new ArrayDeque<>();
		for(int i = 0; i < M; i++) {
			list.add(virus[i]);
			map[virus[i].x][virus[i].y] = -1;
		}
		while(!list.isEmpty()) {
			Point cur = list.pop();
			for(int i = 0; i < 4; i++) {
				int x = cur.x + dirX[i];
				int y = cur.y + dirY[i];
				if(x < 0 || y < 0 || x >= N|| y >= N // �� ������ ����ų�
						|| map[x][y] < 0) //�̹� ������ �ڸ��ų�
					continue;
				else if(MAP[x][y] == WALL) {
					map[x][y] = WALL;
					continue;
				}
				map[x][y] = map[cur.x][cur.y]-1;
				if(MAP[x][y] != VIRUS) {
					if(map[x][y] < time)
						time = map[x][y];
				}
				list.add(new Point(x,y));
			}
		}
		if(checkTraversalAllOver(map)) {
			if(time == -1)
				return 0;
			return ((time*-1)-1);
		}
		else
			return -1;
	}
	private boolean checkTraversalAllOver(int[][] map) {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				if(map[i][j] == PATH && MAP[i][j]!= WALL)
					return false;
		return true;
	}
	private boolean setNextVirus(Point[] virus, int idx/*�ø����� �ڸ��� �ε���*/, ArrayList<Point> list, int num/*ī��Ʈ��*/) {
		// �ش� idx�ڸ��� 1 ���� �� �ִ°�?
		// �����ϸ� �ش��ڸ����� �������� ��Ʈ����Ʈ
		// ���ϸ� �״��� �ڸ��� �ø���
		if(idx < 0)
			return false;
		int tmpIdx = virus[idx].idx;
		if(tmpIdx + 1 < list.size() - num) {
			virus[idx] = list.get(tmpIdx+1); // �ش��ڸ� �� �ø�
			for(int i = 1; i <= num; i++)
				virus[idx+i] = list.get(tmpIdx+1+i);
			return true;
		}
		else
			return setNextVirus(virus, idx-1, list, num+1);
	}
	private ArrayList<Point> processCommand() {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		M = kb.nextInt();
		MAP = new int[N][N];
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i< N; i++)
			for(int j = 0; j < N; j++) {
				int tmp = kb.nextInt();
				MAP[i][j] = tmp;
				if(tmp == VIRUS) {
					Point p = new Point(i,j);
					p.setIdx(list.size());
					list.add(p);
				}
			}
		kb.close();
		return list;
	}
}
