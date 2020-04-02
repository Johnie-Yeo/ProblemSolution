package Programmers;

import java.util.ArrayList;
import java.util.Collections;

//���� Ŭ�����͸�
//���� ��л翡�� ������� ����, Ư�� �Ӻ��� ������ ���� �������� ������ ��簡 ���� ���� �ʿ��� ��縦 ã�Ⱑ ��ƴ�. Daum ������ ���� ������ �ð� �� ���Ի�� Ʃ��� ����ڵ��� ���ϰ� �پ��� ������ ã�ƺ� �� �ֵ��� �������� �����ϴ� ������ �ð� �Ǿ���.
//
//������ ������ ��� ���� Ʃ��� �켱 �ֱ� ȭ���� �ǰ� �ִ� īī�� ���� ������ ��ä ���� ��縦 �˻��غ��Ҵ�.
//
//īī�� ù ��ä..'����ε�' ��� ä��
//īī��, �պ� �� ù ��ä.. ����ε� �������� ������ ä��
//īī��, ����ε� �������� ���� ������ ��ä
//īī�� ��ä, ���� ������ �ڵ� �ɷ¸� ����
//īī��, ���� ��ä.. �ڵ� �Ƿ¸� ����
//īī�� �ڵ� �ɷ¸����� 2018 ���� ������ �̴´�
//����� ������ �������� ����ε� ������ �ָ��ϴ� ���� �ڵ� �׽�Ʈ�� �ָ��ϴ� ���� ������ �� �߰��ߴ�. Ʃ��� �̵��� ���� ��� �����ָ� īī�� ��ä ���� ��縦 ã�ƺ��� ����ڿ��� ������ ��;���.
//
//������ ��縦 ���� ������ ���ϱ� ���ؼ� ���� �ڷḦ �����ϴ� Ʃ��� ��ī�� ���絵��� ����� ã�Ƴ´�.
//
//��ī�� ���絵�� ���� ���� ���絵�� �˻��ϴ� ���� ��� ���� �ϳ��� �˷��� �ִ�. �� ���� A, B ������ ��ī�� ���絵 J(A, B)�� �� ������ ������ ũ�⸦ �� ������ ������ ũ��� ���� ������ ���ǵȴ�.
//
//���� ��� ���� A = {1, 2, 3}, ���� B = {2, 3, 4}��� �� ��, ������ A �� B = {2, 3}, ������ A �� B = {1, 2, 3, 4}�� �ǹǷ�, ���� A, B ������ ��ī�� ���絵 J(A, B) = 2/4 = 0.5�� �ȴ�. ���� A�� ���� B�� ��� �������� ��쿡�� �������� ���ǵ��� ������ ���� J(A, B) = 1�� �����Ѵ�.
//
//��ī�� ���絵�� ������ �ߺ��� ����ϴ� �������տ� ���ؼ� Ȯ���� �� �ִ�. �������� A�� ���� 1�� 3�� ������ �ְ�, �������� B�� ���� 1�� 5�� ������ �ִٰ� ����. �� ���������� ������ A �� B�� ���� 1�� min(3, 5)�� 3��, ������ A �� B�� ���� 1�� max(3, 5)�� 5�� ������ �ȴ�. �������� A = {1, 1, 2, 2, 3}, �������� B = {1, 2, 2, 4, 5}��� �ϸ�, ������ A �� B = {1, 2, 2}, ������ A �� B = {1, 1, 2, 2, 3, 4, 5}�� �ǹǷ�, ��ī�� ���絵 J(A, B) = 3/7, �� 0.42�� �ȴ�.
//
//�̸� �̿��Ͽ� ���ڿ� ������ ���絵�� ����ϴµ� �̿��� �� �ִ�. ���ڿ� FRANCE�� FRENCH�� �־����� ��, �̸� �� ���ھ� ��� ���������� ���� �� �ִ�. ���� {FR, RA, AN, NC, CE}, {FR, RE, EN, NC, CH}�� �Ǹ�, �������� {FR, NC}, �������� {FR, RA, AN, NC, CE, RE, EN, CH}�� �ǹǷ�, �� ���ڿ� ������ ��ī�� ���絵 J("FRANCE", "FRENCH") = 2/8 = 0.25�� �ȴ�.
//
//�Է� ����
//�Է����δ� str1�� str2�� �� ���ڿ��� ���´�. �� ���ڿ��� ���̴� 2 �̻�, 1,000 �����̴�.
//�Է����� ���� ���ڿ��� �� ���ھ� ��� ���������� ���ҷ� �����. �̶� �����ڷ� �� ���� �ָ� ��ȿ�ϰ�, ��Ÿ �����̳� ����, Ư�� ���ڰ� ����ִ� ���� �� ���� ���� ������. ���� ��� ab+�� �Է����� ������, ab�� ���������� ���ҷ� ���, b+�� ������.
//�������� ���� ���̸� ���� ��, �빮�ڿ� �ҹ����� ���̴� �����Ѵ�. AB�� Ab, ab�� ���� ���ҷ� ����Ѵ�.
//��� ����
//�Է����� ���� �� ���ڿ��� ��ī�� ���絵�� ����Ѵ�. ���絵 ���� 0���� 1 ������ �Ǽ��̹Ƿ�, �̸� �ٷ�� ������ 65536�� ���� �Ŀ� �Ҽ��� �Ʒ��� ������ �����θ� ����Ѵ�.
//
//���� �����
//str1	str2	answer
//FRANCE	french	16384
//handshake	shake hands	65536
//aa1+aa2	AAAA12	43690
//E=M*C^2	e=m*c^2	65536
public class NewsClustering {
	public static void main(String[] args) {
		new NewsClustering().solve();
	}
	public void solve() {
		String str1 = "FRA  NCE  ";	
		String str2 = "french";
		
		int result = solution(str1, str2);
		System.out.println(result);
	}
	public int solution(String str1, String str2) {
		ArrayList<String> list1 = getmultipleSet(str1);
		ArrayList<String> list2 = getmultipleSet(str2);
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		return getJaccardSimilarity(list1, list2);
	}
	public int getJaccardSimilarity(ArrayList<String> list1, ArrayList<String> list2) {
		int result = 0;
//		ArrayList<String> intersection = new ArrayList<>();
//		ArrayList<String> union = new ArrayList<>();
		
		int firstIdx = 0;
		int secondIdx = 0;
		
		while(firstIdx < list1.size() && secondIdx < list2.size()) {
			String str1 = list1.get(firstIdx);
			String str2 = list2.get(secondIdx);
			
			if(str1.equals(str2)) {
				
			}else if(str1.compareTo(str2) > 1) {
				
			}else {
				
			}
		}
		
		return result;
	}
	public ArrayList<String> getmultipleSet(String str){//�̰ŵ� �߸�®��
		ArrayList<String> list = new ArrayList<>();
		int len = str.length();
		
		String tmp = ""+str.charAt(0);
		
		for(int i = 1; i < len; i++) {
			char cur = str.charAt(i);
			if((cur >= 'a' && cur <= 'z') || (cur >= 'A' && cur <= 'Z')) {
				tmp += cur;
				
				list.add(tmp.toLowerCase());
				tmp = tmp.substring(1);
			}
		}
		
		return list;
	}
}
