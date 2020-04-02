package BOJ;

import java.util.Scanner;

public class Baseball {
	public static void main(String[] args) {
		new Baseball().solve();
	}
	public void solve() {
//		�߱��� 9������ �̷���� �� ���� ���ݰ� ���� ������ �ϴ� �����̴�.
		Scanner kb = new Scanner(System.in);
		
		//ù° �ٿ� �̴� �� N(2 �� N �� 50)�� �־�����. 
		int N = kb.nextInt();
		
//		��° �ٺ��� N���� �ٿ��� �� ������ �� �̴׿��� ��� ����� 1�� �̴׺��� N�� �̴ױ��� ������� �־�����. 
//		�̴׿��� ��� ����� 9���� ������ �������� ���еǾ��� �ִ�. �� ����� �ǹ��ϴ� ������ ������ ����.
		int[][] player = new int[N][9];
//		��Ÿ: 1 Ÿ�ڿ� ��� ���ڰ� �� �羿 �����Ѵ�.
//		2��Ÿ: 2 Ÿ�ڿ� ��� ���ڰ� �� �羿 �����Ѵ�.
//		3��Ÿ: 3 Ÿ�ڿ� ��� ���ڰ� �� �羿 �����Ѵ�.
//		Ȩ��: 4 Ÿ�ڿ� ��� ���ڰ� Ȩ���� �����Ѵ�.
//		�ƿ�: 0 ��� ���ڴ� �������� ���ϰ�, ���� ���� �ƿ��� �ϳ� �����Ѵ�.
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 9; j++) {
				player[i][j] = kb.nextInt();
			}
		}
		kb.close();
		
		int result = getMaxScore(player, N);
//		����Ÿ���� ���� �� �ִ� �ִ� ������ ����Ѵ�.
		System.out.println(result);
	}
	public int getMaxScore(int[][] player, int N) {
//		�� �߱����� ���� ����Ÿ�� Ÿ���� ���Ϸ��� �Ѵ�.
//		����Ÿ ���� ������ �� 9���� �ְ�, 1������ 9������ ��ȣ�� �Ű��� �ִ�. ����Ÿ�� �ڽ��� ���� �����ϴ� ������ 1�� ������ 4�� Ÿ�ڷ� �̸� �����ߴ�.
		int[] turn = new int[9];
		boolean[] check = new boolean[9];
		turn[3] = 0;
		check[0] = true;
		return setTurnAndgetMaxScore(player, N, turn, check, 0);
		
//		���� �ٸ� ������ Ÿ���� ��� �����ؾ� �Ѵ�. ����Ÿ�� �� ������ �� �̴׿��� � ����� ����� �̸� �˰� �ִ�. ���� ���� ������ �ϴ� Ÿ���� ã��, �� ���� ������ ���غ���.
//		�� ���� ��Ⱑ �����ϱ� ������ Ÿ��(Ÿ�ڰ� Ÿ���� ���� ����)�� ���ؾ� �ϰ�, ��� �߿��� Ÿ���� ������ �� ����.
	}
	public int setTurnAndgetMaxScore(int[][] player, int n, int[] turn, boolean[] check, int curTurn) {
		if(curTurn >= 9) {
			int score = getScore(turn, player, 0, n, 0, 0);
			return score;
		}
		
		int max = 0;
		if(curTurn == 3) {
			int result = setTurnAndgetMaxScore(player, n, turn, check, curTurn+1);
			max = Math.max(result, max);
		}else {			
			for(int i = 0; i < 9; i++) {
				if(!check[i]) {
					check[i] = true;
					turn[curTurn] = i;
					int result = setTurnAndgetMaxScore(player, n, turn, check, curTurn+1);
					max = Math.max(result, max);
					check[i] = false;
				}
			}
		}
		return max;
	}
	public int getScore(int[] turn, int[][] player, int curPlayerTurnIndex, int N, int inning, int score) {
//		�ϳ��� �̴��� ���ݰ� ����� �̷���� �ְ�, �� N�̴׵��� ������ �����ؾ� �Ѵ�.
		if(inning >= N) {
			return score;
		}
		int out = 0;
		int[] field = new int[4];
//		 �� �̴׿� 3�ƿ��� �߻��ϸ� �̴��� ����ǰ�, �� ���� ���ݰ� ���� ���� �ٲ۴�.
		while(out < 3) {
			int curPlayer = turn[curPlayerTurnIndex];
			int curPoint = player[inning][curPlayer];
			
			if(curPoint == 0) {
				out++;
			}else if(curPoint == 1 || curPoint == 2 || curPoint == 3 || curPoint == 4) {
				move(curPoint, field);
				field[curPoint%4] += 1; 
			}
			
			score += field[0];
			field[0] = 0;
//			9�� Ÿ�ڱ��� ���� �ƴµ� 3�ƿ��� �߻����� ���� ���¸� �̴��� ������ �ʰ�, 1�� Ÿ�ڰ� �ٽ� Ÿ���� ����. 
			curPlayerTurnIndex++;
			curPlayerTurnIndex %= 9;
		}
//		Ÿ���� �̴��� ����Ǿ ������ �����ؾ� �Ѵ�. ���� ���, 2�̴׿� 6�� Ÿ�ڰ� ������ Ÿ�ڿ��ٸ�, 3�̴��� 7�� Ÿ�ں��� Ÿ���� ����.
		return getScore(turn, player, curPlayerTurnIndex, N, inning+1, score);
	}
	public void move(int curPoint, int[] field) {
		for(int i = 3; i > 0; i--) {
			int movePosition = i+curPoint;
			if(movePosition > 3) {
				movePosition = 0;
			}
			field[movePosition] += field[i];
			field[i] = 0;
		}
	}
}
