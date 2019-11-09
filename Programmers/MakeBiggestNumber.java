package Programmers;

import java.util.ArrayList;
import java.util.Collections;

//ū�������

//���� ����
//� ���ڿ��� k���� ���� �������� �� ���� �� �ִ� ���� ū ���ڸ� ���Ϸ� �մϴ�.
//
//���� ���, ���� 1924���� �� �� ���� �����ϸ� [19, 12, 14, 92, 94, 24] �� ���� �� �ֽ��ϴ�. �� �� ���� ū ���ڴ� 94 �Դϴ�.
//
//���ڿ� �������� ���� number�� ������ ���� ���� k�� solution �Լ��� �Ű������� �־����ϴ�. number���� k ���� ���� �������� �� ���� �� �ִ� �� �� ���� ū ���ڸ� ���ڿ� ���·� return �ϵ��� solution �Լ��� �ϼ��ϼ���.
//
//���� ����
//number�� 1�ڸ� �̻�, 1,000,000�ڸ� ������ �����Դϴ�.
//k�� 1 �̻� number�� �ڸ��� �̸��� �ڿ����Դϴ�.
//����� ��
//number	k	return
//1924	2	94
//1231234	3	3234
//4177252841	4	775841


public class MakeBiggestNumber {
	public static void main(String[] args) {
		new MakeBiggestNumber().solve();
	}
	public void solve() {
		String number = "1924";
		int k = 2;
		String result = solution(number, k);
		System.out.println(result);
	}
	public String solution(String number, int k) {
        class Pair implements Comparable<Pair>{
        	int idx, val;
        	public Pair(int idx, int val) {
        		this.idx = idx;
        		this.val = val;
        	}
			@Override
			public int compareTo(Pair o) {
				int result = o.val - this.val;
				if(result != 0)
					return result;
				else 
					return this.idx - o.idx;
			}
        }
		String answer = "";
        int len = number.length();
        int start = -1;//������ ������ ����
        ArrayList<Pair> list = new ArrayList<>();
        
        for(int i = 0; i < len; i++)
        	list.add(new Pair(i, number.charAt(i) - 48));
        Collections.sort(list);
        
        while(true) {
        	int index = -1;
        	for(Pair p : list) {
        		index++;
        		int numOfThrowOut = p.idx - start - 1;
        		if(numOfThrowOut > k) // �ʹ� ���� ������ �Ѵٸ�
        			continue;
        		else if(p.idx < start) // �̹� ���� �κ��̶��
        			continue;
        		else if(len - start == k + 1) {//���� ������ k���̸� ��
        			return answer;
        		}
        		else {
        			answer += p.val;
        			k -= numOfThrowOut;
        			start = p.idx;
        			list.remove(index);
        			break;
        		}
        	}//ù��° �ڸ��� �ִ����� ����
        	
        	if(k == 0) {
        		answer += number.substring(start+1);
        		break;
        	}
        }
        return answer;
    }
}
