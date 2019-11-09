package Programmers;

//�� ������ ��ϵ� �ֽİ����� ��� �迭 prices�� �Ű������� �־��� ��, ������ �������� ���� �Ⱓ�� �� �������� return �ϵ��� solution �Լ��� �ϼ��ϼ���.

//����, ť �������� �ٸ� ������� �õ��غ��� �;���.
public class StockPrice {
	public static void main(String[] args) {
		new StockPrice().solve();
	}
	public void solve() {
		int []prices = {1, 2, 3, 2, 3};
		int []result = solution(prices);
		
		System.out.println(result);
	}
	public int[] solution(int[] prices) {
		int len = prices.length;
        int[] answer = new int[len];
        int[] diff = new int[len];
        
        for(int i = 1; i < len; i++) {
        	diff[i] = prices[i] - prices[i-1];
        }
        
        for(int i = 0; i < len; i++) {
        	int sum = 0;
        	for(int j = i+1; j < len; j++) {
        		sum += diff[j];
        		answer[i]++;
        		if(sum < 0)
        			break;
        	}
        }
        return answer;
    }
}
