package BOJ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//���� 13460

//�� ���� ���� bfs�� ���� ���� �����ٰ� Ȧ�� ������ ��
//���� �ִ� ���� ������ -1 return
//ť�� ����ť ���ť ���� ������ ����

//5 7
//#######
//#.....#
//#...#.#
//#RBO###
//#######

//10 10
//##########
//#R#...##B#
//#...#.##.#
//#####.##.#
//#......#.#
//#.######.#
//#......###
//###.##...#
//#O.......#
//##########

//10 10
//##########
//#R#...##B#
//#...#.##.#
//#####.##.#
//##.....#.#
//#..#####.#
//##.....###
//#O#.##...#
//#........#
//##########
public class EscapeMarble2 {
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Point other) {
			if(this.x == other.x && this.y == other.y)
				return true;
			return false;
		}
	}
	public class Route{
		Point red;
		Point blue;
		int count;
		int dir;
		public Route(Point red, Point blue, int count, int dir) {
			this.red = red;
			this.blue = blue;
			this.count = count;
			this.dir = dir;//������ �Դ� ���� �ٷ� ���ư��� �� ����
		}
	}//ť�� ���� ����
	final char WALL = '#';
	final char PATH = '.';
	final char HOLE = 'O';
	final char RED = 'R';
	final char BLUE = 'B';

	final int[] dirX = {-1, 0, 1, 0};
	final int[] dirY = {0, -1, 0, 1};

	public static void main(String[] args) {
		new EscapeMarble2().solve();
	}
	
	public void solve() {
		Scanner kb = new Scanner(System.in);

		int N = kb.nextInt();
		int M = kb.nextInt();
		char[][] map = new char[N][M];
		Point REDBALL = null, BLUEBALL = null;

		for(int i = 0; i < N; i ++){
			String tmp = kb.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'R') {
					REDBALL = new Point(i, j);
				}
				else if(map[i][j] == 'B') {
					BLUEBALL = new Point(i, j);
				}
			}
		}

		int result = solution(map, N, M, REDBALL, BLUEBALL);
		System.out.println(result);

		kb.close();
	}
	
	private int solution(char[][] map, int n, int m, Point REDBALL, Point BLUEBALL) {
		Deque<Route> queue = new ArrayDeque<>();
		int count = 0;
		
		queue.add(new Route(REDBALL, BLUEBALL, count, -3));
		
		while(!queue.isEmpty() && queue.peek().count < 10) {
			Route cur = queue.pop();
			Point redCur = cur.red;
			Point blueCur = cur.blue;
			count = cur.count;
			int dir = cur.dir;
			
			for(int d = 0; d < 4; d++) {
				if((dir+2)%4 == d || dir == d)
					continue;
				int rx = redCur.x;
				int ry = redCur.y;
				map[rx][ry] = RED;
				
				int bx = blueCur.x;
				int by = blueCur.y;
				map[bx][by] = BLUE;
				
				//������ ����
				Point redDestination = goStraight(rx, ry, map, n, m, d);
				map[rx][ry] = PATH;
				
				boolean success = false;
				
				if(map[redDestination.x][redDestination.y] == HOLE) {
					success = true;
				}else {
					map[redDestination.x][redDestination.y] = RED; //�̷��� �ӽ÷θ� ǥ��������� �Ķ��� ���θ� ���� ����
				}
				
				//�Ķ��� ��������
				Point blueDestination = goStraight(bx, by, map, n, m, d);
				map[bx][by] = PATH;
				
				if(map[blueDestination.x][blueDestination.y] == HOLE) {
					if(map[redDestination.x][redDestination.y] != HOLE)
						map[redDestination.x][redDestination.y] = PATH;
					continue;
				}else if(success) { // �Ķ����� �Ⱥ����µ� �������� ����?
					return count + 1;
				}
				map[blueDestination.x][blueDestination.y] = BLUE; // �̵� Ǯ�����
				
				//�տ� ������������ ������ ������ �ѹ� �� ��������. �� �ȿ� �ִ� ������ �Ķ��� �����ٸ� ������ �� �ʿ䵵 ���� ����
				rx = redDestination.x;
				ry = redDestination.y;
				map[rx][ry] = PATH;
				
				redDestination = goStraight(rx, ry, map, n, m, d);
				map[blueDestination.x][blueDestination.y] = PATH; //�� ����������� Ǯ���൵ �ȴ�.
				map[redDestination.x][redDestination.y] = PATH;
				
				//��� �Ķ��ſ� ������ ������ ���� ������ ��츦 �����̹Ƿ� ���ۿ� �� Ȯ���� 0�̴�.
				if(!(redDestination.equals(redCur) && blueDestination.equals(blueCur))) {
					queue.add(new Route(redDestination, blueDestination, count + 1, d));
				}
				
			}
		}
		//10�� �̻� ����������
		return -1;
	}
	
	public Point goStraight(int x, int y, char[][] map, int n, int m, int direction) {
		int nextX = x + dirX[direction];
		int nextY = y + dirY[direction];
		while(nextX >= 0 && nextY >= 0 && nextX < n && nextY < m && map[nextX][nextY] == PATH) {
			x = nextX;
			y = nextY;
			nextX += dirX[direction];
			nextY += dirY[direction];
		}
		if(map[nextX][nextY] == HOLE) {
			return new Point(nextX, nextY);
		}
		return new Point(x, y);
	}
}