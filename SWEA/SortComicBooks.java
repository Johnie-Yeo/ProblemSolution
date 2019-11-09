package SWEA;

import java.util.HashMap;
import java.util.Scanner;

public class SortComicBooks {
	public static void main(String[] args) {
		new SortComicBooks().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			HashMap<Integer, Integer> list = new HashMap<>();
			for(int n = 0; n < N; n++) {
				list.put(kb.nextInt(), n);
			}
			int result = numberOfRotate(N, list);
			
			System.out.println("#"+t+" "+result);
		}
		
		kb.close();
	}
	public int numberOfRotate(int n, HashMap<Integer, Integer> list) {
		int prevIdx = list.get(1);
		int count = 1;
		
		for(int i = 2; i <= n; i++) {
			int index = list.get(i);
			if(prevIdx > index) {
				count++;
			}
			prevIdx = index;
		}
		
		
		
		return count;
	}
}
