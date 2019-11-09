package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class CrossPuzzle {
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		new CrossPuzzle().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		char[][] puzzle = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String tmp = kb.next();
			for(int j = 0; j < M; j++) {
				puzzle[i][j] = tmp.charAt(j);
			}
		}
		
		kb.close();
		
		ArrayList<Point> result = getClue(N, M, puzzle);
		System.out.print(toString(result));
	}
	String toString(ArrayList<Point> list) {
		String result = "";
		
		result += (list.size()+"\n");
		for(Point cur : list) {
			result += (cur.x + " " + cur.y + "\n");
		}
		
		return result;
	}
	ArrayList<Point> getClue(int N, int M, char[][] puzzle){
		ArrayList<Point> result = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(puzzle[i][j] == '.') {
					//���� ����?
					//����ĭ�� �����ų� ���� ���̰� ������ �ΰ� �̻� ����־�� ��
					if((j-1 < 0 || puzzle[i][j-1] == '#') && 
					   (j+2 < M && puzzle[i][j+1] == '.' && puzzle[i][j+2] == '.')) {
						result.add(new Point(i+1, j+1));
					}
					//���� ����?
					else if((i-1 < 0 || puzzle[i-1][j] == '#') &&
							(i+2 < N && puzzle[i+1][j] == '.' && puzzle[i+2][j] == '.')) {
						result.add(new Point(i+1, j+1
								));
					}
				}
			}
		}
		
		return result;
	}
}
