package Programmers;

import Test.OldestTest;

public class GetAverage {
	public static void main(String[] args) {
		new GetAverage().solve();
	}
	public void solve() {
		OldestTest test = new OldestTest();
		int[] input1 = {1,2,3,4};
		double result = 2.5;
		
		test.test(solution(input1), result);
		
		int[] input2 = {5,5};
		result = 5;
		
		test.test(solution(input2), result);

	}
	public double solution(int[] arr) {
		int size = arr.length;
		int sum = 0;
		for(int element : arr) {
			sum += element;
		}
		double result = (double)sum / size;
		return result;
	}
}
