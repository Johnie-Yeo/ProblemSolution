package Programmers;

import java.util.HashMap;

import Test.Test;

public class Camouflage {//위장
	public static void main(String[] args) {
		new Camouflage().solve();
	}
	public void solve() {
		Test<Integer> test = new Test<Integer>();
		
		String[][] clothes = {
				{"yellow_hat", "headgear"}, 
				{"blue_sunglasses", "eyewear"}, 
				{"green_turban", "headgear"}
			};
		int expect = 5;
		int result = solution(clothes);
		test.test(result, expect);
		
		clothes = new String[][] {
			{"crow_mask", "face"}, 
			{"blue_sunglasses", "face"}, 
			{"smoky_makeup", "face"}
		};
		expect = 3;
		result = solution(clothes);
		test.test(result, expect);
		
		clothes = new String[][] {
			{"crow_mask", "face"}, 
			{"blue_sunglasses", "face"}, 
			{"smoky_makeup", "face"},
			{"yellow_hat", "headgear"}, 
			{"green_turban", "headgear"},
			{"blue_sunglasses", "eyewear"}
		};
		expect = 23;
		result = solution(clothes);
		test.test(result, expect);
	}
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes) {
        	int cur = 0;
        	if(map.containsKey(cloth[1])) {
        		cur = map.get(cloth[1]);
        	}
        	map.put(cloth[1], cur+1);
        }
        
    	int answer = 1;
        
    	for(String key : map.keySet()) {
        	answer *= (map.get(key)+1);
        }
        return answer-1;
    }
}
