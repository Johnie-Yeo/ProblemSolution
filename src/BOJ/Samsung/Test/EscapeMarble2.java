package BOJ.Samsung.Test;

import Test.OldTest;

import java.util.*;

public class EscapeMarble2 {
	private final char EMPTY = '.';
	private final char BLOCK = '#';
	private final char HOLE  = 'O';
	private final char RED_MARBLE = 'R';
	private final char BLUE_MARBLE = 'B';
	private final int[] dirX = {-1, 0, 1, 0};
	private final int[] dirY = {0, -1, 0, 1};

	public static void main(String[] args) {
//		new Main().solve();
		new EscapeMarble2().test();
	}
	private void test(){
		OldTest test = new OldTest();

		int N, M;
		String input;
		char[][] map;
		int result, expect;

		N = 5; M = 5;
		input = "#####\n" +
				"#..B#\n" +
				"#.#.#\n" +
				"#RO.#\n" +
				"#####";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 1;
		test.test(result, expect).printResult();

		N = 7; M = 7;
		input = "#######\n" +
				"#...RB#\n" +
				"#.#####\n" +
				"#.....#\n" +
				"#####.#\n" +
				"#O....#\n" +
				"#######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 5;
		test.test(result, expect).printResult();

		N = 7; M = 7;
		input = "#######\n" +
				"#..R#B#\n" +
				"#.#####\n" +
				"#.....#\n" +
				"#####.#\n" +
				"#O....#\n" +
				"#######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 5;
		test.test(result, expect).printResult();

		N = 10; M = 10;
		input = "##########\n" +
				"#R#...##B#\n" +
				"#...#.##.#\n" +
				"#####.##.#\n" +
				"#......#.#\n" +
				"#.######.#\n" +
				"#.#....#.#\n" +
				"#.#.#.#..#\n" +
				"#...#.O#.#\n" +
				"##########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = -1;
		test.test(result, expect).printResult();

		N = 3; M = 7;
		input = "#######\n" +
				"#R.O.B#\n" +
				"#######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 1;
		test.test(result, expect).printResult();

		N = 10; M = 10;
		input = "##########\n" +
				"#R#...##B#\n" +
				"#...#.##.#\n" +
				"#####.##.#\n" +
				"#......#.#\n" +
				"#.######.#\n" +
				"#.#....#.#\n" +
				"#.#.##...#\n" +
				"#O..#....#\n" +
				"##########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 7;
		test.test(result, expect).printResult();

		N = 3; M = 10;
		input ="##########\n" +
				"#.O....RB#\n" +
				"##########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = -1;
		test.test(result, expect).printResult();

		N = 9; M = 6;
		input = "######\n" +
				"#.B#.#\n" +
				"#..R##\n" +
				"##.#.#\n" +
				"#..#.#\n" +
				"#O.#.#\n" +
				"#..#.#\n" +
				"#....#\n" +
				"######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 9;
		test.test(result, expect).printResult();

		N = 6; M = 7;
		input = "#######\n" +
				"#.#.R##\n" +
				"#B..#.#\n" +
				"#.....#\n" +
				"#O#..##\n" +
				"#######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 6;
		test.test(result, expect).printResult();

		N = 10; M = 6;
		input = "######\n" +
				"#BO.##\n" +
				"#...##\n" +
				"###.##\n" +
				"##...#\n" +
				"###..#\n" +
				"###R##\n" +
				"#.#..#\n" +
				"##.#.#\n" +
				"######\n";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 2;
		test.test(result, expect).printResult();

		N = 5; M = 9;
		input = "#########\n" +
				"#.##....#\n" +
				"#..R..###\n" +
				"#O#B##..#\n" +
				"#########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 2;
		test.test(result, expect).printResult();


		N = 8; M = 7;
		input = "#######\n" +
				"##.####\n" +
				"#.##.B#\n" +
				"##...R#\n" +
				"###..O#\n" +
				"##..#.#\n" +
				"#####.#\n" +
				"#######";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 6;
		test.test(result, expect).printResult();

		N = 9; M = 9;
		input = "#########\n" +
				"##.R..###\n" +
				"##..#####\n" +
				"###.#.#.#\n" +
				"##.B#.#.#\n" +
				"#.....###\n" +
				"#.O..#.##\n" +
				"###..####\n" +
				"#########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 2;
		test.test(result, expect).printResult();

		N = 7; M = 5;
		input = "#####\n" +
				"###O#\n" +
				"#...#\n" +
				"##.##\n" +
				"##..#\n" +
				"#.BR#\n" +
				"#####";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 4;
		test.test(result, expect).printResult();

		N = 6; M = 10;
		input = "##########\n" +
				"##O..#..##\n" +
				"#.#.##.###\n" +
				"#.#..B..R#\n" +
				"#######.##\n" +
				"##########";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 5;
		test.test(result, expect).printResult();

		N = 8; M = 5;
		input = "#####\n" +
				"#.###\n" +
				"#...#\n" +
				"#...#\n" +
				"#O.##\n" +
				"#.###\n" +
				"#.RB#\n" +
				"#####";
		map = getMap(input);
		result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		expect = 2;
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();

		StringBuilder input = new StringBuilder();
		for(int i = 0; i < N; i++){
			String tmp = kb.next();
			input.append(tmp);
			input.append("\n");
		}
		kb.close();
		char[][] map = getMap(input.toString());

		int result = getMinMoveToInsertRedMarbleIntoHole(N, M, map);
		System.out.println(result);
	}


	private char[][] getMap(String input) {
		final String DELIM = "\n";

		char[][] map = Arrays.stream(input.split(DELIM))
							.map(e -> e.toCharArray())
							.toArray(char[][]::new);
		return map;
	}

	private class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Point o = (Point)obj;
			return (this.x == o.x && this.y == o.y);
		}

		@Override
		public String toString() {
			return "("+this.x+","+this.y+")";
		}
	}
	private class Marbles{
		int count;
		Point red, blue;
		public Marbles(int count, Point red, Point blue){
			this.count = count;
			this.red = red;
			this.blue = blue;
		}

		@Override
		public int hashCode() {
			String redString = red.toString();
			String blueString = blue.toString();
			return (redString+blueString).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			Marbles o = (Marbles)obj;

			return (this.red.equals(o.red) && this.blue.equals(o.blue));
		}
	}

	private int getMinMoveToInsertRedMarbleIntoHole(int n, int m, char[][] map) {
		Queue<Marbles> queue = initQueue(map, n, m);
		HashSet<Marbles> set = new HashSet<>();
		Point hole = getPoint(HOLE, n, m, map);

		while(!queue.isEmpty()){
			Marbles cur = queue.poll();
			set.add(cur);
			Point red = cur.red;
			Point blue = cur.blue;
			int count = cur.count;
			if(count >= 10){
				break;
			}

			for(int d = 0; d < 4; d++){
				setMarbles(map, red, blue);

				Point movedBlue = move(map, blue, BLUE_MARBLE, d);
				Point movedRed = move(map, red, RED_MARBLE, d);
				movedBlue = move(map, movedBlue, BLUE_MARBLE, d);

				clearMarbles(map, movedRed, movedBlue);

				if(movedBlue.equals(hole)){
					continue;
				}else if(movedBlue.equals(blue) && movedRed.equals(red)){
					continue;
				}else if(movedRed.equals(hole)){
					return count+1;
				}

				Marbles next = new Marbles(count+1, movedRed, movedBlue);
				if(!set.contains(next)){
					queue.add(next);
					set.add(next);
				}
			}
		}

		return -1;
	}

	private Point move(char[][] map, Point point, char marble, int dir) {
		int x = point.x;
		int y = point.y;
		if(map[x][y] == HOLE){
			return point;
		}
		map[x][y] = EMPTY;

		while(map[x][y] == EMPTY){
			x += dirX[dir];
			y += dirY[dir];
		}
		if(map[x][y] != HOLE){
			x -= dirX[dir];
			y -= dirY[dir];
			map[x][y] = marble;
		}
		return new Point(x, y);
	}

	private void setMarbles(char[][] map, Point red, Point blue){
		map[red.x][red.y] = RED_MARBLE;
		map[blue.x][blue.y] = BLUE_MARBLE;
	}

	private void clearMarbles(char[][] map, Point red, Point blue){
		if(map[red.x][red.y] ==RED_MARBLE){
			map[red.x][red.y] = EMPTY;
		}
		if(map[blue.x][blue.y] == BLUE_MARBLE){
			map[blue.x][blue.y] = EMPTY;
		}
	}

	private Queue<Marbles> initQueue(char[][] map, int n, int m){
		Queue<Marbles> queue = new LinkedList<>();

		Point red = getPoint(RED_MARBLE, n, m, map);
		Point blue = getPoint(BLUE_MARBLE, n, m, map);
		Marbles marbles = new Marbles(0, red, blue);
		queue.add(marbles);
		clearMarbles(map, red, blue);

		return queue;
	}
	private Point getPoint(char marble, int n, int m, char[][] map){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(map[i][j] == marble){
					return new Point(i, j);
				}
			}
		}
		return null;
	}
}