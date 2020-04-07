package Programmers;

import Test.Test;

public class CollatzGuess {
	public static void main(String[] args) {
		new CollatzGuess().solve();
	}
	public void solve() {
		Test test = new Test();
		int input = 6;
		int result = 8;
		
		test.test(solution(input), result);
		
		input = 16;
		result = 4;
		test.test(solution(input), result);
		
		input = 626331;
		result = -1;
		test.test(solution(input), result);
	}
	public int solution(int num) {
		int count = 0;
		long number = num;

		while(count < 500) {
			if(number == 1) {
				return count;
			}
			
			count++;
			if(number % 2 == 0) {
				number /= 2;
			}else {
				number *= 3;
				number++;
			}
		}
		return -1;
	}
}
