package BOJ;

import Test.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Baseball {
	public static void main(String[] args) {
//		new Main().solve();
		new Baseball().test();
	}

	private void test() {
		Test test = new Test();

		int N;
		int[][] players;
		String input;
		int result, expect;

		N = 2;
		input = "4 0 0 0 0 0 0 0 0\n" +
				"4 0 0 0 0 0 0 0 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 1;
		test.test(result, expect).printResult();

		N = 2;
		input = "4 0 0 0 1 1 1 0 0\n" +
				"0 0 0 0 0 0 0 0 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 4;
		test.test(result, expect).printResult();

		N = 2;
		input = "0 4 4 4 4 4 4 4 4\n" +
				"0 4 4 4 4 4 4 4 4";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 43;
		test.test(result, expect).printResult();

		N = 2;
		input = "4 3 2 1 0 4 3 2 1\n" +
				"1 2 3 4 1 2 3 4 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 46;
		test.test(result, expect).printResult();

		N = 9;
		input = "4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0\n" +
				"4 4 4 4 4 4 4 4 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 216;
		test.test(result, expect).printResult();

		N = 9;
		input = "1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"0 2 0 3 0 1 0 2 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 89;
		test.test(result, expect).printResult();

		N = 41;
		input = "1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"1 2 4 3 0 2 1 0 3\n" +
				"1 2 1 2 0 0 0 0 1\n" +
				"3 4 2 3 1 2 3 4 0\n" +
				"0 1 2 3 4 2 1 0 0\n" +
				"0 0 0 0 0 0 1 4 4\n" +
				"0 4 0 4 0 4 0 4 0\n" +
				"0 4 2 2 2 2 2 2 2\n" +
				"1 1 1 1 1 1 1 1 0\n" +
				"0 2 0 3 0 1 0 2 0";
		players = InputParser.parseStringTo2DIntArray(input);
		result = getMaxScore(players, N);
		expect = 433;
		test.test(result, expect).printResult();
	}

	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int[][] player = new int[N][9];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 9; j++) {
				player[i][j] = kb.nextInt();
			}
		}
		kb.close();
		
		int result = getMaxScore(player, N);
		System.out.println(result);
	}

	private final int SINGLE = 1;
	private final int DOUBLE = 2;
	private final int TRIPLE = 3;
	private final int HOME_RUN = 4;
	private final String SCORE = "score";
	private final String INDEX = "index";
	private final int OUT = 0;
	public int getMaxScore(int[][] player, int N) {
		ArrayList<int[]> ordersList = getOrders();
		int max = 0;
		for(int[] order : ordersList){
			int score = getScore(order, player, N);
			max = Math.max(score, max);
		}
		return max;
	}

	private ArrayList<int[]> getOrders() {
		int index = 0;
		int[] order = new int[9];
		boolean[] check = new boolean[9];
		check[0] = true;
		return dfs(index, order, check);
	}

	private ArrayList<int[]> dfs(int index, int[] order, boolean[] check){
		ArrayList<int[]> result = new ArrayList<>();

		if(index == 3){
			order[index] = 0;
			return dfs(index+1, order, check);
		}else if(index >= 9){
			result.add(order.clone());
			return result;
		}

		for(int i = 1; i < 9; i++){
			if(check[i]){
				continue;
			}
			check[i] = true;
			order[index] = i;
			ArrayList<int[]> cur = dfs(index+1, order, check);
			result.addAll(cur);
			check[i] = false;
		}

		return result;
	}

	private int getScore(int[] order, int[][] player, int n) {
		int result = 0;
		int start = 0;
		for(int inning = 0; inning < n; inning++){
			HashMap<String, Integer> map = getInningScore(start, order, player[inning]);
			start = map.get(INDEX);
			result += map.get(SCORE);
		}
		return result;
	}

	private HashMap<String, Integer> getInningScore(int index, int[] order, int[] players) {
		int score = 0;
		int []field = new int[3];
		int out = 0;
		while(out < 3){
			int hit = players[order[index]];
			index = (index+1)%9;
			switch (hit){
				case SINGLE:
					score += hitSingle(field);
					break;
				case DOUBLE:
					score += hitDouble(field);
					break;
				case TRIPLE:
					score += hitTriple(field);
					break;
				case HOME_RUN:
					score += hitHomeRun(field);
					break;
				case OUT:
					out++;
					break;
			}
		}
		HashMap<String, Integer> map = new HashMap<>();
		map.put(SCORE, score);
		map.put(INDEX, index);
		return map;
	}

	private int hitHomeRun(int[] field) {
		int score = field[2] + field[1] + field[0] + 1;
		field[2] = 0;
		field[1] = 0;
		field[0] = 0;
		return score;
	}

	private int hitTriple(int[] field) {
		int score = field[2] + field[1] + field[0];
		field[2] = 1;
		field[1] = 0;
		field[0] = 0;
		return score;
	}

	private int hitDouble(int[] field) {
		int score = field[2] + field[1];
		field[2] = field[0];
		field[1] = 1;
		field[0] = 0;
		return score;
 	}

	private int hitSingle(int[] field) {
		int score = field[2];
		field[2] = field[1];
		field[1] = field[0];
		field[0] = 1;
		return score;
	}

}
