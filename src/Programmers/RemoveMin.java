package Programmers;

public class RemoveMin {
	public static void main(String[] args) {
		new RemoveMin().solve();
	}
	public void solve() {
		int[] input1 = {4,3,2,1};
		int[] result1 = {4,3,2};
		
		test(input1, result1);
		
		int[] input2 =	{10};
		int[] result2 = {-1};
		
		test(input2, result2);
	}
	public boolean equals(int[] a, int[] b) {
		if(a.length != b.length) {
			return false;
		}
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	public void test(int[] input, int[] result) {
		if(equals(solution(input), result)){
			System.out.println("Pass");
		}else {
			System.err.println("Fail");
		}
	}
	public int[] solution(int[] input) {
		int[] result = new int[input.length-1];
		if(input.length == 1) {
			result = new int[1];
			result[0] = -1;
			return result;
		}
		int minIndex = getMinIndex(input);
		
		for(int i = 0; i < input.length; i++) {
			if(i < minIndex) {
				result[i] = input[i];
			}else if(i > minIndex) {
				result[i-1] = input[i];
			}
		}
		return result;
	}
	public int getMinIndex(int[] input) {
		int index = 0;
		for(int i = 1; i < input.length; i++) {
			if(input[index] > input[i]) {
				index = i;
			}
		}
		return index;
	}
}
