package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartAndLink {
	public static void main(String[] args) {
//		new Main().solve();
		new StartAndLink().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "4\n" +
				"0 1 2 3\n" +
				"4 0 5 6\n" +
				"7 1 0 2\n" +
				"3 4 5 0";
		expect = 0;
		testCase(test, input, expect);

		input = "6\n" +
				"0 1 2 3 4 5\n" +
				"1 0 2 3 4 5\n" +
				"1 2 0 3 4 5\n" +
				"1 2 3 0 4 5\n" +
				"1 2 3 4 0 5\n" +
				"1 2 3 4 5 0";
		expect = 2;
		testCase(test, input, expect);

		input = "8\n" +
				"0 5 4 5 4 5 4 5\n" +
				"4 0 5 1 2 3 4 5\n" +
				"9 8 0 1 2 3 1 2\n" +
				"9 9 9 0 9 9 9 9\n" +
				"1 1 1 1 0 1 1 1\n" +
				"8 7 6 5 4 0 3 2\n" +
				"9 1 9 1 9 1 0 9\n" +
				"6 5 4 3 2 1 9 0";
		expect = 1;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect) {
		String[] parsed = input.split("\n", 2);
		int N = Integer.parseInt(parsed[0]);
		int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getMinDiffBetweenTeams(N, map);
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int result = getMinDiffBetweenTeams(N, map);
		System.out.println(result);
	}

	private class Teams{
		ArrayList<Integer> start, link;
		public Teams(){
			this.start = new ArrayList<>();
			this.link  = new ArrayList<>();
		}

		public Teams(ArrayList<Integer> start, ArrayList<Integer> link) {
			this.start = new ArrayList<>(start);
			this.link  = new ArrayList<>(link);
		}

		public void addStartTeam(int index){
			this.start.add(index);
		}
		public void removeStartTeam(){
			int size = this.start.size();
			this.start.remove(size-1);
		}

		public void addLinkTeam(int index){
			this.link.add(index);
		}
		public void removeLinkTeam(){
			int size = this.link.size();
			this.link.remove(size-1);
		}
		public Teams clone(){
			Teams clone = new Teams(this.start, this.link);
			return clone;
		}
	}
	private int getMinDiffBetweenTeams(int n, int[][] map) {
		ArrayList<Teams> teams = getStartAndLinkTeams(n);
		int min = Integer.MAX_VALUE;
		for(Teams team : teams){
			int diff = getAbilityDiff(team, map);
			min = Math.min(diff, min);
		}
		return min;
	}

	private ArrayList<Teams> getStartAndLinkTeams(int n) {
		Teams teams = new Teams();
		teams.addStartTeam(0);
		int index = 1;
		ArrayList<Teams> result = dfs(teams, index, n);
		return result;
	}

	private ArrayList<Teams> dfs(Teams teams, int index, int n) {
		ArrayList<Teams> result = new ArrayList<>();

		if(index >= n){
			if(teams.start.size() == n/2){
				Teams cur = teams.clone();
				result.add(cur);
			}
			return result;
		}

		teams.addStartTeam(index);
		ArrayList<Teams> start = dfs(teams, index+1, n);
		teams.removeStartTeam();
		result.addAll(start);

		teams.addLinkTeam(index);
		ArrayList<Teams> link = dfs(teams, index+1, n);
		teams.removeLinkTeam();
		result.addAll(link);

		return result;
	}

	private int getAbilityDiff(Teams team, int[][] map) {
		int startTeamAbility = getAbility(team.start, map);
		int linkTeamAbility = getAbility(team.link, map);
		return Math.abs(startTeamAbility - linkTeamAbility);
	}

	private int getAbility(ArrayList<Integer> team, int[][] map) {
		int sum = 0;
		for(int i : team){
			for(int j : team){
				sum += map[i][j];
			}
		}
		return sum;
	}
}
