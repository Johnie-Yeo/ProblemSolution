package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Test.OldestTest;

public class Search {
	public static void main(String[] args) throws IOException {
		new Search().test();
	}
	public void solve() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String T = br.readLine();
		String P = br.readLine();
		
		char[] context = T.toCharArray();
	    char[] keyword = P.toCharArray();
		ArrayList<Integer> indexes = kmp(context, keyword);
		System.out.println(indexes.size());
		for(int index : indexes) {
			System.out.println(index);
		}
	}
	public int[] toArray(ArrayList<Integer> list) {
		int[] result = new int[list.size()];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	public void test() {
		OldestTest test = new OldestTest();
		
		String T = "ABC ABCDAB ABCDABCDABDE";
		String P = "ABCDABD";
		char[] context = T.toCharArray();
	    char[] keyword = P.toCharArray();
	    int[] expect = {16};
		ArrayList<Integer> result = kmp(context, keyword);
		test.test(toArray(result), expect);
		
		P = "abababa";
		int[] pi = getPI(P.toCharArray());
		for(int value : pi) {
			System.out.println(value);
		}
	}
	public int[] getPI(char[] keyword){
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
	};

	public ArrayList<Integer> kmp(char[] context, char[] keyword){
	    int[] pi = getPI(keyword);
	    ArrayList<Integer> indexes = new ArrayList<>();
	    
	    int keywordIndex = 0;
	    int keywordLength = keyword.length;
	    int contextLength = context.length;

	    for(int contextIndex = 0; contextIndex < contextLength; contextIndex++){
	        while(keywordIndex > 0 && 
	        	  context[contextIndex] != keyword[keywordIndex]){
	            keywordIndex = pi[keywordIndex-1];
	        }
	        if(context[contextIndex] == keyword[keywordIndex]){
	            if(keywordIndex == keywordLength-1){
	                indexes.add(contextIndex - keywordIndex + 1);
	                keywordIndex = pi[keywordIndex];
	            }else{
	                keywordIndex++;
	            }
	        }
	    }

	    return indexes;
	};
}
