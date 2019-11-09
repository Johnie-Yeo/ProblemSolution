package SWEA;

import java.util.Scanner;
//SWEA 7829
//D4
//B�� ����
//���̺귯�� ��������
//���ø� �ϸ� ��

public class KingOfTreasure {
	public static void main(String[] args) {
		new KingOfTreasure().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int []data = new int[N];
			for(int i = 0; i < N; i++)
				data[i] = kb.nextInt();
			int result = solution(N, data);
			System.out.println("#"+t+" "+result);
		}
		kb.close();
	}
	private int solution(int n, int[] data) {
		quickSort(0, n-1 , data);
		if(n % 2 == 1) {
			int medianValue = data[n/2];
			return medianValue * medianValue;
		}
		else {
			return data[0] * data[n-1];
		}
	}
	public void quickSort(int begin, int end, int []data) {
		int medianIdx;
		if(begin < end) {
			medianIdx = getMedian(begin, end, data);
			quickSort(begin, medianIdx-1, data);
			quickSort(medianIdx+1, end, data);
		}
	}
	private int getMedian(int begin, int end , int[] data) {
		int first = data[begin];
		int median = data[(end - begin)/2];
		int last = data[end];
		int pivotIdx = 0;
		if((first > median && first < last) || (first < median && first > last))
			pivotIdx  = begin;
		else if((median > first && median < last || median < first && median > last))
			pivotIdx =  begin+(end-begin)/2;
		else
			pivotIdx =  end;
		if(pivotIdx != begin) {
			int tmp = data[begin];
			data[begin] = data[pivotIdx];
			data[pivotIdx] = tmp;
			pivotIdx = begin;
		}
		
		int pivot = data[pivotIdx];
		for(int i = begin+1; i <= end; i++) {
			if(data[i] < pivot) {
				int tmp = data[i];
				data[i] = data[pivotIdx+1];
				data[pivotIdx+1] = pivot;
				data[pivotIdx] = tmp;
				pivotIdx++;
			}
		}
		return pivotIdx;
	}
}
