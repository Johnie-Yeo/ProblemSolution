package Programmers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

//Ʈ�� ���� �밡 ���� ���������� �� ���� �ٸ��� ������ ������ �ǳʷ� �մϴ�. ��� Ʈ���� �ٸ��� �ǳʷ��� �ּ� �� �ʰ� �ɸ����� �˾Ƴ��� �մϴ�. Ʈ���� 1�ʿ� 1��ŭ �����̸�, �ٸ� ���̴� bridge_length�̰� �ٸ��� ���� weight���� �ߵ��ϴ�.
//�� Ʈ���� �ٸ��� ������ ������ ���� ���, �� Ʈ���� ���Դ� ������� �ʽ��ϴ�.
//
//���� ���, ���̰� 2�̰� 10kg ���Ը� �ߵ�� �ٸ��� �ֽ��ϴ�. ���԰� [7, 4, 5, 6]kg�� Ʈ���� ������� �ִ� �ð� �ȿ� �ٸ��� �ǳʷ��� ������ ���� �ǳʾ� �մϴ�.

//�ٸ��� ���̰� 2�� ��� ������ ���°� 3�̻��� �Ǿ�� �ٸ��� ������ ���̴�.
//���������� ť�� ������ �ȴ�.

public class TruckOnBridge {
	public static void main(String[] args) {
		new TruckOnBridge().solve();
	}
	public void solve() {
		int length = 2;
		int weight = 10;
		int[] trucks = {7,4,5,6};

		int result = solution(length, weight, trucks);
		System.out.println(result);
	}
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		class Pair{
			int idx;
			int status;
			Pair(int idx, int status){
				this.idx = idx;
				this.status = status;
			}
			void move(){
				this.status++;
			}
		}
		int time = 0;
		int num = truck_weights.length;
		int currentWeightOnBridge = 0;
		int cur = 0;
		Deque<Pair> queue = new ArrayDeque<>();
		queue.addLast(new Pair(cur, 1));
		time++;
		currentWeightOnBridge += truck_weights[cur];
		cur++;
		
		while(true) {
			//�����غ���
			Iterator<Pair> iter = queue.iterator();
			while(iter.hasNext()) {
				iter.next().move();
			}
			if(queue.peek().status > bridge_length) {
				Pair tmp = queue.pop();
				currentWeightOnBridge -= truck_weights[tmp.idx];
				if(tmp.idx == num -1) {
					time++;
					break;
				}
			}
			//���� �ö�ü� �ִ°�?
			if(cur < num && currentWeightOnBridge + truck_weights[cur] <= weight) {
				currentWeightOnBridge += truck_weights[cur];
				queue.addLast(new Pair(cur, 1));
				cur++;
			}
			
			time++;
		}
		
		return time;
	}
}
