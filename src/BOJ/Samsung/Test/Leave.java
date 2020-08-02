package BOJ.Samsung.Test;

import Test.Test;

import java.util.Scanner;

public class Leave {
	public static void main(String[] args) {
//		new Main().solve();
		new Leave().test();
	}

	private void test() {
		Test test = new Test();

		int N;
		int[] T, P;
		int result, expect;

		N = 7;
		T = new int[]{3, 5, 1, 1, 2, 4, 2};
		P = new int[]{10, 20, 10, 20, 15, 40, 200};
		result = getMaxWage(N, T, P);
		expect = 45;
		test.test(result, expect).printResult();

		N = 10;
		T = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		P = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		result = getMaxWage(N, T, P);
		expect = 55;
		test.test(result, expect).printResult();

		N = 10;
		T = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		P = new int[]{10, 9, 8, 7, 6, 10, 9, 8, 7, 6};
		result = getMaxWage(N, T, P);
		expect = 20;
		test.test(result, expect).printResult();

		N = 10;
		T = new int[]{5, 4, 3, 2, 1, 1, 2, 3, 4, 5};
		P = new int[]{50, 40, 30, 20, 10, 10, 20, 30, 40, 50};
		result = getMaxWage(N, T, P);
		expect = 90;
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int[] T = new int[N];
		int[] P = new int[N];
		for(int i = 0; i < N; i++) {
			T[i] = kb.nextInt();
			P[i] = kb.nextInt();
		}
		kb.close();

		int result = getMaxWage(N, T, P);
		System.out.println(result);
	}

	private int getMaxWage(int n, int[] t, int[] p) {
		int index = 0;
		int wage = 0;
		int max = dfs(n, t, p, index, wage);
		return max;
	}

	private int dfs(int n, int[] t, int[] p, int index, int wage) {
		int max = 0;

		if(index >= n){
			return wage;
		}

		if(index + t[index] <= n){
			int tmp1 = dfs(n, t, p, index + t[index], wage + p[index]);
			max = Math.max(max, tmp1);
		}
		int tmp2 = dfs(n, t, p, index+1, wage);
		max = Math.max(max, tmp2);
		return max;
	}

}
