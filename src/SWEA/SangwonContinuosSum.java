package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SangwonContinuosSum {
	//SWExperts 7510
	//ó������ �� ���ϴٰ�
	//�� ū ���� ������
	//���ų� �۾��������� �տ������� ���ϴ�
	//�۾����� �ٽ� �ڷ� �ø���
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for(int t = 1; t <= test; t++) {
			int N = Integer.parseInt(br.readLine());
			int sum = 0;
			int count = 0;
			int start = 1;
			for(int i = 1; i <= N; i++) {
				sum += i;
				if(sum == N) {
					count++;
					i = ++start;
					sum=i;
					continue;
				}
				else if(sum > N){
					while(sum > N) {
						sum -= start;
						start++;
					}
					if(sum == N){
						sum -= i;
						i--;
					}
				}
			}
			System.out.println("#"+t+" "+count);
		}
	}
}
