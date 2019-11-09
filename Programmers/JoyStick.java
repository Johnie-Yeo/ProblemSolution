package Programmers;

//���̽�ƽ
//���� ����
//���̽�ƽ���� ���ĺ� �̸��� �ϼ��ϼ���. �� ó���� A�θ� �̷���� �ֽ��ϴ�.
//ex) �ϼ��ؾ� �ϴ� �̸��� �� ���ڸ� AAA, �� ���ڸ� AAAA
//
//���̽�ƽ�� �� �������� �����̸� �Ʒ��� �����ϴ�.
//
//�� - ���� ���ĺ�
//�� - ���� ���ĺ� (A���� �Ʒ������� �̵��ϸ� Z��)
//�� - Ŀ���� �������� �̵� (ù ��° ��ġ���� �������� �̵��ϸ� ������ ���ڿ� Ŀ��)
//�� - Ŀ���� ���������� �̵�
//���� ��� �Ʒ��� ������� JAZ�� ���� �� �ֽ��ϴ�.
//
//- ù ��° ��ġ���� ���̽�ƽ�� ���� 9�� �����Ͽ� J�� �ϼ��մϴ�.
//- ���̽�ƽ�� �������� 1�� �����Ͽ� Ŀ���� ������ ���� ��ġ�� �̵���ŵ�ϴ�.
//- ������ ��ġ���� ���̽�ƽ�� �Ʒ��� 1�� �����Ͽ� Z�� �ϼ��մϴ�.
//���� 11�� �̵����� "JAZ"�� ���� �� �ְ�, �̶��� �ּ� �̵��Դϴ�.
//������� �ϴ� �̸� name�� �Ű������� �־��� ��, �̸��� ���� ���̽�ƽ ���� Ƚ���� �ּڰ��� return �ϵ��� solution �Լ��� ���弼��.
//
//���� ����
//name�� ���ĺ� �빮�ڷθ� �̷���� �ֽ��ϴ�.
//name�� ���̴� 1 �̻� 20 �����Դϴ�.
//����� ��
//name	return
//JEROEN	56
//JAN	23

//�ణ �߸ŷ� Ǯ����
//�⺻�����δ� ������� ������ �纸�� ���� A�� ���ڰ� ������ ���ư�����
public class JoyStick {
	public static void main(String[] args) {
		new JoyStick().solve();
	}
	public void solve() {
		String name = "AAAAABAAAAAAAAAA";
		int result = solution(name);

		System.out.println(result);
	}
	public int solution(String name) {
		int answer = 0;
		int size = name.length();

		for(int i = 0; i < size; i++){
			answer += moveToWord(name.charAt(i));
		}

		int maxALength = 0;
		int count = 0; //�������°�
		int index = 0;
		int dir = 1;

		int first = getALength(name, 0, dir);
		if(first == size)
			return 0;
		else if(first > 0) {
			int back = getALength(name, size-1, dir*-1);
			if(back >= first) { // ������ ����
				index = first;
				count += (first-1);
				maxALength = back;
			}else { //�ݴ�� ����
				maxALength = first;
				dir *= -1;
				count += back;
				index = size - (back+1);
			}
		}

		while(count < size - maxALength){//���� A�� ���� ������ ������ �ʿ䰡 ����
			if(name.charAt(index) == 'A'){
				int aLength = getALength(name, index, dir);

				if(aLength > maxALength && aLength > count - 1){ //A�� ���̰� ������� �� ���̺��� ����
					dir *= -1;
					maxALength = aLength;
					index = (index + size + dir)%size;
					count--;
				}else{
					count += aLength;
					index += (dir * aLength);
				}
			}else{
				count++;
				index = (index + size + dir)%size;
			}
		}
		return answer + count;
	}
	public int getALength(String name, int index, int dir){
		int size = name.length();
		int count = 0;
		while(index >= 0 && index < size && name.charAt(index) == 'A'){
			count++;
			index += dir;
		}

		return count;
	}
	public int moveToWord(char word){
		int count = 0;
		int a = 'A';

		while(a != word){
			a = a+1;
			count++;
		}
		if(count > 13){
			count = 26 - count;
		}

		return count;
	}
}
