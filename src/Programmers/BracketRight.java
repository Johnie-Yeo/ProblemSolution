package Programmers;
import java.util.ArrayList;
import java.util.Scanner;

public class BracketRight {
	public static void main(String[] args) {
		BracketRight app = new BracketRight();
		app.solve();
	}

	private void solve() {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		kb.close();
		String[] result = solution(n);
		System.out.println(result);
	}

	public String[] solution(int n) {
		ArrayList<String> result = new ArrayList<>();
		String curBra = "(";
		dfs(result,curBra, 0, 1, 0, n);
		String []list = new String[result.size()];
		for(int i = 0; i < result.size(); i++)
			list[i] = result.get(i);
		return list;
	}

	public void dfs(ArrayList<String> result, String curBra, int idx, int open, int close, int n) {
		if(close == n) {
			result.add(curBra);
			return;
		}
		else if(open == n) {
			String tmp="";
			for(int i = 0; i < open-close; i++)
				tmp += ")";
			curBra+=tmp;
			result.add(curBra);
			return;
		}
		String cur = curBra;		
		
		cur += "(";
		open++;
		dfs(result,cur,idx,open,close,n);
		open--;
		if(open > close) {
			idx++;
			close++;
			curBra +=")";
			dfs(result,curBra,idx,open,close,n);
		}
	}
}
