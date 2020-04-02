package BOJ;
import java.util.Scanner;

public class Z {
	static int value = 0;
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int r = kb.nextInt();
		int c = kb.nextInt();
		kb.close();
		recursiveZ(N, 0, 0, r, c);
		System.out.println(value);
	}
	public static int recursiveZ(int N, int i, int j, int r, int c) {
		int half = (int)Math.pow(2, N-1);
		if(i == r && j == c)
			return value;
		if(N == 0) {
			value++;
			return 0;
		}
		if(Math.abs(i - r) > half*2 || Math.abs(j - c) > half*2) {
			value += (int)Math.pow(half * 2,2);
			return 0;
		}
		else {
			if(
			recursiveZ(N-1, i, j, r, c) != 0 ||
			recursiveZ(N-1, i, j + half, r, c) != 0 ||
			recursiveZ(N-1, i + half, j, r, c) != 0 ||
			recursiveZ(N-1, i + half, j + half, r, c) != 0
			)
				return value;
		}
		return 0;
	}

}
