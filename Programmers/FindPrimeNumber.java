package Programmers;

//�Ҽ� ã��
//���� ����
//���ڸ� ���ڰ� ���� ���� ������ ������ֽ��ϴ�. ����� ���� ������ �ٿ� �Ҽ��� �� �� ���� �� �ִ��� �˾Ƴ��� �մϴ�.
//
//�� ���� ������ ���� ���ڰ� ���� ���ڿ� numbers�� �־����� ��, ���� �������� ���� �� �ִ� �Ҽ��� �� ������ return �ϵ��� solution �Լ��� �ϼ����ּ���.
//
//���ѻ���
//numbers�� ���� 1 �̻� 7 ������ ���ڿ��Դϴ�.
//numbers�� 0~9���� ���ڸ����� �̷���� �ֽ��ϴ�.
//013�� 0, 1, 3 ���ڰ� ���� ���� ������ ������ִٴ� �ǹ��Դϴ�.
//����� ��
//numbers	return
//17	3
//011	2
//����� �� ����
//���� #1
//[1, 7]���δ� �Ҽ� [7, 17, 71]�� ���� �� �ֽ��ϴ�.
//
//���� #2
//[0, 1, 1]���δ� �Ҽ� [11, 101]�� ���� �� �ֽ��ϴ�.
//
//11�� 011�� ���� ���ڷ� ����մϴ�.

public class FindPrimeNumber {
	public static void main(String[] args) {
		new FindPrimeNumber().solve();
	}
	public void solve() {
		String number = "17";
		
		int result = solution(number);
		System.out.println(result);
	}
	public int solution(String number) {
		return findPrime(0, number);//������ �����ٲ��ϱ� 0���� ����
	}
	public int findPrime(int cur, String number) {
		int result = 0;
		int len = number.length();
		boolean[] chk = new boolean[10];
		if(isPrime(cur)) {
			result++;
		}
		for(int i = 0; i < len; i++) {
			int tmp = number.charAt(i)-'0';//�� �ڸ����� ���� �˻�
			int next = cur*10 + tmp;
			
			if(next == 0) {
				continue;
			}else if(chk[tmp]) {//��� �θ�� �ٸ� �����̹Ƿ� �ڽĿ����� �ٸ����ڷ� Ȯ�����ָ� ��
				continue;
			}else {
				chk[tmp] = true;
			}
			
			result += findPrime(next, number.substring(0, i)+number.substring(i+1));
		}
		return result;
	}
	public boolean isPrime(int number) {
		if(number < 2) {
			return false;
		}
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
