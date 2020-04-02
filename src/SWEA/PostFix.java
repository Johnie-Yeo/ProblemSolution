package SWEA;

import java.util.Scanner;
import java.util.Stack;

public class PostFix {
	public static void main(String[] args) {
		new PostFix().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		for(int i = 1; i <= 10; i++) {
			int N = kb.nextInt();
			String fomula = kb.next();
			int result = solution(N, fomula);
			System.out.println("#"+i+" "+result);
		}
		kb.close();
	}
	public int solution(int n, String f) {
		String postfix = makePostFix(n, f);
		
		Stack<String> stack = new Stack<>();
		for(int i = 0; i < postfix.length(); i++) {
			char cur = postfix.charAt(i);
			
			if(cur >= 48 && cur <= 57)
				stack.push(""+cur);
			else {
				if(cur == '+') {
					int first = Integer.parseInt(stack.pop());
					int second = Integer.parseInt(stack.pop());
					int add = first + second;
					stack.push(""+add);
				}else {
					int first = Integer.parseInt(stack.pop());
					int second = Integer.parseInt(stack.pop());
					int mul = first * second;
					stack.push(""+mul);
				}
			}
		}
		return Integer.parseInt(stack.pop());
	}
	public String makePostFix(int n, String f) {
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			char cur = f.charAt(i);
			
			if(cur >= 48 && cur <= 57)
				sb.append(cur);
			else {
				if(cur == '(') {
					stack.push(cur);
				}else if(cur == ')'){
					while(stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop();
				}else {
					if(stack.isEmpty())
						stack.push(cur);
					else if(cur == '+') {
						while(!stack.isEmpty() && stack.peek() != '(') {
							sb.append(stack.pop());
						}
						stack.push(cur);
					}else {
						while(!stack.isEmpty() && (stack.peek() != '(' && stack.peek() != '+')) {
							sb.append(stack.pop());
						}
						stack.push(cur);
					}
				}
			}
		}
		while(!stack.isEmpty())
			sb.append(stack.pop());
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private String makePostFix(int n, String f) {
//		StringBuilder sb = new StringBuilder();
//		Stack<Character> stack = new Stack<>();
//		for(int i = 0; i < n; i++) {
//			char cur = f.charAt(i);
//			if(cur >= 48 && cur <= 57)
//				sb.append(cur);
//			else if(cur == '(') {
//				stack.push(cur);
//			}else if(cur == ')') {
//				while(stack.peek() != '(') {
//					char tmp = stack.pop();
//					sb.append(tmp);
//				}
//				stack.pop();
//			}else {
//				if(stack.isEmpty())
//					stack.push(cur);
//				else {
//					if(cur == '+' || cur == '-') {
//						while(!stack.isEmpty() && stack.peek() != '(') {
//							sb.append(stack.pop());
//						}
//						stack.push(cur);
//					}else {
//						while(!stack.isEmpty() && !(stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '(')) {
//							sb.append(stack.pop());
//						}
//						stack.push(cur);
//					}
//				}
//			}
//		}
//		while(!stack.isEmpty()) {
//			sb.append(stack.pop());
//		}
//		return sb.toString();
//	}
}
