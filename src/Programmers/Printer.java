package Programmers;

import java.util.ArrayList;

//������
//���� ����
//�Ϲ����� �����ʹ� �μ� ��û�� ���� ������� �μ��մϴ�. �׷��� ������ �߿��� ������ ���߿� �μ�� �� �ֽ��ϴ�. �̷� ������ �����ϱ� ���� �߿䵵�� ���� ������ ���� �μ��ϴ� �����͸� �����߽��ϴ�. �� ���Ӱ� ������ �����ʹ� �Ʒ��� ���� ������� �μ� �۾��� �����մϴ�.
//
//1. �μ� ������� ���� �տ� �ִ� ����(J)�� ����Ͽ��� �����ϴ�.
//2. ������ �μ� ����Ͽ��� J���� �߿䵵�� ���� ������ �� ���� �����ϸ� J�� ������� ���� �������� �ֽ��ϴ�.
//3. �׷��� ������ J�� �μ��մϴ�.
//���� ���, 4���� ����(A, B, C, D)�� ������� �μ� ����Ͽ� �ְ� �߿䵵�� 2 1 3 2 ��� C D A B ������ �μ��ϰ� �˴ϴ�.
//
//���� �μ⸦ ��û�� ������ �� ��°�� �μ�Ǵ��� �˰� �ͽ��ϴ�. ���� ������ C�� 1��°��, A�� 3��°�� �μ�˴ϴ�.
//
//���� ����Ͽ� �ִ� ������ �߿䵵�� ������� ��� �迭 priorities�� ���� �μ⸦ ��û�� ������ ���� ������� � ��ġ�� �ִ����� �˷��ִ� location�� �Ű������� �־��� ��, ���� �μ⸦ ��û�� ������ �� ��°�� �μ�Ǵ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.
//
//���ѻ���
//���� ����Ͽ��� 1�� �̻� 100�� ������ ������ �ֽ��ϴ�.
//�μ� �۾��� �߿䵵�� 1~9�� ǥ���ϸ� ���ڰ� Ŭ���� �߿��ϴٴ� ���Դϴ�.
//location�� 0 �̻� (���� ����Ͽ� �ִ� �۾� �� - 1) ������ ���� ������ ������� ���� �տ� ������ 0, �� ��°�� ������ 1�� ǥ���մϴ�.
//����� ��
//priorities	location	return
//[2, 1, 3, 2]	2	1
//[1, 1, 9, 1, 1, 1]	0	5
//����� �� ����
//���� #1
//
//������ ���� ���� �����ϴ�.
//
//���� #2
//
//6���� ����(A, B, C, D, E, F)�� �μ� ����Ͽ� �ְ� �߿䵵�� 1 1 9 1 1 1 �̹Ƿ� C D E F A B ������ �μ��մϴ�.
public class Printer {
	public static void main(String[] args) {
		new Printer().solve();
	}
	public void solve() {
		int []arr = {1, 1, 9, 1, 1, 1};
		int n = 0;
		int result = solution(arr, n);
		System.out.println(result);
	}
	public class Pair{
		int index;
		int value;
		public Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayList<Pair> list = new ArrayList<>();
        
        int len = priorities.length;
        
        for(int i = 0; i < len; i++) {
        	list.add(new Pair(i, priorities[i]));
        }
        
        while(true) {
        	while(list.get(0).value != biggest(list)) {
        		Pair tmp = list.remove(0);
        		list.add(tmp);
        	}
        	Pair print = list.remove(0);
        	answer++;
        	if(print.index == location) {
        		break;
        	}
        }
        
        return answer;
    }
	public int biggest(ArrayList<Pair>  list) {
		int max = 0;
		
		for(Pair cur : list) {
			if(cur.value > max) {
				max = cur.value;
			}
			
		}
		
		return max;
	}
}
