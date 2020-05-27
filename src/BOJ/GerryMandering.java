package BOJ;

import Test.*;

import java.util.*;

public class GerryMandering {
	public static void main(String[] args) {
//		new Main().solve();
		new GerryMandering().test();
	}

	private void test() {
		Test test = new Test();

		int N;
		int[] population;
		int[][] adjacent;
		String input;
		int result, expect;

		N = 6;
		input = "5 2 3 4 1 2";
		population = InputParser.parseStringToIntArray(input);
		input = "2 2 4\n" +
				"4 1 3 6 5\n" +
				"2 4 2\n" +
				"2 1 3\n" +
				"1 2\n" +
				"1 2";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = 1;
		test.test(result, expect).printResult();

		N = 6;
		input = "1 1 1 1 1 1";
		population = InputParser.parseStringToIntArray(input);
		input = "2 2 4\n" +
				"4 1 3 6 5\n" +
				"2 4 2\n" +
				"2 1 3\n" +
				"1 2\n" +
				"1 2";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = 0;
		test.test(result, expect).printResult();

		N = 6;
		input = "10 20 10 20 30 40";
		population = InputParser.parseStringToIntArray(input);
		input = "0\n" +
				"0\n" +
				"0\n" +
				"0\n" +
				"0\n" +
				"0";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = -1;
		test.test(result, expect).printResult();

		N = 6;
		input = "2 3 4 5 6 7";
		population = InputParser.parseStringToIntArray(input);
		input = "2 2 3\n" +
				"2 1 3\n" +
				"2 1 2\n" +
				"2 5 6\n" +
				"2 4 6\n" +
				"2 4 5";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = 9;
		test.test(result, expect).printResult();

		N = 8;
		input = "17 42 46 81 71 8 37 12";
		population = InputParser.parseStringToIntArray(input);
		input = "4 2 4 5 7\n" +
				"5 1 3 4 5 8\n" +
				"2 2 4\n" +
				"5 1 2 3 7 8\n" +
				"5 1 2 6 7 8\n" +
				"2 5 8\n" +
				"4 1 4 5 8\n" +
				"5 2 4 5 6 7";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = 2;
		test.test(result, expect).printResult();

		N = 10;
		input = "1 2 3 4 5 6 7 8 9 10";
		population = InputParser.parseStringToIntArray(input);
		input = "2 10 2\n" +
				"2 1 3\n" +
				"2 2 4\n" +
				"2 3 5\n" +
				"2 4 6\n" +
				"2 5 7\n" +
				"2 6 8\n" +
				"2 7 9\n" +
				"2 8 10\n" +
				"2 9 1";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = 1;
		test.test(result, expect).printResult();

		N = 3;
		input = "1 2 3";
		population = InputParser.parseStringToIntArray(input);
		input = "0\n" +
				"0\n" +
				"0";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = -1;
		test.test(result, expect).printResult();

		N = 6;
		input = "2 2 2 2 2 2";
		population = InputParser.parseStringToIntArray(input);
		input = "1 3\n" +
				"1 4\n" +
				"1 1\n" +
				"1 2\n" +
				"1 6\n" +
				"1 5";
		adjacent = InputParser.parseStringTo2DIntArray(input);
		result = getMinDiffOfPopulation(N, population, adjacent);
		expect = -1;
		test.test(result, expect).printResult();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[] population = new int[N];
		for(int i = 0; i < N; i++){
			population[i] = kb.nextInt();
		}
		kb.nextLine();

		int[][] adjacentInfo = new int[N][];
		for(int i = 0; i < N; i++){
			String input = kb.nextLine();
			adjacentInfo[i] = Arrays.stream(input.split(" "))
									.mapToInt(e -> Integer.parseInt(e))
									.toArray();
		}
		kb.close();
		int result = getMinDiffOfPopulation(N, population, adjacentInfo);
		System.out.println(result);
	}

	private class City{
		HashSet<Integer> area1, area2;
		public City(HashSet<Integer> area1, HashSet<Integer> area2){
			this.area1 = new HashSet<>(area1);
			this.area2 = new HashSet<>(area2);
		}
	}
	private int getMinDiffOfPopulation(int n, int[] population, int[][] adjacentInfo) {
		ArrayList<City> dividedAreaList = getDividedArea(n, adjacentInfo);
		if(dividedAreaList.size() == 0){
			return -1;
		}
		int min = Integer.MAX_VALUE;
		for(City city : dividedAreaList){
			int diff = getDiff(city, population);
			min = Math.min(diff, min);
		}
		return min;
	}

	private int getDiff(City city, int[] population) {
		int area1 = getNumberOfPeople(city.area1, population);
		int area2 = getNumberOfPeople(city.area2, population);
		return Math.abs(area1 - area2);
	}

	private int getNumberOfPeople(HashSet<Integer> area, int[] population) {
		int sum = 0;
		for(int people : area){
			sum += population[people];
		}
		return sum;
	}

	private ArrayList<City> getDividedArea(int n, int[][] adjacentInfo) {
		HashSet<Integer> area1 = new HashSet<>();
		HashSet<Integer> area2 = new HashSet<>();
		area1.add(0);
		ArrayList<HashSet<Integer>> adjacent = getAdjacent(n, adjacentInfo);
		int index = 1;
		ArrayList<City> result = dfs(index, n, area1, area2, adjacent);
		return result;
	}

	private ArrayList<City> dfs(int index, int n, HashSet<Integer> area1, HashSet<Integer> area2, ArrayList<HashSet<Integer>> adjacent) {
		ArrayList<City> result = new ArrayList<>();
		if(index >= n){
			if(isConnected(area1, adjacent) && isConnected(area2, adjacent)){
				City comb = new City(area1, area2);
				result.add(comb);
			}
			return result;
		}
		area1.add(index);
		ArrayList<City> tmp = dfs(index+1, n, area1, area2, adjacent);
		result.addAll(tmp);
		area1.remove(index);

		area2.add(index);
		tmp = dfs(index+1, n, area1, area2, adjacent);
		result.addAll(tmp);
		area2.remove(index);

		return result;
	}

	private boolean isConnected(HashSet<Integer> area, ArrayList<HashSet<Integer>> adjacent){
		if(area.isEmpty()){
			return false;
		}
		HashSet<Integer> clone = new HashSet<>(area);
		int start = clone.iterator().next();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);

		while(!queue.isEmpty()){
			int cur = queue.poll();
			clone.remove(cur);

			HashSet<Integer> next = adjacent.get(cur);
			for(int elem : next){
				if(clone.contains(elem)){
					queue.add(elem);
				}
			}
		}

		return clone.isEmpty();
	}

	private boolean isConnected(int target, HashSet<Integer> area, ArrayList<HashSet<Integer>> adjacent) {
		if(area.size() == 1){
			return true;
		}
		for(int node : area){
			if(node == target){
				continue;
			}
			HashSet<Integer> cur = adjacent.get(node);
			if(cur.contains(target)){
				return true;
			}
		}
		return false;
	}

	private ArrayList<HashSet<Integer>> getAdjacent(int n, int[][] adjacentInfo) {
		ArrayList<HashSet<Integer>> list = new ArrayList<>();
		for(int[] adjacent : adjacentInfo){
			HashSet<Integer> set = new HashSet<>();
			int size = adjacent[0];
			for(int i = 0; i < size; i++){
				int next = adjacent[i+1] - 1;
				set.add(next);
			}
			list.add(set);
		}
		return list;
	}
}
