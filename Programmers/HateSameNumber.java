package Programmers;

import java.util.ArrayList;

public class HateSameNumber {
	public static void main(String[] args) {
		new HateSameNumber().solve();
	}
	public void solve() {
		int []arr = {1,1,3,3,0,1,1};
		int []result = solution(arr);
		System.out.println(result);
	}
	public int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int last = 0;
        for(int cur : arr) {
        	if(list.isEmpty()) {
        		list.add(cur);
        		last = cur;
        	}else {
        		if(last != cur) {
        			list.add(cur);
        			last = cur;
        		}
        	}
        }
        
        int []answer = new int[list.size()];
        int idx = 0;
        for(int cur : list) {
        	answer[idx++] = cur;
        }
        return answer;
	}
}
