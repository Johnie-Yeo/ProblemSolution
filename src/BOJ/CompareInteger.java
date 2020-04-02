package BOJ;
import java.util.Arrays;
import java.util.Scanner;

public class CompareInteger {
public static void main(String[] args) {
	Scanner kb = new Scanner(System.in);
	int []arr = new int[3];
	for(int i = 0; i < 3; i++)
		arr[i] = kb.nextInt();
	kb.close();
	Arrays.sort(arr);
	System.out.println(arr[1]);
}
}
