package BOJ;

import java.util.Scanner;

import Test.OldTest;

public class Advertisement {
	public static void main(String[] args) {
		new Advertisement().test();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int length = kb.nextInt();
		String current = kb.next();
		
		kb.close();
		
		int result = shortestOriginalString(length, current);
		System.out.println(result);
	}
	public void test() {
		OldTest test = new OldTest();
		
		int length = 5;
		String current = "aaaaa";
		int expect = 1;
		int result = shortestOriginalString(length, current);
		test.test(result, expect);
		
		length = 6;
		current = "abaaab";
		expect = 4;
		result = shortestOriginalString(length, current);
		test.test(result, expect);
	}
	public int shortestOriginalString(int length, String ads) {
		char[] adsList = ads.toCharArray();
		
		int []pi = getPI(adsList);
		return length - pi[length-1];
	}
	public int[] getPI(char[] keyword) {
		int j = 0;
	    int length = keyword.length;
	    int[] pi = new int[length];

	    for(int i = 1; i < length; i++){
	        while(j > 0 && keyword[i] != keyword[j]){
	            j = pi[j-1];
	        }
	        if(keyword[i] == keyword[j]){
	            pi[i] = ++j;
	        }
	    }

	    return pi;
	}
}
