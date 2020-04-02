package Programmers;

import java.util.ArrayList;

//�Ʒ��� ���� 5�� ��Ģ���길���� 12�� ǥ���� �� �ֽ��ϴ�.
//
//12 = 5 + 5 + (5 / 5) + (5 / 5)
//12 = 55 / 5 + 5 / 5
//12 = (55 + 5) / 5
//
//5�� ����� Ƚ���� ���� 6,5,4 �Դϴ�. �׸��� ���� ���� ���� ���� 4�Դϴ�.
//��ó�� ���� N�� number�� �־��� ��, N�� ��Ģ���길 ����ؼ� ǥ�� �� �� �ִ� ��� �� N ���Ƚ���� �ּڰ��� return �ϵ��� solution �Լ��� �ۼ��ϼ���

//���ڸ��� �� ���� ��ƴ�.
//���۸��ϸ鼭 ������ �����
//��� Ƚ���� ���ü� �ִ� ����� ���� ��� �����ϸ� �Ѿ��.
//���۸��ϸ鼭 ���Ʈ������ Ǭ ����� ���� �ôµ� �ð��ʰ��� �ȳ��� ������ DP��� ���� ��ǻ� ����Ž���� �ʿ��ϱ� �����ε�
//���� ��� ���ڰ� 4�� ���� ����� ���� ���ڸ� 3������ ����� ���� 1�� ���� ����� ��, 2������ ����� �� ������ ��Ģ���� ���ո� �ϼ��� �ָ� �ȴ�.
//1�� 3�� ������ �ʿ� ���� ������ �̹� ���� �����̰ų� ���ڰ� �۾����⸸ �� ���̱� ����.

public class MonodigitalRepresentation {
	public static void main(String[] args) {
		new MonodigitalRepresentation().solve();
	}
	public void solve() {
		int N = 5;
		int number = 31168;
		int result = solution(N, number);
		System.out.println(result);
	}
	public int solution(int N, int number) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int first = 0;
		for(int i = 0; i < 8; i++) {
			first += (Math.pow(10, i)*N);
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(first);

			if(first == number)
				return (i+1);
			if(i > 0) {
				if(dfs(list, i, tmp, N, number))//list���� ���纸�� ���� ������ ������� ��� ����� ���� ���� ������ ����ִ�.
					return (i+1);
			}
			list.add(tmp);
		}
		return -1;
	}
	public boolean dfs(ArrayList<ArrayList<Integer>> list, int i, ArrayList<Integer> tmp, int n, int number) {
		int div1 = i-1;
		int div2 = 0;
		
		while(div1 >= div2) {
			ArrayList<Integer> prev1 = list.get(div1);
			ArrayList<Integer> prev2 = list.get(div2);
			
			for(int first : prev1) {
				for(int second : prev2) {
					int calc = first + second;
					if(calc == number)
						return true;
					else if(calc > 0)
						tmp.add(calc);
					
					calc = first - second;
					if(calc == number)
						return true;
					else if(calc > 0)
						tmp.add(calc);
					
					calc = first * second;
					if(calc == number)
						return true;
					else if(calc > 0)
						tmp.add(calc);
					
					calc = first / second;
					if(calc == number)
						return true;
					else if(calc > 0)
						tmp.add(calc);
				}
			}
			
			div1--;
			div2++;
		}
		return false;
	}
}
