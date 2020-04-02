package BOJ;

import java.util.Scanner;
import java.util.Stack;

public class BrainFuck {
	public static void main(String[] args) {
		new BrainFuck().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int memorySize = kb.nextInt();
			int codeSize = kb.nextInt();
			int inputSize = kb.nextInt();
			
			char[] code = new char[codeSize];
			String brainCode = kb.next();
			for(int i = 0; i < codeSize; i++) {
				code[i] = brainCode.charAt(i);
			}
			
			String inputCode = kb.next();
			char[] input = new char[inputSize];
			for(int i = 0; i < inputSize; i++) {
				input[i] = inputCode.charAt(i);
			}
			
			int[] loop = getLoopList(code);
			String result = getResultOfCode(memorySize, loop, code, input);
			System.out.println(result);
		}
		
		kb.close();
	}
	private String getResultOfCode(int memorySize, int[] loop, char[] code, char[] input) {
		int[] memory = new int[memorySize];
		int memoryIndex = 0;
		int inputIndex = 0;
		int numOfOrder = 0;
		int max = 0;
		
		for(int i = 0; i < code.length; i++) {
			numOfOrder++;

			max = Math.max(max, i);
			if(numOfOrder >= 50000000) {
				return "Loops "+loop[max]+" "+max;
			}
			
			if(code[i] == '-') {
				memory[memoryIndex] = (memory[memoryIndex]+256-1)%256;
			}else if(code[i] == '+') {
				memory[memoryIndex] = (memory[memoryIndex]+1)%256;
			}else if(code[i] == '<') {
				memoryIndex = (memoryIndex+memorySize-1)%memorySize;
			}else if(code[i] == '>') {
				memoryIndex = (memoryIndex+1)%memorySize;
			}else if(code[i] == '[') {
				if(memory[memoryIndex] == 0) {
					i = loop[i]-1;
				}
			}else if(code[i] == ']') {
				if(memory[memoryIndex] != 0) {
					i = loop[i]-1;
				}
			}else if(code[i] == '.') {
				//print memory[memoryIndex]
			}else if(code[i] == ',') {
				if(inputIndex >= input.length) {
					memory[memoryIndex] = 255;
				}else {					
					memory[memoryIndex] = input[inputIndex++];
				}
			}
		}
		return "Terminates";
	}
	private int[] getLoopList(char[] code) {
		int []loop = new int[code.length];
		Stack<Integer> bracket = new Stack<>();
		
		for(int i = 0; i < code.length; i++) {
			if(code[i] == '[') {
				bracket.add(i);
			}else if(code[i] == ']') {
				int open = bracket.pop();
				loop[open] = i;
				loop[i] = open;
			}
		}
		return loop;
	}
}