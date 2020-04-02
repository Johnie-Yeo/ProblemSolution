package SWEA;

import java.util.Scanner;

public class PostBox {
	public static void main(String[] args) {
		new PostBox().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		int T = kb.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = kb.nextInt();
			int A = kb.nextInt();
			int B = kb.nextInt();
			
			int []letters = new int[N];
			for(int n = 0; n < N; n++) {
				letters[n] = kb.nextInt();
			}
			
			int []result = getCheckTime(letters, N, A, B);
			
			System.out.println("#"+t+" "+toString(result));
		}
		
		kb.close();
	}
	int []getCheckTime(int []letters, int N, int A, int B){
		int []result = new int[N];
		int time = 0;
		int postBox = 0;
		int n = 0;
		int idx = 0;
		int earliest = letters[0];
		
		while(true) {
			if(n < N && letters[n] == time) {
				n++;
				postBox++;
			}
			
			if(postBox >= A || time-earliest >= B) {
				int read = (postBox+1)/2;//���� ��
				
				for(int i = idx; i < (idx+read); i++) {
					result[i] = time;
					postBox--;
				}
				
				idx += read;
				if(idx == N) {
					break;
				}
				earliest = letters[idx];
			}
			
			time++;
		}
		
		return result;
	}
	String toString(int []arr) {
		String result = "";
		int len = arr.length;
		
		for(int i = 0; i < len-1; i++) {
			result+=(arr[i]+" ");
		}
		result += arr[len-1];
		
		return result;
	}
}
