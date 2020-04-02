package BOJ;

import java.util.Scanner;

public class InputOperator {
	static int N;
	static int []data;
	static int []operator;
	static int MAX = -1000000000, MIN = 1000000000;
	static final int plus = 0, minus = 1, multiply = 2, divide = 3;
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		data = new int[N+1];
		operator = new int[4];
		for(int i = 0; i < N; i++)
			data[i] = kb.nextInt();
		for(int i = 0; i < 4; i++)
			operator[i] = kb.nextInt();
		kb.close();
		
		data[N] = data[0];
		dfs(1);
		System.out.println(MAX + "\n" + MIN);
	}
	private static void dfs(int idx) {
		if(idx >= N) {
			if(data[N] > MAX)
				MAX = data[N];
			if(data[N] < MIN)
				MIN = data[N];
		}
		else {
			for(int i = 0; i < 4; i++) {
				if(operator[i] > 0) {
					int tmp = data[N];
					calculate(idx, i);
					operator[i]--;
					dfs(idx+1);
					data[N] = tmp;
					operator[i]++;
				}
			}
		}
		
	}
	private static void calculate(int dataIdx, int operIdx) {
		if(operIdx == plus) {
			data[N] += data[dataIdx];
		}
		else if(operIdx == minus) {
			data[N] -= data[dataIdx];
		}
		else if(operIdx == multiply) {
			data[N] *= data[dataIdx];
		}
		else if(operIdx == divide) {
			data[N] /= data[dataIdx];
		}
	}
}
