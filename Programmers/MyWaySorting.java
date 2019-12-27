package Programmers;

import java.util.Arrays;
import java.util.Comparator;

public class MyWaySorting {
	public static void main(String[] args) {
		new MyWaySorting().solve();
	}
	public void solve() {
		String[] str1 = {"sun", "bed", "car"};
		int n = 1;
		String[] result1 = {"car", "bed", "sun"};

		test(str1, n, result1);

		String[] str2 = {"abce", "abcd", "cdx"};
		n = 2;
		String[] result2 = {"abcd", "abce", "cdx"};
		test(str2, n, result2);
	}
	public boolean equals(String[] a, String[] b) {
		if(a.length != b.length) {
			return false;
		}
		for(int i = 0; i < a.length; i++) {
			if(!a[i].equals(b[i])) {
				return false;
			}
		}
		return true;
	}
	public void test(String[] strings, int n, String[] result) {
		if(equals(solution(strings, n), result)) {
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public String[] solution(String[] strings, int n) {
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				int first = arg0.charAt(n) - arg1.charAt(n);
				if(first != 0) {
					return first;
				} else {
					return arg0.compareTo(arg1);
				}
			}
		};
		Arrays.sort(strings, comparator);
		
		return strings;
	}
}
