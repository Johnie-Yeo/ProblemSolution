package BOJ;
import java.util.Scanner;

public class Test {
public static void main(String[] args) {
	Scanner kb= new Scanner(System.in);
	int n =kb.nextInt();
	kb.close();
	char result;
	if(n >= 90)
		result = 'A';
	else if(n >= 80)
		result = 'B';

	else if(n >= 70)
		result = 'C';

	else if(n >= 60)
		result = 'D';

	else
		result = 'F';
	System.out.println(result);
}
}
