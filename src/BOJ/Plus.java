package BOJ;
import java.util.Scanner;

public class Plus {
public static void main(String[] args) {
	Scanner kb= new Scanner(System.in);
	int a = kb.nextInt();
	int b= kb.nextInt();
	while(true) {
		if(a == 0 && b == 0)
			break;
		System.out.println(a+b);
		a = kb.nextInt();
		b = kb.nextInt();
	}
	kb.close();
}
}
