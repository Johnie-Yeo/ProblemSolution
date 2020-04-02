package Programmers;

import java.util.ArrayList;

public class GetReversedArray {
	public static void main(String[] args) {
		new GetReversedArray().solve();
	}
	public void solve() {
		long input = 12345;
		int[] result = {5,4,3,2,1};
		
		test(input, result);
	}
	public boolean equals(int[] a, int[] b) {
		if(a.length != b.length) {
			return false;
		}
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	public void test(long input, int[] result) {
		if(equals(solution(input), result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public int[] solution(long input) {
		ArrayList<Integer> list = new ArrayList<>();
		
		while(input > 0) {
			int cur = (int) (input % 10);
			list.add(cur);
			input /= 10;
		}
		
		return toArray(list);
	}
	public int[] toArray(ArrayList<Integer> list) {
		int size = list.size();
		int[] result = new int[size];
		
		for(int i = 0; i < size; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
}
