package Programmers;

import java.util.Stack;

import Test.OldestTest;

public class DartGame {
	public final char STANDARD_POINT = 'S';
	public final char DOUBLE_POINT = 'D';
	public final char TRIPLE_POINT = 'T';
	public final char STAR = '*';
	public final char ACHA = '#';
	
	public final char CHAR_NULL = '\u0000';
	
	public static void main(String[] args) {
		new DartGame().solve();
	}
	public void solve() {
		OldestTest test = new OldestTest();
		
		String input = "1S2D*3T";
		int expect = 37;//1^1 * 2 + 2^2 * 2 + 3^3
		int result = solution(input);
		test.test(result, expect);
		
		input = "1D2S#10S";
		expect = 9;	//1^2 + 2^1 * (-1) + 10^1
		result = solution(input);
		test.test(result, expect);
		
		input = "1D2S0T";
		expect = 3;	//1^2 + 2^1 + 0^3
		result = solution(input);
		test.test(result, expect);
		
		input = "1S*2T*3S";
		expect = 23;//1^1 * 2 * 2 + 2^3 * 2 + 3^1
		result = solution(input);
		test.test(result, expect);
		
		input = "1D#2S*3S";
		expect = 5;	//1^2 * (-1) * 2 + 2^1 * 2 + 3^1
		result = solution(input);
		test.test(result, expect);
		
		input = "1T2D3D#";
		expect = -4;	//1^3 + 2^2 + 3^2 * (-1)
		result = solution(input);
		test.test(result, expect);
		
		input = "1D2S3T*";
		expect = 59;	//1^2 + 2^1 * 2 + 3^3 * 2
		result = solution(input);
		test.test(result, expect);
	}
	public class Turn{
		int score;
		char bonus;
		char price;
		public Turn(int score, char bonus, char price) {
			this.score = score;
			this.bonus = bonus;
			this.price = price;
		}
	}
	public int solution(String dartResult) {
		Turn[] eachTurnResult = split(dartResult);
		Stack<Integer> stack = new Stack<>();
		
		for(Turn turn : eachTurnResult) {
			int cur = turn.score;
			cur = getBonus(cur, turn.bonus);
			if(turn.price == STAR) {
				if(!stack.isEmpty()) {
					int prev = stack.pop();
					prev *= 2;
					stack.push(prev);
				}
				cur *= 2;
			}else if(turn.price == ACHA) {
				cur *= -1;
			}
			stack.push(cur);
		}
		
		int answer = getScore(stack);
		return answer;
	}
	public int getScore(Stack<Integer> stack) {
		int sum = 0;
		for(int cur : stack) {
			sum += cur;
		}
		return sum;
	}
	public int getBonus(int score, char bonus) {
		if(bonus == STANDARD_POINT) {
			return score;
		}else if(bonus == DOUBLE_POINT) {
			return score*score;
		}else if(bonus == TRIPLE_POINT) {
			return score*score*score;
		}
		return 0;
	}
	public Turn[] split(String dartResult) {
		char[] list = dartResult.toCharArray();
		Turn[] result = new Turn[3];
		int score = 0;
		char bonus = CHAR_NULL, price=CHAR_NULL;
		int index = 0;
		
		for(char element : list) {
			if(element >= '0' && element <= '9') {
				if(bonus != CHAR_NULL) {
					result[index++] = new Turn(score, bonus, price);
					bonus = CHAR_NULL; price=CHAR_NULL;
					score = element-48;
				}else if(bonus == CHAR_NULL && score == 1 && element == '0') {
					score = 10;
				}else {					
					score = element-48;
				}
			}else if(element == STANDARD_POINT || element == DOUBLE_POINT || element == TRIPLE_POINT){
				bonus = element;
			}else if(element == STAR || element == ACHA) {
				price = element;
			}
		}
		
		result[index] = new Turn(score, bonus, price);
		
		return result;
	}
 }
