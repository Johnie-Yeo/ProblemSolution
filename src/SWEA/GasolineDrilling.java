package SWEA;

import java.util.Scanner;
//SWEA 7830
//D5
//������� ���簢���� �����Ҽ� �ۿ� ���� 
//������ �����ϱ� ���� ���簢���� �� ���� ���̴� Ȧ������ ��
public class GasolineDrilling {
	public static void main(String[] args) {
		new GasolineDrilling().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int M = kb.nextInt();
			int [][]map = new int[N][M];
			String tmp;
			for(int i = 0; i < N; i++) {
				tmp = kb.next();
				for(int j = 0; j < M; j++)
					map[i][j] = tmp.charAt(j)-48;
			}
			
			int result = solution(N, M, map);
			System.out.println("#"+t+" "+result);
		}
		kb.close();
	}
	public int solution(int n, int m, int[][] map) {
		int maxSquare = getMax(n, m);
		if(maxSquare %2 == 0)
			maxSquare--;
		int size = (maxSquare+1)/2;
		
		while(size > 0){
			//�� ����� �����ϸ� ����
			for(int i = 0; i < n - maxSquare + 1; i++) {
				for(int j = 0; j < m - maxSquare + 1; j++) {
					if(doesExist(maxSquare, size, i, j, map))
						return size;
				}
			}
			size--;
			maxSquare-=2;
		}
		
		return size;
	}
	private boolean doesExist(int maxSquare, int size, int row, int col, int[][] map) {
		int i = row;
		int j = size -1+col;
		int leftJ = j;
		int rightJ = j;
		
		if(map[i][j] == 0)
			return false;
		i++;
		leftJ--;
		rightJ++;
		while(i <= size - 1 + row) {
			if(map[i][leftJ] == 0 || map[i][rightJ] == 0)
				return false;
			i++;
			leftJ--;
			rightJ++;
		}
		leftJ += 2;
		rightJ -= 2;
		while(i < maxSquare + row) {
			if(map[i][leftJ] == 0 || map[i][rightJ] == 0)
				return false;
			i++;
			leftJ++;
			rightJ--;
		}
		return true;
	}
	private int getMax(int n, int m) {
		return n > m?  m : n;
	}
}