package BOJ;


import Test.*;

import java.util.*;

public class CastleDefence {
	public static void main(String[] args) {
//		new Main().solve();
		new CastleDefence().test();
	}

	private void test() {
		Test test = new Test();

		int N, M, D;
		int[][] map;
		int result, expect;
		String input;

		N = 5; M = 5; D = 1;
		input = "0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"1 1 1 1 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 3;
		test.test(result, expect).printResult();

		N = 5; M = 5; D = 1;
		input = "0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"1 1 1 1 1\n" +
				"0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 3;
		test.test(result, expect).printResult();

		N = 5; M = 5; D = 2;
		input = "0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"0 0 0 0 0\n" +
				"1 1 1 1 1\n" +
				"0 0 0 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 5;
		test.test(result, expect).printResult();

		N = 5; M = 5; D = 5;
		input = "1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1\n" +
				"1 1 1 1 1";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 15;
		test.test(result, expect).printResult();

		N = 6; M = 5; D = 1;
		input = "1 0 1 0 1\n" +
				"0 1 0 1 0\n" +
				"1 1 0 0 0\n" +
				"0 0 0 1 1\n" +
				"1 1 0 1 1\n" +
				"0 0 1 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 9;
		test.test(result, expect).printResult();

		N = 6; M = 5; D = 2;
		input = "1 0 1 0 1\n" +
				"0 1 0 1 0\n" +
				"1 1 0 0 0\n" +
				"0 0 0 1 1\n" +
				"1 1 0 1 1\n" +
				"0 0 1 0 0";
		map = InputParser.parseStringTo2DIntArray(input);
		result = getMaxKill(N, M, D, map);
		expect = 14;
		test.test(result, expect).printResult();
	}
	private void solve(){
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		int D = kb.nextInt();
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				map[i][j] = kb.nextInt();
			}
		}
		int result = getMaxKill(N, M, D, map);
		System.out.println(result);
		kb.close();
	}

	private final int ENEMY = 1;
	private final int[] dirX = {0, -1, 0};
	private final int[] dirY = {-1, 0, 1};
	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Point clone(){
			return new Point(this.x, this.y);
		}

		@Override
		public boolean equals(Object obj) {
			Point o = (Point) obj;
			return (this.x == o.x && this.y == o.y);
		}

		@Override
		public int hashCode() {
			String toString = this.x + ","+this.y;
			return toString.hashCode();
		}
	}
	private int getMaxKill(int n, int m, int d, int[][] map) {
		ArrayList<Point[]> archerComb = getArcherCombinationList(n, m);
		int max = 0;
		for(Point[] archerList : archerComb){
			int kill = getKill(archerList, n, m, d, map);
			max = Math.max(kill, max);
		}
		return max;
	}

	private int getKill(Point[] archerList, int n, int m, int d, int[][] map) {
		int kill = 0;
		int[][] clone = clone(map, n, m);
		for(int i = 0; i < n; i++){
			int cur = killEnemy(archerList, n, m, d, clone);
			kill += cur;
			clone = moveDown(clone, n, m);
		}
		return kill;
	}

	private int[][] clone(int[][] map, int n, int m) {
		int[][] clone = new int[n][m];

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				clone[i][j] = map[i][j];
			}
		}

		return clone;
	}

	private int killEnemy(Point[] archerList, int n, int m, int d, int[][] map) {
		HashSet<Point> set = new HashSet<>();
		for(Point archer : archerList){
			Point target = getTarget(archer, n, m, d, map);
			if(target != null){
				set.add(target);
			}
		}
		Iterator<Point> iter = set.iterator();
		while(iter.hasNext()){
			Point killed = iter.next();
			map[killed.x][killed.y] = 0;
		}
		return set.size();
	}

	private int[][] moveDown(int[][] map, int n, int m) {
		for(int i = n-1; i > 0; i--){
			map[i] = map[i-1].clone();
		}
		map[0] = new int[m];
		return map;
	}

	private Point getTarget(Point archer, int n, int m, int dist, int[][] map) {
		Queue<Point> queue = new LinkedList<>();
		HashMap<Point, Integer> hashMap = new HashMap<>();
		enqueue(queue, hashMap, archer, 0);

		while(!queue.isEmpty()){
			Point cur = queue.poll();

			for(int dir = 0; dir < 3; dir++){
				int x = cur.x + dirX[dir];
				int y = cur.y + dirY[dir];
				if(isOutOfRange(x, y, n, m)){
					continue;
				}
				Point next = new Point(x, y);
				int nextDist = hashMap.get(cur) + 1;
				if(map[x][y] == ENEMY){
					if(!hashMap.containsKey(next)){
						if(nextDist <= dist){
							return next;
						}
					}
				}else{
					if(nextDist <= dist) {
						enqueue(queue, hashMap, next, nextDist);
					}
				}
			}
		}
		return null;
	}

	private boolean isOutOfRange(int x, int y, int n, int m) {
		return (x < 0 || y < 0 || x >= n || y >= m);
	}

	private void enqueue(Queue<Point> queue, HashMap<Point, Integer> hashMap, Point archer, int dist) {
		queue.add(archer);
		hashMap.put(archer, dist);
	}


	private ArrayList<Point[]> getArcherCombinationList(int n, int m) {
		int i = n;
		int j = 0;
		Point[] comb = new Point[3];
		int index = 0;
		ArrayList<Point[]> list = getCombination(i, j, comb, index, m);

		return list;
	}

	private ArrayList<Point[]> getCombination(int i, int j, Point[] comb, int index, int limit) {
		ArrayList<Point[]> result = new ArrayList<>();

		if(index >= 3){
			result.add(comb.clone());
			return result;
		}else if(j >= limit){
			return result;
		}

		comb[index] = new Point(i, j);
		ArrayList<Point[]> tmp1 = getCombination(i, j+1, comb, index+1, limit);
		ArrayList<Point[]> tmp2 = getCombination(i, j+1, comb, index, limit);
		result.addAll(tmp1);
		result.addAll(tmp2);

		return result;
	}

}
