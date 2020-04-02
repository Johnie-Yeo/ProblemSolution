package Programmers;

public class GCD_LCM {
	public static void main(String[] args) {
		new GCD_LCM().solve();
	}
	public void solve() {
		int a = 3;
		int b = 12;
		int[] result = {3, 12};

		test(a, b, result);

		a = 2;
		b = 5;
		result[0] = 1;
		result[1] = 10;
		test(a, b, result);
	}
	public void test(int a, int b, int[] result) {
		if(equals(solution(a, b), result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public boolean equals(int[] a, int[] b) {
		if(a.length != b.length) {
			return false;
		}
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	public int[] solution(int a, int b) {
		int gcd = GCD(a, b);
		int lcm = LCM(a, b, gcd);
		int[] result = {gcd, lcm};
		return result;
	}
	public int LCM(int a, int b, int gcd) {
		return a*b/gcd;
	}
	public int GCD(int a, int b){
		while(b != 0){
			int r = a%b;
			a= b;
			b= r;
		}
		return a;
	}
}
