package Programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class BeginTarget {
	public static void main(String[] args) {
		new BeginTarget().solve();
	}
	public void solve() {
		String begin = "hit";
		String target = "cog";
		String []words = {"hot", "dot", "dog", "lot", "log", "cog"};

		int re = solution(begin, target, words);
		System.out.println(re);
	}
	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		if(target.equals(begin) || !doesExist(words, target))
			return answer;

		int sizeOfGraph = words.length+1;
		ArrayList<String> list = new ArrayList<>();
		list.add(begin);
		for(String w : words)
			list.add(w);
		int [][]graph = new int[sizeOfGraph][sizeOfGraph];
		makeGraph(list, graph, sizeOfGraph);

		class Pair{
			int idx;
			int num;
			Pair(int idx, int num){
				this.idx = idx;
				this.num = num;
			}
		}
		ArrayDeque<Pair> deque = new ArrayDeque<>();
		deque.add(new Pair(0, 0));
		while(!deque.isEmpty()) {
			Pair tmp = deque.removeFirst();
			for(int i = 1; i < sizeOfGraph; i++) {
				if(graph[tmp.idx][i] == 1) {
					if(list.get(i).equals(target)) {
						return tmp.num+1;
					}
					graph[tmp.idx][i] = 0;
					graph[i][tmp.idx] = 0;
					deque.addLast(new Pair(i, tmp.num+1));
				}
			}
		}

		return answer;
	}
	private void makeGraph(ArrayList<String> list, int[][] graph, int sizeOfGraph) {
		for(int i = 0; i < sizeOfGraph; i++) {
			for(int j = i+1; j < sizeOfGraph; j++) {
				if(isNeighbor(list.get(i), list.get(j))) {
					graph[i][j] = 1;
					graph[j][i] = 1;
				}		
			}
		}
	}
	private boolean isNeighbor(String s1, String s2) {
		int length = s1.length();
		boolean chk = false;
		for(int i = 0; i < length; i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				if(chk) {
					return false;
				}
				chk = true;
			}

		}
		return true;
	}
	public boolean doesExist(String[] words, String target) {
		for(String comp : words) {
			if(comp.equals(target))
				return true;
		}
		return false;
	}
}
