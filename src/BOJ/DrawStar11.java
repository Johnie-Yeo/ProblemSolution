package BOJ;
import java.util.Scanner;

public class DrawStar11 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		kb.close();
		int k = N / 3;
		int gen = 0;
		while(k != 1) {
			gen++;
			k /= 2;
		}
		StringBuilder[] sb = new StringBuilder[N];
		for(int i = 0; i < N; i++)
			sb[i] = new StringBuilder();
		sb[0].append("  *   ");
		sb[1].append(" * *  ");
		sb[2].append("***** ");
		buildTree(sb, gen);
	}
	public static void buildTree(StringBuilder[] sb, int gen) {
		for(int i = 1; i <= gen; i++)
			builderNGenerationTree(sb, i);
		drawMap(sb);
	}

	public static void builderNGenerationTree(StringBuilder[] sb, int gen) {
		int prevHeight = 3 * (int)Math.pow(2, gen - 1);
		int curHeight = prevHeight * 2;
		StringBuilder tmp = new StringBuilder();
		for(int i = 0; i < prevHeight; i ++)
			tmp.append(" ");
		for(int i = prevHeight; i < curHeight; i++) {
			sb[i].append(sb[i - prevHeight]);
			sb[i].append(sb[i - prevHeight]);
			sb[i - prevHeight].insert(0, tmp);
			sb[i - prevHeight].append(tmp);
		}
	}
	
	public static void drawMap(StringBuilder[] sb) {
		for(int i = 0; i < sb.length; i++) 
			System.out.println(sb[i]);
	}

}
