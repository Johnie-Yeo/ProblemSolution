package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinimumSpanningTree{
	public class Edge implements Comparable<Edge>{
		int depart;
		int destin;
		int weight;
		
		public Edge(int depart, int destin, int weight) {
			this.depart = depart;
			this.destin = destin;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MinimumSpanningTree().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int V = kb.nextInt();
		int E = kb.nextInt();
		
		ArrayList<Edge> graph = new ArrayList<>();
		for(int i = 0; i < E; i++) {
			int depart = kb.nextInt()-1;
			int destin = kb.nextInt()-1;
			int weight = kb.nextInt();
			
			graph.add(new Edge(depart, destin, weight));
		}
		
		kb.close();
		
		int result = getMSTWeight(graph, V);
		System.out.println(result);
	}
	public void initializeParents(int []parents) {
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	public int getMSTWeight(ArrayList<Edge> graph, int V) {
		int result = 0;
		
		int []parent = new int [V];
		initializeParents(parent);
		
		Collections.sort(graph);
		
		for(Edge cur : graph) {
			int depart = cur.depart;
			int destin = cur.destin;
			int departParent = findSet(parent, depart);
			int destinParent = findSet(parent, destin);
			
			if(departParent != destinParent) {
				union(depart, destin, departParent, destinParent, parent);
				result += cur.weight;
			}
		}
		
		return result;
	}
	public int findSet(int []parent, int node) {
		while(node != parent[node]) {
			parent[node] = parent[parent[node]];
			node = parent[node];
		}
		return node;
	}
	public int countSetHeight(int []parent, int node) {
		int count = 0;
		while(node != parent[node]) {
			node = parent[node];
			count++;
		}
		return count;
	}
	public void union(int depart, int destin, int departParent, int destinParent, int[] parent) {
		int departSetHeight = countSetHeight(parent, depart);
		int destinSetHeight = countSetHeight(parent, destin);
		
		if(departSetHeight < destinSetHeight) {
			parent[departParent] = destinParent;
		}else {
			parent[destinParent] = departParent;
		}
	}
}
