package Programmers;

//�����ڴ� ������ ������ ����� �ظ��Դϴ�. ������ ���ι��� ���ǰ�翡 ���� ������ ���� ������ �մϴ�. �����ڴ� 1�� �������� ������ �������� ������ ���� ����ϴ�.
//
//1�� �����ڰ� ��� ���: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
//2�� �����ڰ� ��� ���: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
//3�� �����ڰ� ��� ���: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
//
//1�� �������� ������ ���������� ������ ������� ���� �迭 answers�� �־����� ��, ���� ���� ������ ���� ����� �������� �迭�� ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.

//������ ����� ���Ʈ���� �����̴�.
//���� ��ο� ���� ������ ����ϸ� ��
public class ExampleTest {
	public static void main(String[] args) {
		new ExampleTest().solve();
	}
	public void solve() {
		int[] answer = {1,3,2,4,2};
		int[] result = solution(answer);
		System.out.println(result);
	}
	public int[] solution(int[] answers) {
        int[] answer = new int[3];
        
        int[] first  = {1,2,3,4,5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third  = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int firstScore  = getScore(first, answers);
        int secondScore = getScore(second, answers);
        int thirdScore  = getScore(third, answers);
        
        int max = 0;
        if(firstScore  > max)
        	max = firstScore;
        if(secondScore > max)
        	max = secondScore;
        if(thirdScore  > max)
        	max = thirdScore;
        
        int idx = 0;
        if(firstScore  == max)
        	answer[idx++] = 1; 
        if(secondScore == max)
        	answer[idx++] = 2;
        if(thirdScore  == max)
        	answer[idx++] = 3;
        
        int[] result = new int[idx];
        for(int i = 0; i < idx; i++)
        	result[i] = answer[i];
        return result;
    }
	private int getScore(int[] pick, int[] answers) {
		int correct = 0;
		int size = pick.length;
		for(int i = 0; i < answers.length; i++) {
			if(pick[i%size] == answers[i])
				correct++;
		}
		return correct;
	}
}
