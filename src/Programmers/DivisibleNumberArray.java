package Programmers;

import java.util.ArrayList;
import java.util.Collections;

public class DivisibleNumberArray {
	public static void main(String[] args) {
		new DivisibleNumberArray().solve();
	}
	public void solve() {
		int [][] arr = {
				{5, 9, 7, 10},
				{2, 36, 1, 3},
				{3,2,6}
		};
		int [] divisor = {5,1,10};
		int [][] result = {{5,10},{1,2,3,36},{-1}};

		for(int i = 0; i < arr.length; i++) {
			int[] answer = solution(arr[i], divisor[i]);
			for(int j = 0; j < result[i].length; j++) {
				try {
					if(answer[j] != result[i][j]) {
						System.err.println("Fail");
						return;
					}	
				}catch(Exception e) {
					System.err.println("Fail");
					return;
				}
			}
			System.out.println("Pass");
		}
	}
	public int[] solution(int[] arr, int divisor) {
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % divisor == 0) {
				list.add(arr[i]);
			}
		}
		
		if(list.size() == 0) {
			int [] result = {-1};
			return result;
		}
		Collections.sort(list);
		
		int[] result = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return 	result;
	}
}
