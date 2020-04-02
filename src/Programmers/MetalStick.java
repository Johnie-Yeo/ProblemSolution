package Programmers;

import java.util.Stack;

public class MetalStick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MetalStick().solve();
	}
	public void solve() {
		String arrangement = "()(((()())(())()))(())";
		
		int result = solution(arrangement);
		System.out.println(result);
	}
	public int solution(String arrangement) {
        int count = 0;
        int len = arrangement.length();
        Stack<Character> stack = new Stack<>();
        char prev = ' ';
        for(int i = 0; i < len; i++) {
        	char cur = arrangement.charAt(i);
        	
        	if(cur == '(') {
        		stack.push(cur);
        	}else if(cur == ')') {
        		stack.pop();
        		if(prev == '(') {//it means it's a razor
        			count += stack.size();
        		}else if(prev == ')') {
        			count++;
        		}
        	}
        	
        	prev = cur;
        }
        
        return count;
    }
}
