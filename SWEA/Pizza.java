package SWEA;

import java.util.Scanner;

public class Pizza {
	public static void main(String[] args) {
		new Pizza().solve();
	}
	public void solve(){
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int K = kb.nextInt();
			
			int[] pizza = new int[N];
			
			int result = getNumberOfCombination(pizza, 0, N, K);
			System.out.println("#"+t+" "+result);
		}
		
		kb.close();
	}
	public int getNumberOfCombination(int[] pizza, int index, int N, int K) {
		if(index == N) {
			if(N == 1 && K == 1) {
				return 1;
			}else if(pizza[index-1] == pizza[0]) {
				return 0;
			}else {
				if(doesEveryoneGetPizza(pizza, K)) {					
					return 1;
				}
				return 0;
			}
		}

		int result = 0;
		for(int i = 0; i < K; i++) {
			pizza[index] = i;
			if(index == 0) {
				result += getNumberOfCombination(pizza, index+1, N, K);
			}
			else if(index > 0 && pizza[index-1] != pizza[index]) {
				result += getNumberOfCombination(pizza, index+1, N, K);
			}
		}
		return result;
	}
	public boolean doesEveryoneGetPizza(int[] pizza, int k) {
		boolean[] check = new boolean[k];
		for(int slice : pizza) {
			check[slice] = true;
		}
		
		for(boolean chk : check) {
			if(!chk) {
				return false;
			}
		}
		return true;
	}
}
