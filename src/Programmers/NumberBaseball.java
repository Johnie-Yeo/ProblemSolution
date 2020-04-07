package Programmers;

import java.util.Arrays;

import Test.Test;

public class NumberBaseball {
	public static void main(String[] args) {
		new NumberBaseball().solve();
	}
	public void solve() {
		Test test = new Test();

		int[][] baseball = {
				{123, 1, 1}, {356, 1, 0}, 
				{327, 2, 0}, {489, 0, 1}
		};
		int expect = 2;
		int result = solution(baseball);
		test.test(result, expect);
		
		baseball = new int[][]{
				{123, 1, 1}, {356, 1, 0}, 
				{327, 2, 0}, {489, 0, 1},
				{328, 3, 0}
		};
		expect = 1;
		result = solution(baseball);
		test.test(result, expect);
		
		baseball = new int[][]{
			{123,0,3}
		};
		expect = 8;
		result = solution(baseball);
		test.test(result, expect);
	}
	public int solution(int[][] baseball) {
		boolean[] candidates = new boolean[1000];
		Arrays.fill(candidates, true);
		
		for(int i = 100; i < 1000; i++) {
			for(int[] pitch : baseball) {
				if(!isFeasible(i, pitch)) {
					candidates[i] = false;
				}
			}
		}

		int count = 0;
		for(int i = 100; i < 1000; i++) {
			if(candidates[i]) {
				if(isPossibleAnswer(i)) {					
					count++;
				}
			}
		}
		return count;
	}
	public boolean isPossibleAnswer(int number) {
		boolean[] check = new boolean[10];
		while(number > 0) {
			int mod = number % 10;
			if(mod == 0) {
				return false;
			}else {
				if(check[mod]) {
					return false;
				}
			}
			check[mod] = true;
			number /= 10;
		}
		return true;
	}
	public int[] toArray(int number) {
		int[] result = new int[3];
		result[2] = number % 10;
		number /= 10;
		result[1] = number % 10;
		number /= 10;
		result[0] = number % 10;

		return result;
	}
	public boolean isFeasible(int candidate, int[] result) {
		int[] pitch = toArray(result[0]);
		int[] candidateArray = toArray(candidate);

		int strike = 0;
		int ball = 0;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(pitch[i] == candidateArray[j]) {
					if(i == j) {
						strike++;
					}else {
						ball++;
					}
				}
			}
		}

		if(strike == result[1] && ball == result[2]) {
			return true;
		}
		return false;
	}
	public int countStrike(int candidate, int result) {
		int strike = 0;
		if(candidate % 10 == result % 10) {
			strike++;
		}
		candidate /= 10;
		result /= 10;
		if(candidate % 10 == result % 10) {
			strike++;
		}
		candidate /= 10;
		result /= 10;
		if(candidate % 10 == result % 10) {
			strike++;
		}
		return strike;
	}
	public int countBall(int candidate, int result) {
		int ball = 0;
		while(result > 0) {
			int number = result % 10;
			if(doesInclude(candidate, number)) {
				ball++;
			}
			result /= 10;
		}
		return ball;
	}
	public boolean doesInclude(int candidate, int number) {
		while(candidate > 0) {
			if(candidate % 10 == number) {
				return true;
			}
			candidate /= 10;
		}
		return false;
	}
}
