package BOJ;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GerryMandering {
	public static void main(String[] args) {
		new GerryMandering().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int N = kb.nextInt();
		int []region = new int[N];
		ArrayList<ArrayList<Integer>> neighbor = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			region[i] = kb.nextInt();
		}

		for(int i = 0; i < N; i++) {
			int n = kb.nextInt();
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int j = 0; j < n; j++) {				
				int next = kb.nextInt();
				tmp.add(next-1);
			}
			neighbor.add(tmp);
		}
		
		kb.close();
		
		int result = getMinimumPopulationGap(region, neighbor);
		System.out.println(result);
	}
	public int getMinimumPopulationGap(int[] region, ArrayList<ArrayList<Integer>> neighbor) {
		int numOfSection = getNumberOfSection(neighbor);
		if(numOfSection == 0) {
			return -1;
		}else if(numOfSection == 1) {
			HashSet<Integer> first = new HashSet<>();
			HashSet<Integer> second = new HashSet<>();
			for(int i = 0 ; i < region.length; i++) {
				second.add(i);
			}
			return dfs(region, neighbor, first, second, 0, Integer.MAX_VALUE);
		}else if(numOfSection == 2){
			int result = getPopulationGap(region, neighbor);
			return result;
		}else {//numOfSection > 2
			return -1;
		}
		
	}
	public int dfs(int[] region, ArrayList<ArrayList<Integer>> neighbor, HashSet<Integer> first, HashSet<Integer> second, int cur, int min) {
		if(!(first.size() == 0 || first.size() == region.length)) {
			int firstConnected = getSumOfConnection(region, first, neighbor);
			int secondConnected = getSumOfConnection(region, second, neighbor);
			if(firstConnected > 0 && secondConnected > 0) {				
				int tmp = firstConnected - secondConnected;
				if(tmp < 0) {
					tmp *= -1;
				}
				if(min > tmp) {
					min = tmp;
				}
			}
			
		}
		if(cur >= region.length) {
			return min;
		}
		
		int tmp1 = dfs(region, neighbor, first, second, cur+1, min);
		if(min > tmp1) {
			min = tmp1;
		}
		first.add(cur);
		second.remove(cur);
		
		tmp1 = dfs(region, neighbor, first, second, cur+1, min);
		if(min > tmp1) {
			min = tmp1;
		}
		first.remove(cur);
		second.add(cur);
		
		return min;
	}
	private int getSumOfConnection(int[] region, HashSet<Integer> sets, ArrayList<ArrayList<Integer>> neighbor) {
		int result = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		HashSet<Integer> set = new HashSet<>();
		set.addAll(sets);
		
		Iterator<Integer> iter = set.iterator();
		int cur = iter.next();
		queue.add(cur);
		set.remove(cur);
		
		while(!queue.isEmpty()) {
			cur = queue.pop();
			result += region[cur];
			ArrayList<Integer> nextList = neighbor.get(cur);
			for(int next : nextList) {
				if(set.contains(next)) {
					queue.add(next);
					set.remove(next);
				}
			}
		}
		
		if(!set.isEmpty()) {
			return -1;
		}
		return result;
	}
	public int getNumberOfSection(ArrayList<ArrayList<Integer>> neighbor) {
		int numOfReigion = neighbor.size();
		if(numOfReigion == 0) {
			return numOfReigion;
		}
		boolean[] check = new boolean[numOfReigion];

		int numOfSection = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int cur = 0;
		
		queue.add(cur);
		check[cur] = true;
		while(true) {
			if(queue.isEmpty()) {
				numOfSection++;
				for(int i = 0; i < numOfReigion; i++) {
					if(!check[i]) {
						check[i] = true;
						queue.add(i);
						break;
					}
				}
				if(queue.isEmpty()) {
					break;
				}
			}else {
				cur = queue.pop();
				ArrayList<Integer> nextList = neighbor.get(cur);
				for(int next : nextList) {
					if(!check[next]) {
						queue.add(next);	
						check[next] = true;
					}
				}
			}
		}
		
		return numOfSection;
	}
	public int getPopulationGap(int[] region, ArrayList<ArrayList<Integer>> neighbor) {
		int result = 0;
		
		boolean[] check = new boolean[region.length];
		int cur = 0;
		result += region[cur];
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(cur);
		check[cur] = true;
		
		while(!queue.isEmpty()) {
			cur = queue.pop();
			ArrayList<Integer> nextList = neighbor.get(cur);
			for(int next : nextList) {
				if(!check[next]) {
					queue.add(next);
					result += region[next];
					check[next] = true;
				}
			}
		}
		
		for(int i = 0; i < check.length; i++) {
			if(!check[i]) {
				cur = i;
				break;
			}
		}
		
		queue.add(cur);
		check[cur] = true;
		result -= region[cur];
		
		while(!queue.isEmpty()) {
			cur = queue.pop();
			ArrayList<Integer> nextList = neighbor.get(cur);
			for(int next : nextList) {
				if(!check[next]) {
					queue.add(next);
					result -= region[next];
					check[next] = true;
				}
			}
		}
		
		if(result < 0) {
			result *= -1;
		}
		return result;
	}
	
}
