package Programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
	//���� ����ġ�� �������
	//�� �������� ��ü�� ���� �ִܰŸ�
	//80% Ȯ���Ѵ� �׽�Ʈ ���غ����� �ͽ����� ����
	public class Vertex implements Comparable<Vertex>{
		int idx, weight;
		public Vertex(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex arg0) {
			return this.weight - arg0.weight;
		}
	}
	public void start() {
		int numOfVertex=0;
		int [][]graph = new int[numOfVertex][numOfVertex];
		dijkstra(graph, numOfVertex);
	}
	public void dijkstra(int[][] graph, int numberOfVertex) {
		ArrayList<Integer> fixed = new ArrayList<>(); //������ ���� ����Ʈ
		int[] dist = new int[numberOfVertex];
		for(int i = 0; i < numberOfVertex; i++)
			dist[i] = Integer.MAX_VALUE;//��� ������ �� ���Ѵ�
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		//weight�� ���� �켱����
		int startIdx = 0;
		dist[startIdx] = 0;
		queue.add(new Vertex(startIdx, dist[startIdx]));
		while(!queue.isEmpty() || fixed.size() < numberOfVertex) {
			Vertex cur = queue.poll();
			fixed.add(cur.idx);
			for(int i = 0; i < numberOfVertex; i++) {
				int val = graph[cur.idx][i];
				if(val > 0) {
					if(dist[i] > val) {
						dist[i] = val;
						queue.add(new Vertex(i, dist[i]));
					}
				}
			}
		}
	}
	public int getNext(int[][] graph, int start, int cur) {
		return 0;
	}
}
