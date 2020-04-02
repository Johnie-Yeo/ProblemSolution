package Programmers;

import java.util.ArrayList;

public class SumOfFactors {
	public static void main(String[] args) {
		new SumOfFactors().solve();
	}
	public void solve() {
		int n = 12, result = 28;
		
		test(n, result);
		
		n = 5; result = 6;
		
		test(n, result);		
	}
	public void test(int n, int result) {
		if(solution(n) == result) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public int solution(int n) {
		ArrayList<Integer> factors = getFactors(n);
		int sum = 0;
		for(int factor : factors) {
			sum += factor;
		}
		return sum;
	}
	public ArrayList<Integer> getFactors(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 1; i <= n; i++) {
			if(n % i == 0) {
				list.add(i);
			}
		}
		
		return list;
	}
}
