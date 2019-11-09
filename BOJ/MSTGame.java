package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MSTGame {
	public class Edge implements Comparable<Edge>{
		int depart, destin;
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
		new MSTGame().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();//number of Vertex
		int M = kb.nextInt();//number of Edge
		int K = kb.nextInt(); // number of turn
		
		ArrayList<Edge> edges = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			int depart = kb.nextInt()-1;
			int destin = kb.nextInt()-1;
			edges.add(new Edge(depart, destin, i+1));
		}
		
		kb.close();
		
		String result = getScores(N,K, edges);
		System.out.println(result);
	}
	public String getScores(int N, int K, ArrayList<Edge> edges) {
		StringBuilder result = new StringBuilder();
		
		
		for(int i = 0; i < K; i++) {
			
			int[] parent = new int[N];
			for(int j = 0; j < N; j++) {
				parent[j] = j; 
			}
			
			int removedEdgeWeight = buildMST(parent, edges, N);
			result.append(removedEdgeWeight+" ");
			if(removedEdgeWeight == 0) {
				for(;i<K-1;i++) {
					result.append(0+" ");
				}
				break;
			}
		}
		String scores = result.toString();
		scores = scores.substring(0, scores.length()-1);
		return scores;
	}
	public boolean isMST(int[] parent) {
		boolean check = false;
		int length = parent.length;
		for(int i = 0; i < length; i++) {
			if(parent[i] == i) {
				if(check) {
					return false;
				}
				check = true;
			}
		}
		return true;
	}
	private int buildMST(int[] parent, ArrayList<Edge> edges, int n) {
		Collections.sort(edges);
		boolean check = false;
		Edge min = null;
		int result = 0;
		
		for(Edge edge : edges) {
			int depart = edge.depart;
			int destin = edge.destin;
			int departParent = find(depart, parent);
			int destinParent = find(destin, parent);
			
			if(departParent != destinParent) {
				result += edge.weight;
				union(departParent, destinParent, parent);
				if(!check) {
					min = edge;
					check = true;
				}
			}
		}
		
		if(isMST(parent)) {
			remove(edges, min);
			return result;
		}else {
			return 0;
		}
	}
	public void remove(ArrayList<Edge> edges, Edge min) {
		int index = 0;
		for(Edge edge : edges) {
			if(edge.weight == min.weight) {
				edges.remove(index);
				return;
			}
			index++;
		}
//		return result;
	}
	public void union(int u, int v, int[] parent) {
		parent[u] = v;
	}
	public int find(int depart, int[] parent) {
		while(depart != parent[depart]) {
			depart = parent[depart];
			parent[depart] = parent[parent[depart]];
		}
		return depart;
	}
}
