package BOJ;
import java.util.Scanner;

public class Scale {
public static void main(String[] args) {
	int []num = new int[8];
	Scanner kb= new Scanner(System.in);
	for(int i = 0; i < 8; i++)
		num[i] = kb.nextInt();
	kb.close();
	String result;
	if(isAscending(num))
		result = "ascending";
	else if(isDescending(num))
		result = "descending";
	else
		result = "mixed";
	System.out.println(result);
}

private static boolean isDescending(int[] num) {
	for(int i = 0; i < 8; i++)
		if(num[i] != 8 - i)
			return false;
	return true;
}

private static boolean isAscending(int[] num) {
	for(int i = 0; i < 8; i++)
		if(num[i] != i + 1)
			return false;
	return true;
}
}
