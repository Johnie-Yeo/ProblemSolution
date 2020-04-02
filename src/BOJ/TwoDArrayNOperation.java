package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TwoDArrayNOperation {
	//���� 17140
	//���ѽð��� �����ϴ� DP ���� �˰� ���� ����ߴµ� �������� ��������� ���ٺ��� �׳� �ܼ� �ùķ��̼��̴�
	//�׳� ��Ű�´�� �ϳ��ϳ� ��ɸ� ������ ��
	//time�� 100 �ʰ��ϸ� -1
	
	public final int MAX = 100;
	public class Pair implements Comparable<Pair>{
		int number;
		int count;
		public Pair(int number) {
			this.number = number;
			this.count = 1;
		}
		public void countPlus() {
			this.count++;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.count < o.count)
				return -1;
			else if(this.count > o.count)
				return 1;
			else {
				if(this.number < o.number)
					return -1;
				else if(this.number > o.number)
					return 1;
				return 0;
			}
		}
	}
	public static void main(String[] args) {
		TwoDArrayNOperation app = new TwoDArrayNOperation();
		app.solve();
	}
	private void solve() {
		Scanner kb = new Scanner(System.in);
		int r = kb.nextInt();
		int c = kb.nextInt();
		int k = kb.nextInt();
		int [][]arr = new int[MAX][MAX];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				arr[i][j] = kb.nextInt();
		kb.close();
		
		int rSize = 3;
		int cSize = 3;

		int time = 0;
		while(arr[r-1][c-1] != k && time <= 100) {
			if(rSize >= cSize)
				cSize = sortRow(arr, rSize, cSize);
			else
				rSize = sortCol(arr, rSize, cSize);
			time++;
		}
		if(time > 100)
			time = -1;
		System.out.println(time);
	}
	private int sortCol(int[][] arr, int r, int c) {
		int max = 0;
		for(int i = 0; i < c; i++) {
			ArrayList<Pair> list = new ArrayList<>();
			for(int j = 0; j < r;j++) {
				int val = arr[j][i];
				if(val == 0)
					continue;
				int idx = findIdx(list, val);
				if(idx == -1)
					list.add(new Pair(val));
				else
					list.get(idx).countPlus();
			}
			Collections.sort(list);
			int idx = 0;
			for(Pair p: list) {
				if(idx >= MAX)
					break;
				arr[idx++][i] = p.number;
				arr[idx++][i] = p.count;
			}
			for(int j = idx; j < MAX; j++)
				arr[j][i] = 0;
			if(idx > max)
				max = idx;
		}
		return max;
	}
	private int sortRow(int[][] arr, int r, int c) {
		int max = 0;
		for(int i = 0; i < r; i++) {
			ArrayList<Pair> list = new ArrayList<>();
			for(int j = 0; j < c;j++) {
				int val = arr[i][j];
				if(val == 0)
					continue;
				int idx = findIdx(list, val);
				if(idx == -1)
					list.add(new Pair(val));
				else
					list.get(idx).countPlus();
			}
			Collections.sort(list);
			int idx = 0;
			for(Pair p: list) {
				if(idx >= MAX)
					break;
				arr[i][idx++] = p.number;
				arr[i][idx++] = p.count;
			}
			for(int j = idx; j < MAX; j++)
				arr[i][j] = 0;
			if(idx > max)
				max = idx;
		}
		return max;
	}
	private int findIdx(ArrayList<Pair> list, int num) {
		int i = 0;
		for(Pair p: list) {
			if(p.number == num)
				return i;
			i++;
		}
		return -1;
	}
}
