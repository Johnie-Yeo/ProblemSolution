package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//MST�� ����ġ�� ���� �ּ�ȭ�Ǵ� ����Ŭ�� ���� �׷���

public class NetworkConnect {

	public class Node implements Comparable<Node> {
		int depart;
		int destin;
		int weight;
		
		public Node(int depart, int destin, int weight) {
			this.depart = depart;
			this.destin = destin;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o1) {
			return this.weight - o1.weight;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NetworkConnect().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
			
		int N = kb.nextInt();
		int M = kb.nextInt();
		ArrayList<Node> graph = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			int depart = kb.nextInt() - 1;
			int destin = kb.nextInt() - 1;
			int weight = kb.nextInt();
			
			graph.add(new Node(depart, destin, weight));
		}
		
		kb.close();
		
		int result = getMinimumWeight(N, graph);
		System.out.println(result);
	}
	
	public int getMinimumWeight(int N, ArrayList<Node> graph) {
		int result = 0;
		
		int []parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;//������ ������ �׷����� �̷�� �����Ƿ� ��� ���� �θ�� �ڱ� �ڽ�
		}
		Collections.sort(graph);//����ġ�� ������ ���� ��������
		for(Node cur : graph) {
			int parentU = getParent(cur.depart, parents);
			int parentV = getParent(cur.destin, parents);
			if(parentU != parentV) { //�������� �������� �θ� �ٸ��ٸ� == �� �׷����� ������ �׷������
				//��ġ�� == �ϳ��� ���� �ٸ� �� ���� �θ� �Ǹ� �ȴ�. �׷����� ���̴� getParent���� ������ �ȴ�.
				union(parentU, parentV, parents);
				result += cur.weight;
			}
		}
		
		return result;
	}
	public int getParent(int cur, int []parents) {
		while(parents[cur] != cur) {
			parents[cur] = parents[parents[cur]];
			cur = parents[cur];
		}
		return parents[cur];
	}
	public void union(int dep, int des, int[] parents) {
		parents[des] = dep;
	}
}
