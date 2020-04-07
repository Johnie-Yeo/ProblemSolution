package Programmers;

import java.util.Arrays;

import Test.Test;

public class Lifeboat {
	public static void main(String[] args) {
		new Lifeboat().solve();
	}
	public void solve() {
		Test test = new Test();
		
		int[] people = {70, 50, 80, 50};
		int limit = 100;
		int expect = 3;
		int result = solution(people, limit);
		test.test(result, expect);
		
		people = new int[] {70, 80, 50};
		limit = 100;
		expect = 3;
		result = solution(people, limit);
		test.test(result, expect);
	}
	public int solution(int[] people, int limit) {
		int answer = 0;
		int length = people.length;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = length-1;
        while(true) {
        	if(start == end) {
        		answer++;
        		break;
        	}else if(start > end) {
        		break;
        	}
        	
        	if(people[start] + people[end] > limit) {
        		end--;
        		answer++;
        	}else {
        		start++;
        		end--;
        		answer++;
        	}
        }
        
        return answer;
    }
}
