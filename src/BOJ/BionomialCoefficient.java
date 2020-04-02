package BOJ;
import java.util.Scanner;

public class BionomialCoefficient {
public static void main(String[] args) {
	Scanner kb= new Scanner(System.in);
	
	int n = kb.nextInt();
	int k = kb.nextInt();
	kb.close();
	
	System.out.println(factorial(n) / (factorial(k) * factorial(n-k)));
}
public static int factorial(int a) {
	if(a <= 1)
		return 1;
	return a*factorial(a-1);
}
}
