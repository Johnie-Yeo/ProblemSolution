package Programmers;

import java.util.ArrayList;
import java.util.Collections;


//K��°��
//���� ����
//�迭 array�� i��° ���ں��� j��° ���ڱ��� �ڸ��� �������� ��, k��°�� �ִ� ���� ���Ϸ� �մϴ�.
//
//���� ��� array�� [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3�̶��
//
//array�� 2��°���� 5��°���� �ڸ��� [5, 2, 6, 3]�Դϴ�.
//1���� ���� �迭�� �����ϸ� [2, 3, 5, 6]�Դϴ�.
//2���� ���� �迭�� 3��° ���ڴ� 5�Դϴ�.
//�迭 array, [i, j, k]�� ���ҷ� ���� 2���� �迭 commands�� �Ű������� �־��� ��, commands�� ��� ���ҿ� ���� �ռ� ������ ������ �������� �� ���� ����� �迭�� ��� return �ϵ��� solution �Լ��� �ۼ����ּ���.
//
//���ѻ���
//array�� ���̴� 1 �̻� 100 �����Դϴ�.
//array�� �� ���Ҵ� 1 �̻� 100 �����Դϴ�.
//commands�� ���̴� 1 �̻� 50 �����Դϴ�.
//commands�� �� ���Ҵ� ���̰� 3�Դϴ�.
//����� ��
//array	commands	return
//[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]
//����� �� ����
//[1, 5, 2, 6, 3, 7, 4]�� 2��°���� 5��°���� �ڸ� �� �����մϴ�. [2, 3, 5, 6]�� �� ��° ���ڴ� 5�Դϴ�.
//[1, 5, 2, 6, 3, 7, 4]�� 4��°���� 4��°���� �ڸ� �� �����մϴ�. [6]�� ù ��° ���ڴ� 6�Դϴ�.
//[1, 5, 2, 6, 3, 7, 4]�� 1��°���� 7��°���� �ڸ��ϴ�. [1, 2, 3, 4, 5, 6, 7]�� �� ��° ���ڴ� 3�Դϴ�.

//�� ���� �����ϴ°� �ð����⵵�� ���鿡�� �״��� ���� �˰������� ������ �ʴ´�
//�翬�� �ð��ʰ� ������ �ϰ� ������״µ� ����Ǽ� ��Ȳ������
public class findNumberOnKth {

	public static void main(String[] args) {
		new findNumberOnKth().solve();
	}
	public void solve() {
		int []array = {1, 5, 2, 6, 3, 7, 4};
		int [][]commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		
		int []result = solution(array, commands);
		
		for(int value : result)
			System.out.print(value + " ");
		System.out.println();
	}
	public int[] solution(int[] array, int[][] commands) {
        int size = commands.length;
        int[] answer = new int[size];
        
        for(int i = 0; i < size; i++){
            ArrayList<Integer> tmpArray = new ArrayList<>();
            for(int j = commands[i][0]-1; j < commands[i][1]; j++){
                tmpArray.add(array[j]);
            }
            Collections.sort(tmpArray);
            
            answer[i] = tmpArray.get(commands[i][2]-1);
        }
        
        return answer;
    }
}
