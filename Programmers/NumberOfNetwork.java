package Programmers;


public class NumberOfNetwork {
	public static void main(String[] args) {
		NumberOfNetwork app = new NumberOfNetwork();
		app.solve();
	}
	public void solve() {
		int n = 3;
		int [][]com = {{1,1,0},{1,1,1},{0,1,1}};
		
		int re = solution(n,com);
		System.out.println(re);
	}
	public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean []chk = new boolean[n];
        
        for(int idx = 0; idx < n; idx++) {
        	answer += dfs(computers,n, idx, chk);
        }
        return answer;
    }
	private int dfs(int[][] computers, int n, int idx, boolean[] chk) {
		if(chk[idx])
			return 0;
		chk[idx] = true;
		for(int i = 0; i < n; i++) {
			if(computers[idx][i] == 1 && !chk[i])
				dfs(computers, n, i, chk);
		}
		return 1;
	}
}
