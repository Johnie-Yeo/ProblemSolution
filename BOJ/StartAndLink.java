package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class StartAndLink {
	static int N;
	static int [][]S;
	static int MIN = 999999999;
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		S = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				S[i][j] = kb.nextInt();
		kb.close();
		
		ArrayList<Integer> TeamA = new ArrayList<>();
		ArrayList<Integer> TeamB = new ArrayList<>();
		TeamA.add(0);
		dfs(TeamA, TeamB, 1);
		System.out.println(MIN);
	}
	private static void dfs(ArrayList<Integer> teamA, ArrayList<Integer> teamB, int idx) {
		if(idx >= N) {
			if(teamA.size() != teamB.size())
				return;
			int teamAStats = getStats(teamA);
			int teamBStats = getStats(teamB);
			int diff = Math.abs(teamAStats - teamBStats);
			if(MIN > diff)
				MIN = diff;
		}
		else if(teamA.size() == N/2) {
			for(int i = idx; i < N; i++)
				teamB.add(i);
			dfs(teamA, teamB, N);
			int i = N/2 -1;
			while(i >= 0 && teamB.get(i) >= idx) {
				teamB.remove(i);
				i--;
			}
		}
		else {
			teamA.add(idx);
			dfs(teamA, teamB, idx+1);
			teamA.remove(teamA.size()-1);
			teamB.add(idx);
			dfs(teamA, teamB, idx+1);
			teamB.remove(teamB.size()-1);
		}
	}
	private static int getStats(ArrayList<Integer> team) {
		int idx = 0;
		int start; // ���� ù��° ����
		int result = 0;
		int i;
		while(idx < team.size()-1) {
			start = team.get(idx);
			for(i = idx + 1; i < team.size(); i++) {
				int tmp = team.get(i);
				result += S[start][tmp];
				result += S[tmp][start];
			}
			idx++; // �״��� ������ ��
		}
		return result;
	}
}
