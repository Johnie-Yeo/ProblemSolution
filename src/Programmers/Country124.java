package Programmers;

//124 ���� �ֽ��ϴ�. 124 ���󿡼��� 10������ �ƴ� ������ ���� �ڽŵ鸸�� ��Ģ���� ���� ǥ���մϴ�.
//
//124 ���󿡴� �ڿ����� �����մϴ�.
//124 ���󿡴� ��� ���� ǥ���� �� 1, 2, 4�� ����մϴ�.
//���� �� 124 ���󿡼� ����ϴ� ���ڴ� ������ ���� ��ȯ�˴ϴ�.

//���ο� ������ ������ �ؼ��ؼ� Ǯ����
//�׳� ������ ������ ����
public class Country124 {
	public static void main(String[] args) {
		new Country124().solve();
	}
	public void solve() {
		int n = 3;
		String result = solution(n);
		System.out.println(result);
	}
	public String solution(int n) {
	      String answer = "";
	      int digit = numOfDigit(n);
	      int start = findStartNum(digit);
	      int diff = n - start;
	      
	      int []tmpAnswer = new int[digit];
	      for(int i = 0; i < digit; i++) {
	    	  tmpAnswer[i] = 1;
	      }
	      tmpAnswer[0]+=diff;
	      int idx = 0;
	      while(tmpAnswer[idx] > 3) {
	    	  while(tmpAnswer[idx] > 3) {
	    		  tmpAnswer[idx]-=3;
	    		  tmpAnswer[idx+1]++;
	    	  }
	    	  idx++;
	      }
	      for(int i = digit-1; i>= 0; i--) {
	    	  if(tmpAnswer[i] == 3)
	    		  tmpAnswer[i]++;
	    	  answer+=tmpAnswer[i];
	      }
	      return answer;
	}
	public int findStartNum(int digit) {
		int answer = 0;
		for(int i = 0; i < digit; i++)
			answer += Math.pow(3, i);
		return answer;
	}
	public int numOfDigit(int n) {
		int answer = 1;
		while(true) {
			int tmp = (int) Math.pow(3, answer);
			if(tmp >= n)
				break;
			answer++;
			n-=tmp;
		}
		return answer;
	}
}
