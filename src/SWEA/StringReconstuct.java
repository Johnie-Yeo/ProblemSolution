package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StringReconstuct {
	// SwExperts5949
	// �ٸ����� index�� ������ ����Ʈ�� ����
	// ť ������ ��
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Test = Integer.parseInt(br.readLine());
		for(int t = 1; t <= Test; t++) {
			String S = br.readLine();
			String T = br.readLine();
			int len = S.length();
			ArrayList<Integer> listA = new ArrayList<>();
			ArrayList<Integer> listB = new ArrayList<>();
			long result = 0;
			boolean change = false;
			for(int i = 0; i < len; i++) {
				char cur = S.charAt(i);
				if(cur == 'a' && !listB.isEmpty()) {
					int idx = listB.remove(0);
					change = true;
					result += (i - idx);
				}
				else if(cur == 'b' && !listA.isEmpty()) {
					int idx = listA.remove(0);
					change = true;
					result += (i - idx);
				}
				if(change) {
					if(cur == 'a')
						cur = 'b';
					else
						cur = 'a';
				}
				if(cur != T.charAt(i)) {
					if(cur == 'a')
						listA.add(i);
					else
						listB.add(i);
				}
				change = false;
			}
			if(!(listA.isEmpty()&&listB.isEmpty()))
				result = -1;
			System.out.println("#"+t+" "+result);
		}
	}

}
