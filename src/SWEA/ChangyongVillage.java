package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangyongVillage {
	public static void main(String[] args) {
		//SwExperts 7465
		//Very Simple DFS problem
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int M = kb.nextInt();
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] list = new ArrayList[N+1];
			for(int i = 1; i <= N; i++)
				list[i] = new ArrayList<>();

			boolean []chk = new boolean[N+1];
			for(int i = 0; i < M; i++) {
				int a = kb.nextInt();
				int b = kb.nextInt();
				list[a].add(b);
				list[b].add(a);
			}
			int result = 0;
			for(int i = 1; i <= N; i++) {
				if(!chk[i]) {
					makeGroup(i, list, chk);
					result++;
				}
			}
			System.out.println("#"+t+" "+result);
		}
		kb.close();
	}

	private static void makeGroup(int idx, ArrayList<Integer>[] list2, boolean[] chk) {
		ArrayList<Integer> list = list2[idx];
		chk[idx] = true;
		if(!knowAnyBodyElse(list, chk)) {
			return;
		}
		else {
			for(int i = 0; i < list.size(); i++) {
				int k = list.get(i);
				boolean tmp = chk[k];
				if(!tmp) {
					makeGroup(k, list2, chk);
				}
			}
		}
	}
	private static boolean knowAnyBodyElse(ArrayList<Integer> list, boolean[] chk) {
		for(int idx = 0; idx < list.size(); idx++) {
			if(!chk[list.get(idx)])
				return true;
		}
		return false;
	}
}
