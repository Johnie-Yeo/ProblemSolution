package Programmers;

public class FiveGTower {
	public static void main(String[] args) {
		FiveGTower app = new FiveGTower();
		app.solve();
	}
	public void solve() {
		int N = 16;
		int []station = {9};
		int W = 2;
		int re = solution(N,station,W);
		System.out.println(re);
	}
	public int solution(int n, int[] stations, int w) {
		int answer = 0;
        int e = 1;
        int tmp = 2 * w + 1;
        for(int i = 0; i < stations.length; i++) {
        	int idx = stations[i];
        	int start = idx - w;
        	int end = idx + w;
        	if(start > e) {
        		int gap = (start-1) - e;
        		answer += gap/tmp+1;
        	}
        	e = end+1;
        }
        if(e <= n) {
        	int gap = n - e;
        	answer += gap/tmp+1;
        }
        return answer;
    }
}
