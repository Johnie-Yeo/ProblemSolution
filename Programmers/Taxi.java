package Programmers;

import java.util.Scanner;

public class Taxi {
	
	public static void main(String[] args) {
		Taxi app = new Taxi();
		app.solve();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int []s = new int[n];
		for(int i = 0; i < n; i++)
			s[i] = kb.nextInt();
		kb.close();
		
		System.out.println(solution(s));
	}

	private int solution(int[] s) {
		int result = 0;
		int []count  = new int[4];
		for(int i = 0; i < s.length; i++) {
			if(s[i] == 4) {
				result++;
			}
			else {
				count[s[i]]++;
			}
		}
		result += count[3];
		result += (count[2]/2);
		result += (count[2]%2);
		if(count[3] < count[1]) {
			result += (count[1]/4);
			if(count[2] % 2 == 1) {
				if(count[1] % 4 <= 2);
				else
					result++;
			}
			else {
				if(count[1]%4 != 0)
					result++;
			}
		}
		return result;
	}
}
