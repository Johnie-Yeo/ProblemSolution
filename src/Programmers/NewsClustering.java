package Programmers;

import Test.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class NewsClustering {
	public static void main(String[] args) {
		new NewsClustering().test();
	}
	public void test() {
		Test test = new Test();

		String str1, str2;
		int expect, result;

		str1   = "FRANCE";
		str2   = "french";
		expect = 16384;
		result = solution(str1, str2);
		test.test(result, expect).printResult();

		str1   = "handshake";
		str2   = "shake hands";
		expect = 65536;
		result = solution(str1, str2);
		test.test(result, expect).printResult();

		str1   = "aa1+aa2";
		str2   = "AAAA12";
		expect = 43690;
		result = solution(str1, str2);
		test.test(result, expect).printResult();

		str1   = "E=M*C^2";
		str2   = "e=m*c^2";
		expect = 65536;
		result = solution(str1, str2);
		test.test(result, expect).printResult();
	}
	public int solution(String str1, String str2) {
		ArrayList<String> str1ElemList = getElemList(str1);
		ArrayList<String> str2ElemList = getElemList(str2);

		int intersectionSize = getIntersectionSize(str1ElemList, str2ElemList);
		int unionSize = getUnionSize(str1ElemList, str2ElemList, intersectionSize);

		double jaccard = getJaccard(intersectionSize, unionSize);
		jaccard *= 65536;
		int result = (int)jaccard;
		return result;
	}

	private double getJaccard(int intersectionSize, int unionSize) {
		if(intersectionSize == 0 && unionSize == 0){
			return 1;
		}
		return (double)intersectionSize / unionSize;
	}

	private int getUnionSize(ArrayList<String> list1, ArrayList<String> list2, int intersectionSize) {
		int count = list1.size() + list2.size() - intersectionSize;
		return count;
	}

	private int getIntersectionSize(ArrayList<String> list1, ArrayList<String> list2) {
		ArrayList<String> clone = new ArrayList(list1);
		ArrayList<String> intersection = new ArrayList<>();
		for(String elem : list2){
			if(clone.contains(elem)){
				clone.remove(elem);
				intersection.add(elem);
			}
		}
		int result = intersection.size();
		return result;
	}

	private ArrayList<String> getElemList(String str) {
		String term = "";
		ArrayList<String> list = new ArrayList<>();

		for(char c : str.toCharArray()){
			if(isWord(c)){
				term += c;
			}else{
				term = "";
			}
			if(term.length() == 2){
				list.add(term.toUpperCase());
				term = "" + c;
			}
		}
		return list;
	}

	private boolean isWord(char c){
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}
}
