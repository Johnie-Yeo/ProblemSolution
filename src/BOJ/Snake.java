package BOJ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Snake {
	public final int []dirX = {-1,0,1,0};
	public final int []dirY = {0,-1,0,1};

	public final int UP = 0;
	public final int LEFT = 1;
	public final int DOWN = 2;
	public final int RIGHT = 3;
	
	public final int APPLE = 2;
	public final int SNAKE = 1;
	public final int PATH = 0;
	
	public static void main(String[] args) {
		new Snake().solve();
	}
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public class Turn{
		int sec;
		char dir;
		public Turn(int sec, char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();// size of the board
		int [][]map = new int[N][N];
		
		int K = kb.nextInt(); // number of the apple
		Point []position = new Point[K];
		for(int k = 0; k < K; k++) {
			int x = kb.nextInt();
			int y = kb.nextInt();
			position[k] = new Point(x, x);//position of the apple
			map[x-1][y-1] = APPLE;
		}
		
		int L = kb.nextInt();//number of turning what the snake does
		Turn []turn =  new Turn[L];
		for(int l = 0; l < L; l++) {
			int sec = kb.nextInt();
			char dir = kb.next().charAt(0);
			turn[l] = new Turn(sec, dir);
		}
		kb.close();
		
		int result = calculateEndOfTheGame(N, map, position, turn);
		System.out.println(result);
	}
	private int calculateEndOfTheGame(int n, int[][] map, Point[] position, Turn[] turn) {
		int initDir = RIGHT;
		Point initPos = new Point(0,0);
		map[initPos.x][initPos.y] = SNAKE;
		int sec = 0;
		int turnIdx = 0;
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(initPos);
		
		int dir= initDir;
		int curX = initPos.x + dirX[dir];
		int curY = initPos.y + dirY[dir];
		sec++;
		
		while(true) {
			if(curX < 0 || curY < 0 || curX >= n || curY >= n || map[curX][curY] == SNAKE) {
				break;
			}
			
			snake.add(new Point(curX, curY));//�⺻������ �������� �����Ѵ�.
			if(map[curX][curY] != APPLE) {//���̰� �þ �� ������ ť�� ���� �� ������
				Point tmp = snake.removeFirst();
				map[tmp.x][tmp.y] = PATH;
			}
			
			map[curX][curY] = SNAKE;
			
			if(turnIdx < turn.length && sec == turn[turnIdx].sec) {
				switch(turn[turnIdx].dir){
					case 'L' : 
						dir = (dir+1)%4;
						break;
					case 'D':
						dir = (dir+3)%4;
						break;
				}
				turnIdx++;
			}
			curX += dirX[dir];
			curY += dirY[dir];
			
			sec++;
		}
		return sec;
	}
}
