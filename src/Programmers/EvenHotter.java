package Programmers;

import java.util.PriorityQueue;

//���� ����
//�ſ� ���� �����ϴ� Leo�� ��� ������ ���ں� ������ K �̻����� ����� �ͽ��ϴ�. ��� ������ ���ں� ������ K �̻����� ����� ���� Leo�� ���ں� ������ ���� ���� �� ���� ������ �Ʒ��� ���� Ư���� ������� ���� ���ο� ������ ����ϴ�.
//
//���� ������ ���ں� ���� = ���� ���� ���� ������ ���ں� ���� + (�� ��°�� ���� ���� ������ ���ں� ���� * 2)
//Leo�� ��� ������ ���ں� ������ K �̻��� �� ������ �ݺ��Ͽ� �����ϴ�.
//Leo�� ���� ������ ���ں� ������ ���� �迭 scoville�� ���ϴ� ���ں� ���� K�� �־��� ��, ��� ������ ���ں� ������ K �̻����� ����� ���� ����� �ϴ� �ּ� Ƚ���� return �ϵ��� solution �Լ��� �ۼ����ּ���.
//
//���� ����
//scoville�� ���̴� 1 �̻� 1,000,000 �����Դϴ�.
//K�� 0 �̻� 1,000,000,000 �����Դϴ�.
//scoville�� ���Ҵ� ���� 0 �̻� 1,000,000 �����Դϴ�.
//��� ������ ���ں� ������ K �̻����� ���� �� ���� ��쿡�� -1�� return �մϴ�.
//����� ��
//scoville	K	return
//[1, 2, 3, 9, 10, 12]	7	2
//����� �� ����
//���ں� ������ 1�� ���İ� 2�� ������ ������ ������ ���ں� ������ �Ʒ��� ���� �˴ϴ�.
//���ο� ������ ���ں� ���� = 1 + (2 * 2) = 5
//���� ������ ���ں� ���� = [5, 3, 9, 10, 12]
//
//���ں� ������ 3�� ���İ� 5�� ������ ������ ������ ���ں� ������ �Ʒ��� ���� �˴ϴ�.
//���ο� ������ ���ں� ���� = 3 + (5 * 2) = 13
//���� ������ ���ں� ���� = [13, 9, 10, 12]
//
//��� ������ ���ں� ������ 7 �̻��� �Ǿ��� �̶� ���� Ƚ���� 2ȸ�Դϴ�.

//min heap�� �̿��ϸ� �ּڰ��� log n �ð��� ���� �� �ִ�.

public class EvenHotter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EvenHotter().solve();
	}
	public void solve() {
		int []scoville = {1, 2, 3, 9, 10, 12};
		int K = 27;
		
		int result = solution(scoville, K);
		System.out.println(result);
	}
	public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int cur : scoville) {
        	pq.add(cur);
        }
        
        int n = pq.size();
        int min = pq.poll();
        n--;
        
        while(min < K) {
        	if(n < 1) {
        		return -1;
        	}
        	int tmp = pq.poll();
        	tmp *= 2;
        	tmp += min;
        	pq.add(tmp);
        	min = pq.poll();
        	n--;
        	answer++;
        }
        
        return answer;
    }
}
