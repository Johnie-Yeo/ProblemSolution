package BOJ;

import java.util.Scanner;

public class Tetromino {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tetromino().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		
		int [][]map = new int[N][M];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		
		kb.close();
		
		int result = solution(N, M, map);
		System.out.println(result);
	}
	public int solution(int N, int M, int [][]map) {
		int x = 0;
		int y = 0;
		int max = 0;
		int answer = search(N, M, map, x, y, max);
		
		return answer;
	}
	public int search(int N, int M, int [][]map, int x, int y, int max) {
		
		if(x >= N) {
			return max;
		}
		if(y >= M) {
			return search(N, M, map, x+1, 0, max);
		}
		
		int tmp = getStraight(N, M, map, x, y);
		if(tmp > max) {
			max = tmp;
		}
		
		tmp = getSquare(N, M, map, x, y);
		if(tmp > max) {
			max = tmp;
		}
		
		tmp = getL(N, M, map, x, y);
		if(tmp > max) {
			max = tmp;
		}
		
		tmp = getThunder(N, M, map, x, y);
		if(tmp > max) {
			max = tmp;
		}
		
		tmp = getArrow(N, M, map, x, y);
		if(tmp > max) {
			max = tmp;
		}
		
		return search(N, M, map, x, y+1, max);
	}
	public int getStraight(int n, int m, int[][] map, int x, int y) {
		int max = 0;
		int tmp = 0;
		
		for(int i = x; i < x+4; i++) {
			if(i >= n) {
				tmp = 0;
				break;
			}
			tmp += map[i][y];
		}
		if(tmp > max) {
			max = tmp;
		}
		
		tmp = 0;
		for(int i = y; i < y+4; i++) {
			if(i >= m) {
				tmp = 0;
				break;
			}
			tmp += map[x][i];
		}
		if(tmp > max) {
			max = tmp;
		}
		
		return max;
	}
	public int getSquare(int n, int m, int[][] map, int x, int y) {
		int max = 0;
		
		if(x + 1 >= n || y + 1 >= m) {
			return 0;
		}
		
		max += map[x][y];
		max += map[x][y+1];
		max += map[x+1][y];
		max += map[x+1][y+1];
		
		return max;
	}
	public int getL(int n, int m, int[][] map, int x, int y) {
		int tmp = 0;
		int max = 0;
		
		if(x + 2 < n && y + 1 < m) {
			//first case
			tmp += map[x][y];
			tmp += map[x+1][y];
			tmp += map[x+2][y];
			tmp += map[x+2][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
			
			//����
			tmp = 0;
			tmp += map[x][y+1];
			tmp += map[x+1][y+1];
			tmp += map[x+2][y+1];
			tmp += map[x+2][y];
			
			if(tmp > max) {
				max = tmp;
			}
			
			//second case
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x+1][y+1];
			tmp += map[x+2][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
			
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x+1][y];
			tmp += map[x+2][y];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		if(x + 1 < n && y + 2 < m) {
			//��
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
			
			//����
			tmp = 0;
			tmp += map[x+1][y];
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
			
			//��
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x][y+2];
			tmp += map[x+1][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
			
			//����
			tmp = 0;
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y+2];
			tmp += map[x][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		
		return max;
	}
	public int getThunder(int n, int m, int[][] map, int x, int y) {
		int tmp = 0;
		int max = 0;
		
		if(x + 2 < n && y + 1 < m) {
			tmp += map[x][y];
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x+2][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
			
			tmp = 0;
			tmp += map[x][y+1];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y];
			tmp += map[x+2][y];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		if(x + 1 < n && y + 2 < m) {
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
			
			tmp = 0;
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x][y+1];
			tmp += map[x][y+2];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		
		return max;

	}
	public int getArrow(int n, int m, int[][] map, int x, int y) {
		int tmp = 0;
		int max = 0;
		
		if(x + 2 < n && y + 1 < m) {
			tmp += map[x][y];
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x+2][y];
			
			if(tmp > max) {
				max = tmp;
			}
			
			tmp = 0;
			tmp += map[x][y+1];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y];
			tmp += map[x+2][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		if(x + 1 < n && y + 2 < m) {
			tmp = 0;
			tmp += map[x][y];
			tmp += map[x][y+1];
			tmp += map[x][y+2];
			tmp += map[x+1][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
			
			tmp = 0;
			tmp += map[x+1][y];
			tmp += map[x+1][y+1];
			tmp += map[x+1][y+2];
			tmp += map[x][y+1];
			
			if(tmp > max) {
				max = tmp;
			}
		}
		return max;
	}
}
