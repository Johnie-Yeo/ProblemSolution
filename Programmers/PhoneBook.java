package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PhoneBook {
	public static void main(String[] args) {
		PhoneBook app = new PhoneBook();
		app.solve();
	}
	public void solve() {
		String[] phone = {"123","456","789"};
		boolean r = solution(phone);
		System.out.println(r);
	}
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        ArrayList<String> list = new ArrayList<>();
        for(String s : phone_book)
        	list.add(s);
        Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() > s2.length())
					return 1;
				else if(s1.length() < s2.length())
					return -1;
				return 0;
			}
		});
        for(int i = 0; i < phone_book.length-1; i++) {
        	for(int j = i+1; j < phone_book.length; j++) {
        		if(isInclude(list.get(i),list.get(j))) {
        			return false;
        		}
        	}
        }
        return answer;
    }
	private boolean isInclude(String string, String string2) {
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) != string2.charAt(i))
				return false;
		}
		return true;
	}
}
