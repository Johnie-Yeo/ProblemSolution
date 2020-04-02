package Programmers;

import java.util.ArrayList;
import java.util.Collections;

public class MinimumCostToBuildBridge {
	public static void main(String[] args) {
		MinimumCostToBuildBridge app = new MinimumCostToBuildBridge();
		app.solve();
	}
	public void solve() {
		int n = 4;
		int [][]cost= {
				{0,1,1},
				{0,2,2},
				{1,2,5},
				{1,3,1},
				{2,3,8}
		};
		int re = solution(n, cost);
		System.out.println(re);
	}
	class Edge implements Comparable<Edge>{
    	int x, y, val;
    	Edge(int x, int y, int val){
    		this.x = x;
    		this.y = y;
    		this.val = val;
    	}
		@Override
		public int compareTo(Edge arg0) {
			return this.val - arg0.val;
		}
    }
	public int solution(int n, int[][] costs) {
        int answer = 0;
        int []parents = new int[n];
        for(int i = 0; i < n; i++)
        	parents[i] = i;
        ArrayList<Edge> list = new ArrayList<>();
        for(int i = 0; i < costs.length; i++) {
        	int x = costs[i][0];
        	int y = costs[i][1];
        	int val = costs[i][2];
        	list.add(new Edge(x,y,val));
        }
        Collections.sort(list);
        for(Edge e : list) {
        	int parentU = findSetPC(e.x, parents);
        	int parentV = findSetPC(e.y, parents);
        	if(parentU != parentV) {
        		unionJohnie(parentU, parentV, parents);
        		answer += e.val;
        	}
        }
        return answer;
    }
	public int findSetPC(int x, int []parents) {
		while(x != parents[x]) {
			parents[x] = parents[parents[x]];
			x = parents[x];
		}
		return parents[x];
	}
	public void unionJohnie(int x, int y, int []parents) {
		parents[x] = y;
	}
//	public void union(int u, int v, int []parents) {
//		int x = findSetPC(u, parents);
//		int y = findSetPC(v, parents);
//		parents[x] = y;
//	}
}
