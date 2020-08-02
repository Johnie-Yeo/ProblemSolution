package BOJ.Samsung.TypeA;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

// 17780
public class TheNewGame {
	public final int []dirX = {0, 0, -1, 1};
	public final int []dirY = {1, -1, 0, 0};

	public class Horse{
		int index;
		int row, col, dir;
		public Horse(int index, int row, int col, int dir) {
			this.index = index;
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		public void setPoint(int dir) {
			this.row += dirX[dir];
			this.col += dirY[dir];
		}
		public void setDir(int dir) {
			this.dir = dir;
		}
		public boolean equals(Horse o) {
			return this.index==o.index;
		}
		public Horse clone() {
			return new Horse(this.index, this.row, this.col, this.dir);
		}
	}
	public static void main(String[] args) {
		new TheNewGame().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();// 체스판의 크기 N
		int K = kb.nextInt();// 말의 개수 K

		// 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 
		// 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.

		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = kb.nextInt();
			}
		}

		// 다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 
		// 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 
		// 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.
		// 같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.

		ArrayList<Horse> list = new ArrayList<>();
		for(int k = 0; k < K; k++) {
			int row = kb.nextInt();
			int col = kb.nextInt();
			int dir = kb.nextInt();

			Horse horse = new Horse(k, row-1, col-1, dir-1);
			list.add(horse);
		}

		kb.close();

		int result = firstTurnWhenHorseStackHeightMoreThan4(map, list);
		System.out.println(result);
	}
	private int firstTurnWhenHorseStackHeightMoreThan4(int[][] map, ArrayList<Horse> list) {
		int result = 0;
		int N = map.length;

		@SuppressWarnings("unchecked")
		ArrayDeque<Horse>[][] horseMap = new ArrayDeque[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				horseMap[i][j] = new ArrayDeque<>();
			}
		}

		for(Horse horse : list) {
			horseMap[horse.row][horse.col].add(horse);
		}

		while(true) {
			if(result >= 1000) {
				// 그 값이 1,000보다 크거나 절대로 말 4개 이상이 쌓이지 않는 경우에는 -1을 출력한다.
				return -1;
			}
			// 말 4개 이상이 쌓인 가장 첫 턴을 출력한다.
			int max = 0;

			int len = list.size();
			for(int i = 0 ; i < len; i++){

				if(isBottom(horseMap, list.get(i))) {					
					int x = list.get(i).row + dirX[list.get(i).dir];
					int y = list.get(i).col + dirY[list.get(i).dir];

					ArrayDeque<Horse> horses = horseMap[list.get(i).row][list.get(i).col];

					// 0은 흰색, 1은 빨간색, 2는 파란색이다.
					if(x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 2) {//	체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
						//	파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
						if(list.get(i).dir % 2 == 0) {							
							list.get(i).dir++;

						}else {
							list.get(i).dir--;
						}
						x = list.get(i).row + dirX[list.get(i).dir];
						y = list.get(i).col + dirY[list.get(i).dir];
					}
					if(x >= 0 && y >= 0 && x < N && y < N) {
						if(map[x][y] == 0) {
							//	흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
							//	A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
							//	예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
							horseMap[x][y].addAll(horses);
						}else if(map[x][y] == 1) {
							//								빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
							//	A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
							//	A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
							Horse first = horses.removeFirst();
							Horse last = null;
							if(horses.size() > 0) {							
								last = horses.removeLast();
							}

							horses.add(first);
							if(last != null) {							
								horses.addFirst(last);
							}
							horseMap[x][y].addAll(horses);
						}else if(map[x][y] == 2) {
							// 방향을 반대로 한 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 방향만 반대로 바꾼다.
							if(list.get(i).dir % 2 == 0) {							
								list.get(i).dir++;

							}else {
								list.get(i).dir--;
							}
							continue;
						}
						
						horseMap[list.get(i).row][list.get(i).col] = new ArrayDeque<>();//GC
						for(Horse horse : horses) {
							horse.row = x;
							horse.col = y;
						}

						if(horseMap[x][y].size() >= 4) {
							max = horseMap[x][y].size();
							break;
						}
					}
				}

			}

			result++;

			if(max >= 4) {
				break;
			}
		}
		return result;
	}
	public boolean isBottom(ArrayDeque<Horse>[][] horseMap, Horse horse) {
		if(horseMap[horse.row][horse.col].size() > 0 && horseMap[horse.row][horse.col].peekFirst().index == horse.index) {
			return true;
		}
		return false;
	}
}
