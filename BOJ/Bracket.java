package BOJ;
import java.util.Scanner;

public class Bracket {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String tmp = kb.next();
		kb.close();
		int result = 0;
		int []bracketValue = new int[tmp.length()];
		for(int i = 0; i< tmp.length(); i++) {
			if(tmp.charAt(i) == '(')
				bracketValue[i] = 2;
			else if(tmp.charAt(i) == ')')
				bracketValue[i] = -2;

			else if(tmp.charAt(i) == '[')
				bracketValue[i] = 3;

			else if(tmp.charAt(i) == ']')
				bracketValue[i] = -3;
		}
		if(tmp.length() % 2 == 0) {
			result = getBracketValue(bracketValue, 0, tmp.length()-1);
		}
		System.out.println(result);
	}

	private static int getBracketValue(int[] bracket, int start, int end) {
		int finish = getFinishIndex(bracket, start, end);
		if(finish < 0)
			return 0;
		if(end - start == 1)
			return bracket[start];
		else if(finish == end)
			return bracket[start] * getBracketValue(bracket, start+1, end-1);
		else {
			int a = getBracketValue(bracket, start, finish);
			int b = getBracketValue(bracket, finish + 1, end);
			if(a != 0 && b != 0)
				return a + b;
		}
		return 0;
	}

	private static int getFinishIndex(int[] bracket, int i, int j) {
		if(bracket[i] < 0)
			return -1;
		int result = -1;
		int sum = 0;
		sum += bracket[i++];
		for(; i <= j; i++) {
			sum += bracket[i];
			if(sum == 0) {
				result = i;
				break;
			}
		}
		return result;
	}

}
