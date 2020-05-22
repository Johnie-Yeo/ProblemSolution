package BOJ;

import Test.Test;

import java.util.Scanner;

public class TestAdmin {
	public static void main(String[] args) {
//		new Main().solve();
		new TestAdmin().test();
	}

	private void test() {
		Test test = new Test();
		
		int N;
		int[] A;
		int B, C;
		long result, expect;
		
		N = 1;
		A = new int[]{1};
		B = 1; C = 1;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 1;
		test.test(result, expect).printResult();

		N = 3;
		A = new int[]{3, 4, 5};
		B = 2; C = 2;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 7;
		test.test(result, expect).printResult();

		N = 5;
		A = new int[]{1000000, 1000000, 1000000, 1000000, 1000000};
		B = 5; C = 7;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 714290;
		test.test(result, expect).printResult();

		N = 5;
		A = new int[]{10, 9, 10, 9, 10};
		B = 7; C = 20;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 10;
		test.test(result, expect).printResult();

		N = 5;
		A = new int[]{10, 9, 10, 9, 10};
		B = 7; C = 2;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 13;
		test.test(result, expect).printResult();

		N = 1000000;
		A = new int[N];
		for(int i = 0; i < N; i++){
			A[i] = N;
		}
		B = 1; C = 1;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 1000000000000L;
		test.test(result, expect).printResult();

		N = 3;
		A = new int[]{1,4,3};
		B = 3; C = 2;
		result = getMinNumberOfAdmin(N, A, B, C);
		expect = 4;
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[] A = new int[N];
		for(int i = 0; i < N; i++){
			A[i] = kb.nextInt();
		}
		int B = kb.nextInt();
		int C = kb.nextInt();
		long result = getMinNumberOfAdmin(N, A, B, C);
		System.out.println(result);
		kb.close();
	}

	private long getMinNumberOfAdmin(int n, int[] a, int b, int c) {
		long result = 0;
		for(int i = 0; i < n; i++){
			a[i] = Math.max(a[i]-b, 0);
			result++;
		}
		for(int i = 0; i < n; i++){
			int subAdmin = (a[i] + c - 1) / c;
			result += subAdmin;
		}
		return result;
	}
}
