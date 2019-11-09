package Programmers;

import java.util.HashMap;

public class GameCharacter {
	public static void main(String[] args) {
		GameCharacter app = new GameCharacter();
		app.solve();
	}
	public void solve() {
		String dir = "ULURRDLLU";
		int re = solution(dir);
		System.out.println(re);
	}
	public int solution(String dirs) {
		class Point{
			int x, y;
			Point(int x, int y){
				this.x = x;
				this.y = y;
			}
			@Override
			public boolean equals(Object obj) {
				Point p = (Point)obj;
				if(this.x == p.x && this.y == p.y)
					return true;
				return false;
			}
			@Override
		    public int hashCode() {
		        int result = (int)(x ^ (x >>> 32));
		        
		        result = 31 * result + (int)(y ^ (y >>> 32));
		        
		        return result;
		    }
			
		}
		int[] dirX = {-1, 0, 1, 0};
		int[] dirY = {0, -1, 0, 1};
		int answer = 0;
		int x = 0, y = 0;
		HashMap<Point, Integer> hash= new HashMap<>();
		for(int i = 0; i < dirs.length(); i++) {
			char cur = dirs.charAt(i);
			int d = 0;
			if(cur=='U')
				d = 3;
			else if(cur == 'L')
				d = 0;
			else if(cur == 'R')
				d = 2;
			else
				d = 1;
			int tmpX = x+dirX[d];
			int tmpY = y+dirY[d];
			if(tmpX < -5 || tmpX > 5 ||
				tmpY < -5 || tmpY > 5)
				continue;
			Point p = new Point(tmpX+x,tmpY+y);
			x = tmpX;
			y = tmpY;
			if(!hash.containsKey(p)) {
				hash.put(p, 1);
				answer++;
			}
		}
		return answer;
	}
}
