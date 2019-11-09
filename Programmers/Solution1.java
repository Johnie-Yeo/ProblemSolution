package Programmers;

import java.util.Scanner;

public class Solution1 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int []arr= new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = kb.nextInt();
		}
		kb.close();
		int []result = solution(arr);
		for(int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}
	public static int[] solution(int[] arr) {
		int len = arr.length;  
		if(len == 1) {
			int[] answer = {-1};
			return answer;
		}
		int[] answer = new int[len-1];
		int min = getMin(arr);
		int idx = 0;
		for(int i = 0; i < len; i++) {
			if(i == min)
				continue;
			answer[idx++] = arr[i];
		}
		return answer;
	}
	private static int getMin(int[] arr) {
		int min = 0;
		for(int i = 1; i < arr.length; i++) {
			if(arr[min] > arr[i])
				min = i;
		}
		return min;
	}
}
