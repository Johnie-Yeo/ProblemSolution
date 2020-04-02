package Programmers;

import java.util.Arrays;
import java.util.Comparator;

//0 �Ǵ� ���� ������ �־����� ��, ������ �̾� �ٿ� ���� �� �ִ� ���� ū ���� �˾Ƴ� �ּ���.
//
//���� ���, �־��� ������ [6, 10, 2]��� [6102, 6210, 1062, 1026, 2610, 2106]�� ���� �� �ְ�, ���� ���� ū ���� 6210�Դϴ�.
//
//0 �Ǵ� ���� ������ ��� �迭 numbers�� �Ű������� �־��� ��, ������ ���ġ�Ͽ� ���� �� �ִ� ���� ū ���� ���ڿ��� �ٲپ� return �ϵ��� solution �Լ��� �ۼ����ּ���.

//ù��° ���ڰ� ū ������� ���;� �Ѵ�.
public class TheBigggesNumber {
	public static void main(String[] args) {
		new TheBigggesNumber().solve();
	}
	public void solve() {
		int[] numbers = {0,0,0,0,0};
		String result = solution(numbers);
		System.out.println(result);
	}
	public String solution(int[] numbers) {
		String answer = "";
		
		String[] list = new String[numbers.length];
		for(int i= 0; i < numbers.length; i++)
			list[i] = Integer.toString(numbers[i]);
		Arrays.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o2+o1).compareTo(o1+o2);
			}
			
		});
		
		for(String tmp : list)
			answer += tmp;
		if(answer.charAt(0) == '0')
			return "0";
		return answer;
	}
}
