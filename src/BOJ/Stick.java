package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class Stick {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int original = 64;
		int X = kb.nextInt();
		kb.close();
		int sum = original;
		ArrayList<Integer> val = new ArrayList<>();
		val.add(original);
		int num = 0;
		
		while(sum > X) {
			int tmp = val.remove(num);
			sum -= tmp;
			val.add(tmp/2);
			val.add(tmp/2);
			num++;
			if(sum+tmp/2 >= X) {
				val.remove(num);
				num--;
				sum -= tmp/2;
			}
			sum += tmp;
		}
		
		System.out.println(num+1);
	}
}
