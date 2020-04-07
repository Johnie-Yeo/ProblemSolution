package Programmers;

//���� ���̰� 2�̰� ������ ���̰� 1�� ���簢������� Ÿ���� �ֽ��ϴ�. �� ���簢�� Ÿ���� �̿��Ͽ� ������ ���̰� 2�̰� ������ ���̰� n�� �ٴ��� ���� ä����� �մϴ�. Ÿ���� ä�� ���� ������ ���� 2���� ����� �ֽ��ϴ�.
//
//Ÿ���� ���η� ��ġ �ϴ� ���
//Ÿ���� ���η� ��ġ �ϴ� ���
//������ n�� 7�� ���簢���� ������ ���� ä�� �� �ֽ��ϴ�.
//
//���簢���� ������ ���� n�� �Ű������� �־��� ��, �� ���簢���� ä��� ����� ���� return �ϴ� solution �Լ��� �ϼ����ּ���.

//�Ǻ���ġ�� ���¸� ������ �ۿ� ���� ������ �����Ҷ� ���η� ������ ���η� ������ 2���� ����� ���� ������ �� ���η� ���� ����� ���� ���̰� 1���� ���簢���� ä�� �� �ִ�
//����� ���� ���� ���η� ���� ���� ���̰� 2 �������� ���� ����

public class Tiling {
	public static void main(String[] args) {
		new Tiling().solve();
	}
	public void solve() {
		int n = 100;
		int result = solution(n);
		System.out.println(result);
	}
	public int solution(int n) {
		int[] data = new int[n];
		data[0] = 1;
		data[1] = 2;
		
		for(int i = 2; i < n; i++) {
			data[i] = (data[i-1] + data[i-2])%1000000007;
		}
		
		return data[n-1];
	}
}