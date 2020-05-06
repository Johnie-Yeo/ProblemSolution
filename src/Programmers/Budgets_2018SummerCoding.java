package Programmers;

import java.util.Arrays;

import Test.OldestTest;

public class Budgets_2018SummerCoding {
	public static void main(String[] args) {
		new Budgets_2018SummerCoding().solve();
	}
	public void solve() {
		OldestTest test = new OldestTest();
		int[] d = {1,3,2,5,4};
		int budget = 9;
		int result = 3;
		test.test(solution(d, budget), result);

		d = new int[]{2,2,3,3};
		budget = 10;
		result = 4;
		test.test(solution(d, budget), result);
	}
	public int solution(int[] d, int budget) {
		int count = 0;
		int sum = 0;
		
		Arrays.sort(d);
		for(int team: d) {
			sum += team;
			if(sum > budget) {
				break;
			}
			count++;
		}
		
		return count;
	}
}
