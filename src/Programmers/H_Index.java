package Programmers;

import Test.Test;

public class H_Index {
	public static void main(String[] args) {
		new H_Index().solve();
	}
	public void solve() {
		Test test = new Test();
		
		int[] citations = {3, 0, 6, 1, 5};
		int expect = 3;
		int result = solution(citations);
		test.test(result, expect);
		
		citations = new int[]{4,3,3,3,3};
		expect = 3;
		result = solution(citations);
		test.test(result, expect);
		
		citations = new int[]{4,4,4,4,3,3,3,3};
		expect = 4;
		result = solution(citations);
		test.test(result, expect);
		
		citations = new int[]{5,3,3,3,3};
		expect = 3;
		result = solution(citations);
		test.test(result, expect);
		
		citations = new int[]{0,0,0,0};
		expect = 0;
		result = solution(citations);
		test.test(result, expect);
		
		citations = new int[]{22, 42};
		expect = 2;
		result = solution(citations);
		test.test(result, expect);
	}
	public int solution(int[] citations) {
        int min = 0;
        int max = citations.length;
        int middle = (min+max) / 2;
		
        while(max - min > 1) {
        	int result = isHIndex(middle, citations);
            if(result == 0) {
            	return middle;
            }else if(result > 0) {
            	//could increase but possible
            	min = middle;
            	int tmp = (middle + max) / 2;
            	if(tmp == middle) {
            		middle = tmp + 1;
            	}else {
            		middle = tmp;
            	}
            }else if(result < 0){
            	max = middle;
            	int tmp = (min + middle)/2; 
            	if(tmp == middle) {
            		middle = tmp-1;
            	}else {            		
            		middle = tmp;
            	}
            }
        }
        int result = isHIndex(max, citations);
        if(result >= 0) {
        	return max;
        }else if(result < 1) {
        	return min;
        }
        return -1;
    }
	public int isHIndex(int index, int[] citations) {
		int count = 0;
		for(int paper : citations) {
			if(paper >= index) {
				count++;
			}
			if(count > index) {
				return 1;
			}
		}
		if(count == index) {
			return 0;
		}else {
			return -1;	
		}
	}
}
