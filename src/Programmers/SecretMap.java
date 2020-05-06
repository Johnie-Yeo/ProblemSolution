package Programmers;

import Test.OldestTest;

public class SecretMap {
	public static void main(String[] args) {
		new SecretMap().solve();
	}
	public void solve() {
		OldestTest test = new OldestTest();
		int n = 5;
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		String[] result = {"#####","# # #", "### #", "#  ##", "#####"};
		test.test(solution(n, arr1, arr2), result);

		n = 6;
		arr1 = new int[] {46, 33, 33 ,22, 31, 50};
		arr2 = new int[]{27 ,56, 19, 14, 14, 10};
		result = new String[]{"######", "###  #", "##  ##", " #### ", " #####", "### # "};
		test.test(solution(n, arr1, arr2), result);
	}
	public String[] solution(int n, int[] arr1, int[] arr2) {
		int len = arr1.length;
		String[] answer = new String[len];
		
		int max = 0;
		for(int i = 0; i < len; i++) {
			String added = toMap(arr1[i] | arr2[i]);
			answer[i] = added;
			if(max < added.length()) {
				max = added.length();
			}
		}
		answer = buildStream(answer, max);
		return answer;
	}
	public String[] buildStream(String[] answer, int max) {
		int len = answer.length;
		
		for(int i = 0; i < len; i++) {
			int diff = max - answer[i].length();
			if(diff > 0) {
				for(int j = 0; j < diff; j++) {
					answer[i] = " "+answer[i];
				}
			}
		}
		return answer;
	}
	public String toMap(int num) {
		String bin = Integer.toBinaryString(num);
		int len = bin.length();
		String result = "";
		for(int i = 0; i < len; i++) {
			if(bin.charAt(i) == '1') {
				result += "#";
			}else {
				result += " ";
			}
		}
		return result;
	}
}
