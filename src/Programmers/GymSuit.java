package Programmers;

import java.util.Arrays;

//ü����
//���� ����
//���ɽð��� ������ ���, �Ϻ� �л��� ü������ �������߽��ϴ�. ������ ���� ü������ �ִ� �л��� �̵鿡�� ü������ �����ַ� �մϴ�. �л����� ��ȣ�� ü�� ������ �Ű��� �־�, �ٷ� �չ�ȣ�� �л��̳� �ٷ� �޹�ȣ�� �л����Ը� ü������ ������ �� �ֽ��ϴ�. ���� ���, 4�� �л��� 3�� �л��̳� 5�� �л����Ը� ü������ ������ �� �ֽ��ϴ�. ü������ ������ ������ ���� �� ���� ������ ü������ ������ ���� �ִ��� ���� �л��� ü�������� ���� �մϴ�.
//
//��ü �л��� �� n, ü������ �������� �л����� ��ȣ�� ��� �迭 lost, ������ ü������ ������ �л����� ��ȣ�� ��� �迭 reserve�� �Ű������� �־��� ��, ü�������� ���� �� �ִ� �л��� �ִ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.
//
//���ѻ���
//��ü �л��� ���� 2�� �̻� 30�� �����Դϴ�.
//ü������ �������� �л��� ���� 1�� �̻� n�� �����̰� �ߺ��Ǵ� ��ȣ�� �����ϴ�.
//������ ü������ ������ �л��� ���� 1�� �̻� n�� �����̰� �ߺ��Ǵ� ��ȣ�� �����ϴ�.
//���� ü������ �ִ� �л��� �ٸ� �л����� ü������ ������ �� �ֽ��ϴ�.
//���� ü������ ������ �л��� ü������ ���������� �� �ֽ��ϴ�. �̶� �� �л��� ü������ �ϳ��� �������ߴٰ� �����ϸ�, ���� ü������ �ϳ��̱⿡ �ٸ� �л����Դ� ü������ ������ �� �����ϴ�.
//����� ��
//n	lost	reserve	return
//5	[2, 4]	[1, 3, 5]	5
//5	[2, 4]	[3]	4
//3	[3]	[1]	2
//����� �� ����
//���� #1
//1�� �л��� 2�� �л����� ü������ �����ְ�, 3�� �л��̳� 5�� �л��� 4�� �л����� ü������ �����ָ� �л� 5���� ü�������� ���� �� �ֽ��ϴ�.
//
//���� #2
//3�� �л��� 2�� �л��̳� 4�� �л����� ü������ �����ָ� �л� 4���� ü�������� ���� �� �ֽ��ϴ�.

//�׸��� �˰����� ����ϱ� ���ؼ� �־��� �迭�� ���ĵ� ���¿��߸� ��
//���� ���ο����� �ִµ� �������� ��츦 üũ���ְ�
//�翷�� �������� �ִ��� ���������� ã�ƺ��� ��
public class GymSuit {
	public static void main(String[] args) {
		new GymSuit().solve();
	}
	public void solve() {
		int n = 5;
		int []lost = {2,4};
		int []reserve = {1,3,5};
		
		int result = solution(n, lost, reserve);
		System.out.println(result);
	}
	public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        int start = 0;//reserve�� index
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        int index = 0;
        for(int student : lost){
            int check = doesSomeoneselfHaveExtra(student, start, reserve);
            if(check >= 0){
                answer++;
                reserve[check] = 0;
                lost[index] = 0;
                start = check+1;
            }
            index++;
        }
        start = 0;
        for(int student : lost){
            int check = doesNeighborHaveExtra(student, start, reserve);
            if(check >= 0){
                answer++;
                start = check+1;
            }    
        }
        
        return answer;
    }
    public int doesSomeoneselfHaveExtra(int index, int start, int[] reserve){
        int size = reserve.length;
        
        while(start < size){
            if(reserve[start] == index){
                return start;
            }else{
                start++;
            }
        }
        return -1;
    }
    public int doesNeighborHaveExtra(int index, int start, int[] reserve){
        int prev = index - 1;
        int next = index + 1;
        int size = reserve.length;
        
        while(start < size){
            if(reserve[start] == 0){
                start++;
                continue;
            }
            if(reserve[start] == prev || reserve[start] == next){
                return start;
            }else{
                start++;
            }
        }
        return -1;
    }
}
