package Programmers;

public class FindNumberOfPrime {
	public static void main(String[] args) {
		new FindNumberOfPrime().solve();
	}
	public void solve() {
		int n = 10, result = 4;
		test(n, result);
		n = 5;result = 3;
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
		int count = 0;
		
		for(int i = 2; i <= n; i++) {
			if(isPrime(i)) {
				count++;
			}
		}
		
		return count;
	}
	public boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
