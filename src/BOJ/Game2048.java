package BOJ;
import java.util.Scanner;

//BOJ 12100

//���� ������ �� �Ʒ��� Ŀ�ǵ忡 ���� �޼��带 ����� ���� dfs�ϸ� ��
public class Game2048 {
	final int LEFT = 0;
	final int RIGHT = 1;
	final int UP = 2;
	final int DOWN = 3;
	
	final int []dirX = {0, 0, -1, 1};
	final int []dirY = {-1, 1, 0, 0};
	
	public static void main(String[] args) {
		new Game2048().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int [][]map =  new int[n][n];
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		kb.close();

		int numOfPlay = 5;
		int max = getMax(n, map);
		
		max = getMaxBlock(n, map, numOfPlay, max);
		System.out.println(max);
	}
	public int getMax(int n, int[][] map) {
		int max = 0;
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				if(map[i][j] > max)
					max = map[i][j];
		
		return max;
	}
	public int getMaxBlock(int n, int[][] map, int numOfPlay, int maxBlock) {
		if(numOfPlay == 0) {
			return maxBlock;
		}else {
			int max = maxBlock;
			int [][]tmpMap = new int[n][n];

			for(int dir = 0; dir <= 3; dir++) {
				copyMap(n, tmpMap, map); //�����Ѽ� ����
				
				int move = moveDir(n, tmpMap, maxBlock, dir);
				if(!isSame(n, map, tmpMap))
					move = getMaxBlock(n, tmpMap, numOfPlay-1, move);
				if(max < move)
					max = move;
			}
			
			return max;
		}
	}
	
	public int moveDir(int n, int[][] map, int maxBlock, int dir) {
		
		int max = maxBlock;//�߻��ϴ� �ִ�

		if(dir == 0) {
			for(int i = 0; i < n; i++) {
				int start = 0;
				stickToDirection(n, map, LEFT, i);

				while(start < n - 1 && map[i][start] > 0) {
					if(map[i][start] == map[i][start+1]) {
						map[i][start] += map[i][start+1];
						map[i][start+1] = 0;
						if(map[i][start] > max)
							max = map[i][start];

						start += 2;
					}else {
						start++;
					}
				}

				stickToDirection(n, map, LEFT, i);
			}
		}else if(dir == 1) {
			for(int i = 0; i < n; i++) {
				int start = n - 1;
				stickToDirection(n, map, RIGHT, i);

				while(start >  0 && map[i][start] > 0) {
					if(map[i][start] == map[i][start-1]) {
						map[i][start] += map[i][start-1];
						map[i][start-1] = 0;
						if(map[i][start] > max)
							max = map[i][start];

						start -= 2;
					}else {
						start--;
					}
				}

				stickToDirection(n, map, RIGHT, i);
			}
		}else if(dir == 2) {
			for(int i = 0; i < n; i++) {
				int start = 0;
				stickToDirection(n, map, UP, i);

				while(start < n - 1 && map[start][i] > 0) {
					if(map[start][i] == map[start + 1][i]) {
						map[start][i] += map[start + 1][i];
						map[start + 1][i] = 0;
						if(map[start][i] > max)
							max = map[start][i];

						start += 2;
					}else {
						start++;
					}
				}

				stickToDirection(n, map, UP, i);
			}
		}else if(dir == 3) {
			for(int i = 0; i < n; i++) {
				int start = n-1;
				stickToDirection(n, map, DOWN, i);

				while(start > 0 && map[start][i] > 0) {
					if(map[start][i] == map[start-1][i]) {
						map[start][i] += map[start - 1][i];
						map[start - 1][i] = 0;
						if(map[start][i] > max)
							max = map[start][i];

						start -= 2;	
					}else {
						start--;
					}
				}

				stickToDirection(n, map, DOWN, i);
			}
		}else{
			return -1;
		}
		
		return max;
	}
	
	public boolean isSame(int n, int[][] map, int[][] tmpMap) {
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				if(map[i][j] != tmpMap[i][j])
					return false;
		return true;
	}
	private void copyMap(int n, int[][] tmpMap, int[][] map) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}

	public void stickToDirection(int n, int [][]map, int dir, int index) {//�����¿�� ����
		if(dir == LEFT) {

			int empty = 0;
			for(int i = 0; i < n; i++) {
				if(map[index][i] > 0) {
					if(empty > 0) {
						map[index][i-empty] = map[index][i]; // ���ڸ� ���� ��ŭ ����
						map[index][i] = 0;
					}
				}else {
					empty++;//���ڸ� ���� ����
				}
			}

		}else if(dir == RIGHT) {

			int empty = 0;
			for(int i = n-1; i >= 0; i--) {
				if(map[index][i] > 0) {
					if(empty > 0) {
						map[index][i+empty] = map[index][i]; // ���ڸ� ���� ��ŭ ����
						map[index][i] = 0;
					}
				}else {
					empty++;//���ڸ� ���� ����
				}
			}

		}else if(dir == UP) {

			int empty = 0;
			for(int i = 0; i < n; i++) {
				if(map[i][index] > 0) {
					if(empty > 0) {
						map[i - empty][index] = map[i][index]; // ���ڸ� ���� ��ŭ ����
						map[i][index] = 0;
					}
				}else {
					empty++;//���ڸ� ���� ����
				}
			}

		}else if(dir == DOWN) {

			int empty = 0;
			for(int i = n-1; i >= 0; i--) {
				if(map[i][index] > 0) {
					if(empty > 0) {
						map[i + empty][index] = map[i][index]; // ���ڸ� ���� ��ŭ ����
						map[i][index] = 0;
					}
				}else {
					empty++;//���ڸ� ���� ����
				}
			}

		}else {
			//error
			return;
		}
	}
}