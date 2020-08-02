package BOJ.Samsung.TypeA;


import Test.*;

import java.util.*;

public class BuildBridge2 {
	public static void main(String[] args){
//		new Main().solve();
		new BuildBridge2().test();
	}

	private void test() {
		Test test = new Test();

		String input;
		int expect;

		input = "7 8\n" +
				"0 0 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 0 0\n" +
				"1 1 0 0 0 1 1 0\n" +
				"0 0 0 0 0 1 1 0\n" +
				"0 0 0 0 0 0 0 0\n" +
				"1 1 1 1 1 1 1 1";
		expect = 9;
		testCase(test, input, expect);

		input = "7 8\n" +
				"0 0 0 1 1 0 0 0\n" +
				"0 0 0 1 1 0 0 0\n" +
				"1 1 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0\n" +
				"1 1 1 1 1 1 1 1";
		expect = 10;
		testCase(test, input, expect);

		input = "7 8\n" +
				"1 0 0 1 1 1 0 0\n" +
				"0 0 1 0 0 0 1 1\n" +
				"0 0 1 0 0 0 1 1\n" +
				"0 0 1 1 1 0 0 0\n" +
				"0 0 0 0 0 0 0 0\n" +
				"0 1 1 1 0 0 0 0\n" +
				"1 1 1 1 1 1 0 0";
		expect = 9;
		testCase(test, input, expect);

		input = "7 7\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1\n" +
				"0 0 0 0 0 0 0\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1";
		expect = -1;
		testCase(test, input, expect);

		input = "7 7\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1\n" +
				"1 1 1 0 1 1 1\n" +
				"0 0 0 0 0 0 0\n" +
				"1 0 0 0 0 1 1\n" +
				"1 0 0 0 1 1 1\n" +
				"1 1 1 0 1 1 1";
		expect = 8;
		testCase(test, input, expect);

		input = "4 4\n" +
				"0 1 1 0\n" +
				"1 0 0 1\n" +
				"1 0 0 0\n" +
				"0 1 1 1";
		expect = -1;
		testCase(test, input, expect);

		input = "5 5\n" +
				"0 0 0 1 0\n" +
				"1 0 0 0 1\n" +
				"1 1 0 0 1\n" +
				"1 0 1 1 0\n" +
				"0 1 1 1 1";
		expect = -1;
		testCase(test, input, expect);

		input = "10 6\n" +
				"0 0 0 1 0 0\n" +
				"0 0 0 1 0 0\n" +
				"0 1 0 0 0 1\n" +
				"0 0 0 0 0 0\n" +
				"1 1 0 1 1 0\n" +
				"1 0 0 0 1 0\n" +
				"1 1 0 0 1 0\n" +
				"0 0 0 0 1 1\n" +
				"0 0 0 0 0 0\n" +
				"0 1 0 0 0 0";
		expect = 13;
		testCase(test, input, expect);

		input = "9 5\n" +
				"0 1 1 1 1\n" +
				"1 0 0 1 0\n" +
				"0 0 0 1 0\n" +
				"1 1 0 0 1\n" +
				"1 1 0 1 1\n" +
				"1 0 0 0 1\n" +
				"1 0 1 1 0\n" +
				"0 0 1 0 0\n" +
				"0 0 0 0 0";
		expect = 11;
		testCase(test, input, expect);

		input = "10 8\n" +
				"0 0 1 1 1 1 1 0\n" +
				"1 1 1 1 1 1 0 1\n" +
				"0 0 0 1 0 1 0 0\n" +
				"1 1 0 1 1 0 1 1\n" +
				"0 0 1 1 0 1 1 0\n" +
				"0 1 0 0 0 0 0 0\n" +
				"1 1 1 1 0 0 1 0\n" +
				"1 0 0 1 1 1 0 0\n" +
				"1 1 0 0 0 1 1 1\n" +
				"1 1 1 0 0 1 0 1";
		expect = -1;
		testCase(test, input, expect);

		input = "10 10\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"1 0 1 0 1 0 1 0 1 0";
		expect = 40;
		testCase(test, input, expect);

		input = "4 10\n" +
				"0 0 1 0 0 0 0 1 0 1\n" +
				"0 0 0 0 0 0 1 0 0 1\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 1 1 0 0 1 0 0 0 0";
		expect = -1;
		testCase(test, input, expect);

		input = "1 6\n" +
				"1 0 1 0 0 1";
		expect = -1;
		testCase(test, input, expect);

		input = "10 10\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"1 1 0 0 1 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"1 0 1 0 1 0 1 0 1 0";
		expect = 36;
		testCase(test, input, expect);

		input = "5 5\n" +
				"1 0 1 0 1\n" +
				"0 1 1 0 0\n" +
				"0 0 1 1 0\n" +
				"0 0 0 1 1\n" +
				"0 0 0 0 1\n";
		expect = -1;
		testCase(test, input, expect);

		input = "10 10\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1 1 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 0\n" +
				"0 0 0 0 0 0 0 0 0 1\n" +
				"0 0 0 0 0 0 0 0 0 1\n" +
				"1 1 1 1 1 1 1 1 1 1";
		expect = 6;
		testCase(test, input, expect);

		input = "6 10\n" +
				"1 1 1 1 1 1 1 1 1 1\n" +
				"1 0 0 0 0 0 1 0 0 0\n" +
				"1 0 1 0 0 0 1 0 0 1\n" +
				"1 0 1 0 0 0 1 0 0 1\n" +
				"0 0 1 0 0 0 0 0 0 1\n" +
				"1 1 1 1 1 1 1 1 1 1";
		expect = 2;
		testCase(test, input, expect);
	}

	private void testCase(Test test, String input, int expect){
		final String DELIM = "\n";
		final String SPACE = " ";
		String[] parsed = input.split(DELIM);
		String head = parsed[0];
		String[] tmp = head.split(SPACE);
		int N = Integer.parseInt(tmp[0]);
		int M = Integer.parseInt(tmp[1]);
		int[][] map = new int[N][];
		for(int i = 0; i < N; i++){
			map[i] = InputParser.parseStringToIntArray(parsed[i+1]);
		}
		int result = getMinWeightToConnectAllIsland(N, M, map);
		test.test(result, expect).printResult();
	}

	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		int [][]map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		
		kb.close();
		int result = getMinWeightToConnectAllIsland(N, M, map);
		System.out.println(result);
	}

	private class Node implements Comparable<Node>{
		int depart;
		int destination;
		int weight;

		public Node(int depart, int destination, int weight){
			this.depart = Math.min(depart, destination);
			this.destination = Math.max(depart, destination);
			this.weight = weight;
		}

		@Override
		public int hashCode() {
			String toString = this.depart + "," + this.destination;
			return toString.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			Node o = (Node) obj;
			return (this.depart == o.depart && this.destination == o.destination);
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	private int getMinWeightToConnectAllIsland(int n, int m, int[][] map) {
		int numberOfIsland = setIslandNumber(n, m, map);
		ArrayList<Node> nodeList = getNodeList(n, m, map);
		Collections.sort(nodeList);
		int[] parents = initParents(numberOfIsland);

		int result = 0;
		for(Node node : nodeList){
			int u = getParents(parents, node.depart - 1);
			int v = getParents(parents, node.destination - 1);
			if(u != v){
				union(parents, u, v);
				result += node.weight;
			}
		}

		if(!isUnifiedOne(parents, numberOfIsland)){
			return -1;
		}

		return result;
	}

	private boolean isUnifiedOne(int[] parents, int size) {
		int count = 0;
		for(int i = 0; i < size; i++){
			if(parents[i] == i){
				count++;
			}
		}
		return count == 1;
	}

	private int[] initParents(int size) {
		int[] parents = new int[size];
		for(int i = 0 ; i < size; i++){
			parents[i] = i;
		}
		return parents;
	}

	private void union(int[] parents, int u, int v) {
		parents[u] = v;
	}

	private int getParents(int[] parents, int node) {
		while(parents[node] != node){
			node = parents[node];
			parents[node] = parents[parents[node]];
		}
		return node;
	}

	private ArrayList<Node> getNodeList(int n, int m, int[][] map) {
		HashMap<Node, Integer> hashMap = new HashMap<>();

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] < 0){
					for(int d = 0; d < 4; d++){
						Node node = getNode(map, i, j, d, n, m);
						if(node == null){
							continue;
						}
						if(hashMap.containsKey(node)){
							if(node.weight < hashMap.get(node)){
								hashMap.remove(node);
								hashMap.put(node, node.weight);
							}
						}else{
							hashMap.put(node, node.weight);
						}
					}
				}
			}
		}

		ArrayList<Node> result = new ArrayList<>(hashMap.keySet());
		return result;
	}

	private Node getNode(int[][] map, int x, int y, int dir, int n, int m) {
		int depart = map[x][y] * -1;
		int weight = 0;
		while(true){
			x += dirX[dir];
			y += dirY[dir];
			weight++;
			if(isOutOfRange(x, y, n, m)){
				return null;
			}
			if(map[x][y] < 0){
				break;
			}
		}
		weight--;
		int destination = map[x][y] * -1;
		if(weight <= 1 || depart == destination){
			return null;
		}
		Node node = new Node(depart, destination, weight);
		return node;
	}

	private int setIslandNumber(int n, int m, int[][] map) {
		int index = -1;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] == 1){
					setIslandWith(index, map, i, j, n, m);
					index--;
				}
			}
		}

		index *= -1;
		return index-1;
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, -1, 0, 1};
	private void setIslandWith(int index, int[][] map, int i, int j, int n, int m) {
		Queue<Point> queue = new LinkedList<>();
		Point start = new Point(i, j);
		queue.add(start);

		while(!queue.isEmpty()){
			Point cur = queue.poll();
			map[cur.x][cur.y] = index;

			for(int d = 0; d < 4; d++){
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];

				if(isOutOfRange(x, y, n, m)){
					continue;
				}
				if(map[x][y] == 1){
					Point next = new Point(x, y);
					queue.add(next);
				}
			}
 		}
	}
	private boolean isOutOfRange(int x, int y, int n, int m){
		return (x < 0 || y < 0 || x >= n || y >= m);
	}
}
