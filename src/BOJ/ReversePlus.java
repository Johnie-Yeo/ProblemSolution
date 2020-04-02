package BOJ;
import java.util.Scanner;

public class ReversePlus {
public static void main(String[] args) {
	Scanner kb = new Scanner(System.in);
	
	int x = kb.nextInt();
	int y= kb.nextInt();
	kb.close();
	int revX = reverse(x);
	int revY = reverse(y);
	System.out.println(reverse(revX+revY));
}
public static int reverse(int x) {
	String str = Integer.toString(x);
	String result = "";
	for(int i = 0; i< str.length(); i++)
		result += str.charAt(str.length()-i-1);
	return Integer.parseInt(result);
}
}
