package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeInvestment {

	public static class Tree{
		int age;
		public Tree(int age) {
			this.age = age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tmp = br.readLine();
		StringTokenizer tk = new StringTokenizer(tmp);
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int K = Integer.parseInt(tk.nextToken());
		
		int [][]A = new int[N+1][N+1];
		int [][]nutrient = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			for(int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(tk.nextToken());
				nutrient[i][j] = 5;
			}
		}
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Tree> [][]treeList = new ArrayList[N+1][N+1];
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= N; j++)
				treeList[i][j] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			int x = Integer.parseInt(tk.nextToken());
			int y = Integer.parseInt(tk.nextToken());
			int age = Integer.parseInt(tk.nextToken());
			treeList[x][y].add(new Tree(age));
		}
		int count = M;
	
		for(int i = 0; i < K; i++) {
			count += springSummer(treeList, nutrient, N);		
			count +=autumn(treeList, N);
			winter(nutrient, A, N);
		}
		System.out.println(count);
	}
	private static int autumn(ArrayList<Tree>[][] list, int n) {
		
		int count = 0;
		int []dirX = {-1, 0, 1, 1, 1, 0, -1, -1};
		int []dirY = {-1, -1, -1, 0, 1, 1, 1, 0};
		
		@SuppressWarnings("unchecked")
		ArrayList<Tree> [][]tmpList = new ArrayList[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++)
				tmpList[i][j] = new ArrayList<>();
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int size = list[i][j].size();
				for(int idx = size-1; idx >= 0; idx--) {
					if(list[i][j].get(idx).age < 5)
						break;
					if(list[i][j].get(idx).age % 5 == 0) {
						for(int d = 0; d < 8; d++) {
							int x = i + dirX[d];
							int y = j + dirY[d];
							
							if(x < 1 || y < 1 || x > n || y > n)
								continue;
							else {
								tmpList[x][y].add(new Tree(1));
								count++;
							}
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++){
				if(!tmpList[i][j].isEmpty())
					list[i][j].addAll(0, tmpList[i][j]);
			}
		}
		
		return count;
		
	}
	private static int springSummer(ArrayList<Tree>[][] list, int[][] nut, int n) {
		
		int count = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				for(int idx = 0; idx < list[i][j].size(); idx++) {
					int age = list[i][j].get(idx).age;
					if(age > nut[i][j]) {
						//������� �ڿ����� �ѹ��� ����
						//���� �ݸ�ŭ ��е� ���ϱ�
						for(int k = idx; k < list[i][j].size(); k++) {
							nut[i][j] += (list[i][j].get(k).age/2);
							list[i][j].remove(k);
							count--;
							k--;
						}
					}
					else {
						nut[i][j] -= age;
						list[i][j].get(idx).age++;
					}
				}
			}
		}
		return count;
	}

	private static void winter(int[][] a, int[][] a2, int n) {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++)
				a[i][j] += a2[i][j];
		}
	}
}
