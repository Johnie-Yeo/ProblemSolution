package BOJ;
import java.util.Scanner;

public class SugarDeliver {
public static void main(String[] args) {
	Scanner kb= new Scanner(System.in);
	int n = kb.nextInt();
	kb.close();
	
	int tmp = n / 5;
	int tmpM = n % 5;
	int result = tmp;
	while(true) {
		if(result < 0)
			break;
		else if(tmpM % 3 == 0) {
			result+=(tmpM/3);
			break;
		}
		else {
			result--;
			tmpM += 5;
		}
	}
	System.out.println(result);
}
}
