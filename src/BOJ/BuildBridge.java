package BOJ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BuildBridge {
	public static void main(String[] args) {
		new BuildBridge().solve();
	}
	
	public int[] dirX = {0,1,0,-1};
	public int[] dirY = {1,0,-1,0};
	public class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public class Edge implements Comparable<Edge>{
		int depart;
		int destin;
		int dist;
		
		public Edge(int depart, int destin) {
			this.depart = depart;
			this.destin = destin;
		}
		public Edge(int depart, int destin, int dist) {
			this.depart = depart;
			this.destin = destin;
			this.dist = dist;
		}
		public boolean equals(Object o) {
			if(this.depart == ((Edge)o).depart && this.destin == ((Edge)o).destin) {
				return true;
			}
			return false;
		}
		@Override
		public int hashCode() {

			final int prime = 31;
			int hashCode = 1;

			hashCode = prime * hashCode + (((""+this.depart) == null) ? 0 : (""+this.depart).hashCode());
			hashCode = prime * hashCode + this.destin;

			return hashCode;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}
	
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int M = kb.nextInt();
		int [][]map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = kb.nextInt();
			}
		}
		
		kb.close();
		
		int numOfComponent = divideComponent(map, N, M); //������Ʈ�� ������
		ArrayList<Edge> edgeList = getEdgeList(map, N, M); //��ü�� Ž���ϸ� ��θ� ������
		int result = getSumOfEdge(edgeList, numOfComponent); //MST ������
		System.out.println(result);
	}
	public int getSumOfEdge(ArrayList<Edge> edgeList, int num) {
		int []parent = new int[num+1];
		setParents(parent, num);
		
		Collections.sort(edgeList);
		
		int result = 0;
		for(Edge edge : edgeList) {
			int depart = -edge.depart;
			int destin = -edge.destin;
			
			int departParent = findParent(depart, parent);
			int destinParent = findParent(destin, parent);
			
			if(departParent != destinParent) {
				result += edge.dist;
				union(departParent, destinParent, parent);
			}
		}
		if(isUnioned(parent)) {			
			return result;
		}else {
			return -1;
		}
	}
	public boolean isUnioned(int[] parent) {
		boolean chk = false;
		for(int i = 1; i < parent.length; i++) {
			if(i == parent[i]) {
				if(chk) {
					return false;
				}else {
					chk = true;
				}
			}
		}
		return true;
	}
	public void union(int depart, int destin, int[] parent) {
		parent[depart] = destin;
	}
	public int findParent(int destin, int[] parent) {
		while(destin != parent[destin]) {
			destin = parent[destin];
			parent[destin] = parent[parent[destin]];
		}
		return destin;
	}
	public void setParents(int []parents, int num) {
		for(int i = 0; i <= num; i++) {
			parents[i] = i;
		}
	}
	public ArrayList<Edge> getEdgeList(int[][] map, int n, int m) {
		HashMap<Edge, Integer> list = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			int start = -1;
			int end = 0;
			
			for(int j = 0; j < m; j++) {
				if(map[i][j] != 0) {
					if(start < 0) {						
						start = j;
					}else {
						end = j;
						
						int dist = end - start - 1;
						int depart = map[i][start];
						int destin = map[i][end];
						if(depart < destin) {
							int tmp = depart;
							depart = destin;
							destin = tmp;
						}
						
						if(depart != destin) {							
							Edge cur = new Edge(depart, destin);
							if(list.containsKey(cur) && list.get(cur) < dist) {
								dist = list.get(cur);
							}
							if(dist > 1) {								
								list.put(cur, dist);
							}
							
							start=end;
						}else {
							start = j;
						}
					}
				}
			}
		}
		
		for(int j = 0; j < m; j++) {
			int start = -1;
			int end = 0;
			
			for(int i = 0; i < n; i++) {
				if(map[i][j] != 0) {
					if(start < 0) {
						start = i;
					}else {
						end = i;
						
						int dist = end - start - 1;
						int depart = map[start][j];
						int destin = map[end][j];
						
						if(depart < destin) {
							int tmp = depart;
							depart = destin;
							destin = tmp;
						}
						
						if(depart != destin) {							
							Edge cur = new Edge(depart, destin);
							
							if(list.containsKey(cur) && list.get(cur) < dist) {
								dist = list.get(cur);
							}
							if(dist > 1) {								
								list.put(cur, dist);
							}
							
							start = end;
						}else {
							start = i;
						}
					}
				}
			}
		}
		
		ArrayList<Edge> result = new ArrayList<>();
		
		for(Edge cur : list.keySet()) {
			result.add(new Edge(cur.depart, cur.destin, list.get(cur)));
		}
		
		return result;
	}
	public int divideComponent(int[][] map, int n, int m) {
		int cur = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {					
					checkComponent(map, n, m, i, j, cur);
					cur++;
				}
			}
		}
		
		return cur-1;
	}
	public void checkComponent(int[][] map, int n, int m, int X, int Y, int index) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		
		queue.add(new Point(X, Y));
		map[X][Y] = -index;
		while(!queue.isEmpty()) {
			Point cur = queue.pop();
			
			for(int d = 0; d < 4; d++) {
				int x = cur.x + dirX[d];
				int y = cur.y + dirY[d];
				
				if(x < 0 || y < 0 || x >= n || y >= m || map[x][y] != 1) {
					continue;
				}					
				
				map[x][y] = -index;
				queue.add(new Point(x, y));
			}
		}
	}
}
