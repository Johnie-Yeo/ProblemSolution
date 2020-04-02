package BOJ;

import java.util.Scanner;

public class IntelligenceTrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		
		int peopleIn = 0;
		int peopleOut = 0;
		int capacity = 0;
		int max = capacity;
		
		for(int i = 0; i < 4; i++) {
			peopleOut = kb.nextInt();
			peopleIn = kb.nextInt();
			capacity += (peopleIn - peopleOut);
			if(max < capacity)
				max = capacity;
		}
		kb.close();

		System.out.println(max);
	}

}
