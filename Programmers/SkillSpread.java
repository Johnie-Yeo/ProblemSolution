package Programmers;

import java.util.ArrayList;

public class SkillSpread {
	public static void main(String[] args) {
		SkillSpread app = new SkillSpread();
		app.solve();
	}
	public void solve() {
		int[] progress = {93,30,55};
		int[] speed = {1,30,5};
		
		int[] result = solution(progress, speed);
		System.out.println(result);
	}
	public int[] solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> answer = new ArrayList<>();
		int size = speeds.length;
		int cur = 0;
		int result = 0;
		while(true) {
			boolean isfin = false;
			for(int i = 0; i < size; i++) {
				progresses[i] += speeds[i];
			}
			if(progresses[cur] >= 100) {
				cur++;
				result++;
				if(cur >= size) {
					answer.add(result);
					break;
				}
				while(progresses[cur] >= 100) {
					cur++;
					result++;
					if(cur >= size) {
						answer.add(result);
						isfin =true;
						break;
					}
				}
				if(!isfin) {
					answer.add(result);
					result = 0;
				}
				if(cur >= size)
					break;
			}
		}
		int []a = new int[answer.size()];
		for(int i = 0; i < answer.size(); i++)
			a[i] = answer.get(i);
		return a;
	}
}
