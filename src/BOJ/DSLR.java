package BOJ;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

public class DSLR {
	public static void main(String[] args) {
		new DSLR().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int A = kb.nextInt();
			int B = kb.nextInt();
			
			String result = getMinimumCourse(A, B);
			System.out.println(result);
		}
		
		kb.close();
	}
	public class Pair{
		int number;
		String course;
		public Pair(int number, String course) {
			this.number = number;
			this.course = course;
		}
	}
	public String getMinimumCourse(int A, int B) {
		HashSet<Integer> set = new HashSet<>();
		ArrayDeque<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(A, ""));
		set.add(A);
		
		while(!queue.isEmpty()) {
			Pair cur = queue.pop();
			int register = cur.number;
			String Course = cur.course;
			
			int[] nextRegisterSet = new int[4];
			nextRegisterSet[0]= D(register);
			nextRegisterSet[1]= S(register);
			nextRegisterSet[2]= L(register);
			nextRegisterSet[3]= R(register);
			String[] order = {"D", "S", "L", "R"};
			
			for(int i = 0; i < 4; i++) {
				int nextRegister = nextRegisterSet[i];
				if(!set.contains(nextRegister)) {
					if(B == nextRegister) {
						return Course+order[i];
					}else {
						set.add(nextRegister);
						queue.add(new Pair(nextRegister, Course+order[i]));
					}
				}
			}
			
		}
		return null;
	}
	public int D(int num) {
		return num*2%10000;
	}
	public int S(int num) {
		return (num+9999)%10000;
	}
	public int L(int num) {
		if(num < 1000) {
			return num*10;
		}
		return num/1000 + (num%1000)*10;
	}
	public int R(int num) {
		return num/10 + (num%10)*1000;
	}
}
