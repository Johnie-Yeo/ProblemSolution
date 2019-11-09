package Programmers;

import java.util.HashMap;
import java.util.Scanner;

public class Marathon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marathon app = new Marathon();
		app.solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		int a = kb.nextInt();
		int b = kb.nextInt();
		String[] player = new String[a];
		String[] complete = new String[b];
		for(int i = 0; i < a; i++)
			player[i] = kb.next();
		for(int i = 0; i < b; i++)
			complete[i] = kb.next();
		kb.close();
		String result = solution(player, complete);
		System.out.println(result);
	}
	public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer> map = setTable(completion);
        for(String s : participant) {
        	if(!map.containsKey(s))
        		return s;
        	else {
        		int val = map.get(s);
        		map.remove(s);
        		if(val > 1) {
        			map.put(s, val-1);
        		}
        	}
        }
        return answer;
    }
	public HashMap<String,Integer> setTable(String[] player){
        if(player == null) return null;

        HashMap<String, Integer> map = new HashMap<>();

        for(String s: player){
        	if(map.containsKey(s)) {
        		int val = map.get(s);
        		map.remove(s);
        		map.put(s, val+1);
        	}
        	else
        		map.put(s, 1);
        }
        return map; 
    }
}
