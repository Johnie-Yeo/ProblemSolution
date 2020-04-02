package Programmers;
import java.util.Scanner;

public class Budgets {
	public static void main(String[] args) {
		Budgets app = new Budgets();
		app.solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int[] budgets = new int[n];
		for(int i = 0; i < n; i++)
			budgets[i] = kb.nextInt();
		int M = kb.nextInt();
		kb.close();
		int re = solution(budgets,M);
		System.out.println(re);
	}
	public int solution(int[] budgets, int M) {
        int limit = 0;
        long sum = 0;
        int max = 0;
        for(int b : budgets) {
        	sum+=b;
        	if(b > max)
        		max = b;
        }
        if(sum <= M)
        	return max;
        limit = binarySearch(budgets,M,0,max);
        
        return limit;
    }
	private int binarySearch(int[] budgets, int m, int start, int end) {
		while(end - start > 1) {
			int val = (start + end) / 2;
			long sum = 0;
			for(int b : budgets) {
				if(b > val)
					b = val;
	        	sum+=b;
	        }
			if(sum < m)
				start = val;
			else if(sum > m)
				end = val;
			else
				return val;
		}
		if(start > end)
			return end;
		return start;
	}
}
