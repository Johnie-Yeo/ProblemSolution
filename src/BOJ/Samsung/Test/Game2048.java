package BOJ.Samsung.Test;
import Test.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//BOJ 12100

public class Game2048 {
	private final int MAX_MOVE = 5;
	private final int UP = 0;
	private final int LEFT = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;

	public static void main(String[] args) {
//		new Main().solve();
		new Game2048().test();
	}
	private void test(){
		Test test = new Test();

		int N;
		int[][] map;
		int result, expect;

		N = 3;
		map = new int[][]{
				{2, 2, 2},
				{4, 4, 4},
				{8, 8, 8},
		};
		result = getMaxBlock(N, map);
		expect = 16;
		test.test(result, expect).printResult();

		N = 3;
		map = new int[][]{
				{4, 4, 8},
				{4, 4, 8},
				{8, 8, 16},
		};
		result = getMaxBlock(N, map);
		expect = 64;
		test.test(result, expect).printResult();

		N = 4;
		map = new int[][]{
				{4, 4, 8, 16},
				{4, 4, 8, 16},
				{8, 8, 16, 32},
				{0,0,0,0}
		};
		result = getMaxBlock(N, map);
		expect = 128;
		test.test(result, expect).printResult();

		N = 4;
		map = new int[][]{
				{2, 2, 2, 2},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}

		};
		result = getMaxBlock(N, map);
		expect = 8;
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				map[i][j] = kb.nextInt();
			}
		}
		int result = getMaxBlock(N, map);
		System.out.println(result);
		kb.close();
	}
	private class Board{
		int[][] map;
		int count;
		public Board(int count, int[][] map){
			this.count = count;
			this.map = clone(map);
		}
		private int[][] clone(int[][] arr){
			int size = arr.length;
			int[][] clone = new int[size][];
			for(int i = 0; i < size; i++){
				clone[i] = arr[i].clone();
			}
			return clone;
		}
	}
	private int getMaxBlock(int n, int[][] map) {
		Queue<Board> queue = new LinkedList<>();
		queue.add(new Board(0, map));
		int max = 0;

		while(!queue.isEmpty()){
			Board cur = queue.poll();
			int[][] board = cur.map;
			int count = cur.count;
			if(count >= MAX_MOVE){
				break;
			}

			for(int d = 0; d < 4; d++){
				int[][] moved = move(d, n, board);
				int tmp = getMax(moved);
				max = Math.max(max, tmp);
				queue.add(new Board(count+1, moved));
			}
		}
		return max;
	}

	private int[][] move(int dir, int n, int[][] map) {
		switch (dir){
			case UP:
				return moveUp(n, map);
			case DOWN:
				return moveDown(n, map);
			case LEFT:
				return moveLeft(n, map);
			case RIGHT:
				return moveRight(n, map);
			default:
				return null;
		}
	}

	private int[][] moveUp(int n, int[][] map) {
		int[][] moved = new int[n][n];

		for(int i = 0; i < n; i++){
			ArrayList<Integer> column = new ArrayList<>();
			int store = -1;
			for(int j = 0; j < n; j++){
				if(map[j][i] > 0){
					if(store < 0){
						store = map[j][i];
					}else{
						if(store == map[j][i]){
							column.add(store*2);
							store = -1;
						}else{
							column.add(store);
							store = map[j][i];
						}
					}
				}
			}
			if(store > 0){
				column.add(store);
			}
			int size = column.size();
			for(int s = 0; s < size; s++){
				moved[s][i] = column.get(s);
			}
		}

		return moved;
	}

	private int[][] moveDown(int n, int[][] map) {
		int[][] moved = new int[n][n];

		for(int i = 0; i < n; i++){
			ArrayList<Integer> column = new ArrayList<>();
			int store = -1;
			for(int j = n-1; j >= 0; j--){
				if(map[j][i] > 0){
					if(store < 0){
						store = map[j][i];
					}else{
						if(store == map[j][i]){
							column.add(store*2);
							store = -1;
						}else{
							column.add(store);
							store = map[j][i];
						}
					}
				}
			}
			if(store > 0){
				column.add(store);
			}
			int size = column.size();
			for(int s = 0; s < size; s++){
				moved[n-s-1][i] = column.get(s);
			}
		}

		return moved;
	}

	private int[][] moveLeft(int n, int[][] map) {
		int[][] moved = new int[n][n];

		for(int i = 0; i < n; i++){
			ArrayList<Integer> row = new ArrayList<>();
			int store = -1;
			for(int j = 0; j < n; j++){
				if(map[i][j] > 0){
					if(store < 0){
						store = map[i][j];
					}else{
						if(store == map[i][j]){
							row.add(store*2);
							store = -1;
						}else{
							row.add(store);
							store = map[i][j];
						}
					}
				}
			}
			if(store > 0){
				row.add(store);
			}
			int size = row.size();
			for(int s = 0; s < size; s++){
				moved[i][s] = row.get(s);
			}
		}
		return moved;
	}

	private int[][] moveRight(int n, int[][] map) {
		int[][] moved = new int[n][n];

		for(int i = 0; i < n; i++){
			ArrayList<Integer> row = new ArrayList<>();
			int store = -1;
			for(int j = n-1; j >= 0; j--){
				if(map[i][j] > 0){
					if(store < 0){
						store = map[i][j];
					}else{
						if(store == map[i][j]){
							row.add(store*2);
							store = -1;
						}else{
							row.add(store);
							store = map[i][j];
						}
					}
				}
			}
			if(store > 0){
				row.add(store);
			}
			int size = row.size();
			for(int s = 0; s < size; s++){
				moved[i][n-s-1] = row.get(s);
			}
		}
		return moved;
	}

	private int getMax(int[][] arrays) {
		int max = 0;
		for(int[] arr : arrays){
			for(int elem : arr){
				max = Math.max(elem, max);
			}
		}
		return max;
	}
}
