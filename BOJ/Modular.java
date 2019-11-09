package BOJ;
import java.util.Scanner;

public class Modular {
public static void main(String[] args) {
	Scanner kb = new Scanner(System.in);
	int a = kb.nextInt();
	int b = kb.nextInt();
	int c = kb.nextInt();
	kb.close();
	
	int result1 = (a + b) % c;
	int result2 = (a % c + b % c) %c;
	int result3 = (a * b) % c;
	System.out.println(result1);
	System.out.println(result2);
	System.out.println(result3);
	System.out.println(result3);
}
}
