package BOJ;

import java.util.Scanner;

public class Leave {
	//���� 14501
	//�� ��¥�� ���ؼ� ���� ���� ���ϰ�
	//�ִ� ã��
	//���������� ���� ���ϴ�
	int MAX = 0;
	int N;
	int []T;
	int []P;
	
	public static void main(String[] args) {
		Leave app = new Leave();
		app.solve();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		T = new int[N];
		P = new int[N];
		for(int i = 0; i < N; i++) {
			T[i] = kb.nextInt();
			P[i] = kb.nextInt();
		}
		kb.close();
		if(T[0] <= N)
			getMaxWage(0, true, P[0], T[0]);
		getMaxWage(0, false, 0, 0);
		System.out.println(MAX);
	}

	private void getMaxWage(int idx, boolean work, int wage, int amount) {
		if(idx == N) {
			if(MAX < wage)
				MAX = wage;
			return;
		}
		
		if(amount <= 0 && T[idx] <= N-idx)
			getMaxWage(idx+1, true, wage+P[idx], T[idx]-1);
		getMaxWage(idx+1, false, wage, amount-1);
	}
}
