package Programmers;

import java.util.HashMap;

public class SkillTree {
	public static void main(String[] args) {
		SkillTree app = new SkillTree();
		app.solve();
	}
	public void solve() {
		String skill = "CBD";
		String[] tree = {"BACDE", "CBADF", "AECB", "BDA"};
		int r = solution(skill, tree);
		System.out.println(r);
	}
	public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Character, Integer> map= new HashMap<>();
        for(int i = 0; i < skill.length(); i++)
    		map.put(skill.charAt(i), i);
        for(String s : skill_trees) {
        	answer++;
        	int possible = 0;
        	for(int i = 0; i < s.length(); i++) {
        		char tmp = s.charAt(i);
        		if(map.containsKey(tmp)) {
        			if(map.get(tmp) > possible) {
        				answer--;
        				break;
        			}
        			else
        				possible++;
        		}
        	}
        }
        return answer;
    }
}
