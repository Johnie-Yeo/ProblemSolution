package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class ChickenDeliver {
	public static void main(String[] args) {
		ChickenDeliver app = new ChickenDeliver();
		app.solve();
	}
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int M = kb.nextInt();
		int [][]map = new int[N][N];
		
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++) {
				int tmp = kb.nextInt();
				map[i][j] = tmp;
				if(tmp == 2)
					list.add(new Point(i, j));
			}
		kb.close();
		
		int []select = new int[M];
		for(int i = 0; i < M; i++)
			select[i] = i;
		select[M-1]--;
		int tmpResult = 0;
		int result = 2100000000; // almost max of int
		while(selectNext(select, M, list.size())) {
			//���� ġŲ�Ÿ� ������
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int dist = 2 * N;
					if(map[i][j] == 1) {
						for(int k = 0; k < M; k++) {
							int tmp = getDist(i, j, list.get(select[k]));
							if(tmp < dist)
								dist = tmp;
						}
						tmpResult += dist;//ù���� ���տ����� ġŲ�Ÿ�
					}
				}
			}
			if(result > tmpResult)
				result = tmpResult;
			tmpResult = 0;
		}
		
		System.out.println(result);
	}
	private int getDist(int i, int j, Point point) {
		int a = Math.abs(i - point.x);
		int b = Math.abs(j - point.y);
		return (a + b);
	}
	private boolean selectNext(int[] select, int m, int size) {
		if(m == size && select[m-1] == m-2) {
			select[m-1]++;
			return true;
		}
		if(select[0] == (size - m))
			return false;
		int tmp = 0;
		for(int i = m - 1; i >= 0; i--) {
			if(++select[i] > size + (i - m))
				tmp++;
			else {
				for(int j = 1; j <= tmp; j++)
					select[i + j] = select[i + j - 1] + 1;
				break;
			}
		}
		return true;
	}
}
