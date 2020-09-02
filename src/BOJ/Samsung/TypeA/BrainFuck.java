package BOJ.Samsung.TypeA;


import Test.OldTest;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class BrainFuck {
	public static void main(String[] args) {
//		new Main().solve();
		new BrainFuck().test();
	}

	private void test() {
		OldTest test = new OldTest();

		int sizeOfMemory, sizeOfCode, sizeOfInput;
		String code, input;
		String result, expect;

		sizeOfMemory = 3;
		sizeOfCode = 6;
		sizeOfInput = 0;
		code = ">>>>,+";
		input = "";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Terminates";
		test.test(result, expect).printResult();

		sizeOfMemory = 10;
		sizeOfCode = 4;
		sizeOfInput = 3;
		code = "+-.,";
		input = "qwe";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Terminates";
		test.test(result, expect).printResult();

		sizeOfMemory = 1000;
		sizeOfCode = 5;
		sizeOfInput = 1;
		code = "+[+-]";
		input = "a";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Loops 1 4";
		test.test(result, expect).printResult();

		sizeOfMemory = 100;
		sizeOfCode = 74;
		sizeOfInput = 4;
		code = "+++++[->++<]>[-<+++++++>]<[->+>+>+>+<<<<]>+++>--->++++++++++>---<<<.>.>.>.";
		input = "xxyz";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Terminates";
		test.test(result, expect).printResult();

		sizeOfMemory = 9999;
		sizeOfCode = 52;
		sizeOfInput = 14;
		code = "+++++[>+++++++++<-],+[-[>--.++>+<<-]>+.->[<.>-]<<,+]";
		input = "this_is_a_test";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Terminates";
		test.test(result, expect).printResult();

		sizeOfMemory = 10;
		sizeOfCode = 14;
		sizeOfInput = 11;
		code = ",,,,,,[[+-]+].";
		input = "hello_world";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Loops 7 10";
		test.test(result, expect).printResult();

		sizeOfMemory = 10;
		sizeOfCode = 9;
		sizeOfInput = 3;
		code = "+[-[><]-]";
		input = "qwe";
		result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
		expect = "Loops 1 8";
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);

		int T = kb.nextInt();
		for(int t = 0; t < T; t++){
			int sizeOfMemory = kb.nextInt();
			int sizeOfCode = kb.nextInt();
			int sizeOfInput = kb.nextInt();

			String code = kb.next();
			String input = kb.next();
			String result = getResultOfProgram(sizeOfMemory, sizeOfCode, sizeOfInput, code, input);
			System.out.println(result);
		}
		kb.close();
	}

	private final int MAX_ORDER_COUNT = 50000000;
	private final int MAX_VALUE = 256;

	private String getResultOfProgram(int sizeOfMemory, int sizeOfCode, int sizeOfInput, String codes, String input) {
		int[] memory = new int[sizeOfMemory];
		int memoryIndex = 0;
		int inputIndex = 0;
		int count = 0;
		HashMap<Integer, Integer> loop = getLoopList(codes, sizeOfCode);
		int max = 0;

		for(int i = 0; i < sizeOfCode; i++){
			max = Math.max(i, max);
			if(count > MAX_ORDER_COUNT){
				return "Loops " + loop.get(max) + " " + max;
			}

			char code = codes.charAt(i);
			switch (code){
				case '-':
					memory[memoryIndex] = (memory[memoryIndex] + MAX_VALUE - 1) % MAX_VALUE;
					break;
				case '+':
					memory[memoryIndex] = (memory[memoryIndex] + 1) % MAX_VALUE;
					break;
				case '<':
					memoryIndex = (memoryIndex + sizeOfMemory - 1) % sizeOfMemory;
					break;
				case '>':
					memoryIndex = (memoryIndex + 1) % sizeOfMemory;
					break;
				case '[':
					if(memory[memoryIndex] == 0){
						i = loop.get(i) - 1;
						continue;
					}
					break;
				case ']':
					if(memory[memoryIndex] != 0){
						i = loop.get(i) - 1;
						continue;
					}
					break;
				case '.':
					break;
				case ',':
					if(inputIndex >=  sizeOfInput){
						memory[memoryIndex] = 255;
					}else{
						char cur = input.charAt(inputIndex++);
						memory[memoryIndex] = cur % MAX_VALUE;
					}
					break;
			}
			count++;
		}
		return "Terminates";
	}

	private HashMap<Integer, Integer> getLoopList(String code, int sizeOfCode){
		HashMap<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack();

		for(int i = 0; i < sizeOfCode; i++){
			char cur = code.charAt(i);
			switch (cur){
				case '[':
					stack.push(i);
					break;
				case ']':
					int open = stack.pop();
					map.put(i, open);
					map.put(open, i);
					break;
			}
		}

		return map;
	}

	private int getCloseBracketIndex(int index, String codes, int size) {
		int value = 0;
		for(int i = index; i < size; i++){
			char cur= codes.charAt(i);
			switch (cur){
				case '[':
					value++;
					break;
				case ']':
					value--;
					if(value == 0){
						return i;
					}
					break;
			}
		}
		return -1;
	}

	private int getOpenBracketIndex(int index, String codes, int size) {
		int value = 0;
		for(int i = index; i >= 0; i--){
			char cur = codes.charAt(i);
			switch(cur){
				case '[':
					value--;
					if(value == 0){
						 return i;
					}
					break;
				case ']':
					value++;
					break;
			}
		}
		return -1;
	}
}