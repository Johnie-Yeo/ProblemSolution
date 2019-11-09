package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class Marathon1 {
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Marathon1().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		ArrayList<Point> list = new ArrayList<>();
		for(int n = 0; n < N; n++) {
			list.add(new Point(kb.nextInt(), kb.nextInt()));
		}
		
		kb.close();
		
		int result = shortestDistance(list);
		System.out.println(result);
	}
	public int shortestDistance(ArrayList<Point> list) {
		
		ArrayList<Integer> reducedDistance = new ArrayList<>();
		
		for(int i = 1; i < list.size()-1; i++) {
			Point prev = list.get(i-1);
			Point cur = list.get(i);
			Point after = list.get(i+1);
			
			int reduced = getDistance(prev, cur) + getDistance(cur, after) - getDistance(prev, after);
			reducedDistance.add(reduced);
		}
		
		int idx = removeMaxIdx(reducedDistance);
		list.remove(idx+1);
		
		Point start = list.get(0);
		int result = 0;
		for(Point cur : list) {
			result += getDistance(start, cur);
			start = cur;
		}
		return result;
	}
	public int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
	}
	public int removeMaxIdx(ArrayList<Integer> list) {
		int idx = 0;
		int max = 0;
		int maxIdx = 0;
		
		for(int cur : list) {
			if(cur > max) {
				maxIdx = idx;
				max = cur;
			}
			idx++;
		}
		
		return maxIdx;
	}
}
