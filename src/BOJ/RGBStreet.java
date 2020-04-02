package BOJ;
import java.util.Scanner;

public class RGBStreet {
	
	public static void main(String[] args) {
		int [][]house;
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		house = new int[N][3];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < 3; j++)
				house[i][j] = kb.nextInt();
		
		kb.close();
		for(int i = N-2; i >= 0; i--) {
			for(int j = 0; j < 3; j++) {
				int val = Math.min(house[i+1][(j+1)%3], house[i+1][(j+2)%3]);
				house[i][j] += val;
			}
		}
		int min = house[0][0];
		int min2 = Math.min(house[0][1], house[0][2]);
		System.out.print(Math.min(min, min2));
	}

}
