package SWEA;

import java.util.Scanner;
// SWEA 7814
// ��ü�� ������ �ʿ��ϴ�.
// ����
public class TasteSpecialist {
	int MAX = 0;
	public class Stats{
		int level;
		int understandability;//�̷��ܾ ������
		int point;
		public Stats(int l, int u, int p) {
			this.level = l;
			this.understandability = u;
			this.point = p;
		}
		public void getPoint(int l, int u) {
			this.level += l;
			this.understandability += u;
		}
		public int sum() {
			return this.level + this.understandability;
		}
	}
	public static void main(String[] args) {
		new TasteSpecialist().solve();
	}
	public void solve() {
		Scanner kb= new Scanner(System.in);
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			MAX = 0;
			int N = kb.nextInt();
			Stats[] list = new Stats[N];
			for(int i = 0; i < N; i++) {
				int l = kb.nextInt();
				int u = kb.nextInt();
				int p = kb.nextInt();
				list[i] = new Stats(l, u, p);
			}
			int result = solution(N, list);
			System.out.println("#"+t+" "+result);
		}
		kb.close();
	}
	private int solution(int n, Stats[] list) {
		Stats init = new Stats(1,1,1);

		quickSort(list, 0, n-1);
		int count = 0;
		int potential = 0;
		dfs(init, list, n, count, potential);

		return MAX;
	}
	private void dfs(Stats init, Stats[] list, int n, int count, int potential) {
		if(count >= n) {
			return;
		}
		if((list[count].level > init.level) && (list[count].understandability > init.understandability)) {
			return;
		}else {
			count++;
			if(count >= n) {
				MAX = n;
				return;
			}
			//������ �÷�����
			int diff = list[count].level - init.level;
			if(diff < list[count].point + potential) {
				init.getPoint(diff, 0);
				dfs(init, list, n, count, potential + (list[count].point - diff));
				init.getPoint(diff*-1, 0);
			}
			//���ط��� �÷��� ��
			diff = list[count].understandability - init.understandability;
			if(diff < list[count].point + potential) {
				init.getPoint(0, diff);
				dfs(init, list, n, count, potential + (list[count].point - diff));
				init.getPoint(0, diff*-1);
			}
			if(MAX < count)
				MAX = count;
		}
	}
	private void quickSort(Stats[] list, int begin, int end) {
		int pivot;
		if(begin < end) {
			pivot = medianValue(list, begin, end);
			quickSort(list, begin, pivot-1);
			quickSort(list, pivot+1, end);
		}
	}
	private int medianValue(Stats[] list, int begin, int end) {
		int middle = begin + (end - begin) / 2;
		int pivot = middle;
		if((list[begin].sum() < list[middle].sum() && list[begin].sum() > list[end].sum())||
				(list[begin].sum() > list[middle].sum() && list[begin].sum() < list[end].sum())) {
			pivot = begin;
		}else if((list[end].sum() < list[middle].sum() && list[end].sum() > list[begin].sum())||
				(list[end].sum() > list[middle].sum() && list[end].sum() < list[begin].sum())) {
			pivot = end;
		}
		if(pivot != begin) {
			Stats tmp = list[pivot];
			list[pivot] = list[begin];
			list[begin] = tmp;
			pivot = begin;
		}

		for(int i = begin + 1; i <= end; i++) {
			if(list[i].sum() < list[pivot].sum()) {
				Stats tmp = list[i];
				list[i] = list[pivot+1];
				list[pivot+1] = list[pivot];
				list[pivot] = tmp;
				pivot++;
			}
		}
		return pivot;
	}
}